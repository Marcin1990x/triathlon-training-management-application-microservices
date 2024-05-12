package pl.koneckimarcin.functionsservice.messaging;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;

@Component
public class AddAthleteRequestMessageConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    private final String REQUEST_QUEUE_NAME = "addAthleteRequest=";
    private final String REPLY_QUEUE_NAME = "addAthleteReply=";


    public AddAthleteRequestMessage receiveRequestMessage(Long athleteId) {

        String routingKey = REQUEST_QUEUE_NAME + athleteId;

        AddAthleteRequestMessage message = rabbitTemplate.receiveAndConvert(routingKey,
                ParameterizedTypeReference.forType(AddAthleteRequestMessage.class));

        return message;
    }

    public boolean receiveReplyMessage(Long athleteId) {

        String routingKey = REPLY_QUEUE_NAME + athleteId;

        Boolean reply = rabbitTemplate.receiveAndConvert(routingKey, ParameterizedTypeReference.forType(Boolean.class));

        amqpAdmin.deleteQueue(REQUEST_QUEUE_NAME + athleteId);
        amqpAdmin.deleteQueue(routingKey);

        return reply;
    }
}
