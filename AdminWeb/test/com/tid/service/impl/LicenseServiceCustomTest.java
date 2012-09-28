package com.tid.service.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LicenseServiceCustomTest {

	static final String SERVER = "http://leprechaunvm:8080/axistest";
	static final String USER = "sergio";
	static final String PASSWORD = "sergio";
	
	static final int CENTER_ID = 62;
	static final int SERVICE_ID = 33;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testLicenseService() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);
	}

	@Test
	public final void testGetEnabledLicensesCenter() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);
		int result = service.getEnabledLicensesCenter(CENTER_ID);
		result = service.getEnabledLicensesCenter(-1);

	}

	@Test
	public final void testGetUsedLicensesCenter() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);		
		int result = service.getUsedLicensesCenter(CENTER_ID);
	}

	@Test
	public final void testGetUsedLicensesService() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);		
		int result = service.getUsedLicensesService(SERVICE_ID);
	}

	@Test
	public final void testGetEnabledLicensesService() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);		
		int result = service.getEnabledLicensesService(CENTER_ID, SERVICE_ID);
		result = service.getEnabledLicensesService(CENTER_ID, -1);
	}

	@Test
	public final void testGetLicenses() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);
		assertTrue(service.getLicenses()>0);
	}

	@Test
	public final void testGetUsedLicenses() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);
		assertTrue(service.getUsedLicenses()>0);
	}

	@Test
	public final void testSetLicenses() throws Exception {
		LicenseService service = new LicenseService(SERVER, USER, PASSWORD);		
		assertNotNull(service);
		int currentLicenses = service.getLicenses();
		service.setLicenses(currentLicenses+10);
		assertTrue(service.getLicenses() == currentLicenses+10);
		service.setLicenses(currentLicenses);
	}

}
