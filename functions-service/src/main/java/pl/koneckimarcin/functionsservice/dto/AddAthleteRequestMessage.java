package pl.koneckimarcin.functionsservice.dto;

public class AddAthleteRequestMessage {


    private Long coachId;
    private String coachFirstName;
    private String coachLastName;

    public AddAthleteRequestMessage() {
    }

    public AddAthleteRequestMessage(Long coachId,String coachFirstName, String coachLastName) {
        this.coachId = coachId;
        this.coachFirstName = coachFirstName;
        this.coachLastName = coachLastName;
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

    @Override
    public String toString() {
        return "AddAthleteRequestMessage{" +
                "coachId=" + coachId +
                ", coachFirstName='" + coachFirstName + '\'' +
                ", coachLastName='" + coachLastName + '\'' +
                '}';
    }
}
