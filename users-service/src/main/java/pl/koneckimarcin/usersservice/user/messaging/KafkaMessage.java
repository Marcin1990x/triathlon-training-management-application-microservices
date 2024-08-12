package pl.koneckimarcin.usersservice.user.messaging;

public class KafkaMessage {
    private String athleteId;
    private String coachId;

    private String content;

    private String timestamp;

    private String senderTypeAndId;

    public KafkaMessage() {
    }

    public KafkaMessage(String athleteId, String coachId, String content, String senderTypeAndId) {
        this.athleteId = athleteId;
        this.coachId = coachId;
        this.content = content;
        this.senderTypeAndId = senderTypeAndId;
    }

    public String getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(String athleteId) {
        this.athleteId = athleteId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderTypeAndId() {
        return senderTypeAndId;
    }

    public void setSenderTypeAndId(String senderTypeAndId) {
        this.senderTypeAndId = senderTypeAndId;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "athleteId='" + athleteId + '\'' +
                ", coachId='" + coachId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", senderTypeAndId='" + senderTypeAndId + '\'' +
                '}';
    }
}

