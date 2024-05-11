package pl.koneckimarcin.functionsservice.messaging;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAthleteRequestMessageConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private final String REQUEST_QUEUE_NAME = "addAthleteRequest=";
    private final String REPLY_QUEUE_NAME = "addAthleteReply=";


    public void receiveRequestMessage(Long athleteId) {

        String routingKey = REQUEST_QUEUE_NAME + athleteId;

        Object message = rabbitTemplate.receiveAndConvert(routingKey);
        System.out.println(message.toString());
    }

    public void receiveReplyMessage(Long athleteId) {

        String routingKey = REPLY_QUEUE_NAME + athleteId;

        Object message = rabbitTemplate.receiveAndConvert(routingKey);

        amqpAdmin.deleteQueue(REQUEST_QUEUE_NAME + athleteId);
        amqpAdmin.deleteQueue(routingKey);

        // if true
        System.out.println("Athlete added successfully!" + message);
    }
}
