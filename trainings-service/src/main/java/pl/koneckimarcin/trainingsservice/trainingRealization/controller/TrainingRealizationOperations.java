package pl.koneckimarcin.trainingsservice.trainingRealization.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealizationRequest;

import java.util.List;

public interface TrainingRealizationOperations {

    @GetMapping("/training-realizations")
    public List<TrainingRealization> getAllTrainingRealizations(@RequestParam Long athleteId);

    @GetMapping("/training-realizations/{id}")
    public TrainingRealization getById(@PathVariable String id);

    //    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @DeleteMapping("/training-realizations/{id}")
    public void deleteById(@PathVariable String id);


    //    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @PutMapping("/training-realizations/{id}")
    public TrainingRealization updateTrainingRealizationById(@PathVariable String id,
                                                             @RequestBody TrainingRealizationRequest request);

    @PostMapping("/training-realizations")
    public TrainingRealization addNew(@RequestBody TrainingRealization trainingRealization);

    @PostMapping("/training-realizations/synchronizeStrava")
    public Integer synchronizeStravaActivitiesForAthleteByUserId(@RequestParam Long athleteId,
                                                                 @RequestParam Long userId);
}
