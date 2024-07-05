package pl.koneckimarcin.email_service.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(
            topics = "${topics.training-plan.name}",
            containerFactory = "messageListenerFactory"
    )
    public void trainingPlanMessageListener(TrainingPlanMessage message){
        logger.info("Consuming: " + message);
    }
}
