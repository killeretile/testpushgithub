package com.vuongnh.myweb.service;

import com.vuongnh.myweb.entity.Member;

public interface SignUpService {

	boolean isSignUp(Member member);
	
	void save(Member member);
}
