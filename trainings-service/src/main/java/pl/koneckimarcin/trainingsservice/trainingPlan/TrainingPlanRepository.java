package pl.koneckimarcin.trainingsservice.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.trainingsservice.trainingPlan.constant.TrainingType;

import java.util.List;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlanEntity, Long> {

    List<TrainingPlanEntity> findByTrainingType(TrainingType trainingType);
}
