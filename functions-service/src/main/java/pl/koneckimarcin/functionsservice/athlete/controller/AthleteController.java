package pl.koneckimarcin.functionsservice.athlete.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.functionsservice.athlete.dto.Athlete;
import pl.koneckimarcin.functionsservice.athlete.dto.AthleteResponseDto;
import pl.koneckimarcin.functionsservice.athlete.service.AthleteService;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;

import java.util.List;
import java.util.Set;

@RestController
public class AthleteController implements AthleteOperations {

    @Autowired
    private AthleteService athleteService;

    public AthleteResponseDto getById(Long id) {

        return athleteService.getById(id);
    }

    @Override
    public List<AthleteResponseDto> getByLastname(String lastname) {

        return athleteService.getByLastname(lastname);
    }

    @Override
    public Set<Athlete> getByCoachId(Long coachId) {

        return athleteService.getByCoachId(coachId);
    }

    public Athlete addNew(@Valid Athlete athlete) {

        return athleteService.addNew(athlete);
    }

    public void deleteById(Long id) {

        athleteService.deleteById(id);
    }

    @Override
    public void setAssignedToUser(Long athleteId) {

        athleteService.setAssignedToUser(athleteId);
    }

    @Override
    public AddAthleteRequestMessage getCoachingRequest(Long id) {
        return athleteService.getCoachingRequest(id);
    }

    @Override
    public String sendCoachingReply(Long id, boolean confirmation) {

        return athleteService.sendCoachingReply(id, confirmation);
    }
}
