package pl.koneckimarcin.usersservice.user.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${topics.chat.name}")
    private String topicName;
    @Value("${topics.chat.partitions}")
    private int topicPartitions;
    @Value("${topics.chat.replicas}")
    private int topicReplicas;

    @Bean
    public NewTopic chatTopic() {
        return TopicBuilder
                .name(topicName)
                .partitions(topicPartitions)
                .replicas(topicReplicas)
                .build();
    }
}
