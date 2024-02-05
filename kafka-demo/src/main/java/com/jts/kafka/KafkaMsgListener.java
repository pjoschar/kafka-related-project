package com.jts.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMsgListener {

	@KafkaListener(id = "id-1", groupId = "group-1", topics = "demo-topics", containerFactory = "msgListenerFactory", autoStartup = "false")
	public void consumeMsg(Message msg) {
		System.out.println("Message Received::" + msg);
	}
}
