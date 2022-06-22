package com.fengfan.kafkbasis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author fengfan
 * @description 自定义序列化
 * @date 2022/6/22 15:24
 */
public class MySerializer<T> implements Serializer<T> {

    @SneakyThrows
    @Override
    public byte[] serialize(String s, T t) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(t);
    }
}
