package pl.koneckimarcin.trainingsservice.trainingPlan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.trainingsservice.exception.ResourceNotFoundException;
import pl.koneckimarcin.trainingsservice.exception.WrongDateException;
import pl.koneckimarcin.trainingsservice.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingPlanStatus;
import pl.koneckimarcin.trainingsservice.trainingPlan.dto.TrainingPlan;
import pl.koneckimarcin.trainingsservice.trainingPlan.repository.TrainingPlanRepository;
import pl.koneckimarcin.trainingsservice.trainingStage.StageEntity;
import pl.koneckimarcin.trainingsservice.trainingStage.StageRepository;
import pl.koneckimarcin.trainingsservice.trainingStage.bike.BikeStageEntity;
import pl.koneckimarcin.trainingsservice.trainingStage.run.RunStageEntity;
import pl.koneckimarcin.trainingsservice.trainingStage.swim.SwimStageEntity;
import pl.koneckimarcin.trainingsservice.trainingStage.weight.WeightStageEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingPlanService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingPlanService.class);

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private StageRepository stageRepository;

    public boolean checkIfIsNotNull(Long id) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);
        if (trainingPlanEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public void deleteById(Long id) {

        checkTrainingPlanIdException(id);

        trainingPlanRepository.deleteById(id);
    }

    public TrainingPlan addNewTrainingPlan(TrainingPlan trainingPlan) {

        trainingPlan.setTrainingPlanStatus(TrainingPlanStatus.TEMPLATE);
        TrainingPlanEntity savedPlan = trainingPlanRepository.save(trainingPlan.mapToTrainingPlanEntity());

        return TrainingPlan.fromTrainingPlanEntity(savedPlan);
    }

    public TrainingPlan scheduleTrainingPlanForAthlete(Long id, Long athleteId, Date plannedDate) {

        checkTrainingPlanIdException(id);
        checkIfDateIsCorrectException(plannedDate);

        TrainingPlanEntity trainingPlan = trainingPlanRepository.findById(id).get();
        TrainingPlanEntity copiedPlan = copyTrainingPlanEntity(trainingPlan);

        copiedPlan.setAthleteId(athleteId);
        copiedPlan.setPlannedDate(plannedDate);
        copiedPlan.setTrainingPlanStatus(TrainingPlanStatus.PLANNED);

        trainingPlanRepository.save(copiedPlan);

        logger.info(
                "Training plan scheduled. " +
                        " AthleteId: " + copiedPlan.getAthleteId() +
                        " Date: " + copiedPlan.getPlannedDate() +
                        " Name: " + copiedPlan.getName() +
                        " Description: " + copiedPlan.getDescription() +
                        " Status: " + copiedPlan.getTrainingPlanStatus()
                );
        return TrainingPlan.fromTrainingPlanEntity(copiedPlan);
    }

    private void checkTrainingPlanIdException(Long trainingPlanId) {

        if (!checkIfIsNotNull(trainingPlanId)) {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(trainingPlanId));
        }
    }

    private void checkIfDateIsCorrectException(Date date) {

        if (!isDateCorrect(date)) {
            throw new WrongDateException(date.toString());
        }
    }

    private boolean isDateCorrect(Date date) {

        Date now = Date.valueOf(LocalDate.now());
        return !date.before(now);
    }

    private TrainingPlanEntity copyTrainingPlanEntity(TrainingPlanEntity original) {

        TrainingPlanEntity copy = new TrainingPlanEntity();
        copy.setTrainingType(original.getTrainingType());
        copy.setDescription(original.getDescription());
        copy.setName(original.getName());

        if (original.getStages().size() > 0) {
            handleStagesForCopiedTrainingPlan(original, copy);
        }
        return copy;
    }

    private void handleStagesForCopiedTrainingPlan(TrainingPlanEntity original, TrainingPlanEntity copy) {

        StageEntity stage = original.getStages().get(0);
        List<StageEntity> originalStages = original.getStages();

        List<StageEntity> stagesForCopy = new ArrayList<>();

        if (stage instanceof BikeStageEntity)
            handleBikeStageEntity(originalStages, stagesForCopy);
        if (stage instanceof SwimStageEntity)
            handleSwimStageEntity(originalStages, stagesForCopy);
        if (stage instanceof RunStageEntity)
            handleRunStageEntity(originalStages, stagesForCopy);
        if (stage instanceof WeightStageEntity)
            handleWeightStageEntity(originalStages, stagesForCopy);

        copy.setStages(stagesForCopy);
    }

    private void handleWeightStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(WeightStageEntity.copyStage((WeightStageEntity) originalStage));
    }

    private void handleRunStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(RunStageEntity.copyStage((RunStageEntity) originalStage));
    }

    private void handleSwimStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(SwimStageEntity.copyStage((SwimStageEntity) originalStage));
    }

    private void handleBikeStageEntity(List<StageEntity> originalStages, List<StageEntity> stagesForCopy) {
        for (StageEntity originalStage : originalStages)
            stagesForCopy.add(BikeStageEntity.copyStage((BikeStageEntity) originalStage));
    }

    public TrainingPlan getTrainingPlanById(Long id) {

        checkTrainingPlanIdException(id);

        return TrainingPlan.fromTrainingPlanEntity(trainingPlanRepository.findById(id).get());
    }

    public List<TrainingPlan> findAllByCoachId(Long coachId) {

        List<TrainingPlan> trainingPlans = new ArrayList<>();
        List<TrainingPlanEntity> trainingPlanEntities
                = trainingPlanRepository.findByCoachId(coachId);
        for (TrainingPlanEntity planEntity : trainingPlanEntities) {
            trainingPlans.add(TrainingPlan.fromTrainingPlanEntity(planEntity));
        }
        return trainingPlans;
    }

    public List<TrainingPlan> findAllByAthleteId(Long athleteId) {

        List<TrainingPlan> trainingPlans = new ArrayList<>();
        List<TrainingPlanEntity> trainingPlanEntities
                = trainingPlanRepository.findByAthleteId(athleteId);
        for (TrainingPlanEntity planEntity : trainingPlanEntities) {
            trainingPlans.add(TrainingPlan.fromTrainingPlanEntity(planEntity));
        }
        return trainingPlans;
    }
}
