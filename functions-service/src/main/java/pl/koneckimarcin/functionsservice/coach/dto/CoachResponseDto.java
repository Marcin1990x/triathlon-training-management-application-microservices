package pl.koneckimarcin.functionsservice.coach.dto;

import pl.koneckimarcin.functionsservice.coach.CoachEntity;
import pl.koneckimarcin.functionsservice.external.TrainingPlan;

import java.util.List;

public class CoachResponseDto {

    private Long id;
    private String firstName;
    private String lastName;

    private List<TrainingPlan> trainingPlans;

    public static CoachResponseDto fromCoachEntity(CoachEntity coachEntity, List<TrainingPlan> trainingPlans) {

        CoachResponseDto coachResponseDto = new CoachResponseDto();

        coachResponseDto.setId(coachEntity.getId());
        coachResponseDto.setFirstName(coachEntity.getFirstName());
        coachResponseDto.setLastName(coachEntity.getLastName());
        coachResponseDto.setTrainingPlans(trainingPlans);

        return coachResponseDto;
    }

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

    public List<TrainingPlan> getTrainingPlans() {
        return trainingPlans;
    }

    public void setTrainingPlans(List<TrainingPlan> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }
}
