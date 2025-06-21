package com.work2win.product.payment.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.work2win.product.payment.model.PaymentTransaction;

import jakarta.transaction.Transactional;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Integer>{
	
	
	@Transactional
	@Modifying
	@Query(value = "update payment_transactions set amount = amount + :amount, status = :status where id = :id", nativeQuery = true)
	int payment(double amount, String status, int id);

}
