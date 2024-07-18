package pl.koneckimarcin.email_service.messaging;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import pl.koneckimarcin.email_service.EmailSender;

@Configuration
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private EmailSender emailSender;

    @KafkaListener(
            topics = "${topics.training-plan.name}",
            containerFactory = "messageListenerFactory"
    )
    public void trainingPlanMessageListener(TrainingPlanMessage message) {

        try {
            emailSender.sendEmailWithTrainingPlan(
                    message.getEmailAddress(), message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("Consuming: " + message);
    }
}
