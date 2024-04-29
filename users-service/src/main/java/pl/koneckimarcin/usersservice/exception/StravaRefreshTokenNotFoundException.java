package pl.koneckimarcin.usersservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StravaRefreshTokenNotFoundException extends RuntimeException{

    private Long userId;

    public StravaRefreshTokenNotFoundException(Long userId) {
        super(String.format("Strava refresh token for user with id: '%d' does not exists", userId));
    }
}
