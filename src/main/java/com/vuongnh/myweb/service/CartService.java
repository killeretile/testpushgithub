package com.vuongnh.myweb.service;

import java.util.List;

import com.vuongnh.myweb.entity.Item;

public interface CartService {

	int isExists(Long pid, List<Item> cart);
	
	float tinhtong(List<Item> cart);
	
	void dropFromCart(Long pid, List<Item> cart);
}
