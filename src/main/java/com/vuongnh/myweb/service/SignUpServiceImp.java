package com.vuongnh.myweb.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vuongnh.myweb.entity.Access;
import com.vuongnh.myweb.entity.Member;
import com.vuongnh.myweb.repository.AccessRepository;
import com.vuongnh.myweb.repository.MemberRepository;

@Service
public class SignUpServiceImp implements SignUpService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AccessRepository accessRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean isSignUp(Member member) {
		if(memberRepository.findByEmail(member.getEmail()) != null) {
			return false;
		}
		return true;
	}

	@Override
	public void save(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		Set<Access> accesses = new HashSet<>();
		accesses.add(accessRepository.findByName("ROLE_MEMBER"));
		member.setAccesses(accesses);
		memberRepository.save(member);
		
	}

}
