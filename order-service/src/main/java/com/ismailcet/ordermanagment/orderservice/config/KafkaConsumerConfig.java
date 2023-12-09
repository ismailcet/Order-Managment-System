package com.ismailcet.ordermanagment.orderservice.config;

import com.ismailcet.ordermanagment.orderservice.entity.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaAddress;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Bean
    public ConsumerFactory<String, Order> consumerFactory(){
        // Deserialize Json Config Object
        JsonDeserializer<Order> jsonDeserializer = new JsonDeserializer<>(Order.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);

        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);
        return new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(),jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Order> factory =
                new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
