package pl.koneckimarcin.chat_service.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import pl.koneckimarcin.chat_service.subscription.SubscriptionService;

@Configuration
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @KafkaListener(
            topics = "${topics.chat.name}",
            groupId = "${topics.chat.group-name}",
            containerFactory = "messageListenerFactory"
    )
    public void chatListener(ConsumerRecord<String, String> messageRecord) {

        String key = messageRecord.key();
        String message = messageRecord.value();

        logger.info("keys"  + subscriptionService.getSubscribedKeys().toString());

        logger.info("all " + key + message);

        if(subscriptionService.isSubscribed(key)){
            logger.info("subscribed: " + key + message);
        }
    }
}
