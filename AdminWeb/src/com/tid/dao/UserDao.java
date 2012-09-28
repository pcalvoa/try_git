package com.tid.dao;

import java.net.URL;
import java.util.List;

import com.tid.pojo.ApplicationUser;
import com.tid.util.UserParser;

public class UserDao {

	String file;
	String server;
	String user;
	String password;
			
	public UserDao(String usersFile) {
		
		//Init users' file
		this.file = usersFile;	
	}
	
	public UserDao(String usersFile, String server, String user, String password) {
		
		//Get users' file from web system
		this.file = usersFile;
		
		//TODO Connect with db server
		this.server = server;
		this.user = user;
		this.password = password;
		
	}
	
	public List<ApplicationUser> getUsers() {
					
		List<ApplicationUser> users = UserParser.parse(file);
		
		return users;
	}
	
	public ApplicationUser getUser(String userName) {
		
		ApplicationUser user = null;
		
		List<ApplicationUser> users = getUsers();
		
		for (ApplicationUser usr:users) {
			if (usr.getUserName().equals(userName)) {
				user = usr;
				break;
			}
		}
		
		return user;
		
	}
	
	public void setUser(ApplicationUser user) {
		
		UserParser.update(file, user);
		
	}	

	public ApplicationUser getUserFromMail(String email) {
		ApplicationUser user = null;
		
		List<ApplicationUser> users = getUsers();
		
		for (ApplicationUser usr:users) {
			if (usr.getMail().equals(email)) {
				user = usr;
				break;
			}
		}
		
		return user;
	}
	
	public ApplicationUser getUserFromCode(String code) {
		ApplicationUser user = null;
		
		List<ApplicationUser> users = getUsers();
		
		for (ApplicationUser usr:users) {
			if (usr.getCode().equals(code)) {
				user = usr;
				break;
			}
		}
		
		return user;
	}
			
	public static String getConfigFile() {
		   String configFile = "";
		   URL classURL = UserDao.class.getProtectionDomain().getCodeSource().getLocation();	   
		   configFile = classURL.getPath();
		   //Extract the lib name from the path
		   configFile = configFile.substring(0, configFile.length()-15);
		   configFile += "/" + "user-config.xml";
		   
		   return configFile;
		   
	   }
	
	
}
