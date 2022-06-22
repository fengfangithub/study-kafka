package com.fengfan.kafkbasis.config;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author fengfan
 * @description Kafka生产者发送结果监听
 * @date 2022/6/22 10:13
 */
public class KafkaProducerListener<K, V> implements ProducerListener<K, V> {
    private final static Logger logger = LoggerFactory.getLogger(KafkaProducerListener.class);

    /**
     * 成功处理
     *
     * @param producerRecord
     * @param recordMetadata
     */
    @Override
    public void onSuccess(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata) {
        logger.info(producerRecord.value() + "，发送成功");
    }

    /**
     * 失败处理
     *
     * @param producerRecord
     * @param recordMetadata
     * @param exception
     */
    @Override
    public void onError(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata, Exception exception) {
        logger.error(producerRecord.value() + "，发送失败，异常为：" + exception.getMessage());
    }
}
