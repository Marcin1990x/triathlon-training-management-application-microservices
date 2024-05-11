package pl.koneckimarcin.functionsservice.dto;

public class AddAthleteRequestMessage {

    private String coachFirstName;
    private String coachLastName;

    public AddAthleteRequestMessage() {
    }

    public AddAthleteRequestMessage(String coachFirstName, String coachLastName) {
        this.coachFirstName = coachFirstName;
        this.coachLastName = coachLastName;
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
                "coachFirstName='" + coachFirstName + '\'' +
                ", coachLastName='" + coachLastName + '\'' +
                '}';
    }
}
