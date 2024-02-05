package com.jts.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

	@Autowired
	private KafkaActivity kafkaActivity;

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	@GetMapping("/start")
	public void start(@RequestParam String id) {
		kafkaActivity.startListener(id);
	}

	@GetMapping("/stop")
	public void stop(@RequestParam String id) {
		kafkaActivity.stopListener(id);
	}

	@GetMapping("/send")
	public void send(@RequestParam String msg) {
		Message message = new Message();
		message.setMsg(msg);

		kafkaTemplate.send("demo-topics", message);
	}
}
