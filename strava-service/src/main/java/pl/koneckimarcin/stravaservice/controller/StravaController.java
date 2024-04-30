package pl.koneckimarcin.stravaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.stravaservice.dto.AccessTokenDto;
import pl.koneckimarcin.stravaservice.service.StravaService;

@RestController
public class StravaController implements StravaOperations {

    @Autowired
    private StravaService stravaService;

    @Override
    public AccessTokenDto refreshAccessToken(String refreshToken) {

        return stravaService.refreshAccessToken(refreshToken);
    }
}
