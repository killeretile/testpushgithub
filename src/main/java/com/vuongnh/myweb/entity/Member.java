package com.vuongnh.myweb.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="member")
public class Member implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private Long id;
	
	@NotEmpty
	@Column(name="name", nullable=false)
	private String name;
	
	@Email
	@Column(name="email", nullable=false)
	private String email;
	
	@Size(min=8,max=60)
	@Column(name="password", nullable=false)
	private String password;
	
	@Pattern(regexp="(\\+84|0)[1-9]{9}")
	@Column(name="phone", nullable=false)
	private String phone;
	
	@NotEmpty
	@Column(name="address", nullable=false)
	private String address;
	
	@ManyToMany
	@JoinTable(
				name="member_access",
				joinColumns=@JoinColumn(name="member_id"),
				inverseJoinColumns=@JoinColumn(name="access_id")
			)
	private Set<Access> accesses;

	public Member() {}

	public Member(String name, String email, String password, String phone, String address, Set<Access> accesses) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.accesses = accesses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Access> getAccesses() {
		return accesses;
	}

	public void setAccesses(Set<Access> accesses) {
		this.accesses = accesses;
	}
	
	
}
