package pl.koneckimarcin.functionsservice.messaging;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.functionsservice.dto.AddAthleteRequestMessage;
import pl.koneckimarcin.functionsservice.dto.AddAthleteResponseMessage;

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
    public void sendReplyMessage(AddAthleteResponseMessage responseMessage, Long coachId) {

        String routingKey = "addAthleteReply=" + coachId;

        Queue queue = createQueue(routingKey);
        amqpAdmin.declareQueue(queue);

        rabbitTemplate.convertAndSend(routingKey, responseMessage);
    }

    private Queue createQueue(String queueName) {
        return new Queue(queueName);
    }
}
