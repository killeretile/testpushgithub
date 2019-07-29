package com.vuongnh.myweb.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vuongnh.myweb.entity.Access;
import com.vuongnh.myweb.entity.Member;
import com.vuongnh.myweb.repository.AccessRepository;
import com.vuongnh.myweb.repository.MemberRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AccessRepository accessRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(accessRepository.findByName("ROLE_ADMIN")==null) {
			accessRepository.save(new Access("ROLE_ADMIN"));
		}
		
		if(accessRepository.findByName("ROLE_MEMBER")==null) {
			accessRepository.save(new Access("ROLE_MEMBER"));
		}
		
		if(memberRepository.findByEmail("member@gmail.com")==null) {
			Member member = new Member();
			member.setEmail("member@gmail.com");
			member.setPassword(passwordEncoder.encode("123456"));
			member.setName("member");
			member.setAddress("luong tai,bac ninh");
			member.setPhone("92631532");
			Set<Access> access = new HashSet<>();
			access.add(accessRepository.findByName("ROLE_MEMBER"));
			member.setAccesses(access);
			memberRepository.save(member);
		}
		if(memberRepository.findByEmail("admin@gmail.com")==null) {
			Member admin = new Member();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("654321"));
			admin.setName("admin");
			admin.setAddress("luong tai,bac ninh");
			admin.setPhone("12132432");
			Set<Access> access = new HashSet<>();
			access.add(accessRepository.findByName("ROLE_ADMIN"));
			access.add(accessRepository.findByName("ROLE_MEMBER"));
			admin.setAccesses(access);
			memberRepository.save(admin);
		}
	}

	
}
