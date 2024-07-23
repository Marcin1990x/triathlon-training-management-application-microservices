package pl.koneckimarcin.chat_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.chat_service.dto.KafkaMessage;
import pl.koneckimarcin.chat_service.messaging.KafkaMessageEntity;
import pl.koneckimarcin.chat_service.repository.KafkaMessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageService {

    @Autowired
    private KafkaMessageRepository repository;

    public List<KafkaMessage> getChatMessages(String athleteId, String coachId) {

        List<KafkaMessage> messages = new ArrayList<>();
        List<KafkaMessageEntity> messageEntities = repository.findByAthleteIdAndCoachId(athleteId, coachId);

        for(KafkaMessageEntity entity: messageEntities){
            messages.add(KafkaMessage.fromKafkaMessageEntity(entity));
        }
        return messages;
    }
}
