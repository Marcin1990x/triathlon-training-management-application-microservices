package pl.koneckimarcin.usersservice.dto;

public class AddAthleteRequestMessage {

    private Long coachId;
    private String coachFirstName;
    private String coachLastName;

    private Long athleteId;
    private String athleteFirstName;
    private String athleteLastName;

    public AddAthleteRequestMessage() {
    }

    public AddAthleteRequestMessage(Long coachId, String coachFirstName, String coachLastName, Long athleteId,
                                    String athleteFirstName, String athleteLastName) {
        this.coachId = coachId;
        this.coachFirstName = coachFirstName;
        this.coachLastName = coachLastName;
        this.athleteId = athleteId;
        this.athleteFirstName = athleteFirstName;
        this.athleteLastName = athleteLastName;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getCoachFirstName() {
        return coachFirstName;
    }

    public void setCoachFirstName(String coachFirstName) {
        this.coachFirstName = coachFirstName;
    }

    public String getCoachLastName() {
        return coachLastName;
    }

    public void setCoachLastName(String coachLastName) {
        this.coachLastName = coachLastName;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteFirstName() {
        return athleteFirstName;
    }

    public void setAthleteFirstName(String athleteFirstName) {
        this.athleteFirstName = athleteFirstName;
    }

    public String getAthleteLastName() {
        return athleteLastName;
    }

    public void setAthleteLastName(String athleteLastName) {
        this.athleteLastName = athleteLastName;
    }
}
