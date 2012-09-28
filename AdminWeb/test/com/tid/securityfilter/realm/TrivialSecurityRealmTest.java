package com.tid.securityfilter.realm;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrivialSecurityRealmTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testBooleanAuthenticateStringString() throws IOException {
		
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
		
		TrivialSecurityRealm sec = new TrivialSecurityRealm();
		sec.setUsersConfig(temp.getAbsolutePath());
		assertTrue(sec.booleanAuthenticate("user", "user"));
	}

	@Test
	public final void testIsUserInRoleStringString() throws IOException {
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
		
		TrivialSecurityRealm sec = new TrivialSecurityRealm();
		sec.setUsersConfig(temp.getAbsolutePath());
		assertTrue(sec.isUserInRole("user", "admin"));
	}

}
