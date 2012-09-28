package com.tid.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MailDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetMail() {
		MailDao dao = new MailDao();		
		assertNotNull(dao);
		assertNotNull(dao.getConfigFile());
		assertNotNull(dao.getMail(dao.getConfigFile()));
	}

}
