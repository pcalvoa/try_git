package com.tid.service.impl;

import java.util.Calendar;

import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;

import junit.framework.TestCase;

public class CenterServiceCustomTest extends TestCase {

	static final String SERVER = "http://leprechaunvm:8080/axistest";
	static final String USER = "sergio";
	static final String PASSWORD = "sergio";
	static final String searchQuery ="centro";
	
		
	private CenterService service;	
	
	private int centerId = 0;
		
	@Override
	protected void setUp() throws Exception {
		service = null;
		
		try {
			service = new CenterService(SERVER, USER, PASSWORD);
		} catch (Exception e) {
			this.fail("Error on service creation");
		}
		
		
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		service = null;
	}
	
	
	public void testCenter() {
						
		CentroVO center = new CentroVO();		
		center.setNombre("Test Center " + String.valueOf(Calendar.getInstance().getTimeInMillis()));
		center.setDescripcion("Test Description");
		
		this.assertNotNull(center);
		
		//Create
		int result = 0;
		
		try {
			result = service.create(center);			
			
		} catch (Exception e) {
			this.fail("Error on center creation");
		}
		
		this.assertTrue(result>0);
		
		center.setIdCentro(result);
							
		//Update
		result = 0;
		
		center.setNombre("Test Center updated" + String.valueOf(Calendar.getInstance().getTimeInMillis()));
		center.setDescripcion("Test Description updated");
		
		try {
			result = service.update(center);
		} catch (Exception e) {
			this.fail("Error on center update");
		}
		
		this.assertTrue(result>0);
		
		//Delete
		try {
			service.delete(center);
		} catch (Exception e) {
			this.fail("Error on center delete");
		}
		
	}
			
	public void testSearch() {
		
		Object[] centers = null;
		try {
			centers = service.search(searchQuery);
		} catch (Exception e) {
			this.fail("Error on center search");
		}
						
		this.assertNotNull(centers);
		
	}
			
	public void testGetCenters() {
				 			
		Object[] centers= service.getCenters();
		
		this.assertNotNull(centers);	
				
	}			
	
	/*
	public void testSearchCount() {
		
		int results = 0;
		try {
			results = service.getServicesCount(String.valueOf(IdCenter));
		} catch (Exception e) {
			this.fail("Error on services' search");
		}
		
		this.assertTrue(results>0);
		
	}
	*/

}
