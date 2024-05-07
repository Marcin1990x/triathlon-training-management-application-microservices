package pl.koneckimarcin.functionsservice.athlete.dto;

import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;
import pl.koneckimarcin.functionsservice.external.TrainingRealization;

import java.util.List;

public class AthleteResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long coachId;

    private List<TrainingRealization> trainingRealizations;

    public static AthleteResponseDto fromAthleteEntity(AthleteEntity athleteEntity,
                                                       List<TrainingRealization> trainingRealizations) {

        AthleteResponseDto athlete = new AthleteResponseDto();

        athlete.setId(athleteEntity.getId());
        athlete.setFirstName(athleteEntity.getFirstName());
        athlete.setLastName(athleteEntity.getLastName());
        athlete.setCoachId(athleteEntity.getCoachId());
        athlete.setTrainingRealizations(trainingRealizations);

        return athlete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public List<TrainingRealization> getTrainingRealizations() {
        return trainingRealizations;
    }

    public void setTrainingRealizations(List<TrainingRealization> trainingRealizations) {
        this.trainingRealizations = trainingRealizations;
    }
}
