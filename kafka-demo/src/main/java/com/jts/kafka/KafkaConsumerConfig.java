package com.jts.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {
	
	private static final String kafkaUrl = "localhost:9092";

	@Bean
	public DefaultKafkaConsumerFactory<String, Message> msgCosummerFactory() {
		JsonDeserializer<Message> deserializer = new JsonDeserializer<>(Message.class, false);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl); 
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); 
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); 
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1"); 
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer); 
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Message> msgListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(msgCosummerFactory());
		return factory;
	}
}
