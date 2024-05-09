package pl.koneckimarcin.trainingsservice.trainingRealization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void synchronizeActivitiesWithStravaForAthleteById(Long athleteId) {

        StravaActivityDto[] activitiesFromStrava = getAllActivitiesFromStrava(athleteId);

    }
    private StravaActivityDto[] getAllActivitiesFromStrava(Long athleteId) {

        //call strava-service
        return null;
    }


//    public Integer synchronizeActivitiesForAthlete(Long athleteId) {
//
//        String accessToken = getAccessTokenForUser();
//        List<Long> existingIds = retrieveTrainingRealizationsIdsForAthlete(athleteId);
//
//        ActivityClientDto[] activitiesFromStrava = stravaClient.getAllActivities(accessToken); // temp: activities from 01/02/2024
//
//        List<ActivityClientDto> nonDuplicatedActivities = checkForDuplicatedIds(existingIds, activitiesFromStrava);
//
//        mapAndSaveToDb(nonDuplicatedActivities, athleteId);
//
//        return nonDuplicatedActivities.size();
//    }

//    private String getAccessTokenForUser() {
//
//        return retrieveLoggedUser().getStravaAccessToken();
//    }

//    private void mapAndSaveToDb(List<ActivityClientDto> activities, Long athleteId) {
//
//        List<TrainingRealizationEntity> entitiesList = new ArrayList<>();
//
//        AthleteEntity athlete = athleteRepository.findById(athleteId).get();
//
//        if (activities.size() > 0) {
//            for (ActivityClientDto activity : activities) {
//                TrainingRealizationEntity realization = activity.mapToRealizationEntity();
//                entitiesList.add(realization);
//            }
//            athlete.getTrainingRealizations().addAll(entitiesList);
//            athleteRepository.save(athlete);
//        }
//    }

//    private List<ActivityClientDto> checkForDuplicatedIds(List<Long> existingIds, ActivityClientDto[] activities) {
//
//        List<ActivityClientDto> nonDuplicatedActivities = new ArrayList<>();
//
//        for (ActivityClientDto activity : activities) {
//            if (!existingIds.contains(activity.getId())) {
//                nonDuplicatedActivities.add(activity);
//            }
//        }
//        return nonDuplicatedActivities;
//    }

//    private List<Long> retrieveTrainingRealizationsIdsForAthlete(Long athleteId) {
//
//        return athleteRepository.findById(athleteId).get().getTrainingRealizations()
//                .stream().map(TrainingRealizationEntity::getStravaId).toList();
//    }

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

//    private UserEntity retrieveLoggedUser() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        return userRepository.findByUsername(username).get();
//    }
}
