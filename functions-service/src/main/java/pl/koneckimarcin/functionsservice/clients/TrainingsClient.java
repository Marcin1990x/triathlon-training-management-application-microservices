package pl.koneckimarcin.functionsservice.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.koneckimarcin.functionsservice.external.TrainingPlan;
import pl.koneckimarcin.functionsservice.external.TrainingRealization;

import java.util.List;

@FeignClient(name = "TRAININGS-SERVICE")
public interface TrainingsClient {

    @GetMapping("/training-realizations")
    List<TrainingRealization> getTrainingRealizations(@RequestParam Long athleteId);

    @GetMapping("training-plans/athlete")
    List<TrainingPlan> getTrainingPlansByAthleteId(@RequestParam Long athleteId);

    @GetMapping("training-plans/coach")
    List<TrainingPlan> getTrainingPlansByCoachId(@RequestParam Long coachId);
}