package pl.koneckimarcin.usersservice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.usersservice.user.dto.User;
import pl.koneckimarcin.usersservice.user.external.StravaUserData;
import pl.koneckimarcin.usersservice.user.service.UserService;

import java.util.List;

@RestController
public class UserController implements UserOperations {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(Long id) {

        return userService.getUserById(id);
    }

    @Override
    public User addCoachToUser(Long userId, Long coachId) {

        return userService.addCoachToUser(userId, coachId);
    }

    @Override
    public User addAthleteToUser(Long userId, Long athleteId) {

        return userService.addAthleteToUser(userId, athleteId);
    }

    //
    @Override
    public StravaUserData refreshAccessTokenForUser(Long id) {

        return userService.refreshAccessTokenForUser(id);
    }
}
