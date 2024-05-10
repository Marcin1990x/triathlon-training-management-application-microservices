package pl.koneckimarcin.usersservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.koneckimarcin.usersservice.dto.AddAthleteRequestMessage;

public class AddAthleteRequestMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {

        AddAthleteRequestMessage message = new AddAthleteRequestMessage(1L, "Coach", "Coachtest",
                1L, "Athlete", "Athletetest");

        rabbitTemplate.convertAndSend("addAthleteToCoachQueue", message);
    }
}
