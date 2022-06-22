package com.fengfan.kafkbasis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fengfan
 * @description kafka消息
 * @date 2022/6/22 15:48
 */
@Data
public class KafkaMessageEntity<T> implements Serializable {
    //标识
    private String id;
    //数据
    private T data;
}
