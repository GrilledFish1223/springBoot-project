package com.ping.kafka.consumer;

import com.ping.kafka.common.utils.SpringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangsp
 * @date: 2019/2/28 16:48
 * @copyright: @2019
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    /**
     * kafka:
     * bootstrap-servers: 192.168.14.38:9092
     * consumer:
     * auto-commit-interval: 100
     * auto-offset-reset: latest
     * enable-auto-commit: false
     * group-id: test_group
     * max-poll-records: 5000
     * template:
     * default-topic: test_zsp
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String server;
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private String autoCommitInterval;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupName;
    @Value("${spring.kafka.consumer.max-poll-records}")
    private int maxPollRecords;
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Bean
    public ConcurrentMessageListenerContainer<String, String> testMessageListenerContatiner() {
        ContainerProperties properties = new ContainerProperties(topic);
        properties.setClientId("test");
        ConcurrentMessageListenerContainer<String, String> container =
                new ConcurrentMessageListenerContainer<>(consumerFactory(), properties);
        container.setConcurrency(3);
        container.getContainerProperties().setPollTimeout(1500);
        container.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        container.setupMessageListener(SpringUtils.getBean(TestConsumerEvent.class));
        container.getContainerProperties().setGroupId(groupName);
        container.setAutoStartup(true);
        return container;
    }

    private ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(16);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
