package com.tid.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;
import com.tid.dao.ClientDao;
import com.tid.pojo.Client;

public class ClientServlet extends BaseServlet {

	private static final Logger LOG = Logger.getLogger(ClientServlet.class.getName());
		
	@Override
	public void doGet(final HttpServletRequest request,
	        final HttpServletResponse response)
	        throws ServletException, IOException {
		
		super.doGet(request, response);
				
		//Get  query parameter	
		final String query = request.getParameter(Constants.PARAM_QUERY);								
		search(query, response);
							
	}
	
	/**
	 * Process the HTTP doPost request.
	*/
	public void doPost(final HttpServletRequest request, final HttpServletResponse response) 
		throws ServletException, IOException {
		
		super.doPost(request, response);
						
		//Validate received data
		Client value = validateClient(request);
		
		//Get op parameter	
		String operation = request.getParameter(Constants.PARAM_OP);
				
		if (Constants.VALUE_INSERT.equals(operation)) {
			insert(value, response);
		}
		else if (Constants.VALUE_UPDATE.equals(operation)) {
			update(value, response);
		}
		else if (Constants.VALUE_DELETE.equals(operation)) {
			delete(value, response);
		}
		else if (operation!=null){
			response.sendError(-1, "Parameters not valid");
		}		
			
	}
	
	private void search(String query, HttpServletResponse response) 
		throws IOException {

		//Create object		
		int result = 0;
		String errorMsg = "";
		Object[] values = null;
		
		try {
			
			//FIXME Siempre devuelve el valor de la caché
			values = this.getCache("");				
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
					
		if (result != -1 && values != null) {
			responseFormated(response, values, Client.class);
		} else {
			response.sendError(result, "Client search error " + errorMsg);			 			 
	    }		
	}
	
	
	private void insert(Client value, HttpServletResponse response) throws IOException {
		//Create object		
		int result = -1;
		String errorMsg = "";
		
		ClientDao dao = new ClientDao(ClientDao.getConfigFile());
		
		try {
			
			result = dao.createClient(value);
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result == -1) {
			response.sendError(result, "User creation error " + errorMsg);
		} else {			
			//Default response
			response.setContentType(Constants.TEXT_CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close(); 
	    }
		
	}
	
	private void update(Client value, HttpServletResponse response) throws IOException {
				
		int result = -1;
		String errorMsg = "";
		
		ClientDao dao = new ClientDao(ClientDao.getConfigFile());
		
		try {
			result = dao.setClient(value);
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result == -1) {
			response.sendError(result, "Client update error " + errorMsg);
		} else {
			//Default response
			response.setContentType(Constants.TEXT_CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close(); 			 			 
	    }
		
	}
	
	private void delete(Client value, HttpServletResponse response) throws IOException {
		
		int result = 0;
		String errorMsg = "";
		
		ClientDao dao = new ClientDao(ClientDao.getConfigFile());
		
		try {
			result = dao.deleteClient(value);
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result == -1) {
			response.sendError(result, "Client delete error " + errorMsg);
		} else {						
			//Default response
			response.setContentType(Constants.TEXT_CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close(); 
	    }
		
	}
	
	/**
	 * Search in cache
	 * @param query
	 * @return
	 */
	private Object[] getCache(String query) {
						
		List<Client> clientsCache = (List<Client>)this.getServletContext().getAttribute(Constants.CLIENTS_CACHE);
		return clientsCache.toArray();						
				
	}
	
	private void setCache(Object[] values) {
		//TODO: actualizar cache
	}
	
	/**
	 * Validate user request info
	 * @param request
	 * @return
	 */
	private Client validateClient(HttpServletRequest request) {
		//Init parameters
		
		Client client = new Client();			
		
		try {
			if (request.getParameter(Constants.PARAM_NAME)!=null) {
				client.setName(request.getParameter(Constants.PARAM_NAME));
			}
			if (request.getParameter(Constants.PARAM_DESCRIPTION)!=null) {
				client.setDescription(request.getParameter(Constants.PARAM_DESCRIPTION));
			}
			if (request.getParameter(Constants.PARAM_URL)!=null) {
				client.setUrl(request.getParameter(Constants.PARAM_URL));
			}
			if (request.getParameter(Constants.PARAM_USER)!=null) {
				client.setUser(request.getParameter(Constants.PARAM_USER));
			}
			if (request.getParameter(Constants.PARAM_PASSWORD)!=null) {
				client.setPassword(request.getParameter(Constants.PARAM_PASSWORD));
			}

		}
		catch(Exception e) {
			client = null;
		}
					
		return client;
	}
	
}
