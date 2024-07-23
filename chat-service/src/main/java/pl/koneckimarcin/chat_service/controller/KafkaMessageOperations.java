package pl.koneckimarcin.chat_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.koneckimarcin.chat_service.dto.KafkaMessage;

import java.util.List;

public interface KafkaMessageOperations {

    @GetMapping("/chat/getChatMessages")
    public List<KafkaMessage> getChatMessages(@RequestParam String athleteId, @RequestParam String coachId);
}
