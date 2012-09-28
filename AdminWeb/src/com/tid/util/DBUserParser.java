package com.tid.util;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.tid.pojo.ApplicationUser;

public class DBUserParser {

	/**
	 * Parse configuration file
	 * @param usersFile
	 * @return
	 * @throws SQLException 
	 */
	public static List<ApplicationUser> parse(ResultSet rs) throws SQLException {
		
		List<ApplicationUser> users = new ArrayList<ApplicationUser>();
		
		while (rs.next()) {
			
			ApplicationUser user = new ApplicationUser();
			
			user.setUserName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setMail(rs.getString("mail"));
			user.setRole(rs.getString("role"));			
			user.setCode(rs.getString("code"));
			user.setState(rs.getString("state"));
			
			users.add(user);
		}
		
		
		return users;
        
	}	
			
}
