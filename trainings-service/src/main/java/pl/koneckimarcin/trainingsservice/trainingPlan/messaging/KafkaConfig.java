package pl.koneckimarcin.trainingsservice.trainingPlan.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${topics.training-plan.name}")
    private String topicName;
    @Value("${topics.training-plan.partitions}")
    private int topicPartitions;
    @Value("${topics.training-plan.replicas}")
    private int topicReplicas;

    @Bean
    public NewTopic trainingPlanTopic() {
        return TopicBuilder
                .name(topicName)
                .partitions(topicPartitions)
                .replicas(topicReplicas)
                .build();
    }
}
