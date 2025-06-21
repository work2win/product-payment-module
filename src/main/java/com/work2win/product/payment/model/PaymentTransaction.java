package com.work2win.product.payment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentid;

    @ManyToOne
    @JoinColumn(name = "id")
    private Orders order;

    private String status; // e.g., "SUCCESS", "FAILURE"
    private double amount;    

	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	   
    
}
