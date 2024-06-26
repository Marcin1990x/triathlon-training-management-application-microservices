package pl.koneckimarcin.functionsservice.coach.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;
import pl.koneckimarcin.functionsservice.athlete.repository.AthleteRepository;
import pl.koneckimarcin.functionsservice.athlete.service.AthleteService;
import pl.koneckimarcin.functionsservice.clients.TrainingsClient;
import pl.koneckimarcin.functionsservice.coach.CoachEntity;
import pl.koneckimarcin.functionsservice.coach.dto.Coach;
import pl.koneckimarcin.functionsservice.coach.dto.CoachResponseDto;
import pl.koneckimarcin.functionsservice.coach.repository.CoachRepository;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;
import pl.koneckimarcin.functionsservice.dto.AddAthleteResponseMessage;
import pl.koneckimarcin.functionsservice.exceptions.ResourceNotFoundException;
import pl.koneckimarcin.functionsservice.external.TrainingPlan;
import pl.koneckimarcin.functionsservice.messaging.AddAthleteRequestMessageConsumer;
import pl.koneckimarcin.functionsservice.messaging.AddAthleteRequestMessageProducer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private TrainingsClient trainingsClient;

    @Autowired
    private AddAthleteRequestMessageProducer producer;

    @Autowired
    private AddAthleteRequestMessageConsumer consumer;

    public boolean checkIfIsNotNull(Long id) {
        Optional<CoachEntity> coachEntity = coachRepository.findById(id);
        if (coachEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public CoachResponseDto findById(Long id) {

        Optional<CoachEntity> coachEntity = coachRepository.findById(id);

        if (!coachEntity.isPresent()) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
        List<TrainingPlan> trainingPlans = getTrainingPlansForCoachId(id);

        CoachResponseDto responseDto = CoachResponseDto.fromCoachEntity(coachEntity.get(), trainingPlans);

        return responseDto;
    }

    private List<TrainingPlan> getTrainingPlansForCoachId(Long coachId) {

        return trainingsClient.getTrainingPlansByCoachId(coachId);
    }

    public Coach addNew(@Valid Coach coach) {

        CoachEntity coachEntity = coach.mapToCoachEntity();
        CoachEntity savedCoachEntity = coachRepository.save(coachEntity);
        return Coach.fromCoachEntity(savedCoachEntity);
    }

    public void deleteById(Long id) {
        if (checkIfIsNotNull(id)) {
            coachRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
    }

    public String addAthleteToCoachRequest(Long coachId, Long athleteId) {

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }

        if(athleteRepository.findById(athleteId).get().getCoachId() == null) {

            CoachEntity coach = coachRepository.findById(coachId).get();

            producer.sendRequestMessage(
                    new AddAthleteRequestMessage(coach.getId(), coach.getFirstName(),
                            coach.getLastName()), athleteId);

            return "Request to add athlete has been successfully submitted.";
        }
        return "The selected athlete already has an assigned coach.";
    }

    public AddAthleteResponseMessage getCoachingReply(Long coachId) {

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        AddAthleteResponseMessage response = consumer.receiveReplyMessage(coachId);

        return response;
    }

    public Coach removeAthleteFromCoach(Long coachId, Long athleteId) {

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }
        CoachEntity coach = coachRepository.findById(coachId).get();
        Set<AthleteEntity> athletes = coach.getAthletes();

        athletes.removeIf(athlete -> athlete.getId() == athleteId);

        setCoachIdForAthlete(athleteRepository.findById(athleteId).get(), null);

        coachRepository.save(coach);

        //todo: remove also training-plans with coachId?

        return Coach.fromCoachEntity(coach);
    }

    private void setCoachIdForAthlete(AthleteEntity athlete, Long coachId) {

        athlete.setCoachId(coachId);
        athleteRepository.save(athlete);
    }

    public void setAssignedToUser(Long coachId) {

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        CoachEntity coach = coachRepository.findById(coachId).get();
        coach.setAssignedToUser(true);
        coachRepository.save(coach);
    }
}