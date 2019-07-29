package com.vuongnh.myweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.vuongnh.myweb.entity.Access;

public interface AccessRepository extends CrudRepository<Access, Integer>{

	Access findByName(String name);
}
