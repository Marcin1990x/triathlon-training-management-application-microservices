package pl.koneckimarcin.usersservice.user.controller;


import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.usersservice.user.dto.User;
import pl.koneckimarcin.usersservice.user.external.StravaUserData;

import java.util.List;

public interface UserOperations {

    @GetMapping("/users/health")
    public String checkHealth();

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers();

    //@PreAuthorize("hasAuthority('ADMIN') OR @authenticatedUserService.hasValidId(#id)")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long userId);

    //    //@PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PutMapping("/users/{userId}/coaches/{coachId}/add")
    public User addCoachToUser(@PathVariable Long userId, @PathVariable Long coachId);

    //
//    //@PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PutMapping("/users/{userId}/athletes/{athleteId}/add")
    public User addAthleteToUser(@PathVariable Long userId, @PathVariable Long athleteId);

    //
//    //@PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidId(#id)")
    @PutMapping("/users/{id}/refreshAccessToken")
    public StravaUserData refreshAccessTokenForUser(@PathVariable Long id);

    @GetMapping("/users/getEmailAddressByAthleteId")
    String getEmailAddress(@RequestParam Long athleteId);

    @PostMapping("/users/sendMessage")
    public void sendMessage(@RequestParam Long athleteId, @RequestParam Long coachId, @RequestBody String message);
}

