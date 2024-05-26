package pl.koneckimarcin.functionsservice.dto;

public class AddAthleteResponseMessage {


    private Long athleteId;
    private String athleteFirstName;
    private String athleteLastName;

    private boolean confirmation;

    public AddAthleteResponseMessage() {
    }

    public AddAthleteResponseMessage(Long athleteId, String athleteFirstName, String athleteLastName, boolean confirmation) {
        this.athleteId = athleteId;
        this.athleteFirstName = athleteFirstName;
        this.athleteLastName = athleteLastName;
        this.confirmation = confirmation;
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

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "AddAthleteResponseMessage{" +
                "athleteId=" + athleteId +
                ", athleteFirstName='" + athleteFirstName + '\'' +
                ", athleteLastName='" + athleteLastName + '\'' +
                ", confirmation=" + confirmation +
                '}';
    }
}
