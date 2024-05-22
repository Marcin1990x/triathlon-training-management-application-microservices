package pl.koneckimarcin.usersservice.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticateUserAndGetToken(Authentication authentication) {

        return authenticationService.authenticateUserAndGetToken(authentication);
    }
}
