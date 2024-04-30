package pl.koneckimarcin.stravaservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StravaDataEntity {

    @Id
    private String id;

    private Long userId;

    private String refreshToken;

    private String accessToken;

    private String expirationTime;

    public StravaDataEntity() {
    }

    public StravaDataEntity(String id, Long userId, String refreshToken, String accessToken, String expirationTime) {
        this.id = id;
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.expirationTime = expirationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
}
