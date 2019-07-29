package com.vuongnh.myweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vuongnh.myweb.entity.Item;

@Service
public class CartServiceImp implements CartService{

	@Override
	public int isExists(Long pid, List<Item> cart) {
		for(int i=0; i<cart.size(); i++) {
			if(cart.get(i).getProduct().getPid().equals(pid)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public float tinhtong(List<Item> cart) {
		float tong=0;
		for(int i=0; i<cart.size();i++) {
			 tong = (float) (tong + cart.get(i).getIquantity() * cart.get(i).getProduct().getPrice());
		}
		return tong;
	}

	@Override
	public void dropFromCart(Long pid, List<Item> cart) {
		int i = this.isExists(pid, cart);
		cart.remove(i);
	}

}
