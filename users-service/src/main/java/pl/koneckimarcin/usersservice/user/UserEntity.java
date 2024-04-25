package pl.koneckimarcin.usersservice.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import pl.koneckimarcin.usersservice.user.role.RoleEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;

    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    private String emailAddress;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    private Long athleteEntityId;

    private Long coachEntityId;

    @Column(name = "refresh")
    private String stravaRefreshToken;

    @Column(name = "access")
    private String stravaAccessToken;

    @Column(name = "access_expiration")
    private String stravaAccessTokenExpirationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Long getAthleteEntityId() {
        return athleteEntityId;
    }

    public void setAthleteEntityId(Long athleteEntityId) {
        this.athleteEntityId = athleteEntityId;
    }

    public Long getCoachEntityId() {
        return coachEntityId;
    }

    public void setCoachEntityId(Long coachEntityId) {
        this.coachEntityId = coachEntityId;
    }

    public String getStravaRefreshToken() {
        return stravaRefreshToken;
    }

    public void setStravaRefreshToken(String stravaRefreshToken) {
        this.stravaRefreshToken = stravaRefreshToken;
    }

    public String getStravaAccessToken() {
        return stravaAccessToken;
    }

    public void setStravaAccessToken(String stravaAccessToken) {
        this.stravaAccessToken = stravaAccessToken;
    }

    public String getStravaAccessTokenExpirationTime() {
        return stravaAccessTokenExpirationTime;
    }

    public void setStravaAccessTokenExpirationTime(String stravaAccessTokenExpirationTime) {
        this.stravaAccessTokenExpirationTime = stravaAccessTokenExpirationTime;
    }

    public boolean hasAssignedAthlete() {
        return this.athleteEntityId != null;
    }

    public boolean hasAssignedCoach() {
        return this.coachEntityId != null;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", roles=" + roles +
                ", athleteEntityId=" + athleteEntityId +
                ", coachEntityId=" + coachEntityId +
                ", stravaRefreshToken='" + stravaRefreshToken + '\'' +
                ", stravaAccessToken='" + stravaAccessToken + '\'' +
                ", stravaAccessTokenExpirationTime='" + stravaAccessTokenExpirationTime + '\'' +
                '}';
    }
}
