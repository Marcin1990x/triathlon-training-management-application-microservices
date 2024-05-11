package pl.koneckimarcin.functionsservice.coach.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.functionsservice.coach.dto.Coach;
import pl.koneckimarcin.functionsservice.coach.dto.CoachResponseDto;

public interface CoachOperations {

    @GetMapping("coaches/{id}")
//    @PreAuthorize("(hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)) OR" +
//            "(hasAuthority('ATHLETE') AND @authenticatedUserService.hasAthleteValidCoachId(#id))")
    public CoachResponseDto getById(@PathVariable Long id);

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PostMapping("coaches")
    public Coach addNew(@RequestBody Coach coach);

    //@PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("coaches/{id}")
    public void deleteById(@PathVariable Long id);

    //@PreAuthorize("hasAuthority('COACH')")
//    @PutMapping("coaches/{coachId}/athletes/{athleteId}/add")
//    public Coach addAthleteToCoach(@PathVariable Long coachId, @PathVariable Long athleteId);

    @GetMapping("/coaches/{id}/sendCoachingRequest")
    public Coach addAthleteToCoachRequest(@PathVariable Long id, @RequestParam Long athleteId);

    @PutMapping("/coaches/{id}/getCoachingReply")
    public void getCoachingReply(@PathVariable Long id, @RequestParam Long athleteId);

    //@PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidId(#coachId)")
    @PutMapping("coaches/{coachId}/athletes/{athleteId}/remove")
    public Coach removeAthleteFromCoach(@PathVariable Long coachId, @PathVariable Long athleteId);

    @PutMapping("coaches/{coachId}/setAssignedToUser")
    public void setAssignedToUser(@PathVariable Long coachId);
}
