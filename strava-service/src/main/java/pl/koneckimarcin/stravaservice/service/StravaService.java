package pl.koneckimarcin.stravaservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.koneckimarcin.stravaservice.StravaDataEntity;
import pl.koneckimarcin.stravaservice.StravaPropertyReader;
import pl.koneckimarcin.stravaservice.dto.AccessTokenDto;
import pl.koneckimarcin.stravaservice.dto.RefreshTokenResponseDto;
import pl.koneckimarcin.stravaservice.exception.JsonMapperException;
import pl.koneckimarcin.stravaservice.exception.RefreshTokenException;
import pl.koneckimarcin.stravaservice.repository.StravaDataRepository;

import java.util.Optional;

@Service
public class StravaService {

    @Autowired
    private StravaDataRepository stravaDataRepository;

    private RestTemplate rest = new RestTemplate();
    private final String STRAVA_URL_REFRESH = "https://www.strava.com/oauth/token";

    public void refreshAccessToken(Long userId) {

        StravaDataEntity userStravaData = stravaDataRepository.findByUserId(userId).get();

        ResponseEntity<String> response = connectWithStrava(userStravaData);

        AccessTokenDto accessToken = retrieveTokenFromResponse(response.getBody());

        updateStravaDataForUser(userStravaData, accessToken);
    }

    private ResponseEntity<String> connectWithStrava(StravaDataEntity userStravaData) {

        HttpEntity<MultiValueMap<String, String>> requestBody =
                createRequestBody(userStravaData.getRefreshToken());

        ResponseEntity<String> response = rest.exchange(
                STRAVA_URL_REFRESH,
                HttpMethod.POST,
                requestBody,
                String.class
        );
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RefreshTokenException(response.getStatusCode(), "Test");
        }
        return response;
    }

    private HttpEntity<MultiValueMap<String, String>> createRequestBody(String refreshToken) {

        String clientId = StravaPropertyReader.getValue("strava_client_id");
        String clientSecret = StravaPropertyReader.getValue("strava_client_secret");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("refresh_token", refreshToken);
        parameters.add("grant_type", "refresh_token");

        return new HttpEntity<>(parameters, headers);
    }

    private AccessTokenDto retrieveTokenFromResponse(String responseBody) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            RefreshTokenResponseDto tokenResponse = mapper
                    .readValue(responseBody, RefreshTokenResponseDto.class);
            return new AccessTokenDto(tokenResponse.getAccess_token(), tokenResponse.getExpires_at());
        } catch (JsonProcessingException e) {
            throw new JsonMapperException();
        }
    }

    private void updateStravaDataForUser(StravaDataEntity userStravaData, AccessTokenDto accessToken) {

        userStravaData.setAccessToken(accessToken.getAccessToken());
        userStravaData.setExpirationTime(accessToken.getExpiresAt());
        stravaDataRepository.save(userStravaData);
    }

    public StravaDataEntity addRefreshTokenForUser(Long userId, String refreshToken) {

        Optional<StravaDataEntity> stravaUserData = stravaDataRepository.findByUserId(userId);

        if (stravaUserData.isPresent()) {
            stravaUserData.get().setRefreshToken(refreshToken);
            return stravaDataRepository.save(stravaUserData.get());
        } else {
            StravaDataEntity newStravaUserData = new StravaDataEntity();
            newStravaUserData.setUserId(userId);
            newStravaUserData.setRefreshToken(refreshToken);
            return stravaDataRepository.save(newStravaUserData);
        }
    }
    public StravaDataEntity getStravaUserDataById(Long userId) {

        return stravaDataRepository.findByUserId(userId).get();
    }
}