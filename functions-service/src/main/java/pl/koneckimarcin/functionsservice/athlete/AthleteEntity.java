package pl.koneckimarcin.functionsservice.athlete;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "athlete")
public class AthleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private List<Long> trainingRealizationsIds;

    private List<Long> trainingPlansIds;

    @Column(name = "has_user")
    private boolean isAssignedToUser = false;

    private Long coachId;

    private Long stravaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Long> getTrainingRealizationsIds() {
        return trainingRealizationsIds;
    }

    public void setTrainingRealizationsIds(List<Long> trainingRealizationsIds) {
        this.trainingRealizationsIds = trainingRealizationsIds;
    }

    public List<Long> getTrainingPlansIds() {
        return trainingPlansIds;
    }

    public void setTrainingPlansIds(List<Long> trainingPlansIds) {
        this.trainingPlansIds = trainingPlansIds;
    }

    public boolean isAssignedToUser() {
        return isAssignedToUser;
    }

    public void setAssignedToUser(boolean assignedToUser) {
        isAssignedToUser = assignedToUser;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
}
