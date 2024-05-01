package pl.koneckimarcin.stravaservice.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.stravaservice.StravaDataEntity;

@RequestMapping("/strava")
public interface StravaOperations {

    @PostMapping() // temporary method for tests
    public StravaDataEntity addRefreshTokenForUser(@RequestParam Long userId, @RequestParam String refreshToken);

    @GetMapping("/{userId}")
    public StravaDataEntity getStravaUserDataById(@PathVariable Long userId);

    @PutMapping("/{userId}/refreshAccessToken")
    public void refreshAccessToken(@PathVariable Long userId);
}
