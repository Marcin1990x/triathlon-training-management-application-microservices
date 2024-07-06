package pl.koneckimarcin.email_service.messaging;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${topics.training-plan.name}")
    private String topicName;

    public Map<String, Object> consumerConfiguration() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG, "training-plan-group-0"
        );
    }

    @Bean
    public ConsumerFactory<String, TrainingPlanMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfiguration(),
                new StringDeserializer(),
                new JsonDeserializer<>(TrainingPlanMessage.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TrainingPlanMessage> messageListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TrainingPlanMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
