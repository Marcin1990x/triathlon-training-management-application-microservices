package pl.koneckimarcin.functionsservice.athlete.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;
import pl.koneckimarcin.functionsservice.athlete.dto.Athlete;
import pl.koneckimarcin.functionsservice.athlete.dto.AthleteResponseDto;
import pl.koneckimarcin.functionsservice.athlete.repository.AthleteRepository;
import pl.koneckimarcin.functionsservice.clients.TrainingsClient;
import pl.koneckimarcin.functionsservice.coach.repository.CoachRepository;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;
import pl.koneckimarcin.functionsservice.dto.AddAthleteResponseMessage;
import pl.koneckimarcin.functionsservice.exceptions.ResourceNotFoundException;
import pl.koneckimarcin.functionsservice.external.TrainingPlan;
import pl.koneckimarcin.functionsservice.external.TrainingRealization;
import pl.koneckimarcin.functionsservice.messaging.AddAthleteRequestMessageConsumer;
import pl.koneckimarcin.functionsservice.messaging.AddAthleteRequestMessageProducer;
import pl.koneckimarcin.functionsservice.messaging.QueueService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TrainingsClient trainingsClient;

    @Autowired
    private AddAthleteRequestMessageConsumer consumer;

    @Autowired
    private AddAthleteRequestMessageProducer producer;

    @Autowired
    private QueueService queueService;

    public boolean checkIfIsNotNull(long id) {
        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);
        if (athleteEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public AthleteResponseDto getById(Long id) {

        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);

        if (!athleteEntity.isPresent()) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }

        List<TrainingRealization> trainingRealizations = getTrainingRealizationsForAthleteById(id);
        List<TrainingPlan> trainingPlans = getTrainingPlansForAthleteById(id);

        return AthleteResponseDto.fromAthleteEntity(athleteEntity.get(), trainingRealizations, trainingPlans);
    }

    private List<TrainingRealization> getTrainingRealizationsForAthleteById(Long athleteId) {

        return trainingsClient.getTrainingRealizations(athleteId);
    }

    private List<TrainingPlan> getTrainingPlansForAthleteById(Long athleteId) {

        return trainingsClient.getTrainingPlansByAthleteId(athleteId);
    }

    public Set<AthleteResponseDto> getByCoachId(Long id) {

        if (!coachRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }

        Set<AthleteEntity> athleteEntities =
                coachRepository.findById(id).get().getAthletes()
                        .stream().collect(Collectors.toSet());

        Set<AthleteResponseDto> athletes = new HashSet<>();

        for (AthleteEntity athleteEntity : athleteEntities) {

            List<TrainingPlan> trainingPlans = getTrainingPlansForAthleteById(athleteEntity.getId());
            List<TrainingRealization> trainingRealizations = getTrainingRealizationsForAthleteById(athleteEntity.getId());

            athletes.add(AthleteResponseDto
                    .fromAthleteEntity(athleteEntity, trainingRealizations, trainingPlans));
        }
        return athletes;
    }

    public Athlete addNew(@Valid Athlete athlete) {

        AthleteEntity athleteEntity = athlete.mapToAthleteEntity();
        AthleteEntity savedAthleteEntity = athleteRepository.save(athleteEntity);

        return Athlete.fromAthleteEntity(savedAthleteEntity);
    }

    public void deleteById(long id) {

        if (checkIfIsNotNull(id)) {
            athleteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }

    public List<AthleteResponseDto> getByLastname(String lastName) {

        List<AthleteEntity> athleteEntities =
                athleteRepository.findByLastNameContainingIgnoreCaseAndCoachIdIsNull(lastName);

        List<AthleteResponseDto> athletes = new ArrayList<>();

        if (athleteEntities.size() > 0) {
            for (AthleteEntity athleteEntity : athleteEntities) {
                athletes.add(AthleteResponseDto.fromAthleteEntity(athleteEntity, null, null));
            }
        }
        return athletes;
    }

    public void setAssignedToUser(Long athleteId) {

        if (!checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }
        AthleteEntity athlete = athleteRepository.findById(athleteId).get();
        athlete.setAssignedToUser(true);
        athleteRepository.save(athlete);
    }

    public AddAthleteRequestMessage getCoachingRequest(Long id) {

        AddAthleteRequestMessage request = consumer.receiveRequestMessage(id);

        return request;
    }

    public String sendCoachingReply(Long id, boolean confirmation) {

        String acceptResponse = "Request from the coach has been accepted.";
        String declineResponse = "Request from the coach has been declined.";

        if (!checkIfIsNotNull(id)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }

        AthleteEntity athlete = athleteRepository.findById(id).get();

        producer.sendReplyMessage(new AddAthleteResponseMessage(
                athlete.getId(), athlete.getFirstName(), athlete.getLastName(), confirmation
                )
        );

        if (confirmation) {
            return acceptResponse;
        } else {
            return declineResponse;
        }
    }

    public int checkPendingCoachingRequests(Long id) {

        if(!checkIfIsNotNull(id))
        {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
        return queueService.getRequestCount(id);
    }
}
