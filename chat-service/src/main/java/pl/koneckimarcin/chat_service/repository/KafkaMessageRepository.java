package pl.koneckimarcin.chat_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.koneckimarcin.chat_service.messaging.KafkaMessageEntity;

import java.util.List;

public interface KafkaMessageRepository extends MongoRepository<KafkaMessageEntity, String> {

    List<KafkaMessageEntity> findByAthleteIdAndCoachId(String athleteId, String coachId);
}
