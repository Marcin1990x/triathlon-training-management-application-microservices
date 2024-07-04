package pl.koneckimarcin.trainingsservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USERS-SERVICE",
        url = "${users-service.url}")
public interface UsersClient {
    @GetMapping("/users/getEmailAddressByAthleteId")
    String getEmailAddress(@RequestParam Long athleteId);
}
