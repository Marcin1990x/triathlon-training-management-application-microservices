package pl.koneckimarcin.trainingsservice.trainingPlan.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.trainingsservice.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingPlanStatus;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.trainingsservice.trainingStage.StageEntity;

import java.util.Date;
import java.util.List;

public class TrainingPlan {

    private Long id;

    private Long athleteId;
    @NotNull
    private Long coachId;

    @NotEmpty(message = "TrainingPlan name should not be empty")
    private String name;

    @NotNull(message = "TrainingPlan type can not be empty")
    private TrainingType trainingType;

    private TrainingPlanStatus trainingPlanStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date plannedDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<StageEntity> stage;

    public TrainingPlan() {
    }

    public TrainingPlan(String name, TrainingType trainingType, String description) {
        this.name = name;
        this.trainingType = trainingType;
        this.description = description;
    }

    public TrainingPlanEntity mapToTrainingPlanEntity() {

        TrainingPlanEntity trainingPlanEntity = new TrainingPlanEntity();

        trainingPlanEntity.setId(this.id);
        trainingPlanEntity.setAthleteId(this.athleteId);
        trainingPlanEntity.setCoachId(this.coachId);
        trainingPlanEntity.setName(this.name);
        trainingPlanEntity.setTrainingType(this.trainingType);
        trainingPlanEntity.setTrainingPlanStatus(this.trainingPlanStatus);
        trainingPlanEntity.setDescription(this.getDescription());
        trainingPlanEntity.setPlannedDate(this.plannedDate);
        trainingPlanEntity.setStages(this.getStage()); // todo: not entity

        return trainingPlanEntity;
    }

    public static TrainingPlan fromTrainingPlanEntity(TrainingPlanEntity trainingPlanEntity) {

        TrainingPlan trainingPlan = new TrainingPlan();

        trainingPlan.setId(trainingPlanEntity.getId());
        trainingPlan.setAthleteId(trainingPlanEntity.getAthleteId());
        trainingPlan.setCoachId(trainingPlanEntity.getCoachId());
        trainingPlan.setName(trainingPlanEntity.getName());
        trainingPlan.setTrainingType(trainingPlanEntity.getTrainingType());
        trainingPlan.setTrainingPlanStatus(trainingPlanEntity.getTrainingPlanStatus());
        trainingPlan.setDescription(trainingPlanEntity.getDescription());
        trainingPlan.setPlannedDate(trainingPlanEntity.getPlannedDate());
        trainingPlan.setStage(trainingPlanEntity.getStages()); // todo: not entity

        return trainingPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public TrainingPlanStatus getTrainingPlanStatus() {
        return trainingPlanStatus;
    }

    public void setTrainingPlanStatus(TrainingPlanStatus trainingPlanStatus) {
        this.trainingPlanStatus = trainingPlanStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public List<StageEntity> getStage() {
        return stage;
    }

    public void setStage(List<StageEntity> stage) {
        this.stage = stage;
    }
}
