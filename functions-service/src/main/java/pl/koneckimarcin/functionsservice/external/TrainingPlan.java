package pl.koneckimarcin.functionsservice.external;

import java.sql.Date;
import java.util.List;

public class TrainingPlan {

    private Long id;

    private Long athleteId;

    private Long coachId;

    private String name;

    private TrainingType trainingType;

    private TrainingPlanStatus trainingPlanStatus;

    private String description;

    private Date plannedDate;

    private List<StageEntity> stage;
}
