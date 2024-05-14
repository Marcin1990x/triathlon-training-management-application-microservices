package pl.koneckimarcin.trainingsservice.trainingRealization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.trainingsservice.clients.StravaClient;
import pl.koneckimarcin.trainingsservice.exception.ResourceNotFoundException;
import pl.koneckimarcin.trainingsservice.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealizationRequest;
import pl.koneckimarcin.trainingsservice.trainingRealization.external.StravaActivityDto;
import pl.koneckimarcin.trainingsservice.trainingRealization.repository.TrainingRealizationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingRealizationService {


    @Autowired
    private TrainingRealizationRepository trainingRealizationRepository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private StravaClient stravaClient;

    public boolean checkIfIsNotNull(String id) {
        Optional<TrainingRealizationEntity> trainingRealizationStravaEntity = trainingRealizationRepository.findById(id);
        if (trainingRealizationStravaEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public void deleteById(String id) {

        if (checkIfIsNotNull(id)) {
            trainingRealizationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TrainingRealization", "id", String.valueOf(id));
        }
    }

    public void synchronizeActivitiesWithStravaForAthleteByUserId(Long userId, Long athleteId) {

        StravaActivityDto[] activitiesFromStrava = getAllActivitiesFromStrava(userId);

        List<Long> athleteRealizationsIds = getAthleteRealizationIds(athleteId);

        List<StravaActivityDto> newActivities =
                getNonDuplicatedActivities(athleteRealizationsIds, activitiesFromStrava);

        mapAndSaveTrainingRealizationsForAthlete(newActivities, athleteId);
        System.out.println(newActivities);
    }

    private StravaActivityDto[] getAllActivitiesFromStrava(Long userId) {
        return stravaClient.getActivities(userId);
    }

    private List<Long> getAthleteRealizationIds(Long athleteId) {

        return trainingRealizationRepository.findByAthleteId(athleteId)
                .stream().map(TrainingRealizationEntity::getStravaId).toList();
    }

    private List<StravaActivityDto> getNonDuplicatedActivities(List<Long> existingIds,
                                                               StravaActivityDto[] activities) {
        List<StravaActivityDto> nonDuplicatedActivities = new ArrayList<>();

        for (StravaActivityDto activity : activities) {
            if (!existingIds.contains(activity.getId())) {
                nonDuplicatedActivities.add(activity);
            }
        }
        return nonDuplicatedActivities;
    }

    private void mapAndSaveTrainingRealizationsForAthlete(List<StravaActivityDto> activities, Long athleteId) {

        List<TrainingRealizationEntity> toSaveInDb = new ArrayList<>();

        for (StravaActivityDto activity : activities) {
            TrainingRealizationEntity realization = activity.mapToTrainingRealizationEntity();
            realization.setAthleteId(athleteId);
            toSaveInDb.add(realization);
        }
        trainingRealizationRepository.saveAll(toSaveInDb);
    }

    public TrainingRealization updateTrainingRealizationById(String id, TrainingRealizationRequest request) {

        if (!checkIfIsNotNull(id)) {
            throw new ResourceNotFoundException("TrainingRealization", "id", String.valueOf(id));
        }
        return updateTrainingRealization(id, request);
    }

    private TrainingRealization updateTrainingRealization(String id, TrainingRealizationRequest request) {

        TrainingRealizationEntity trainingRealizationToUpdate = trainingRealizationRepository.findById(id).get();
        trainingRealizationToUpdate.setRealizationDescription(request.getRealizationDescription());
        trainingRealizationToUpdate.setRpeLevel(request.getRpeLevel());
        trainingRealizationToUpdate.setFeelings(request.getFeelings());

        TrainingRealizationEntity updated = trainingRealizationRepository.save(trainingRealizationToUpdate);

        return TrainingRealization.fromTrainingRealizationEntity(updated);
    }

    public TrainingRealization addNew(TrainingRealization trainingRealization) {

        //check valid athleteId

        TrainingRealizationEntity added = trainingRealizationRepository.save(trainingRealization.mapToTrainingRealizationEntity());
        return TrainingRealization.fromTrainingRealizationEntity(added);
    }

    public List<TrainingRealization> getAllTrainingRealizations(Long athleteId) {

        //check if athlete exists
        List<TrainingRealization> trainingRealizations = new ArrayList<>();
        List<TrainingRealizationEntity> trainingRealizationEntities
                = trainingRealizationRepository.findByAthleteId(athleteId);

        for (TrainingRealizationEntity realizationEntity : trainingRealizationEntities) {
            trainingRealizations.add(TrainingRealization.fromTrainingRealizationEntity(realizationEntity));
        }
        return trainingRealizations;
    }

    public TrainingRealization getById(String id) {

        Optional<TrainingRealizationEntity> id1 = trainingRealizationRepository.findById(id);

        return TrainingRealization
                .fromTrainingRealizationEntity(trainingRealizationRepository.findById(id).get());
    }
}
