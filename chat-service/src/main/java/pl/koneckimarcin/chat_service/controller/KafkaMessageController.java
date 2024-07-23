package pl.koneckimarcin.chat_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.chat_service.dto.KafkaMessage;
import pl.koneckimarcin.chat_service.service.KafkaMessageService;

import java.util.List;

@RestController
public class KafkaMessageController implements KafkaMessageOperations{

    @Autowired
    private KafkaMessageService kafkaMessageService;
    @Override
    public List<KafkaMessage> getChatMessages(String athleteId, String coachId) {

        return kafkaMessageService.getChatMessages(athleteId, coachId);
    }
}
