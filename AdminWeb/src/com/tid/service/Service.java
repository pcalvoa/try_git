package com.tid.service;

/**
 * Api for basic services
 * 
 * @author fdelatorre
 *
 */
public interface Service {

	public int create(Object value) throws Exception;
	
	public void delete(Object value) throws Exception;
	
	public int update(Object value) throws Exception;
	
	public Object[] search(String query) throws Exception;
	
}
