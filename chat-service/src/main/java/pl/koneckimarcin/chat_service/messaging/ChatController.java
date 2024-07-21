package pl.koneckimarcin.chat_service.messaging;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public KafkaMessage broadcastMessage(@Payload KafkaMessage kafkaMessage) {
        return kafkaMessage;
    }
}
