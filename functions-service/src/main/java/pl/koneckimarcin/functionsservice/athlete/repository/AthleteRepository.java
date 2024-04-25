package pl.koneckimarcin.functionsservice.athlete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.koneckimarcin.functionsservice.athlete.AthleteEntity;

import java.util.List;

public interface AthleteRepository extends JpaRepository<AthleteEntity, Long> {

    public List<AthleteEntity> findByLastNameContainingIgnoreCaseAndCoachIdIsNull(String lastName);

}
