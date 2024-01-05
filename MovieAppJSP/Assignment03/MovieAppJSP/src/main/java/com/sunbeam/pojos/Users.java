package com.sunbeam.pojos;

import java.util.Date;

public class Users {
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String mobile;
	private Date date;
	
	public Users() {
	}
	
	public Users(String first_name, String last_name, String email, String password, String mobile, Date date) {
		
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", date=" + date + "]";
	}
	
	
}
