package com.ping.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.BatchAcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zhangsp
 * @date: 2019/2/28 17:22
 * @copyright: @2019
 */
@Component
public class TestConsumerEvent implements BatchAcknowledgingMessageListener<String,String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConsumerEvent.class);

    @Override
    public void onMessage(List<ConsumerRecord<String, String>> list, Acknowledgment acknowledgment) {
        String jsonValue = null;
       for (ConsumerRecord<String,String> record :list) {
           jsonValue = record.value();
            LOGGER.info("message:" + jsonValue);
       }
    }
}
