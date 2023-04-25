package com.nkr4m.userService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String emailId;
	private String name;
	private String password;
	private Double phoneNo;
	private String profession;
	private String org;
	
//	auto-gen
	private LocalDate created;
	private LocalDateTime lastLogin;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Double getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(Double phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public LocalDate getCreated() {
		return created;
	}


	public void setCreated(LocalDate created) {
		this.created = created;
	}


	public LocalDateTime getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getOrg() {
		return org;
	}


	public void setOrg(String org) {
		this.org = org;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	



	
	
	

}
