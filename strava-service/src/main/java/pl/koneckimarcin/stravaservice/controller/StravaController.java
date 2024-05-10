package pl.koneckimarcin.stravaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.stravaservice.StravaDataEntity;
import pl.koneckimarcin.stravaservice.dto.ActivityClientDto;
import pl.koneckimarcin.stravaservice.service.StravaService;

@RestController
public class StravaController implements StravaOperations {

    @Autowired
    private StravaService stravaService;

    @Override
    public ActivityClientDto[] getActivities(Long userId) {
        return stravaService.getActivities(userId);
    }

    @Override
    public StravaDataEntity addRefreshTokenForUser(Long userId, String refreshToken) {

        return stravaService.addRefreshTokenForUser(userId, refreshToken);
    }

    @Override
    public StravaDataEntity getStravaUserDataById(Long userId) {

        return stravaService.getStravaUserDataById(userId);
    }

    @Override
    public void refreshAccessToken(Long userId) {

        stravaService.refreshAccessToken(userId);
    }
}
