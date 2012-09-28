package com.tid.service.impl;

import java.util.Calendar;

import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;

import junit.framework.TestCase;

public class ServiceServiceCustomTest extends TestCase {

	static final String SERVER = "http://leprechaunvm:8080/axistest";
	static final String USER = "sergio";
	static final String PASSWORD = "sergio";	
	static final String searchQuery ="servicio";
	
	static final int CENTER_ID = 62;
	static final int SERVICE_ID = 33;
	
	private ServiceService service;
	
	@Override
	protected void setUp() throws Exception {
		service = null;
		
		try {
			service = new ServiceService(SERVER, USER, PASSWORD);
		} catch (Exception e) {
			this.fail("Error on service creation");
		}
		
		
		
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		service = null;
	}
	
	
	public void testService() {
						
		CentroVO center = new CentroVO();
		center.setIdCentro(CENTER_ID);
		
		this.assertNotNull(center);
		
		ServicioVO serviceVO = new ServicioVO();		
		serviceVO.setNombre("Test Center" + String.valueOf(Calendar.getInstance().getTimeInMillis()));
		serviceVO.setDescripcion("Test Description");
		serviceVO.setCentro(center);
		
		this.assertNotNull(serviceVO);
		
		
		int result = 0;
		
		try {
			result = service.create(serviceVO);
		} catch (Exception e) {
			this.fail("Error on creation");
		}
		
		this.assertTrue(result>0);
		
		serviceVO.setIdServicio(result);
		
		//Update
		result = 0;
		
		serviceVO.setNombre("Test Service updated" + String.valueOf(Calendar.getInstance().getTimeInMillis()));
		serviceVO.setDescripcion("Test Description updated");
		
		try {
			result = service.update(serviceVO);
		} catch (Exception e) {
			this.fail("Error on service update");
		}
		
		this.assertTrue(result>0);
		
		//Delete
		try {
			service.delete(serviceVO);
		} catch (Exception e) {
			this.fail("Error on service delete");
		}
							
	}
	
	public void testSearch() {
		
		Object[] results = null;
		try {
			results = service.search(searchQuery);
		} catch (Exception e) {
			this.fail("Error on center search");
		}
		
		this.assertNotNull(results);						
		
	}
			
	public void testGetServices() {
		
		CentroVO center = new CentroVO();
		center.setIdCentro(CENTER_ID);
						 							
		this.assertNotNull(service.getServices(center));
				
	}
	
	/*
		
	public void testSearchCount() {
		
		int results = 0;
		try {
			results = service.getUsersCount(String.valueOf(idService));
		} catch (Exception e) {
			this.fail("Error on services' search");
		}
		
		this.assertTrue(results>0);
		
	}
	
	 */
	

			

}
