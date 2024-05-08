package pl.koneckimarcin.trainingsservice.trainingPlan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.trainingsservice.trainingPlan.dto.TrainingPlan;
import pl.koneckimarcin.trainingsservice.trainingPlan.service.TrainingPlanService;

import java.sql.Date;
import java.util.List;

@RestController
public class TrainingPlanController implements TrainingPlanOperations {

    @Autowired
    private TrainingPlanService trainingPlanService;

    @Override
    public List<TrainingPlan> findAllByCoachId(Long coachId) {

        return trainingPlanService.findAllByCoachId(coachId);
    }

    @Override
    public List<TrainingPlan> findAllByAthleteId(Long athleteId) {

        return trainingPlanService.findAllByAthleteId(athleteId);
    }

    @Override
    public TrainingPlan getTrainingPlanById(Long id) {

        return trainingPlanService.getTrainingPlanById(id);
    }

    public void deleteById(Long id) {

        trainingPlanService.deleteById(id);
    }

    public TrainingPlan addNewTrainingPlan(@Valid TrainingPlan trainingPlan) {

        return trainingPlanService.addNewTrainingPlan(trainingPlan);
    }

    @Override
    public TrainingPlan scheduleTrainingPlanForAthlete(Long id, Long athleteId, Date plannedDate) {

        return trainingPlanService.scheduleTrainingPlanForAthlete(id, athleteId, plannedDate);
    }
}