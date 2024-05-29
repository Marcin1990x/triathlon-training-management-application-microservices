package pl.koneckimarcin.usersservice.user.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pl.koneckimarcin.usersservice.user.external.StravaUserData;

@FeignClient(name = "STRAVA-SERVICE",
    url = "${strava-service.url}")
public interface StravaClient {

    @PutMapping("/strava/{userId}/refreshAccessToken")
    void refreshAccessToken(@PathVariable Long userId);

    @GetMapping("/strava/{id}")
    StravaUserData getStravaUserDataById(@PathVariable Long id);
}
