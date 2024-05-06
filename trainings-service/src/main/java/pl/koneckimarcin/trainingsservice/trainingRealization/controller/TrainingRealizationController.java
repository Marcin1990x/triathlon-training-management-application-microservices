package pl.koneckimarcin.trainingsservice.trainingRealization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealizationRequest;
import pl.koneckimarcin.trainingsservice.trainingRealization.service.TrainingRealizationService;

import java.util.List;

@RestController
public class TrainingRealizationController implements TrainingRealizationOperations {

    @Autowired
    private TrainingRealizationService trainingRealizationService;

//    public List<TrainingRealization> getTrainingRealizationsByAthleteId(Long id) {
//
//        return trainingRealizationService.getTrainingRealizationsByAthleteId(id);
//    }

    @Override
    public List<TrainingRealization> getAllTrainingRealizations(Long athleteId) {

        return trainingRealizationService.getAllTrainingRealizations(athleteId);
    }

    public void deleteById(Long id) {

        trainingRealizationService.deleteById(id);
    }

//    @Override
//    public Integer synchronizeActivitiesForAthlete(Long id) {
//        return trainingRealizationService.synchronizeActivitiesForAthlete(id);
//    }

    @Override
    public TrainingRealization updateTrainingRealizationById(Long id, TrainingRealizationRequest request) {
        return trainingRealizationService.updateTrainingRealizationById(id, request);
    }

    @Override
    public TrainingRealization addNew(TrainingRealization trainingRealization) {

        return trainingRealizationService.addNew(trainingRealization);
    }

//    @Override
//    public TrainingRealization addNewTrainingRealizationForAthlete(Long id, TrainingRealization trainingRealization) {
//        return trainingRealizationService.addNewTrainingRealizationForAthlete(id, trainingRealization);
//    }
}
