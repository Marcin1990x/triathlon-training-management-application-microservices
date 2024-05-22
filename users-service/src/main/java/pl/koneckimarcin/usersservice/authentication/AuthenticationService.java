package pl.koneckimarcin.usersservice.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.usersservice.user.UserEntity;
import pl.koneckimarcin.usersservice.user.repository.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponseDto authenticateUserAndGetToken(Authentication authentication) {

        if (authentication.isAuthenticated()) {
            return createResponse(authentication.getName());
        } else {
            return null;
        }
    }

    private AuthenticationResponseDto createResponse(String username) { // todo: simplify this!

        UserEntity authenticatedUser = userRepository.findByUsername(username).get();

        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();

        authenticationResponseDto.setUserId(authenticatedUser.getId());
        if (authenticatedUser.getAthleteEntityId() != null) {
            authenticationResponseDto.setAthleteId(authenticatedUser.getAthleteEntityId());
            authenticationResponseDto.setAthlete(true);
        } else {
            authenticationResponseDto.setAthlete(false);
        }
        if (authenticatedUser.getCoachEntityId() != null) {
            authenticationResponseDto.setCoachId(authenticatedUser.getCoachEntityId());
            authenticationResponseDto.setCoach(true);
        } else {
            authenticationResponseDto.setCoach(false);
        }
//        if(authenticatedUser.getStravaRefreshToken() != null) {
//            authenticationResponseDto.setHasRefreshToken(true);
//            authenticationResponseDto.setStravaAccessExpiresAt(authenticatedUser.getStravaAccessTokenExpirationTime());
//        } else {
//            authenticationResponseDto.setHasRefreshToken(false);
//        }
        return authenticationResponseDto;
    }

}
