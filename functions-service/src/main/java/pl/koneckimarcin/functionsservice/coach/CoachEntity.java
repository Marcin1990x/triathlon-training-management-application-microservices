package pl.koneckimarcin.functionsservice.coach;

import jakarta.persistence.*;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;

import java.util.Set;

@Entity
@Table(name = "coach")
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<AthleteEntity> athletes;

    private Set<Long> trainingPlanEntitiesIds;

    @Column(name = "has_user")
    private boolean isAssignedToUser = false;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<AthleteEntity> getAthletes() {
        return athletes;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAthletes(Set<AthleteEntity> athletes) {
        this.athletes = athletes;
    }

    public Set<Long> getTrainingPlanEntitiesIds() {
        return trainingPlanEntitiesIds;
    }

    public void setTrainingPlanEntitiesIds(Set<Long> trainingPlanEntitiesIds) {
        this.trainingPlanEntitiesIds = trainingPlanEntitiesIds;
    }

    public Boolean isAssignedToUser() {
        return isAssignedToUser;
    }

    public void setAssignedToUser(boolean assignedToUser) {
        isAssignedToUser = assignedToUser;
    }
}
