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

import com.tid.pojo.Client;

public class ClientDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetConfigFile() {
		String configFile = ClientDao.getConfigFile();
		assertNotNull(configFile);	
	}
	
	@Test
	public final void testClientDaoString() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		ClientDao dao = new ClientDao(temp.getAbsolutePath());
						
		assertNotNull(dao);
	}

	@Test
	public final void testClientDaoStringStringStringString() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
								
		ClientDao dao = new ClientDao(temp.getAbsolutePath(), "server", "user", "password");
		assertNotNull(dao);
	}
	
	@Test
	public final void testGetClient() throws IOException {
	    // Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		ClientDao dao = new ClientDao(temp.getAbsolutePath());				
		Client client = dao.getClient("testname");
		assertNotNull(client);
		assertEquals("description", client.getDescription());
	}

	@Test
	public final void testSetClient() throws IOException {		
	    // Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		
   
   		out.write(buffer.toString());
	    out.close();
		
		
		Client client = new Client();
		client.setName("testname");
		client.setDescription("changed");
		
		ClientDao dao = new ClientDao(temp.getAbsolutePath());
		dao.setClient(client);				
	}
	
	@Test
	public final void testGetClients() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		ClientDao dao = new ClientDao(temp.getAbsolutePath());
		List<Client> clients = dao.getClients();
		assertNotNull(clients);
		assertTrue(clients.size()==1);				
	}

	

	@Test
	public final void testCreateClient() throws IOException {
		// Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		ClientDao dao = new ClientDao(temp.getAbsolutePath());
		assertNotNull(dao);
		
		Client client = new Client();
		client.setName("testname");
								
		//empty method returns allways 0
		assertEquals(0, dao.createClient(client));	
	}

	@Test
	public final void testDeleteClient() throws IOException {
		Client client = new Client();
		client.setName("testname");
		
		// Create temp file.
	    File temp = File.createTempFile("client-config", ".xml");

	    // Delete temp file when program exits.
	    temp.deleteOnExit();

	    // Write to temp file
	    BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	    StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
	    buffer.append("<client-config><client>");
	    buffer.append("<name>testname</name>");
	    buffer.append("<description>description</description>");
	    buffer.append("<url>server</url>");
	    buffer.append("<user>user</user>");
	    buffer.append("<password>password</password>");    
	    buffer.append("<ssl>false</ssl>");
	    buffer.append("</client></client-config>");
   		   
   		out.write(buffer.toString());
	    out.close();
						
		ClientDao dao = new ClientDao(temp.getAbsolutePath());
		assertNotNull(dao);
		
		//empty method returns allways 0
		assertEquals(0, dao.deleteClient(client));	
	}

}
