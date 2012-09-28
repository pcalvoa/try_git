package com.tid.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;

import com.tid.Fields;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.util.encriptacion.MD5;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.pojo.Client;
import com.tid.service.impl.UserService;
import com.tid.thread.ApplicationPasswordRenew;
import com.tid.util.Parser;


public class RecoveryServlet extends BaseServlet {

	private static final Logger LOG = Logger.getLogger(UserServlet.class.getName());
	
	private UserService service = null;
	private Object[] cache = null;
	
	@Override
	public void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
		
		super.doGet(request, response);
									
		String clientName = request.getParameter(Constants.PARAM_CLIENT);
		String username = request.getParameter(Fields.USER_USER);
		String code = request.getParameter(Constants.PARAM_CODE);	
		String securityAnswer = request.getParameter(Fields.USER_SECURITY_ANSWER);	
		String email = request.getParameter(Constants.PARAM_EMAIL);

		//Get password parameter
		String password = null;		
		if (request.getParameter(Fields.USER_PASSWORD)!=null) { 
			//Encoded password				
			password = MD5.digest((new String(request.getParameter(Fields.USER_PASSWORD)).trim()));
		}
						
		//Client is changed in call
		if (clientName!=null && !"".equals(clientName)) {
			
			LOG.debug("ClientName: " + clientName);
			
			clientInfo = null;
			
			javax.servlet.http.HttpSession session = request.getSession();
			//Get config information					    
		    List<Client> clientsCache = (List<Client>)this.getServletContext().getAttribute(Constants.CLIENTS_CACHE);
		   		    
	 	    //Get information for current client
		    for (Client client:clientsCache) {
		    	if (client.getName().equals(clientName)) {
		    		clientInfo = client;
		    		break;
		    	}
		    }
		    
		    if (clientInfo!=null) {
		    			    			    	
		    	session.setAttribute(Constants.CURRENT_CLIENT, clientInfo.getName());
		    	
		    	try {
		    		LOG.debug("Client session initialization...");
		    		initService(request);
				} catch (Exception e) {
					//throw new ServletException(e.getMessage());
					LOG.error("Error on connection (" + e.getMessage() + ")");
					response.sendError(-1, "Error on conection " + e.getMessage());
					return;
				}				
				//Get  query parameter	
				String query = request.getParameter(Constants.PARAM_QUERY);
					
				//Search from code to password recovery		
				if (code!=null && !"".equals(code)) {
					LOG.debug("Searching code...");
					search(code, response);			
				}		
				//Search email
				else if (email!=null && !"".equals(email) ) {
					
					
					if (verifyEmail(email)>=0) {
					
						String url = request.getRequestURL().toString().replace("recoveryservlet", "recovery");
						
						//Revisar proceso para que se adapte a los nuevos requisitos
						Thread thread = new ApplicationPasswordRenew(clientInfo.getName(),
																	 clientInfo.getUrl(),
																	 clientInfo.getUser(), 
																	 clientInfo.getPassword(),
																	 request.getParameter(Constants.PARAM_EMAIL), 
																	 url );
						LOG.debug("Sending email");
						thread.start();
					}
					else {
						LOG.error("Email not valid");
						response.sendError(-1, "Email not valid");
					}
					
					//TODO Add custom return msg
				}
				
		    }		   
		    else {
		    	LOG.error("Parameters not valid");
		    	response.sendError(-1, "Parameters not valid");
		    }
				   						
		}
						
		if (username!=null && !"".equals(username) && 
				securityAnswer!=null && !"".equals(securityAnswer)) { 
		    				
			LOG.debug("Updating new password");
			//Update new password
			update(code, username, securityAnswer, password, response);
			
			//Invalidate session
			request.getSession().invalidate();
			
			//TODO Add custom return msg
				
		}
															
	}
	
	/**
	 * Process the HTTP doPost request.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		super.doPost(request, response);

	}
	
	private void initService(HttpServletRequest request) throws Exception {
		javax.servlet.http.HttpSession session = request.getSession();		
		if (service==null) {
		
			service = new UserService(clientInfo.getUrl(), 
				clientInfo.getUser(), 
				clientInfo.getPassword());
		
			session.setAttribute(Constants.CLIENT_UPDATED_RECOVERY, false);
		}
	}
	
	
	
	/**
	 * Search by code to recovery password
	 * 
	 * @param code
	 * @param response
	 * @throws IOException
	 */
	private void search(String code, HttpServletResponse response) 
		throws IOException {

		//Create object		
		int result = 0;
		String errorMsg = "";
		Object[] values = null;
		
		try {			
			if (code!=null && !"".equals(code)) {
						
				//First try with cache
				values = getCache(code);												
				
				if (values==null) {		
					//First try with cache
					values = service.searchCode(code);	
					setCache(values);
				}
			}
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());
			errorMsg = e.getMessage();
			result = -1;
		}			
					
		if (result != -1 && values != null) {
			
			if (values.length==0) {
				response.sendError(result, "Invalid code or expiration date expired ");
			}
			else {
				responseFormated(response, values, UserVO.class);
			}
			
		} else {		
			LOG.error("User search error " + errorMsg);
			response.sendError(result, "User search error " + errorMsg);								
	    }		
	}
	
	
	/**
	 * Search user by email
	 * 
	 * @param code
	 * @param response
	 * @throws IOException
	 */
	private int verifyEmail(String email) 
		throws IOException {

		//Create object		
		int result = -1;		
		Object[] values = null;		
		
		try {			
			if (email!=null && !"".equals(email)) {
						
				values = service.searchEmail(email);					
			}
			
		} catch (Exception e) {			
			LOG.error(e.getMessage());						
		}			
					
		if (values != null) {
			
			if (values.length>0) {
				
				result = 0;
			}			
			
		} 			
		
		return result;						
	    		
	}
	
	/**
	 * Verify data from user and then update password
	 * 
	 * @param recoveryCode
	 * @param username
	 * @param securityAnswer
	 * @param response
	 * @throws IOException
	 */
	private void update(String recoveryCode, String username, String securityAnswer, String password, HttpServletResponse response) throws IOException {
		
		int result = -1;
		String errorMsg = "";
											
		try {
			//Get current values from cache		
			Object[] values = getCache(String.valueOf(recoveryCode));
											
			Entidad currentUser = null;
						
			if (values!= null && values.length>0) {
				
				//Verify username and security answer
				currentUser = (Entidad)values[0];
												
				UserVO user = Parser.getUser(currentUser);
								
				if (username.equals(user.getUsuario()) && securityAnswer.equals(user.getRespuestaSeguridad())) {
					if (!password.equals(user.getContrasena())) {						
						//Change password in service
						if (this.service.changePassword(username, password)) {
							
							user.setIdUsuario(user.getIdUsuario());
							user.setCodigoSeguridad(Constants.VALUE_NULL);
							user.setFechaCaducidad(null);
							this.service.update(user);
							
							//delete cache
							setCache(null);
							
							result = 0;
						}
					}
				}
			
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
			response.sendError(result, "User update error " + errorMsg);			 			 
	    }
		
	}
	
	/**
	 * Search in cache
	 * @param query
	 * @return
	 */
	private Object[] getCache(String code) {
		if (cache!=null) {				
			for (Object userVO:cache) {
				Entidad user = Entidad.class.cast(userVO);
				try {
					if (user.getAtributos().get(Fields.USER_RECOVERY_CODE).toString().equals(code)) {
				
						Entidad[] result = new Entidad[1];	
						result[0] = user;
						return result; 
					}
				}
				catch(Exception e) {
					LOG.error("Error getting user data");
				}
			}
		}
		
		return new Object[0];
	}
	
	private void setCache(Object[] values) {
		cache = values;
	}
			
}
