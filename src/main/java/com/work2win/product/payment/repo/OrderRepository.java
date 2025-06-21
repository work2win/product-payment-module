package com.work2win.product.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work2win.product.payment.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
