/**
 * 
 */
package com.tid.service.impl;

import java.rmi.Remote;
import java.util.logging.Logger;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.message.token.UsernameToken;

import com.tid.Constants;
import com.tid.Errors;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.dao.remote.ws.FullUserLManager;
import com.tid.atica.htc.core.dao.remote.ws.FullUserLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.FullUserManagerSoapBindingStub;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorServiceLocator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub;
import com.tid.atica.htc.core.dao.remote.ws.ServicioLManager;
import com.tid.atica.htc.core.dao.remote.ws.ServicioLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.ServicioManagerSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasManagerSoapBindingStub;
import com.tid.service.Service;
import com.tid.service.impl.sql.UserSql;
import com.tid.util.Parser;

/**
 * @author fdelatorre
 * 
 */
public class UserService implements Service {
	
	private static final Logger LOG = Logger.getLogger(UserService.class.getName());
	
	private FullUserManagerSoapBindingStub service = null;
	private ConsultasLManager searchService = null;
	private ServicioLManager serviceService = null;
	private LicenseService licenseService = null;
	

	public UserService(String server, String user, String password)
			throws Exception {
		
		super();
		
		LOG.info("Initializing ws connection");
		
		// Init conection con ws
		EngineConfiguration config = new FileProvider(
				Constants.CONFIG_SECURITY_FILE);

		RemoteActivator remoteActivator = null;

		// RemoteActivator
		RemoteActivatorServiceLocator locatorActivator = new RemoteActivatorServiceLocator(
				config);
		locatorActivator.setEndpointAddress(
				Constants.SERVICE_REMOTEACTIVATOR_NAME, server
						+ Constants.SERVICE_REMOTEACTIVATOR_ADDRESS);

		// Security
		Remote remote = locatorActivator.getPort(RemoteActivator.class);
		Stub axisPort = (Stub) remote;
		axisPort._setProperty(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN);
		axisPort._setProperty(UsernameToken.PASSWORD_TYPE,
				WSConstants.PASSWORD_DIGEST);
		axisPort._setProperty(WSHandlerConstants.USER, user);
		axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS,
				"com.tid.service.PWCallback");

		remoteActivator = (RemoteActivatorSoapBindingStub) axisPort;

		remoteActivator.iniciarRemoteDB();

		// User service
		FullUserLManagerServiceLocator locator = new FullUserLManagerServiceLocator(
				config);
		locator.setEndpointAddress(Constants.SERVICE_USER_NAME, server
				+ Constants.SERVICE_USER_ADDRESS);

		// Security
		remote = locator.getPort(FullUserLManager.class);
		axisPort = (Stub) remote;
		axisPort._setProperty(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN);
		axisPort._setProperty(UsernameToken.PASSWORD_TYPE,
				WSConstants.PASSWORD_DIGEST);
		axisPort._setProperty(WSHandlerConstants.USER, user);
		axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS,
				"com.tid.service.PWCallback");

		service = (FullUserManagerSoapBindingStub) axisPort;
		
		// Search service
		ConsultasLManagerServiceLocator locatorSearch = new ConsultasLManagerServiceLocator(
				config);
		locatorSearch.setEndpointAddress(Constants.SERVICE_SEARCH_NAME, server
				+ Constants.SERVICE_SEARCH_ADDRESS);

		// Security
		remote = locatorSearch.getPort(ConsultasLManager.class);
		axisPort = (Stub) remote;
		axisPort._setProperty(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN);
		axisPort._setProperty(UsernameToken.PASSWORD_TYPE,
				WSConstants.PASSWORD_DIGEST);
		axisPort._setProperty(WSHandlerConstants.USER, user);
		axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS,
				"rhcolaboravm.axisversion2.services.CentroManager.PWCallback");

		searchService = (ConsultasManagerSoapBindingStub) axisPort;
		
		//License service
		licenseService = new LicenseService(server, user, password);
		
		
		//Services service
		ServicioLManagerServiceLocator locatorService = new ServicioLManagerServiceLocator(config);
		locatorService.setEndpointAddress(Constants.SERVICE_SERVICE_NAME, server + Constants.SERVICE_SERVICE_ADDRESS);
		
		//Security		
        remote = locatorService.getPort(ServicioLManager.class);
        axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "com.tid.service.PWCallback");
               
        serviceService = (ServicioManagerSoapBindingStub)axisPort;
		
		LOG.info("Initialized WS connection");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tid.service.Service#create(java.lang.Object)
	 */
	@Override
	public int create(Object value) throws Exception {
		
		LOG.info("CREATE user");
		
		//Default error
		int result = Errors.GENERIC_ERROR;

		// Verify class and != null
		if (value == null || value.getClass() != UserVO.class) {
			throw new ClassCastException("Not valid class");
		}

		UserVO user = (UserVO) value;

		//Validate center information
		
		if (user.getServicio() != null) { 
						
			//Select the center but not the Service
			if (user.getServicio().getIdServicio() < 0) {
				LOG.info("GET THE DEFAULT SERVICE");
				CentroVO centro = user.getServicio().getCentro();
				ServicioVO service = serviceService.getServicioDefecto(centro.getIdCentro());
				
				//To maintain the center name
				service.setCentro(centro);
				user.setServicio(service);
			}
		}

		// Verify used licenses
		LOG.info("Verifying licenses");
				
		if (!licenseService.isEnabledLicenses(user)) {
			result = Errors.MAX_LICENSES;
		}
		else {
			
			LOG.info("Calling insert client");
			result = service.insertUser(user);

			// Calling service
			//int idUser = service.insertUser(user);
			//if (idUser>=0) {
			//	result = 0;
			//}		
		}			

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tid.service.Service#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object value) throws Exception {
		// Verify class
		if (value.getClass() != UserVO.class) {
			throw new ClassCastException("Not valid class");
		}

		UserVO user = (UserVO) value;

		// TODO: Validate center information
		
		LOG.info("Calling delete client");

		// Calling service
		service.deleteUser(user);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tid.service.Service#update(java.lang.Object)
	 */
	@Override
	public int update(Object value) throws Exception {
		return update(value, value);
	}
	
	public int update(Object value, Object currentUser) throws Exception {
		//Default error
		int result = Errors.NO_ERROR;

		// Verify class
		if (value.getClass() != UserVO.class) {
			throw new ClassCastException("Not valid class");
		}
		
		UserVO user = (UserVO) value;
		
		UserVO current = null;
		
		if (currentUser.getClass() == Entidad.class) {
			current = Parser.getUser((Entidad)currentUser);		
		}
		else {
			current = (UserVO) currentUser;
		}				

		if (user.getServicio() != null) { 

			//Get default service for selected center
			ServicioVO defaultService = serviceService.getServicioDefecto(user.getServicio().getCentro().getIdCentro());
						
			//Select the center but not the Service
			if (user.getServicio().getIdServicio() < 0 || 
				user.getServicio().getIdServicio() ==  defaultService.getIdServicio()) {
				LOG.info("GET THE DEFAULT SERVICE");
				//To maintain the center name
				//service.setCentro(centro);
				user.setServicio(defaultService);
			}
			if (user.getServicio().getCentro().getIdCentro() == Constants.DEFAULT_CENTER_ID) {
				user.getServicio().getCentro().setNombre(Constants.DEFAULT_CENTER);
			}
			
		}
		
		// Verify used licenses
		LOG.info("Verifying licenses");
		
		//Change center and service
		if (user.getServicio().getCentro().getIdCentro() != current.getServicio().getCentro().getIdCentro() ||
			user.getServicio().getIdServicio() != current.getServicio().getIdServicio()) {
			
			if (!licenseService.isEnabledLicenses(user)) {
				result = Errors.MAX_LICENSES;
			}
		} 

		if (result == Errors.NO_ERROR) {
			
			LOG.info("Calling update client");
			result = service.modifyUser(user);
		}			

		return result;
		

	}
	
	public boolean changePassword(String user, String password)
			throws Exception {

		LOG.info("Calling delete client");

		// Calling service
		return service.changePassword(user, password, password);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tid.service.Service#search(java.lang.String)
	 */
	@Override
	public Object[] search(String query) throws Exception {
		Object[] results = null;
		 		
		LOG.info("Searching users by query: " + query);
		
		String sql = UserSql.getSearchQuery(query);;
		
		// Get results
		results = searchService.realizarConsultaResultado(sql, UserSql.getEntity(), "");

		return results;

	}
	
	public Object[] searchCode(String code) throws Exception {
		Object[] results = null;
		 		
		LOG.info("Searching users by code: " + code);
		
		String sql = UserSql.getSearchCode(code);
				
		// Get results
		results = searchService.realizarConsultaResultado(sql, UserSql.getEntity(), "");

		return results;

	}
	
	public Object[] searchEmail(String email) throws Exception {
		Object[] results = null;
		 		
		LOG.info("Searching users by email: " + email);
		
		String sql = UserSql.getSearchEmail(email);
				
		// Get results
		results = searchService.realizarConsultaResultado(sql, UserSql.getEntity(), "");

		return results;

	}

	public Object[] search(String centerId, String serviceId, String query)
			throws Exception {
		
		Object[] results = null;

		LOG.info("Searching users by center " + centerId + ", service " + serviceId + " and query: " + query);

		String sql = UserSql.getSearchQuery(centerId, serviceId, query);

		// Get results
		results = searchService.realizarConsultaResultado(sql, UserSql.getEntity(), "");

		return results;
	}
	
}
