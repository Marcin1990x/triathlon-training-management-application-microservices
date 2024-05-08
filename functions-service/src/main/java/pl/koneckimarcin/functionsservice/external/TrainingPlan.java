package pl.koneckimarcin.functionsservice.external;

import java.sql.Date;
import java.util.List;

public class TrainingPlan {

    private Long id;

    private Long athleteId;

    private Long coachId;

    private String name;

    private TrainingType trainingType;

    private TrainingPlanStatus trainingPlanStatus;

    private String description;

    private Date plannedDate;

    private List<StageEntity> stage;

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
