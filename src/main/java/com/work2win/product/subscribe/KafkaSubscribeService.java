package com.work2win.product.subscribe;

import java.util.ArrayList;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSubscribeService {	


	@KafkaListener(topics = "kafkaDemoTopic", groupId = "kafkaDemoTopicGroupId")
	public void receiveMessage(ArrayList<String> receivedMessage){
		
		System.out.println("Message received:" + receivedMessage);
	}
}
