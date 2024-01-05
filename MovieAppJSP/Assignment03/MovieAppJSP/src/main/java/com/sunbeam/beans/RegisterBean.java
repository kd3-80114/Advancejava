package com.sunbeam.beans;

import com.sunbeam.daos.UsersDao;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;

public class RegisterBean {
	private String fname;
	private String lname;
	private String email;
	private String passwd;
	private String mobile;
	private String birth;
	private boolean status;

	public RegisterBean() {
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void registration() {
		Users users = new Users(fname, lname, email, passwd, mobile, DateUtil.parse(birth));
		try (UsersDao dao = new UsersDao()) {
			int cnt = dao.save(users);
			if (cnt == 1) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
