package com.vuongnh.myweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pid", nullable=false)
	private Long pid;
	
	@NotEmpty
	@Column(name="pname", nullable=false)
	private String pname;
	
	@NotEmpty
	@Column(name="image", nullable=false)
	private String image;
	
	@NotNull
	@Min(value=1)
	@Column(name="price", nullable=false)
	private Double price;
	
	@NotNull
	@Min(value=1)
	@Column(name="quantity", nullable=false)
	private int quantity;
	
	
	@Column(name="discount")
	private int discount;
	
	@NotEmpty
	@Column(name="tyle", nullable=false)
	private String tyle;
	
	@Column(name="descrip")
	private String descrip;
	
	@Column(name="add_product_date", nullable=false, columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date upLoadDate;
	
	public Product() {
	}


	public Product(String pname, String image, Double price, int discount,
			String descrip, String tyle, int quantity,Date upLoadDate) {
		super();
		this.pname = pname;
		this.image = image;
		this.price = price;
		this.discount = discount;
		this.descrip = descrip;
		this.tyle = tyle;
		this.quantity=quantity;
		this.upLoadDate=upLoadDate;
	}



	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public String getTyle() {
		return tyle;
	}


	public void setTyle(String tyle) {
		this.tyle = tyle;
	}


	public String getDescrip() {
		return descrip;
	}


	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}


	public Date getUpLoadDate() {
		return upLoadDate;
	}


	public void setUpLoadDate(Date upLoadDate) {
		this.upLoadDate = upLoadDate;
	}

	
	
	
}
