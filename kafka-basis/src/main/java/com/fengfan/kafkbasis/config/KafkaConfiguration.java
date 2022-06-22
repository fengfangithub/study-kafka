package com.fengfan.kafkbasis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author fengfan
 * @description kafka相关配置
 * @date 2022/6/22 9:51
 */
@Configuration
public class KafkaConfiguration {

    /**
     * 配置producerListener
     *
     * @return
     */
    @Bean
    public ProducerListener<Object, Object> producerListener(){
        return new KafkaProducerListener<>();
    }

}
