package pl.koneckimarcin.usersservice.user.external;

public class StravaAccessTokenDto {

    private String accessToken;
    private String expiresAt;

    public StravaAccessTokenDto(String accessToken, String expiresAt) {
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }
}
