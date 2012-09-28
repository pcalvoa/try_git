package com.tid.pojo;

/**
 * Implements current user's info
 * 
 * @author fdelatorre
 *
 */
public class ApplicationUser {
	
	public static final String ACTIVATED_STATE = "on";
	public static final String DEACTIVATED_STATE = "off";

	private String userName;
	private String password;	
	private String role;
	private String mail;
	private String state;
	private String code;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
			
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
