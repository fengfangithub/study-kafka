package com.fengfan.kafkbasis.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author fengfan
 * @description kafka相关配置
 * @date 2022/6/22 9:51
 */
@Configuration
public class KafkaConfiguration {

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

    /**
     * 配置producerListener
     *
     * @return
     */
    @Bean
    public ProducerListener<Object, Object> producerListener(){
        return new KafkaProducerListener<>();
    }

    @KafkaListener(topics = "topic")
    public void onMessage(ConsumerRecord<?, ?> record){
        System.out.println("消费者消费，record："+record.topic()+"-"+record.partition()+"-"+record.value());
    }

}
