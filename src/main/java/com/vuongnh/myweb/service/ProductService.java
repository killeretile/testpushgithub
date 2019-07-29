package com.vuongnh.myweb.service;

import java.util.List;

import com.vuongnh.myweb.entity.Product;

public interface ProductService {

	Iterable<Product> findAll();
	
	void save(Product product);
	
	Product findOne(Long pid);
	
	void delete(Long pid);
	
	List<Product> search(String pname);
}
