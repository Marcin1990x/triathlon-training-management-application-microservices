package pl.koneckimarcin.functionsservice.athlete.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;

import java.util.ArrayList;
import java.util.List;

public class Athlete {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Long> trainingRealizationsIds;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Long> trainingPlansIds = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> trainings;

    private Long stravaId;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Athlete(String firstName, String lastName, Long stravaId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stravaId = stravaId;
    }

    public AthleteEntity mapToAthleteEntity() {

        AthleteEntity athleteEntity = new AthleteEntity();

        athleteEntity.setId(this.id);
        athleteEntity.setFirstName(this.firstName);
        athleteEntity.setLastName(this.lastName);
        athleteEntity.setStravaId(this.stravaId);

        return athleteEntity;
    }

    public static Athlete fromAthleteEntity(AthleteEntity athleteEntity) {

        Athlete athlete = new Athlete();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());
        if (athleteEntity.getTrainingRealizationsIds() != null) {
            athlete.setTrainingRealizationsIds(athleteEntity.getTrainingRealizationsIds());
        }
//        if (athleteEntity.getTrainingPlansIds() != null) {
//            athlete.setTrainings(setTrainingPlansInformation(athleteEntity));
//        }
        if (athleteEntity.getTrainingPlansIds() != null) {
            athlete.setTrainingPlansIds(athleteEntity.getTrainingPlansIds());
        }
        return athlete;
    }

    // do I need this? It is used?
//    private static List<String> setTrainingPlansInformation(AthleteEntity athlete) {
//
//        List<String> trainingPlansInformation = new ArrayList<>();
//
//        List<TrainingPlanEntity> trainingPlans = athlete.getTrainingPlans();
//        for (TrainingPlanEntity plan : trainingPlans) {
//            trainingPlansInformation.add(plan.getPlannedDate() + ": " + plan.getTrainingType() + " - " + plan.getName());
//        }
//        return trainingPlansInformation;
//    }

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

    public List<String> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<String> trainings) {
        this.trainings = trainings;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }
}
