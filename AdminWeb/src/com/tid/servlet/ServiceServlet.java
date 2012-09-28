package com.tid.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;
import com.tid.Errors;
import com.tid.Fields;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.service.impl.ServiceService;



public class ServiceServlet extends BaseServlet {
	
	private static final Logger LOG = Logger.getLogger(ServiceServlet.class.getName());
	
	private ServiceService service = null;
	private Object[] cache = null;

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
		
		//Get  query parameter	
		String query = request.getParameter(Constants.PARAM_QUERY);
		String centerId = request.getParameter(Fields.CENTER_ID);
		String serviceId = request.getParameter(Fields.SERVICE_ID);
								
		search(query, centerId, serviceId, response);
							
	}
	
	/**
	 * Process the HTTP doPost request.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		super.doPost(request, response);
		
		try {
			initService(request);
		} catch (Exception e) {
			//throw new ServletException(e.getMessage());
			response.sendError(-1, "Error on conection " + e.getMessage());
			return;
		}
		
		//Validate received data
		ServicioVO value = validateService(request);
		
		//Get op parameter	
		String op = request.getParameter(Constants.PARAM_OP);
		
		if (Constants.VALUE_INSERT.equals(op)) {
			insert(value, response);
		}
		else if (Constants.VALUE_UPDATE.equals(op)) {
			update(value, response);
		}
		else if (Constants.VALUE_DELETE.equals(op)) {
			delete(value, response);
		}
		else if (op!=null){
			response.sendError(-1, "Parameters not valid");
		}		
			
	}
	
	private void initService(HttpServletRequest request) throws Exception {
		javax.servlet.http.HttpSession session = request.getSession();
		boolean updated = (Boolean)session.getAttribute(Constants.CLIENT_UPDATED_SERVICE);
		if (service==null || updated) {
			
			service = new ServiceService(clientInfo.getUrl(), 
				clientInfo.getUser(), 
				clientInfo.getPassword());
			
			session.setAttribute(Constants.CLIENT_UPDATED_SERVICE, false);
		}
	}
	
	private void search(String query,  String centerId, String serviceId, HttpServletResponse response) throws IOException {
		//Create object		
		int result = 0;
		String errorMsg = "";
		Object[] values = null;
		
		try {
			if (centerId!=null && serviceId!=null) {
				//First try with cache
				values = getCache(centerId, serviceId);
			}
			
			if (values==null) {			
				values = service.search(centerId, query);
				setCache(values);
			}	
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result != -1 && values != null) {
			responseFormated(response, values, ServicioVO.class);
		} else {
			response.sendError(result, "Service search error " + errorMsg);			 			 
	    }		
	}
	
	
	private void insert(ServicioVO value, HttpServletResponse response) throws IOException {
		//Create object		
		int result = -1;
		
		try {
			result = service.create(value);
			
			if (result >= 0) {
				//Default response
				response.setContentType(Constants.TEXT_CONTENT_TYPE);
				PrintWriter out = response.getWriter();
				out.println(result);
				out.flush();
				out.close(); 
			} 
			else if (result == Errors.MAX_LICENSES) {
				LOG.error("Exceeded the number of licenses available");
				response.setStatus(Errors.MAX_LICENSES_ERROR);
			}					
			else {
				//response.sendError(result, "Center creation error " + errorMsg);
				LOG.error("ERROR creating Center");	
				response.setStatus(Errors.GENERIC_ERROR);
		    }
						
		} catch (Exception e) {			
			LOG.error("ERROR creating Center....", e);								
			//response.sendError(response.SC_SEE_OTHER, "User Error Insertion");
			response.setStatus(Errors.GENERIC_ERROR);
			//LOG.error(e.getMessage());			
			//errorMsg = e.getMessage();
			//result = -1;
		}			
		
	}
	
	private void update(ServicioVO value, HttpServletResponse response) throws IOException {
				
		int result = -1;
				
		try {
			result = service.update(value);
		
			if (result >= 0) {
				//Default response
				response.setContentType(Constants.TEXT_CONTENT_TYPE);
				PrintWriter out = response.getWriter();
				out.println(result);
				out.flush();
				out.close();
			}
			else if (result == Errors.MAX_LICENSES) {
				LOG.error("Exceeded the number of licenses available");
				response.setStatus(Errors.MAX_LICENSES_ERROR);
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
		
		} catch (Exception e) {			
			LOG.error("ERROR updating Service....", e);											
			response.setStatus(Errors.GENERIC_ERROR);
		}	
		
	}
	
	private void delete(ServicioVO value, HttpServletResponse response) throws IOException {
		
		int result = 0;
		String errorMsg = "";
		
		try {
			int users = service.getUsersCount(String.valueOf(value.getIdServicio()));
			
			if (users>0) {			
				LOG.error("Exist users");
				response.setStatus(Errors.EXIST_USERS_ERROR);
				return;
			}
			else {
				//If not exist users
				service.delete(value);
			}
						
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result != -1) {
			//Default response
			response.setContentType(Constants.TEXT_CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close(); 
		} else {
			response.sendError(result, "Service delete error " + errorMsg);			 			 
	    }
		
	}
	
	/**
	 * Search in cache
	 * @param query
	 * @return
	 */
	private Object[] getCache(String centerId, String serviceId) {
						
		for (Object serviceVO:cache) {
			Entidad service = Entidad.class.cast(serviceVO);
			if ((Integer)service.getAtributos().get(Fields.CENTER_ID) == Integer.parseInt(centerId) &&
				(Integer)service.getAtributos().get(Fields.SERVICE_ID) == Integer.parseInt(serviceId)) {
				Entidad[] result = new Entidad[1];	
				result[0] = service;
				return result; 
			}
		}
		
		return new Object[0];
	}
	
	private void setCache(Object[] values) {
		cache = values;		
	}
	
	/**
	 * Validate service request info
	 * @param request
	 * @return
	 */
	private ServicioVO validateService(HttpServletRequest request) {
		//Init parameters
		ServicioVO serviceVO = new ServicioVO();
		
		try {				
			if (request.getParameter(Fields.SERVICE_ID)!=null) {	
				serviceVO.setIdServicio(Integer.parseInt(request.getParameter(Fields.SERVICE_ID)));
			}
			if (request.getParameter(Fields.SERVICE_DESCRIPTION)!=null) {
				serviceVO.setDescripcion(request.getParameter(Fields.SERVICE_DESCRIPTION));
			}
			if (request.getParameter(Fields.LICENSES)!=null) {
				serviceVO.setLicencias(Integer.parseInt(request.getParameter(Fields.LICENSES)));
			}
			if (request.getParameter(Fields.SERVICE_NAME)!=null) {							
				serviceVO.setNombre(request.getParameter(Fields.SERVICE_NAME));
			}
			if (request.getParameter(Fields.CENTER_ID)!=null) {
				CentroVO center = new CentroVO();
				center.setIdCentro(Integer.parseInt(request.getParameter(Fields.CENTER_ID)));
				serviceVO.setCentro(center);
			}						
		}
		catch(Exception e) {
			serviceVO = null;
		}
		
		return serviceVO;
	}
	
}
