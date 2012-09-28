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
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.service.impl.CenterService;

public class CenterServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4288048571132068732L;

	private static final Logger LOG  = Logger.getLogger(CenterServlet.class.getName());
	
	private CenterService service = null;
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
		
		request.setCharacterEncoding("UTF-8");
		
		//Get  query parameter	
		String query = request.getParameter(Constants.PARAM_QUERY);		
		String centerId = request.getParameter(Fields.CENTER_ID);
		String userId = request.getParameter(Fields.USER_ID);
		
		LOG.info("QUERY: "+query);
								
		if (userId!=null && !"".equals(userId)) {
			search(userId, response);
		}
		else {
			search(query, centerId, response);
		}
							
	}			
	
	@Override
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
		CentroVO value = validateCenter(request);
		
		//Get op parameter	
		String op = request.getParameter(Constants.PARAM_OP);
		
		if (op==null) {
			response.sendError(-1, "Parameters not valid");
		}
		else if (Constants.VALUE_INSERT.equals(op)) {
			insert(value, response);
		}
		else if (Constants.VALUE_UPDATE.equals(op)) {
			update(value, response);
		}
		else if (Constants.VALUE_DELETE.equals(op)) {
			delete(value, response);
		}
		else {
			response.sendError(-1, "Parameters not valid");
		}		
			
	}
	
	/**
	 * Inint configuration
	 * 
	 * @throws Exception
	 */
	private void initService(HttpServletRequest request) throws Exception {
		javax.servlet.http.HttpSession session = request.getSession();
				
		boolean updated = false; 
		
		try {
			updated = (Boolean)session.getAttribute(Constants.CLIENT_UPDATED_CENTER);
		}
		catch(Exception e) {}
		
		if (service==null || updated) {
		
			service = new CenterService(clientInfo.getUrl(), 
				clientInfo.getUser(), 
				clientInfo.getPassword());
			
			session.setAttribute(Constants.CLIENT_UPDATED_CENTER, false);
		}
	}
	
	
	/**
	 * Search process
	 * 
	 * @param query
	 * @param centerId
	 * @param response
	 * @throws IOException
	 */
	private void search(String query, String centerId, HttpServletResponse response) throws IOException {
		//Create object		
		int result = 0;
		String errorMsg = "";
		Object[] values = null;
		
		LOG.info("SEARCH: "+query);
		
		try {
			if (centerId!=null || "0".equals(centerId)) {
				//First try with cache
				values = getCache(centerId);				
			}

			if (values==null) {			
				values = service.search(query);
				setCache(values);
			}	

		} catch (Exception e) {			
			LOG.error(e.getMessage());			
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result != -1 && values != null) {			
			//formated response			
			responseFormated(response, values, CentroVO.class);
		} else {
			response.sendError(result, "Center search error " + errorMsg);			 			 
	    }		
	}
	
	/**
	 * Search administrated centers process
	 * 
	 * @param query
	 * @param centerId
	 * @param response
	 * @throws IOException
	 */
	private void search(String userId, HttpServletResponse response) throws IOException {
		//Create object		
		int result = 0;
		String errorMsg = "";
		Object[] values = null;
		
		try {
		
			values = service.searchAdministrated(userId);
									
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());			
			errorMsg = e.getMessage();
			result = -1;
		}			
				
		if (result != -1 && values != null) {			
			//formated response			
			responseFormated(response, values, CentroVO.class);
		} else {
			response.sendError(result, "Center search error " + errorMsg);			 			 
	    }		
	}
	
	
	
	private void insert(CentroVO value, HttpServletResponse response) throws IOException {
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
	
	private void update(CentroVO value, HttpServletResponse response) throws IOException {
				
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
			LOG.error("ERROR updating Center....", e);											
			response.setStatus(Errors.GENERIC_ERROR);
		}									
		
	}
	
	private void delete(CentroVO value, HttpServletResponse response) throws IOException {
		
		int result = 0;
		String errorMsg = "";
		
		try {
						
			int services = service.getServicesCount(String.valueOf(value.getIdCentro()));
						
			if (services>0) {			
				LOG.error("Exist services");
				response.setStatus(Errors.EXIST_SERVICES_ERROR);
				return;
			}
			else {
				//If not exist services
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
			response.sendError(result, "Center delete error " + errorMsg);			 			 
	    }
		
	}
	
	/**
	 * Search in cache
	 * @param query
	 * @return
	 */
	private Object[] getCache(String centerId) {
						
		for (Object centerVO:cache) {
			Entidad center = Entidad.class.cast(centerVO);
			if ((Integer)center.getAtributos().get(Fields.CENTER_ID) == Integer.parseInt(centerId)) {
				Entidad[] result = new Entidad[1];	
				result[0] = center;
				return result; 
			}
		}
		
		return new Object[0];
	}
	
	private void setCache(Object[] values) {
		cache = values;		
	}
	
	/**
	 * Validate center request info
	 * @param request
	 * @return
	 */
	private CentroVO validateCenter(HttpServletRequest request) {
		//Init parameters
		CentroVO center = new CentroVO();
		try {
			if (request.getParameter(Fields.ID)!=null) {					
				center.setIdCentro(Integer.parseInt(request.getParameter(Fields.ID)));
			}
			if (request.getParameter(Fields.CENTER_ID)!=null) {					
				center.setIdCentro(Integer.parseInt(request.getParameter(Fields.CENTER_ID)));
			}
			if (request.getParameter(Fields.LICENSES)!=null) {
				center.setLicencias(Integer.parseInt(request.getParameter(Fields.LICENSES)));
			}
			if (request.getParameter(Fields.CENTER_DESCRIPTION)!=null) {
				center.setDescripcion(request.getParameter(Fields.CENTER_DESCRIPTION));
			}
			if (request.getParameter(Fields.CENTER_NAME)!=null) {
				center.setNombre(request.getParameter(Fields.CENTER_NAME));
			}
		}
		catch(Exception e) {
			center = null;
		}
		return center;
	}
	
	
	
}
