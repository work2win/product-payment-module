package com.work2win.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work2win.product.model.Product;
import com.work2win.product.publish.KafkaPublishService;
import com.work2win.product.repo.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private final ProductRepository repository;
	
	@Autowired
	KafkaPublishService service;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = repository.findAll();
		service.userInfo(products);
		return products;
	}	

}
