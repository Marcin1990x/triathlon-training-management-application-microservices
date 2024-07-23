package pl.koneckimarcin.chat_service.dto;

import pl.koneckimarcin.chat_service.messaging.KafkaMessageEntity;

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

    public KafkaMessageEntity mapToKafkaMessageEntity() {

        KafkaMessageEntity message = new KafkaMessageEntity();
        message.setAthleteId(this.athleteId);
        message.setCoachId(this.coachId);
        message.setContent(this.content);
        message.setTimestamp(this.timestamp);

        return message;
    }
    public static KafkaMessage fromKafkaMessageEntity(KafkaMessageEntity kafkaMessageEntity) {

        KafkaMessage kafkaMessage = new KafkaMessage();

        kafkaMessage.setAthleteId(kafkaMessageEntity.getAthleteId());
        kafkaMessage.setCoachId(kafkaMessageEntity.getCoachId());
        kafkaMessage.setContent(kafkaMessageEntity.getContent());
        kafkaMessage.setTimestamp(kafkaMessageEntity.getTimestamp());

        return kafkaMessage;
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

