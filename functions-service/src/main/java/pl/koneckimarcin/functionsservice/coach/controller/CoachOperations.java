package pl.koneckimarcin.functionsservice.coach.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.functionsservice.coach.dto.Coach;
import pl.koneckimarcin.functionsservice.coach.dto.CoachResponseDto;
import pl.koneckimarcin.functionsservice.dto.AddAthleteResponseMessage;

public interface CoachOperations {

    @GetMapping("coaches/{id}")
//    @PreAuthorize("(hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)) OR" +
//            "(hasAuthority('ATHLETE') AND @authenticatedUserService.hasAthleteValidCoachId(#id))")
    public CoachResponseDto getById(@PathVariable Long id);

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PostMapping("/coaches")
    public Coach addNew(@RequestBody Coach coach);

    //@PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/coaches/{id}")
    public void deleteById(@PathVariable Long id);

    @GetMapping("/coaches/{id}/sendCoachingRequest")
    public String addAthleteToCoachRequest(@PathVariable Long id, @RequestParam Long athleteId);

    @GetMapping("/coaches/{coachId}/getCoachingReply")
    public AddAthleteResponseMessage getCoachingReply(@PathVariable Long coachId);

    //@PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidId(#coachId)")
    @PutMapping("/coaches/{coachId}/athletes/{athleteId}/remove")
    public Coach removeAthleteFromCoach(@PathVariable Long coachId, @PathVariable Long athleteId);

    @PutMapping("/coaches/{coachId}/setAssignedToUser")
    public void setAssignedToUser(@PathVariable Long coachId);
}
