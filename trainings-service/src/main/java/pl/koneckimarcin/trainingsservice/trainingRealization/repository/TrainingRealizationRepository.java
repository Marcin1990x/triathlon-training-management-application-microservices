package pl.koneckimarcin.trainingsservice.trainingRealization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.trainingsservice.trainingRealization.TrainingRealizationEntity;

@Repository
public interface TrainingRealizationRepository extends JpaRepository<TrainingRealizationEntity, Long> {

}
