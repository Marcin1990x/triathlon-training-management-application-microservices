package pl.koneckimarcin.functionsservice.messaging;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;

@Component
public class AddAthleteRequestMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    public void sendRequestMessage(AddAthleteRequestMessage message, Long athleteId) {

        String routingKey = "addAthleteRequest=" + athleteId;

        Queue queue = createQueue(routingKey);
        amqpAdmin.declareQueue(queue);

        rabbitTemplate.convertAndSend(routingKey, message);
    }
    public void sendReplyMessage(boolean confirmation, Long athleteId) {

        String routingKey = "addAthleteReply=" + athleteId;

        Queue queue = createQueue(routingKey);
        amqpAdmin.declareQueue(queue);

        rabbitTemplate.convertAndSend(routingKey, confirmation);
    }

    private Queue createQueue(String queueName) {
        return new Queue(queueName);
    }
}
