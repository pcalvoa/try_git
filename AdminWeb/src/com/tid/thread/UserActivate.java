package com.tid.thread;

import com.tid.dao.UserDao;
import com.tid.pojo.ApplicationUser;

/**
 * User activation
 * 
 * @author fdelatorre
 *
 */
public class UserActivate extends Thread {

	public String email;
	public String url;
	
	public String code;
	public String oldPassword;
	public String password;
	
	public UserActivate(String code, String oldPassword, String password) {
		this.code = code;
		this.oldPassword = oldPassword;
		this.password = password;
		//start();
	}
	
	@Override
	public void run() {
		//Search email in users list
		UserDao dao = new UserDao(UserDao.getConfigFile());
		
		ApplicationUser user = dao.getUserFromCode(code);
		
		if (user!=null && user.getPassword().equals(oldPassword)) {
			user.setPassword(password);
			user.setState(ApplicationUser.ACTIVATED_STATE);
			dao.setUser(user);
		}
		
	}

}
