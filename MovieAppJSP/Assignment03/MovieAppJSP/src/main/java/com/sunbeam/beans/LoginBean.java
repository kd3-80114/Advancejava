package com.sunbeam.beans;

import com.sunbeam.daos.UsersDao;
import com.sunbeam.pojos.Users;

public class LoginBean {
	private String email;
	private String passwd;
	private Users users;
	private boolean status;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public LoginBean() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void authenticate() {
		try (UsersDao dao = new UsersDao()) {
			Users u = dao.findByEmail(email);
			if (u != null && u.getPassword().equals(passwd)) {
				this.users = u;
				this.status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
