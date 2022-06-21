package com.fengfan.kafkbasis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author fengfan
 * @description 生产者发送消息
 * @date 2022/6/16 15:49
 */
@RestController
public class KafkaProducerController {
    private final static Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 默认异步发送，带有回调方法
     *
     * @param message
     */
    @PostMapping("/sendMessage")
    public void sendMessage(String message) {
        kafkaTemplate.send("topic", message).addCallback(success -> logger.info("发送成功----内容：" + success.getProducerRecord().value() + "，topic："
                        + success.getRecordMetadata().topic() + "，partition：" + success.getRecordMetadata().partition()),
                fail -> logger.info("发送失败----" + fail.getMessage()));
    }


    /**
     * 同步发送：get方法会一直阻塞，直到结果返回
     *
     * @param message
     * @throws Exception
     */
    @PostMapping("/sendSyncMessage")
    public void sendSyncMessage(String message) throws Exception {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic", message);
        //获取发送结果，会一直等待，指导达到设置的超时时间
        SendResult<String, Object> result = future.get(3, TimeUnit.SECONDS);
        logger.info("发送成功----内容：" + result.getProducerRecord().value() + "，topic："
                + result.getRecordMetadata().topic() + "，artition：" + result.getRecordMetadata().partition());
    }

}
