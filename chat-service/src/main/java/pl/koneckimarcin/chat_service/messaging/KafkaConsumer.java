package pl.koneckimarcin.chat_service.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private SimpMessagingTemplate simpTemplate;

    @KafkaListener(
            topics = "${topics.chat.name}",
            groupId = "${topics.chat.group-name}",
            containerFactory = "messageListenerFactory"
    )
    public void chatListener(ConsumerRecord<String, KafkaMessage> messageRecord) {

        String key = messageRecord.key();
        KafkaMessage kafkaMessage = messageRecord.value();

        logger.info("Consumed message: " + kafkaMessage);

        simpTemplate.convertAndSend("/chatroom", kafkaMessage);

        String destination = kafkaMessage.getAthleteId() + "_" +  kafkaMessage.getCoachId();
        System.out.println(destination);
        simpTemplate.convertAndSendToUser(destination, "/private", kafkaMessage);
        // dest: user/athleteId_coachId/private
    }
}
