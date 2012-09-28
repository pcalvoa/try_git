package com.tid.thread;

import java.util.Calendar;
import java.util.HashMap;

import com.tid.Constants;
import com.tid.atica.htc.core.util.encriptacion.MD5;
import com.tid.dao.MailDao;
import com.tid.dao.UserDao;
import com.tid.pojo.ApplicationUser;
import com.tid.util.SMTPMail;

/**
 * Desactive a user and send a new password
 * @author fdelatorre
 *
 */
public class PasswordRenew extends Thread {

	public String email;
	public String url;
	
	public PasswordRenew(String email, String url) {
		this.email = email;
		this.url = url;
		//start();
	}
	
	@Override
	public void run() {
		//Search email in users list
		UserDao dao = new UserDao(UserDao.getConfigFile());
		
		ApplicationUser user = dao.getUserFromMail(email);
		
		if (user!=null) {			
			//Generate activation code			
			String code= MD5.digest(String.valueOf(Calendar.getInstance().getTimeInMillis()));
			user.setCode(code);
			//Generate new password with 8 characters
			String password = MD5.digest(user.getPassword() + String.valueOf(Calendar.getInstance().getTimeInMillis())).substring(0, 8);
			user.setPassword(password);			
			//Deactivated user
			user.setState(ApplicationUser.DEACTIVATED_STATE);
			//Send new password
			MailDao mailDao = new MailDao();
			HashMap mailConfig = mailDao.getMail(MailDao.getConfigFile());
			SMTPMail mail = new SMTPMail(user.getMail(), 
								(String)mailConfig.get(Constants.MAIL_FROM), 
								(String)mailConfig.get(Constants.MAIL_HOST), 
								(String)mailConfig.get(Constants.MAIL_USER), 
								(String)mailConfig.get(Constants.MAIL_PASSWORD));
			
			mail.setSubject("Side by Side - Admin portal Notification");
			mail.setContent("It has been automatically generated an activation code: " + password + "\n\n" + 
					"Please click the following link to activate your username and password validation " + 
					url + code);
			
			mail.send();
						
			//Save user
			dao.setUser(user);
			
		}
		
	}

}
