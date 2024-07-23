package pl.koneckimarcin.usersservice.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.usersservice.exception.IsAlreadyAssignedException;
import pl.koneckimarcin.usersservice.exception.ResourceNotFoundException;
import pl.koneckimarcin.usersservice.user.UserEntity;
import pl.koneckimarcin.usersservice.user.clients.FunctionsClient;
import pl.koneckimarcin.usersservice.user.clients.StravaClient;
import pl.koneckimarcin.usersservice.user.dto.User;
import pl.koneckimarcin.usersservice.user.external.Athlete;
import pl.koneckimarcin.usersservice.user.external.Coach;
import pl.koneckimarcin.usersservice.user.external.StravaUserData;
import pl.koneckimarcin.usersservice.user.messaging.KafkaMessage;
import pl.koneckimarcin.usersservice.user.repository.UserRepository;
import pl.koneckimarcin.usersservice.user.role.Role;
import pl.koneckimarcin.usersservice.user.role.RoleEntity;
import pl.koneckimarcin.usersservice.user.role.RoleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${topics.chat.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FunctionsClient functionsClient;

    @Autowired
    private StravaClient stravaClient;

    public boolean checkIfIsNotNull(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public User getUserById(Long id) {

        if (checkIfIsNotNull(id)) {
            return User.fromUserEntity(userRepository.findById(id).get());
        } else {
            throw new ResourceNotFoundException("User", "id", String.valueOf(id));
        }
    }

    public List<User> getAllUsers() {

        List<UserEntity> allUsers = userRepository.findAll();

        if (allUsers.size() > 0) {
            return allUsers.stream().map(User::fromUserEntity).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("User", "id", "User list empty.");
        }
    }

    public User addCoachToUser(Long userId, Long coachId) {

        UserEntity userToUpdate = userRepository.findById(userId).get();
        Coach coach = getCoachById(coachId); // todo: replace with check if is not null
        // and if is already assigned

        addCoachToUserCheckForExceptions(userToUpdate, coach, userId, coachId);

        setCoachAssignedToUser(coachId);
        userToUpdate.setCoachEntityId(coachId);

        UserEntity userWithUpdatedRoles = updateRoles(userToUpdate, Role.COACH);

        return User.fromUserEntity(userRepository.save(userWithUpdatedRoles));
    }

    private void addCoachToUserCheckForExceptions(UserEntity user, Coach coach, Long userId, Long coachId) {

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        if (user.hasAssignedCoach()) {
            throw new IsAlreadyAssignedException("User", String.valueOf(userId));
        }
        if (coach.isAssignedToUser()) {
            throw new IsAlreadyAssignedException("Coach", String.valueOf(coachId));
        }
    }

    private Coach getCoachById(Long coachId) {

        return functionsClient.getCoachById(coachId);
    }

    private void setCoachAssignedToUser(Long coachId) {

        functionsClient.setCoachAssignedToUser(coachId);
    }

    public User addAthleteToUser(Long userId, Long athleteId) {

        UserEntity userToUpdate = userRepository.findById(userId).get();
        Athlete athlete = getAthleteById(athleteId);

        addAthleteToUserCheckForExceptions(userToUpdate, athlete, userId, athleteId);

        setAthleteAssignedToUser(athleteId);
        userToUpdate.setAthleteEntityId(athleteId);

        UserEntity userWithUpdatedRoles = updateRoles(userToUpdate, Role.ATHLETE);

        return User.fromUserEntity(userRepository.save(userWithUpdatedRoles));
    }

    private Athlete getAthleteById(Long athleteId) {

        return functionsClient.getAthleteById(athleteId);
    }

    private void addAthleteToUserCheckForExceptions(UserEntity user, Athlete athlete, Long userId, Long athleteId) {

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        if (user.hasAssignedAthlete()) {
            throw new IsAlreadyAssignedException("User", String.valueOf(userId));
        }
        if (athlete.isAssignedToUser()) {
            throw new IsAlreadyAssignedException("Athlete", String.valueOf(athleteId));
        }
    }

    private void setAthleteAssignedToUser(Long athleteId) {

        functionsClient.setAthleteAssignedToUser(athleteId);
    }

    public StravaUserData refreshAccessTokenForUser(Long userId) {

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        refreshAccessToken(userId);

        return getStravaUserDataById(userId);
    }

    private void refreshAccessToken(Long userId) {

        stravaClient.refreshAccessToken(userId);
    }

    private StravaUserData getStravaUserDataById(Long userId) {

        return stravaClient.getStravaUserDataById(userId);
    }

    private UserEntity updateRoles(UserEntity user, Role role) {

        RoleEntity roleToAdd = roleRepository.findByRole(role);

        user.getRoles().removeIf(roleToDelete -> roleToDelete.getRole() == Role.NEW);
        user.getRoles().add(roleToAdd);

        return user;
    }

    public String checkHealth() {

        return "Healthy";
    }

    public String getEmailAddress(Long athleteId) {

        UserEntity user = userRepository.findByAthleteEntityId(athleteId).get();
        return user.getEmailAddress();
    }

    public void sendMessage(Long athleteId, Long coachId, String message) {;

        KafkaMessage kafkaMessage = new KafkaMessage(
                String.valueOf(athleteId),
                String.valueOf(coachId),
                message
        );
        kafkaMessage.setTimestamp(LocalDateTime.now().toString());

        kafkaTemplate.send(topicName, kafkaMessage);
        logger.info("Published message content: " + kafkaMessage);
        kafkaTemplate.flush();
    }
}


