package pl.koneckimarcin.stravaservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.koneckimarcin.stravaservice.dto.AccessTokenDto;

public interface StravaOperations {

    @GetMapping("/refreshAccessToken")
    public AccessTokenDto refreshAccessToken(@RequestParam String refreshToken);
}
