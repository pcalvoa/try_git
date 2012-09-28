package com.tid.service.impl.sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServiceSqlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetSearchQueryString() {
		String query = ServiceSql.getSearchQuery("valor");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetSearchQueryStringString() {
		String query = ServiceSql.getSearchQuery("1", "valor");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetCount() {
		String query = ServiceSql.getCount("1");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

}
