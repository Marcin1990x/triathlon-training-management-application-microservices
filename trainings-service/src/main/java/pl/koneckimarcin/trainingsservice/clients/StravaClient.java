package pl.koneckimarcin.trainingsservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.koneckimarcin.trainingsservice.trainingRealization.external.StravaActivityDto;

@FeignClient(name = "STRAVA-SERVICE")
public interface StravaClient {
    @GetMapping("/strava/getActivities")
    StravaActivityDto[] getActivities(@RequestParam Long userId);
}
