package com.tid.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;
import com.tid.Errors;
import com.tid.pojo.Licenses;
import com.tid.service.impl.LicenseService;



public class LicensesServlet extends BaseServlet {

	private static final Logger LOG = Logger.getLogger(LicensesServlet.class.getName());
	
	private LicenseService service = null;
	
	@Override
	public void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
		
		super.doGet(request, response);
		
		try {
			initService(request);
		} catch (Exception e) {
			//throw new ServletException(e.getMessage());
			response.sendError(-1, "Error on conection " + e.getMessage());
			return;
		}					
		
		String errorMsg = "";
		int result = -1;
			
		Licenses licenses = new Licenses();
		
		try {						
			
			licenses.setTotalLicenses(service.getLicenses());
			//licenses.setUsedLicenses(service.getUsedLicenses());
			
			result = licenses.getTotalLicenses();
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();			
		}			
				
		if (result != -1) {			
			//formated response			
			Licenses[] values = {licenses};		
			responseFormated(response, values, Licenses.class);
		
		} else {
			response.sendError(result, "Licenses get error " + errorMsg);			 			 
	    }		
									
	}			
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		super.doPost(request, response);
		
		int result = -1;
		
		try {
			initService(request);
		} catch (Exception e) {
			//throw new ServletException(e.getMessage());
			LOG.error("ERROR updating licenses....", e);											
			response.setStatus(Errors.GENERIC_ERROR);
			return;
		}
					
		try {
			int licenses = Integer.parseInt(request.getParameter(Constants.PARAM_LICENSES));
			result = service.setLicenses(licenses);
			if (result >= 0) {
				//Default response
				response.setContentType(Constants.TEXT_CONTENT_TYPE);
				PrintWriter out = response.getWriter();
				out.println(result);
				out.flush();
				out.close();
			}
			else if (result == Errors.MIN_LICENSES) {
				LOG.error("Invalid number of licenses");
				response.setStatus(Errors.MIN_LICENSES_ERROR);
			}	
			else {
				//response.sendError(result, "Center creation error " + errorMsg);
				LOG.error("ERROR creating Center");	
				response.setStatus(Errors.GENERIC_ERROR);
		    }
								
		}
		catch(Exception e) {
			LOG.error("ERROR updating licenses....", e);											
			response.setStatus(Errors.GENERIC_ERROR);
		}
						
	}
	
	/**
	 * Init configuration
	 * 
	 * @throws Exception
	 */
	private void initService(HttpServletRequest request) throws Exception {
		javax.servlet.http.HttpSession session = request.getSession();
				
		boolean updated = false; 
		
		try {
			updated = (Boolean)session.getAttribute(Constants.CLIENT_UPDATED_LICENSE);
		}
		catch(Exception e) {}
		
		if (service==null || updated) {
		
			service = new LicenseService(clientInfo.getUrl(), 
				clientInfo.getUser(), 
				clientInfo.getPassword());
			
			session.setAttribute(Constants.CLIENT_UPDATED_LICENSE, false);
		}
	}
	
	
	
	
	
}
