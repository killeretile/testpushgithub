package com.vuongnh.myweb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vuongnh.myweb.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	List<Product> findByPnameContaining(String pname);
}
