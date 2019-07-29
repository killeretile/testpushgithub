package com.vuongnh.myweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.vuongnh.myweb.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

	Member findByEmail(String email);
}
