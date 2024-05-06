package pl.koneckimarcin.trainingsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.trainingsservice.trainingStage.Stage;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncompatibleTrainingTypeException extends RuntimeException {

    private Stage stage;
    private TrainingType trainingType;

    public IncompatibleTrainingTypeException(Stage stage, TrainingType trainingType) {
        super("This stage: " + stage.toString() + " can be added only for training type: " + trainingType.toString());
        this.stage = stage;
        this.trainingType = trainingType;
    }


}
