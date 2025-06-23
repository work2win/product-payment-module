package com.work2win.product.payment.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.work2win.product.payment.repo.OrderRepository;
import com.work2win.product.payment.model.PaymentTransaction;
import com.work2win.product.payment.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private PaymentService paymentService;
	
	  @PostMapping("/process")
	  public ResponseEntity<String> processPayment(@RequestParam int id, @RequestParam double amount, @RequestParam(value="paymentSuccess") boolean paymentSuccess) {
	    
		  ResponseEntity<PaymentTransaction> transaction = paymentService.processPayment(id, amount, paymentSuccess);
		  
		  if(transaction.getStatusCode().value() == 200)
			  return ResponseEntity.status(HttpStatus.ACCEPTED).body("Payment succesful");
		  else
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment failure");		 
	
	  }
	  
	
}
