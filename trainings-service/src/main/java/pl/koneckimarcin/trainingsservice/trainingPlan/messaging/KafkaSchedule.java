package pl.koneckimarcin.trainingsservice.trainingPlan.messaging;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.koneckimarcin.trainingsservice.clients.UsersClient;
import pl.koneckimarcin.trainingsservice.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.trainingsservice.trainingPlan.repository.TrainingPlanRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class KafkaSchedule {

    static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    private static final String TIME_ZONE = "Europe/Warsaw";
    private LocalDateTime lastRunTime;


    @Value("${topics.training-plan.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, TrainingPlanMessage> kafkaTemplate;

    @Autowired
    private UsersClient usersClient;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @PostConstruct
    public void init() {
        checkMissedSendTrainingMessageTask();
    }

    //@Scheduled(cron = "0 35 19 * * *", zone = TIME_ZONE) // prod =)
    @Scheduled(fixedRate = 10000) // test
    public void sendTrainingsMessage() {


        List<TrainingPlanMessage> messages = createMessages();
        if (messages != null) {
            for (TrainingPlanMessage message : messages) {
                kafkaTemplate.send(topicName, "t_plan", message);
                logger.info("Published message: " + message);
                kafkaTemplate.flush();
            }
        }
        lastRunTime = LocalDateTime.now(ZoneId.of(TIME_ZONE));
    }

    private List<TrainingPlanMessage> createMessages() {

        List<TrainingPlanEntity> todaysPlans = findTrainingPlansForToday();
        if (!todaysPlans.isEmpty()) {
            List<TrainingPlanMessage> messages = createMessage(todaysPlans);

            return messages;
        }
        return null;
    }

    private List<TrainingPlanEntity> findTrainingPlansForToday() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1); // temp

        Date today = calendar.getTime();

        return trainingPlanRepository.findByPlannedDate(today);
    }

    private List<TrainingPlanMessage> createMessage(List<TrainingPlanEntity> todaysPlans) {

        List<TrainingPlanMessage> messages = new ArrayList<>();

        for (TrainingPlanEntity plan : todaysPlans) {
            LocalDateTime now = LocalDateTime.now();
            TrainingPlanMessage message = new TrainingPlanMessage();
            message.setMessageId(now.toString());
            message.setEmailAddress(getUserEmailAddress(plan.getAthleteId()));
            message.setTrainingName(plan.getName());
            message.setTrainingType(plan.getTrainingType().toString());
            message.setTrainingDescription(plan.getDescription());
            messages.add(message);
        }
        return messages;
    }

    private String getUserEmailAddress(Long athleteId) {

        return usersClient.getEmailAddress(athleteId);
    }

    private void checkMissedSendTrainingMessageTask() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TIME_ZONE));
        LocalDateTime scheduledRunTime = now.withHour(0).withMinute(0).withSecond(0).withNano(0);

        if (lastRunTime == null || lastRunTime.isBefore(scheduledRunTime)) {
            if (now.isAfter(scheduledRunTime)) {
                sendTrainingsMessage();
            }
        }
    }
}
