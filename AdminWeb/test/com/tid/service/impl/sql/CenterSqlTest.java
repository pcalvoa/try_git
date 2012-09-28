package com.tid.service.impl.sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CenterSqlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetSearchQuery() {
		String query = CenterSql.getSearchQuery("valor");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetSearchAdministrated() {
		String query = CenterSql.getSearchAdministrated("1");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

}
