package com.tid.service.impl.sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserSqlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetEntity() {
		String query = UserSql.getEntity();
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetSearchQueryString() {
		String query = UserSql.getSearchQuery("query");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetSearchCode() {
		String query = UserSql.getSearchCode("code");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetSearchEmail() {
		String query = UserSql.getSearchEmail("test@mail.com");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetSearchQueryStringStringString() {
		String query = UserSql.getSearchQuery("centerId", "serviceId", "query");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

	@Test
	public final void testGetCount() {
		String query = UserSql.getCount("serviceId");
		assertNotNull(query);
		assertFalse(query.isEmpty());
	}

}
