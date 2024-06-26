package pl.koneckimarcin.functionsservice.athlete.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.functionsservice.athlete.dto.Athlete;
import pl.koneckimarcin.functionsservice.athlete.dto.AthleteResponseDto;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;

import java.util.List;
import java.util.Set;

public interface AthleteOperations {

    //@PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id)")
    @GetMapping("/athletes/{id}")
    public AthleteResponseDto getById(@PathVariable Long id);

    //@PreAuthorize("hasAuthority('COACH')")
    @GetMapping("/athletes")
    public List<AthleteResponseDto> getByLastname(@RequestParam String lastname);

    //@PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)")
    @GetMapping("/athletes/coach")
    public Set<AthleteResponseDto> getByCoachId(@RequestParam Long coachId);

    //@PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PostMapping("/athletes")
    public Athlete addNew(@RequestBody Athlete athlete);

    //@PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/athletes/{id}")
    public void deleteById(@PathVariable Long id);

    @PutMapping("/athletes/{athleteId}/setAssignedToUser")
    public void setAssignedToUser(@PathVariable Long athleteId);

    @GetMapping("/athletes/{id}/getCoachingRequest")
    public AddAthleteRequestMessage getCoachingRequest(@PathVariable Long id);

    @PutMapping("/athletes/{athleteId}/sendCoachingReply")
    public String sendCoachingReplyAndAssignToCoach(@PathVariable Long athleteId,
                                                    @RequestParam Long coachId,
                                                    @RequestParam boolean confirmation

    );

    @GetMapping("/athletes/{id}/checkRequests")
    public int checkPendingCoachingRequests(@PathVariable Long id);
}
