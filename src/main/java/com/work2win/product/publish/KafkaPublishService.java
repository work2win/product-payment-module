package com.work2win.product.publish;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.work2win.product.model.Product;

@Service
public class KafkaPublishService {
	
	@Autowired
	KafkaTemplate<String, ArrayList<String>> kafkaTemplateUser;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	public void userInfo(List<Product> products){
		
		ArrayList<String> message = new ArrayList<String>();
		for(Product product: products) {			
			message.add(product.getName());			
		}		
			
		try {
			//Spring Kafka will automatically serialize the object into JSON using the configured JsonSerializer
			kafkaTemplateUser.send("kafkaDemoTopic", message);
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
	
	public void payment(String message){			
			
		try {
			
			//Spring Kafka will automatically serialize the object into JSON using the configured JsonSerializer
			kafkaTemplate.send("kafkaDemoTopic", message);
		}catch(Exception e) {e.printStackTrace();}
		
	}
		

	@KafkaListener(topics = "kafkaDemoTopic", groupId = "kafkaDemoTopicGroupId")
	public void receiveMessage(ArrayList<String> receivedMessage){
		
		System.out.println("Message received: " + receivedMessage);
	}

}
