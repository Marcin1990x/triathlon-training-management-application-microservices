package pl.koneckimarcin.trainingsservice.trainingRealization.dto;


import jakarta.validation.constraints.NotNull;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.trainingsservice.trainingRealization.Feelings;
import pl.koneckimarcin.trainingsservice.trainingRealization.TrainingRealizationEntity;

import java.util.Date;


public class TrainingRealization {

    private String id;

    @NotNull
    private Long athleteId;

    private Long stravaId;

    private Long stravaAthleteId;

    @NotNull
    private String name;

    private Double distanceInMeters;

    private Integer timeInSeconds;

    @NotNull
    private TrainingType type;

    @NotNull
    private Date realizationDate;

    private Long averageWatts;

    private Long maxWatts;

    private Long averageHeartrate;

    private Long maxHeartrate;

    private String realizationDescription;

    private Feelings feelings;

    private int rpeLevel;

    public TrainingRealizationEntity mapToTrainingRealizationEntity() {

        TrainingRealizationEntity trainingRealization = new TrainingRealizationEntity();

        trainingRealization.setId(this.id);
        trainingRealization.setAthleteId(this.athleteId);
        trainingRealization.setName(this.name);
        trainingRealization.setDistanceInMeters(this.distanceInMeters);
        trainingRealization.setTimeInSeconds(this.timeInSeconds);
        trainingRealization.setType(this.type);
        trainingRealization.setRealizationDate(this.realizationDate);
        trainingRealization.setAverageWatts(this.averageWatts);
        trainingRealization.setMaxWatts(this.maxWatts);
        trainingRealization.setAverageHeartrate(this.averageHeartrate);
        trainingRealization.setMaxHeartrate(this.maxHeartrate);
        trainingRealization.setRealizationDescription(this.realizationDescription);
        trainingRealization.setFeelings(this.feelings);
        trainingRealization.setRpeLevel(this.rpeLevel);

        return trainingRealization;
    }

    public static TrainingRealization fromTrainingRealizationEntity
            (TrainingRealizationEntity trainingRealizationEntity) {

        TrainingRealization trainingRealization = new TrainingRealization();

        trainingRealization.setId(trainingRealizationEntity.getId());
        trainingRealization.setAthleteId(trainingRealizationEntity.getAthleteId());
        trainingRealization.setName(trainingRealizationEntity.getName());
        trainingRealization.setDistanceInMeters(trainingRealizationEntity.getDistanceInMeters());
        trainingRealization.setTimeInSeconds(trainingRealizationEntity.getTimeInSeconds());
        trainingRealization.setType(trainingRealizationEntity.getType());
        trainingRealization.setRealizationDate(trainingRealizationEntity.getRealizationDate());
        trainingRealization.setAverageWatts(trainingRealizationEntity.getAverageWatts());
        trainingRealization.setMaxWatts(trainingRealizationEntity.getMaxWatts());
        trainingRealization.setAverageHeartrate(trainingRealizationEntity.getAverageHeartrate());
        trainingRealization.setMaxHeartrate(trainingRealizationEntity.getMaxHeartrate());
        trainingRealization.setRealizationDescription(trainingRealizationEntity.getRealizationDescription());
        trainingRealization.setFeelings(trainingRealizationEntity.getFeelings());
        trainingRealization.setRpeLevel(trainingRealizationEntity.getRpeLevel());

        return trainingRealization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }

    public Long getStravaAthleteId() {
        return stravaAthleteId;
    }

    public void setStravaAthleteId(Long stravaAthleteId) {
        this.stravaAthleteId = stravaAthleteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Double distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(Integer timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public Long getAverageWatts() {
        return averageWatts;
    }

    public void setAverageWatts(Long averageWatts) {
        this.averageWatts = averageWatts;
    }

    public Long getMaxWatts() {
        return maxWatts;
    }

    public void setMaxWatts(Long maxWatts) {
        this.maxWatts = maxWatts;
    }

    public Long getAverageHeartrate() {
        return averageHeartrate;
    }

    public void setAverageHeartrate(Long averageHeartrate) {
        this.averageHeartrate = averageHeartrate;
    }

    public Long getMaxHeartrate() {
        return maxHeartrate;
    }

    public void setMaxHeartrate(Long maxHeartrate) {
        this.maxHeartrate = maxHeartrate;
    }

    public String getRealizationDescription() {
        return realizationDescription;
    }

    public void setRealizationDescription(String realizationDescription) {
        this.realizationDescription = realizationDescription;
    }

    public Feelings getFeelings() {
        return feelings;
    }

    public void setFeelings(Feelings feelings) {
        this.feelings = feelings;
    }

    public int getRpeLevel() {
        return rpeLevel;
    }

    public void setRpeLevel(int rpeLevel) {
        this.rpeLevel = rpeLevel;
    }
}
