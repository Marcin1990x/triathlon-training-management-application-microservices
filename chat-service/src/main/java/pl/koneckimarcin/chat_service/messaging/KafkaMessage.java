package pl.koneckimarcin.chat_service.messaging;

public class KafkaMessage {
    private String athleteId;
    private String coachId;

    private String content;

    private String timestamp;

    public KafkaMessage() {
    }

    public KafkaMessage(String athleteId, String coachId, String content) {
        this.athleteId = athleteId;
        this.coachId = coachId;
        this.content = content;
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

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "athleteId='" + athleteId + '\'' +
                ", coachId='" + coachId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

