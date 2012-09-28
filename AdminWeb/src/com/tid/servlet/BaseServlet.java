package com.tid.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tid.Constants;
import com.tid.pojo.Client;
import com.tid.util.Formatter;

/**
 * Process parameters and configuration for child servlets
 * 
 * @author fdelatorre
 *
 */

public class BaseServlet extends HttpServlet {

	public Client clientInfo = null;
	
	/**
	 * Process the HTTP doGet request.
	*/
	public void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
		
		initialization(request, response);
	}
	
	/**
	 * Process the HTTP doPost request.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		initialization(request, response);	
			
	}
	
	/**
	 * Process the HTTP doDelete request.
	*/
	public void doDelete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		initialization(request, response);	
			
	}
	
	
	/**
	 * Generate output in JSON format
	 * @param response
	 * @param values
	 * @throws IOException 
	 */
	protected void responseFormated(HttpServletResponse response, Object[] values, Class type) throws IOException {
			
		//Generate formated results
		String jsonObjectHead = "{\"data\":[";									
		String jsonObjectFoot = "]}";
        StringBuilder jsonObjectData = new StringBuilder();
        
        int count = 0;
        for (Object value:values) {               	
        	jsonObjectData.append(Formatter.toJSON(value, type, count)).append(",");      
        	count++;
        }
        
        String jsonObject = jsonObjectHead;
        
        if (!"".equals(jsonObjectData.toString())) {        
        	jsonObject += jsonObjectData.substring(0, jsonObjectData.length()-1).replace("\r\n", "").replace("\n", ""); 
        }

       	jsonObject += jsonObjectFoot;

		
		//Generate response
		//response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json;charset=UTF-8");
        //response.setContentType("application/x-json;charset=UTF-8");
        // Get the printwriter object from response       
        PrintWriter out = response.getWriter();                         
        out.print(jsonObject);
        out.flush();
      	  
	}			
	
	/**
	 * Init configuration
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void initialization(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = request.getSession();
									
		//Get config information					    
	    List<Client> clientsCache = (List<Client>)this.getServletContext().getAttribute(Constants.CLIENTS_CACHE);
	    String currentClient = (String)session.getAttribute(Constants.CURRENT_CLIENT);
	   
	    
 	    //Get information for current client
	    for (Client client:clientsCache) {
	    	if (client.getName().equals(currentClient)) {
	    		clientInfo = client;
	    		break;
	    	}
	    }

	}		
	
}
