package pl.koneckimarcin.trainingsservice.trainingRealization.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealizationRequest;

import java.util.List;

@RequestMapping("/training-realizations")
public interface TrainingRealizationOperations {

    @GetMapping
    public List<TrainingRealization> getAllTrainingRealizations(Long athleteId);

//    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id)) " +
//            "OR (hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#id))")
//    @GetMapping("athletes/{id}/training-realizations")
//    public List<TrainingRealization> getTrainingRealizationsByAthleteId(@PathVariable Long id);

    //    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id);

//    @PreAuthorize("hasAuthority('ATHLETE')")
//    @PutMapping("athletes/{id}/training-realizations")
//    public Integer synchronizeActivitiesForAthlete(@PathVariable Long id);

    //    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @PutMapping("/{id}")
    public TrainingRealization updateTrainingRealizationById(@PathVariable Long id,
                                                             @RequestBody TrainingRealizationRequest request);

//    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id))")
//    @PostMapping("athletes/{id}/training-realizations")
//    public TrainingRealization addNewTrainingRealizationForAthlete
//            (@PathVariable Long id, @RequestBody TrainingRealization trainingRealization);

    @PostMapping // for tests
    public TrainingRealization addNew(@RequestBody TrainingRealization trainingRealization);
}
