package com.work2win.product.payment.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.work2win.product.payment.model.Orders;
import com.work2win.product.payment.model.PaymentTransaction;
import com.work2win.product.payment.repo.OrderRepository;
import com.work2win.product.payment.repo.PaymentTransactionRepository;
import com.work2win.product.publish.KafkaPublishService;

import jakarta.transaction.Transactional; // Use jakarta.transaction for Hibernate 6.2

@Service
public class PaymentService {

	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;
    
    @Autowired
	KafkaPublishService service;
    
    @Transactional // Ensures atomicity for payment processing
    public ResponseEntity<PaymentTransaction> processPayment(int id, double amount, boolean paymentSuccess) { 
    	
    	 PaymentTransaction transaction = new PaymentTransaction();
    	 LocalDateTime currentDateTime = LocalDateTime.now();
    	 String message = "Order completed:";

    		Orders order = orderRepository.findById(id).orElse(null);  	
    		if(order == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    			
    		else {

           	 		transaction.setOrder(order);
	                transaction.setAmount(amount);
	
	                if (paymentSuccess) {
	                    order.setStatus("COMPLETED");
	                    transaction.setStatus("SUCCESS");
	                    
	                } else {
	                    order.setStatus("FAILED");
	                    transaction.setStatus("FAILURE");
	                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	                    
	                }       
            	
                
                orderRepository.save(order);       
                paymentTransactionRepository.payment(amount, transaction.getStatus(), id);
                message = message + " OrderID: " +id+ " with Payment Status " + transaction.getStatus() + " in " + currentDateTime;
                service.payment(message);

            	return new ResponseEntity<>(transaction, HttpStatus.OK);
    			
    		}
           
           
    }
        
}
