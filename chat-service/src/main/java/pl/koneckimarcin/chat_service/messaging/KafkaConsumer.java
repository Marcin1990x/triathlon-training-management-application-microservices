package pl.koneckimarcin.chat_service.messaging;

import kafka.Kafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import pl.koneckimarcin.chat_service.dto.KafkaMessage;
import pl.koneckimarcin.chat_service.repository.KafkaMessageRepository;

@Configuration
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private SimpMessagingTemplate simpTemplate;

    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @KafkaListener(
            topics = "${topics.chat.name}",
            groupId = "${topics.chat.group-name}",
            containerFactory = "messageListenerFactory"
    )
    public void chatListener(KafkaMessage kafkaMessage) {

        logger.info("Consumed message: " + kafkaMessage);

        kafkaMessageRepository.save(kafkaMessage.mapToKafkaMessageEntity());

        String destination = kafkaMessage.getAthleteId() + "_" +  kafkaMessage.getCoachId();
        simpTemplate.convertAndSendToUser(destination, "/private", kafkaMessage);
        // dest: user/athleteId_coachId/private
    }
}
