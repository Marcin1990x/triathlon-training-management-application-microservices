package pl.koneckimarcin.trainingsservice.trainingPlan.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.trainingsservice.trainingPlan.dto.TrainingPlan;

import java.sql.Date;
import java.util.List;


public interface TrainingPlanOperations {

    @GetMapping("/training-plans/coach")
    public List<TrainingPlan> findAllByCoachId(@RequestParam Long coachId);

    @GetMapping("/training-plans/athlete")
    public List<TrainingPlan> findAllByAthleteId(@RequestParam Long athleteId);

    //    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @GetMapping("/training-plans/{id}")
    public TrainingPlan getTrainingPlanById(@PathVariable Long id);

    //    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @DeleteMapping("/training-plans/{id}")
    public void deleteById(@PathVariable Long id);

    //    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)")
    @PostMapping("/training-plans")
    public TrainingPlan addNewTrainingPlan(@RequestBody TrainingPlan trainingPlan);

//    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#athleteId)" +
//            "AND @authenticatedUserService.hasTrainingPlanInItsResources(#trainingPlanId)")
    @PostMapping("/training-plans/{id}")
    public TrainingPlan scheduleTrainingPlanForAthlete(
            @PathVariable Long id, @RequestParam Long athleteId, @RequestParam Date plannedDate);
}
