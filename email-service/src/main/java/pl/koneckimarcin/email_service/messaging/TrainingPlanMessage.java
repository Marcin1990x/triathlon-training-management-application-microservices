package pl.koneckimarcin.email_service.messaging;

public class TrainingPlanMessage {
    private String messageId;
    private String emailAddress;
    private String trainingName;
    private String trainingType;
    private String trainingDescription;

    public TrainingPlanMessage() {
    }

    public TrainingPlanMessage(String messageId, String emailAddress, String trainingName, String trainingType,
                               String trainingDescription) {
        this.messageId = messageId;
        this.emailAddress = emailAddress;
        this.trainingName = trainingName;
        this.trainingType = trainingType;
        this.trainingDescription = trainingDescription;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    @Override
    public String toString() {
        return "TrainingPlanMessage{" +
                "messageId='" + messageId + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", trainingName='" + trainingName + '\'' +
                ", trainingType='" + trainingType + '\'' +
                ", trainingDescription='" + trainingDescription + '\'' +
                '}';
    }
}
