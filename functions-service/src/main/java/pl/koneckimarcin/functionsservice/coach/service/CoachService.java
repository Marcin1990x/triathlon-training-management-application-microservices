package pl.koneckimarcin.functionsservice.coach.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;
import pl.koneckimarcin.functionsservice.athlete.repository.AthleteRepository;
import pl.koneckimarcin.functionsservice.athlete.service.AthleteService;
import pl.koneckimarcin.functionsservice.coach.CoachEntity;
import pl.koneckimarcin.functionsservice.coach.dto.Coach;
import pl.koneckimarcin.functionsservice.coach.dto.CoachResponseDto;
import pl.koneckimarcin.functionsservice.coach.repository.CoachRepository;
import pl.koneckimarcin.functionsservice.exceptions.ResourceNotFoundException;
import pl.koneckimarcin.functionsservice.external.TrainingPlan;

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
    private RestTemplate restTemplate;

    public boolean checkIfIsNotNull(Long id) {
        Optional<CoachEntity> coachEntity = coachRepository.findById(id);
        if (coachEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public Coach findById(Long id) {

        Optional<CoachEntity> coachEntity = coachRepository.findById(id);

        if (!coachEntity.isPresent()) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
        List<TrainingPlan> trainingPlans = getTrainingPlansForCoachId(id);
        return CoachResponseDto.fromCoachEntity(coachEntity, trainingPlans);
    }

    private List<TrainingPlan> getTrainingPlansForCoachId(Long coachId) {

        ResponseEntity<List<TrainingPlan>> trainingRealizations = restTemplate.exchange(
                "http://TRAININGS-SERVICE:8084/training-realizations?athleteId=" + coachId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingPlan>>() {
                });

        return trainingRealizations.getBody();
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

    public Coach addAthleteToCoach(Long coachId, Long athleteId) {

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }

        AthleteEntity athlete = athleteRepository.findById(athleteId).get();
        CoachEntity coachToUpdate = coachRepository.findById(coachId).get();

        setCoachIdForAthlete(athlete, coachId);

        coachToUpdate.getAthletes().add(athlete);

        return Coach.fromCoachEntity(coachRepository.save(coachToUpdate));
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
