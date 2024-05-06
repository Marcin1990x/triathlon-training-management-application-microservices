package pl.koneckimarcin.trainingsservice.trainingRealization.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.koneckimarcin.trainingsservice.trainingRealization.TrainingRealizationEntity;

import java.util.List;

public interface TrainingRealizationRepository extends MongoRepository<TrainingRealizationEntity, Long> {

    @Query("{'athleteId': ?0}")
    public List<TrainingRealizationEntity> findByAthleteId(Long athleteId);

}
