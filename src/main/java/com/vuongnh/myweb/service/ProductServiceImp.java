package com.vuongnh.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuongnh.myweb.entity.Product;
import com.vuongnh.myweb.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
		
	}

	@Override
	public Product findOne(Long pid) {
		return productRepository.findOne(pid);
		
	}

	@Override
	public void delete(Long pid) {
		productRepository.delete(pid);
		
	}

	@Override
	public List<Product> search(String pname) {
		return productRepository.findByPnameContaining(pname);
	
	}

}
