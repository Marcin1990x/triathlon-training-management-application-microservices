package pl.koneckimarcin.stravaservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.koneckimarcin.stravaservice.dto.ActivityClientDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class StravaClient {


    private RestTemplate rest = new RestTemplate();
    private final String STRAVA_URL = "https://www.strava.com/api/v3/";
    private final String STRAVA_URL_REFRESH = "https://www.strava.com/oauth/token";

    public ActivityClientDto[] getAllActivities(String accessToken) {

        return callGetAllActivities(accessToken);
    }

    private ActivityClientDto[] callGetAllActivities(String accessToken) {

        //activities after
        ZonedDateTime after = ZonedDateTime.of(
                2024, 2, 1, 0, 0, 0, 0,
                ZoneId.of("Europe/Warsaw"));

        //String accessToken = StravaPropertyReader.getValue("strava_access_token");

        return rest.getForObject(STRAVA_URL +
                        "athlete/activities?access_token={accessToken}&after={after}&per_page=100",
                ActivityClientDto[].class, accessToken, after.toEpochSecond());
    }
}
