package pl.koneckimarcin.functionsservice.messaging;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private AmqpAdmin amqpAdmin;

    public int getRequestCount(Long athleteId) {
        QueueInformation information = amqpAdmin.getQueueInfo("addAthleteRequest=" + athleteId);
        if(information != null) {
            return information.getMessageCount();
        }
        return 0;
    }
}
