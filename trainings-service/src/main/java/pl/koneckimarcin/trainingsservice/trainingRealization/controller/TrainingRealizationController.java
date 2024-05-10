package pl.koneckimarcin.trainingsservice.trainingRealization.controller;

import jakarta.validation.Valid;
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

    @Override
    public List<TrainingRealization> getAllTrainingRealizations(Long athleteId) {

        return trainingRealizationService.getAllTrainingRealizations(athleteId);
    }

    @Override
    public TrainingRealization getById(String id) {

        return trainingRealizationService.getById(id);
    }

    public void deleteById(String id) {

        trainingRealizationService.deleteById(id);
    }

    @Override
    public void synchronizeActivitiesWithStravaForAthleteByUserId(Long userId, Long athleteId) {

        trainingRealizationService.synchronizeActivitiesWithStravaForAthleteByUserId(userId, athleteId);
    }

    @Override
    public TrainingRealization updateTrainingRealizationById(String id, TrainingRealizationRequest request) {
        return trainingRealizationService.updateTrainingRealizationById(id, request);
    }

    @Override
    public TrainingRealization addNew(@Valid TrainingRealization trainingRealization) {

        return trainingRealizationService.addNew(trainingRealization);
    }
}
