package pl.koneckimarcin.trainingsservice.trainingRealization.external;

public class StravaActivityDto {

    private Long id;
    private StravaAthleteDto athlete;
    private String name;
    private Double distance;
    private Integer moving_time;
    private String type;
    private String start_date;
    private Long average_watts;
    private Long max_watts;
    private Long average_heartrate;
    private Long max_heartrate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StravaAthleteDto getAthlete() {
        return athlete;
    }

    public void setAthlete(StravaAthleteDto athlete) {
        this.athlete = athlete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getMoving_time() {
        return moving_time;
    }

    public void setMoving_time(Integer moving_time) {
        this.moving_time = moving_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public Long getAverage_watts() {
        return average_watts;
    }

    public void setAverage_watts(Long average_watts) {
        this.average_watts = average_watts;
    }

    public Long getMax_watts() {
        return max_watts;
    }

    public void setMax_watts(Long max_watts) {
        this.max_watts = max_watts;
    }

    public Long getAverage_heartrate() {
        return average_heartrate;
    }

    public void setAverage_heartrate(Long average_heartrate) {
        this.average_heartrate = average_heartrate;
    }

    public Long getMax_heartrate() {
        return max_heartrate;
    }

    public void setMax_heartrate(Long max_heartrate) {
        this.max_heartrate = max_heartrate;
    }
}
