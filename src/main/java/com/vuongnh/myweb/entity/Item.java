package com.vuongnh.myweb.entity;

public class Item {

	private Product product;
	private int Iquantity;
	
	public Item() {}
	
	public Item(Product product, int Iquantity) {
		this.product=product;
		this.Iquantity=Iquantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getIquantity() {
		return Iquantity;
	}

	public void setIquantity(int iquantity) {
		Iquantity = iquantity;
	}
	
	
}
