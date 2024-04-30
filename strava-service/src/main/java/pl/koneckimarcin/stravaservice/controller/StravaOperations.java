package pl.koneckimarcin.stravaservice.controller;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.stravaservice.StravaDataEntity;
import pl.koneckimarcin.stravaservice.dto.AccessTokenDto;

@RequestMapping("/strava")
public interface StravaOperations {

    @PostMapping()
    public StravaDataEntity addRefreshTokenForUser(@RequestParam Long userId, @RequestParam String refreshToken);

    @GetMapping("/{userId}")
    public StravaDataEntity getStravaUserDataById(@PathVariable Long userId);

    @GetMapping("/refreshAccessToken")
    public AccessTokenDto refreshAccessToken(@RequestParam String refreshToken);
}
