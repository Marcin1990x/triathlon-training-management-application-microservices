package pl.koneckimarcin.functionsservice.coach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.functionsservice.coach.dto.Coach;
import pl.koneckimarcin.functionsservice.coach.dto.CoachResponseDto;
import pl.koneckimarcin.functionsservice.coach.service.CoachService;
import pl.koneckimarcin.functionsservice.dto.AddAthleteResponseMessage;

@RestController
public class CoachController implements CoachOperations {

    @Autowired
    private CoachService coachService;

    public CoachResponseDto getById(Long id) {
        return coachService.findById(id);
    }

    public Coach addNew(Coach coach) {

        return coachService.addNew(coach);
    }

    public void deleteById(Long id) {

        coachService.deleteById(id);
    }

    public String addAthleteToCoachRequest(Long id, Long athleteId) {

        return coachService.addAthleteToCoachRequest(id, athleteId);
    }

    @Override
    public AddAthleteResponseMessage getCoachingReplyAndAssignAthlete(Long id, Long athleteId) {

        return coachService.getCoachingReplyAndAssignAthlete(id, athleteId);
    }

    @Override
    public Coach removeAthleteFromCoach(Long coachId, Long athleteId) {

        return coachService.removeAthleteFromCoach(coachId, athleteId);
    }

    @Override
    public void setAssignedToUser(Long coachId) {

        coachService.setAssignedToUser(coachId);
    }
}
