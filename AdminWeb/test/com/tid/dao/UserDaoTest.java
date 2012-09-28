package com.tid.dao;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tid.pojo.ApplicationUser;

public class UserDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testUserDaoString() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		UserDao dao = new UserDao(temp.getAbsolutePath());
						
		assertNotNull(dao);
	}

	@Test
	public final void testUserDaoStringStringStringString() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		UserDao dao = new UserDao(temp.getAbsolutePath(),"server", "user", "password");
		
		assertNotNull(dao);
	}

	@Test
	public final void testGetUsers() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
	    UserDao dao = new UserDao(temp.getAbsolutePath());
	   
	    List<ApplicationUser> users = dao.getUsers();
		assertNotNull(users);
		assertTrue(users.size()==1);	
	    	    	  
	}

	@Test
	public final void testGetUser() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
	    UserDao dao = new UserDao(temp.getAbsolutePath());				
		ApplicationUser user = dao.getUser("user");	    	   
		assertNotNull(user);
		assertEquals("admin", user.getRole());
	}

	@Test
	public final void testSetUser() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
	    UserDao dao = new UserDao(temp.getAbsolutePath());				
		ApplicationUser user = dao.getUser("user");	    	   
		assertNotNull(user);
		user.setPassword("modified");		
		dao.setUser(user);
		assertEquals("modified", dao.getUser("user").getPassword());
	}

	@Test
	public final void testGetUserFromMail() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
	    UserDao dao = new UserDao(temp.getAbsolutePath());				
		ApplicationUser user = dao.getUserFromMail("test@mail.com");	    	   
		assertNotNull(user);
		assertEquals("admin", user.getRole());
	}

	@Test
	public final void testGetUserFromCode() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("user-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<user-config><user>");
	    buffer.append("<name>user</name>");
	    buffer.append("<password>user</password>");
	    buffer.append("<role>admin</role>");
	    buffer.append("<mail>test@mail.com</mail>");
	    buffer.append("<state>on</state>");    
	    buffer.append("<code>0</code>");
	    buffer.append("</user></user-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
	    UserDao dao = new UserDao(temp.getAbsolutePath());				
		ApplicationUser user = dao.getUserFromCode("0");	    	   
		assertNotNull(user);
		assertEquals("admin", user.getRole());
	}

	@Test
	public final void testGetConfigFile() {
		String configFile = UserDao.getConfigFile();
		assertNotNull(configFile);	
	}

}
