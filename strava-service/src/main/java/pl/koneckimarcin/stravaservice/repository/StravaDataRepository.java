package pl.koneckimarcin.stravaservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.koneckimarcin.stravaservice.StravaDataEntity;

import java.util.Optional;

public interface StravaDataRepository extends MongoRepository<StravaDataEntity, String> {

    @Query("{'userId': ?0}")
    public Optional<StravaDataEntity> findByUserId(Long userId);
}
