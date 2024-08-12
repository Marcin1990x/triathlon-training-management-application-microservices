package pl.koneckimarcin.chat_service.messaging;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class KafkaMessageEntity {

    @Id
    private String id;

    private String athleteId;
    private String coachId;

    private String content;

    private String timestamp;

    private String senderTypeAndId;

    public KafkaMessageEntity() {
    }

    public KafkaMessageEntity(String id, String athleteId, String coachId, String content, String timestamp,
                              String senderTypeAndId) {
        this.id = id;
        this.athleteId = athleteId;
        this.coachId = coachId;
        this.content = content;
        this.timestamp = timestamp;
        this.senderTypeAndId = senderTypeAndId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "KafkaMessageEntity{" +
                "id='" + id + '\'' +
                ", athleteId='" + athleteId + '\'' +
                ", coachId='" + coachId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", senderTypeAndId='" + senderTypeAndId + '\'' +
                '}';
    }
}
