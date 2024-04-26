package pl.koneckimarcin.functionsservice.coach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.koneckimarcin.functionsservice.coach.CoachEntity;

import java.util.Optional;

public interface CoachRepository extends JpaRepository <CoachEntity, Long> {

    //Optional<CoachEntity> findByTrainingPlanEntities_id(Long id);
}
