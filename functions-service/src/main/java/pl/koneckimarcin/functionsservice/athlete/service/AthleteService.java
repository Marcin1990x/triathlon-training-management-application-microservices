package pl.koneckimarcin.functionsservice.athlete.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;
import pl.koneckimarcin.functionsservice.athlete.dto.Athlete;
import pl.koneckimarcin.functionsservice.athlete.dto.AthleteResponseDto;
import pl.koneckimarcin.functionsservice.athlete.external.TrainingRealization;
import pl.koneckimarcin.functionsservice.athlete.repository.AthleteRepository;
import pl.koneckimarcin.functionsservice.coach.repository.CoachRepository;
import pl.koneckimarcin.functionsservice.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private RestTemplate restTemplate;

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

        List<TrainingRealization> trainingRealizations = getTrainingRealizationForAthleteById(id);

        return AthleteResponseDto.fromAthleteEntity(athleteEntity.get(), trainingRealizations);
    }

    private List<TrainingRealization> getTrainingRealizationForAthleteById(Long athleteId) {

        ResponseEntity<List<TrainingRealization>> trainingRealizations = restTemplate.exchange(
                "http://TRAININGS-SERVICE:8084/training-realizations?athleteId=" + athleteId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingRealization>>() {
                });

        return trainingRealizations.getBody();
    }

    public Set<Athlete> getAthletesByCoachId(Long id) {

        if (coachRepository.findById(id).isPresent()) {
            return coachRepository.findById(id).get().getAthletes().stream().map(Athlete::fromAthleteEntity).collect(Collectors.toSet());
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
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
                athletes.add(AthleteResponseDto.fromAthleteEntity(athleteEntity, null));
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
}
