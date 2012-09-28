package com.tid.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tid.Constants;
import com.tid.dao.ClientDao;
import com.tid.dao.UserDao;
import com.tid.pojo.ApplicationUser;
import com.tid.pojo.Client;

/**
 * Listener for create cache information
 * 
 * TODO: Add listener to capture add/modify/delete cache information
 * 
 * @author fdelatorre
 *
 */
public class ConfigCache implements ServletContextListener  {
	
	private ServletContext context = null;
	
	//Cache for clients information
	List<Client> clients = null;
	List<ApplicationUser> users = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		this.context = event.getServletContext();
		//Remove clients cache
		this.context.removeAttribute(Constants.CLIENTS_CACHE);		
		clients = null;
		//Remove users cache
		this.context.removeAttribute(Constants.USERS_CACHE);		
		users = null;			
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
									
		initClientCache(event);
		initUserCache(event);
		
	}
	
	private void initClientCache(ServletContextEvent event) {
		//Create cache from configuration file
		if (clients ==null) {
			clients = new ArrayList<Client>();
		}
		
		//Init clients from DAO
		ClientDao dao = new ClientDao(ClientDao.getConfigFile());		
		clients = dao.getClients();
		
				
		//Add cache to servletcontext
		this.context = event.getServletContext();		
		this.context.setAttribute(Constants.CLIENTS_CACHE, clients);
		
		if (clients.size()>0) {		
			//Set first element as default client
			this.context.setAttribute(Constants.DEFAULT_CLIENT, clients.get(0).getName());
		}
	}
	
	private void initUserCache(ServletContextEvent event) {
		//Create cache from configuration file
		if (users ==null) {
			users = new ArrayList<ApplicationUser>();
		}
		
		//Init users from DAO
		UserDao dao = new UserDao(UserDao.getConfigFile());		
		users = dao.getUsers();
		
		
		//Add cache to servletcontext
		this.context = event.getServletContext();		
		this.context.setAttribute(Constants.USERS_CACHE, users);
		
	}

}

