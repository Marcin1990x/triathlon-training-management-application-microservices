package pl.koneckimarcin.trainingsservice.trainingRealization;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingType;

import java.util.Date;


@Document
public class TrainingRealizationEntity {

    @Id
    private String id;

    private Long athleteId;

    private Long stravaId;

    private Long stravaAthleteId;

    private String name;

    private Double distanceInMeters;

    private Integer timeInSeconds;

    @Enumerated(EnumType.STRING)
    private TrainingType type;

    private Date realizationDate;

    private Long averageWatts;

    private Long maxWatts;

    private Long averageHeartrate;

    private Long maxHeartrate;

    private String realizationDescription;

    @Enumerated(EnumType.STRING)
    private Feelings feelings;

    private int rpeLevel;

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
