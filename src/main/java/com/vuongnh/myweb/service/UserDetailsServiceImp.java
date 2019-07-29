package com.vuongnh.myweb.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vuongnh.myweb.entity.Access;
import com.vuongnh.myweb.entity.Member;
import com.vuongnh.myweb.repository.MemberRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member= memberRepository.findByEmail(username);
		if(member== null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<Access> accesses = member.getAccesses();
		for(Access access : accesses) {
			grantedAuthorities.add(new SimpleGrantedAuthority(access.getName()));
		}
		return new  org.springframework.security.core.userdetails.User(member.getEmail(),
				member.getPassword(),grantedAuthorities);
	}

}
