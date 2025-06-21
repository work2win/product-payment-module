package com.work2win.product.payment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {
	
	@Id
    @GeneratedValue
    private int id;
	
	private String status; // e.g., "PENDING", "COMPLETED", "FAILED"	
	
	public Orders() {}
	
    public Orders(int id, String status) {
		
		this.id = id;
		this.status = status;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

}
