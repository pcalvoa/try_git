package com.tid.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;
import com.tid.Errors;
import com.tid.Fields;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.DispositivoVO;
import com.tid.atica.htc.core.bean.PerfilVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.SubperfilVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.util.encriptacion.MD5;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.service.impl.UserService;

public class UserServlet extends BaseServlet {

	private static final Logger LOG = Logger.getLogger(UserServlet.class.getName());
	
	private UserService service = null;
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
		String userId = request.getParameter(Fields.USER_ID);
		
		search(query, centerId, serviceId, userId, response);
								
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
		UserVO value = validateUser(request);
		
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
		boolean updated = (Boolean)session.getAttribute(Constants.CLIENT_UPDATED_USER);
		if (service==null || updated) {
		
			service = new UserService(clientInfo.getUrl(), 
				clientInfo.getUser(), 
				clientInfo.getPassword());
		
			session.setAttribute(Constants.CLIENT_UPDATED_USER, false);
		}
	}
	
	private void search(String query, String centerId, String serviceId, String userId, 
			HttpServletResponse response) throws IOException {

		//Create object		
		int result = 0;
		String errorMsg = "";
		Object[] values = null;
		
		try {
			if (userId!=null) {
				//First try with cache
				values = getCache(userId);				
			}

			if (values==null) {			
				values = service.search(centerId, serviceId, query);
				setCache(values);
			}										
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
					
		if (result != -1 && values != null) {
			responseFormated(response, values, UserVO.class);
		} else {
			response.sendError(result, "User search error " + errorMsg);			
			
	    }		
	}
	
	private void insert(UserVO value, HttpServletResponse response) throws IOException {
		//Create object		
		int result = Errors.GENERIC_ERROR;
				
		try {
			
			int user = service.create(value);
			
			if (user >=0 ) {
				result = HttpServletResponse.SC_OK;
				cache=null;
				
				//Default response
				response.setContentType(Constants.TEXT_CONTENT_TYPE);
				response.setStatus(result);
			}		
			else if (user == Errors.MAX_LICENSES) {
				LOG.error("Exceeded the number of licenses available");
				response.setStatus(Errors.MAX_LICENSES_ERROR);
				//response.sendError(response.SC_CONFLICT, "Exceeded the number of licenses available");
			}
			else {				
				LOG.error("User Error Insertion");
				//response.sendError(response.SC_SEE_OTHER, "User Error Insertion");	
				response.setStatus(Errors.OTHER_ERROR);
			}
						
		} catch (Exception e) {			
			LOG.error("ERROR creating USER....", e);								
			//response.sendError(response.SC_SEE_OTHER, "User Error Insertion");
			response.setStatus(Errors.OTHER_ERROR);
		}			
		
	}
	
	private void update(UserVO value, HttpServletResponse response) throws IOException {
				
		int result = Errors.GENERIC_ERROR;
											
		try {
			//Get current values from cache
			Object[] values = getCache(String.valueOf(value.getIdUsuario()));
			Entidad currentUser = null;
			boolean updated = true;
			
			currentUser = (Entidad)values[0];	
			
			if (values!= null && values.length>0) {
				
				//Verify if password is changed								
				String currentPassword = currentUser.getAtributos().get(Fields.USER_PASSWORD).toString();
																
				if (!currentPassword.equals(value.getContrasena())) {
					//Change password in service
					updated = this.service.changePassword(value.getUsuario(), value.getContrasena());
				}
			}
			if (updated) {
				
				int user = this.service.update(value, currentUser);
				
				if (user >=0 ) {
					result = HttpServletResponse.SC_OK;
					cache=null;
					
					//Default response
					response.setContentType(Constants.TEXT_CONTENT_TYPE);
					response.setStatus(result);
				}		
				else if (user == Errors.MAX_LICENSES) {
					LOG.error("Exceeded the number of licenses available");
					response.setStatus(Errors.MAX_LICENSES_ERROR);
					//response.sendError(response.SC_CONFLICT, "Exceeded the number of licenses available");
				}
				else {				
					LOG.error("Update User Error");	
					response.setStatus(Errors.OTHER_ERROR);
				}				
			}
			
		} catch (Exception e) {			
			LOG.error("Update User Error");	
			response.setStatus(Errors.OTHER_ERROR);
		}									
		
	}
	
	private void delete(UserVO value, HttpServletResponse response) throws IOException {
		
		int result = 0;
		String errorMsg = "";
		
		try {
			service.delete(value);
			cache=null;
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
			response.sendError(result, "User delete error " + errorMsg);			 			 
	    }
		
	}
	
	/**
	 * Search in cache
	 * @param query
	 * @return
	 */
	private Object[] getCache(String userId) {
		if (cache!=null) {				
			for (Object userVO:cache) {
				Entidad user = Entidad.class.cast(userVO);
				//if ((Long)user.getAtributos().get(Fields.USER_ID) == Long.parseLong(userId)) {
				if ((Integer)user.getAtributos().get(Fields.USER_ID) == Integer.parseInt(userId)) {
					Entidad[] result = new Entidad[1];	
					result[0] = user;
					return result; 
				}
			}
		}
		
		return new Object[0];
	}
	
	private void setCache(Object[] values) {			
		cache = values;				
	}
	
	/**
	 * Validate user request info
	 * @param request
	 * @return
	 */
	private UserVO validateUser(HttpServletRequest request) {
		//Init parameters
		LOG.info("VALIDATE USER....");
		
		UserVO user = new UserVO();			
		
		try {											
			//If new mode
			if (request.getParameter(Fields.USER_ID)!=null) {
				user.setIdUsuario(Integer.parseInt(request.getParameter(Fields.USER_ID)));
			}
			if (request.getParameter(Fields.USER_USER)!=null) {
				user.setUsuario(request.getParameter(Fields.USER_USER));
			}
			if (request.getParameter(Fields.USER_NAME)!=null) {
				user.setNombre(request.getParameter(Fields.USER_NAME));
			}
			if (request.getParameter(Fields.USER_SURNAME)!=null) {
				user.setApellidos(request.getParameter(Fields.USER_SURNAME));
			}
			if (request.getParameter("e" + Fields.USER_PASSWORD)!=null) { 
				//Encoded password				
				user.setContrasena(MD5.digest((new String(request.getParameter("e" + Fields.USER_PASSWORD)).trim())));
			}
			if (request.getParameter(Fields.USER_PASSWORD)!=null) { 
				//Encoded password				
				user.setContrasena(MD5.digest((new String(request.getParameter(Fields.USER_PASSWORD)).trim())));
			}
			if (request.getParameter(Fields.USER_EMAIL)!=null) {
				user.setCorreo(request.getParameter(Fields.USER_EMAIL));
			}
			if (request.getParameter(Fields.USER_EMAIL_USER)!=null) {
				user.setUsuarioCorreo(request.getParameter(Fields.USER_EMAIL_USER));
			}
			if (request.getParameter(Fields.USER_EMAIL_PASSWORD)!=null) {
				user.setContrasenaCorreo(request.getParameter(Fields.USER_EMAIL_PASSWORD));
			}
			//TODO: necessary 'SetDatosCuentaCorreo'?
			//user.setDatosCuentaCorreo(rec)
			
			if (request.getParameter(Fields.USER_PHONE)!=null) {
				user.setTelefono(request.getParameter(Fields.USER_PHONE));
			}
			if (request.getParameter(Fields.USER_MOBILE)!=null) {
				user.setMovil(request.getParameter(Fields.USER_MOBILE));
			}
			if (request.getParameter(Fields.USER_OBSERVATIONS)!=null) {
				user.setObservaciones(request.getParameter(Fields.USER_OBSERVATIONS));
			}
			
			if (request.getParameter(Fields.USER_SECURITY_QUESTION)!=null) {			
				user.setPreguntaSeguridad(request.getParameter(Fields.USER_SECURITY_QUESTION));
			}
			if (request.getParameter(Fields.USER_SECURITY_ANSWER)!=null) {				
				user.setRespuestaSeguridad(request.getParameter(Fields.USER_SECURITY_ANSWER));
			}
			
			//Set profile				
			if (request.getParameter(Fields.PROFILE_ID)!=null) {
				PerfilVO profile = new PerfilVO();
				profile.setIdPerfil(Integer.parseInt(request.getParameter(Fields.PROFILE_ID)));						
				user.setPerfil(profile);
			}
			
			//Set user's center
			ServicioVO serviceVO = new ServicioVO();
			if (request.getParameter(Fields.CENTER_ID) != null  && !request.getParameter(Fields.CENTER_ID).equals("")
					&& !request.getParameter(Fields.CENTER_ID).equals("0")) {
				CentroVO centroVO = new CentroVO();
				centroVO.setIdCentro(Integer.parseInt(request.getParameter(Fields.CENTER_ID)));				
			
				//Set user's service	
				if (request.getParameter(Fields.SERVICE_ID) != null && !request.getParameter(Fields.SERVICE_ID).equals("")
						&& !request.getParameter(Fields.SERVICE_ID).equals("0")) {
					LOG.info("Select CENTER AND SERVICE");		
					serviceVO.setIdServicio(Integer.parseInt(request.getParameter(Fields.SERVICE_ID)));
					serviceVO.setCentro(centroVO);
					user.setServicio(serviceVO);
				}
				
				//Select CENTER but not the service
				else {
					LOG.info("Select CENTER but not the SERVICE");		
					serviceVO.setIdServicio(-1);
					serviceVO.setNombre(Constants.DEFAULT_CENTER);
				}
			}
			
			//Do not select the center neither the service
			else {
				LOG.info("Do not select the CENTER neither the SERVICE");
				CentroVO centroVO = new CentroVO(Constants.DEFAULT_CENTER_ID);
				centroVO.setNombre(Constants.DEFAULT_CENTER);
				serviceVO.setIdServicio(Constants.DEFAULT_CENTER_ID);
				serviceVO.setNombre(Constants.DEFAULT_CENTER);
				serviceVO.setCentro(centroVO);	
			}
			user.setServicio(serviceVO);
			
			
			ArrayList<CentroVO> administratedCenters = new ArrayList<CentroVO>();
									
			//Set administrated centers only for local administrators
			if (request.getParameterValues(Fields.USER_ADMINISTRATED_CENTERS)!=null && 
					user.getPerfil()!=null && 
					user.getPerfil().getIdPerfil()==Constants.PROFILE_AMINISTRATOR_LOCAL) {
				
				String[] values = (String[])request.getParameterValues(Fields.USER_ADMINISTRATED_CENTERS);
				for (String value:values) {
					CentroVO center = new CentroVO();
					center.setIdCentro(Integer.parseInt(value));
					administratedCenters.add(center);
				}
				
			}
			
			user.setCentrosAdministrados(administratedCenters);
			user.setFechaAlta(Calendar.getInstance().getTime());
			
			//Init others default values to void values
			
			user.setClaveMovilidad("");
			user.setDispositivos(new ArrayList<DispositivoVO>());				
			user.setInformes(0);
			user.setSubperfiles(new ArrayList<SubperfilVO>());													
		}
		
		catch(Exception e) {
			LOG.error("ERROR Validating user...", e);
			user = null;
		}
			
		
		return user;
	}
	
}
