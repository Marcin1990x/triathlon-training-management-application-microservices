package pl.koneckimarcin.usersservice.user.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pl.koneckimarcin.usersservice.user.external.Athlete;
import pl.koneckimarcin.usersservice.user.external.Coach;

@FeignClient(name = "FUNCTIONS-SERVICE")
public interface FunctionsClient {

    @GetMapping("/coaches/{id}")
    Coach getCoachById(@PathVariable Long id);

    @PutMapping("/coaches/{id}/setAssignedToUser")
    void setCoachAssignedToUser(@PathVariable Long id);

    @GetMapping("/athletes/{id}")
    Athlete getAthleteById(@PathVariable Long id);

    @PutMapping("/athletes/{id}/setAssignedToUser")
    void setAthleteAssignedToUser(@PathVariable Long id);
}
