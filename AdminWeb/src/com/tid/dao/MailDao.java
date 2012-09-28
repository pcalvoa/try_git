package com.tid.dao;

import java.net.URL;
import java.util.HashMap;

import com.tid.util.MailParser;

public class MailDao {
	
	public HashMap getMail(String configFile) {					
		return MailParser.parse(configFile);		
	}
	
	public static String getConfigFile() {
	   String configFile = "";
	   URL classURL = MailDao.class.getProtectionDomain().getCodeSource().getLocation();	   
	   configFile = classURL.getPath();
	   //Extract the class name from the path
	   configFile = configFile.substring(0, configFile.length()- MailDao.class.getName().length()-15);
	   configFile += "/" + "mail-config.xml";
		   
	   return configFile;
		   
	 }

}
