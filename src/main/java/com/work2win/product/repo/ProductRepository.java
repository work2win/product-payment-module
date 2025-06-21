package com.work2win.product.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work2win.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
