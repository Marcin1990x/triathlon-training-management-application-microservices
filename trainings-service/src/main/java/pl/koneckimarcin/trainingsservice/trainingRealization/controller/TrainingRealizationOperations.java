package pl.koneckimarcin.trainingsservice.trainingRealization.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.trainingsservice.trainingRealization.dto.TrainingRealizationRequest;

public interface TrainingRealizationOperations {

//    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id)) " +
//            "OR (hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#id))")
//    @GetMapping("athletes/{id}/training-realizations")
//    public List<TrainingRealization> getTrainingRealizationsByAthleteId(@PathVariable Long id);

    //    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @DeleteMapping("training-realizations/{id}")
    public void deleteById(@PathVariable Long id);

//    @PreAuthorize("hasAuthority('ATHLETE')")
//    @PutMapping("athletes/{id}/training-realizations")
//    public Integer synchronizeActivitiesForAthlete(@PathVariable Long id);

    //    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @PutMapping("training-realizations/{id}")
    public TrainingRealization updateTrainingRealizationById(@PathVariable Long id,
                                                             @RequestBody TrainingRealizationRequest request);

//    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id))")
//    @PostMapping("athletes/{id}/training-realizations")
//    public TrainingRealization addNewTrainingRealizationForAthlete
//            (@PathVariable Long id, @RequestBody TrainingRealization trainingRealization);
}
