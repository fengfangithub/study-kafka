package com.fengfan.kafkbasis.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengfan
 * @description topic初始化
 * @date 2022/6/21 16:12
 */
@Configuration
public class KafkaInitialConfiguration {
    //名称
    @Value("${mq.kafka.topic}")
    private String topic;
    //分区数
    @Value("${mq.kafka.partition}")
    private Integer partition;
    //副本数
    @Value("${mq.kafka.replica}")
    private short replica;

    @Bean
    public NewTopic initialTopic() {
        return new NewTopic(topic, partition, replica);
    }

}
