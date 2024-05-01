package pl.koneckimarcin.usersservice.user.external;

public class StravaUserData {

    private String refreshToken;

    private String accessToken;

    private String expirationTime;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "StravaUserData{" +
                "refreshToken='" + refreshToken + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", expirationTime='" + expirationTime + '\'' +
                '}';
    }
}
