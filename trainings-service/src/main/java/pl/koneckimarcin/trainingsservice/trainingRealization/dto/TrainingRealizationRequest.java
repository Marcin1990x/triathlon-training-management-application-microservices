package pl.koneckimarcin.trainingsservice.trainingRealization.dto;


import pl.koneckimarcin.trainingsservice.trainingRealization.Feelings;

public class TrainingRealizationRequest {

    private String realizationDescription;

    private Feelings feelings;

    private int rpeLevel;

    public String getRealizationDescription() {
        return realizationDescription;
    }

    public Feelings getFeelings() {
        return feelings;
    }

    public int getRpeLevel() {
        return rpeLevel;
    }

    public TrainingRealizationRequest(String realizationDescription, Feelings feelings, int rpeLevel) {
        this.realizationDescription = realizationDescription;
        this.feelings = feelings;
        this.rpeLevel = rpeLevel;
    }
}
