/**
 * 
 */
package com.tid.service.impl;

import java.rmi.Remote;
import java.rmi.RemoteException;

import net.java.sip.communicator.util.Logger;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.message.token.UsernameToken;


import com.tid.Constants;
import com.tid.Errors;
import com.tid.Fields;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorServiceLocator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub;
import com.tid.atica.htc.core.dao.remote.ws.ServicioLManager;
import com.tid.atica.htc.core.dao.remote.ws.ServicioLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.ServicioManagerSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasManagerSoapBindingStub;
import com.tid.service.Service;
import com.tid.service.impl.sql.ServiceSql;
import com.tid.service.impl.sql.UserSql;
import com.tid.util.QueryConstructor;

/**
 * Services' service
 * 
 * @author fdelatorre
 *
 */
public class ServiceService implements Service {
	
	private static final Logger LOG = Logger.getLogger(ServiceService.class.getName());
	
	private ServicioLManager service = null;
	private ConsultasLManager searchService = null;
	private LicenseService licenseService = null;
	
	public ServiceService(String server, String user, String password) throws Exception {
		super();
		
		LOG.debug("Initializing ws connection");
		
		//Init conection con ws
		EngineConfiguration config = new FileProvider(Constants.CONFIG_SECURITY_FILE);
		
		RemoteActivator remoteActivator = null;
		
		//RemoteActivator
		RemoteActivatorServiceLocator locatorActivator = new RemoteActivatorServiceLocator(config);
		locatorActivator.setEndpointAddress(Constants.SERVICE_REMOTEACTIVATOR_NAME, server + Constants.SERVICE_REMOTEACTIVATOR_ADDRESS);
		
		//Security		
        Remote remote = locatorActivator.getPort(RemoteActivator.class);
        Stub axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "com.tid.service.PWCallback");
               
        remoteActivator = (RemoteActivatorSoapBindingStub)axisPort;
		
		remoteActivator.iniciarRemoteDB();	
		
		//Center service
		ServicioLManagerServiceLocator locator = new ServicioLManagerServiceLocator(config);
		locator.setEndpointAddress(Constants.SERVICE_SERVICE_NAME, server + Constants.SERVICE_SERVICE_ADDRESS);
		
		//Security		
        remote = locator.getPort(ServicioLManager.class);
        axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "com.tid.service.PWCallback");
               
        service = (ServicioManagerSoapBindingStub)axisPort;
        
        //Search service
        ConsultasLManagerServiceLocator locatorSearch = new ConsultasLManagerServiceLocator(config);
        locatorSearch.setEndpointAddress(Constants.SERVICE_SEARCH_NAME, server + Constants.SERVICE_SEARCH_ADDRESS);
        		
		//Security		
        remote = locatorSearch.getPort(ConsultasLManager.class);
        axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "rhcolaboravm.axisversion2.services.CentroManager.PWCallback");
               
        searchService = (ConsultasManagerSoapBindingStub)axisPort; 
        
        //License service
		licenseService = new LicenseService(server, user, password);
        
        LOG.debug("Initialized WS connection");
	}
	
	/* (non-Javadoc)
	 * @see com.tid.service.Service#create(java.lang.Object)
	 */
	@Override
	public int create(Object value) throws Exception {
		//Default error
		int result = Errors.NO_ERROR;
		
		//Verify class		
		if (value.getClass()!=ServicioVO.class) 
			throw (new Exception("Not valid class")); 
					
		ServicioVO serviceVO = (ServicioVO)value;
		
		// Verify used licenses
		LOG.info("Verifying licenses");
		
		//Verificamos que existan suficientes licencias disponibles para 
		//la solicitud realizada		
		if (licenseService.getEnabledLicensesService(serviceVO.getCentro().getIdCentro(), Constants.NOT_CREATED)<serviceVO.getLicencias()) {
			result = Errors.MAX_LICENSES;
		}		
		
		if (result >=0){
	        LOG.info("Calling create service");
	        
	        //Calling service
	        result = service.insertServicio(serviceVO);
		}
              									
		return result;		

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object value) throws Exception {
		//Verify class		
		if (value.getClass()!=ServicioVO.class) 
			throw (new Exception("Not valid class")); 
					
		ServicioVO serviceVO = (ServicioVO)value;
					
		LOG.info("Calling delete service");
        
        //Calling service
		service.deleteServicio(serviceVO);

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#update(java.lang.Object)
	 */
	@Override
	public int update(Object value) throws Exception {
		//Default error
		int result = Errors.NO_ERROR;
		
		//Verify class		
		if (value.getClass()!=ServicioVO.class) 
			throw (new Exception("Not valid class")); 
					
		ServicioVO serviceVO = (ServicioVO)value;
			
		// Verify used licenses
		LOG.info("Verifying licenses");
				
		//Verificamos que existan suficientes licencias disponibles para 
		//la solicitud realizada		
		if (licenseService.getEnabledLicensesService(serviceVO.getCentro().getIdCentro(), serviceVO.getIdServicio())<serviceVO.getLicencias()) {
			result = Errors.MAX_LICENSES;
		}
		//Verificamos que no se indique un número de licencias menor del ya usado	
		else if (licenseService.getUsedLicensesService(serviceVO.getIdServicio())>serviceVO.getLicencias()) {
			result = Errors.MIN_LICENSES;
		}	
		
		if (result >=0){	
			LOG.info("Calling update service");
	        //Calling service
	        result = service.updateServicio(serviceVO);
		}
                				
		return result;

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#search(java.lang.String)
	 */
	@Override
	public Object[] search(String query) throws Exception {
		
		Object[] results = null;
		String sql = ServiceSql.getSearchQuery(query);
		LOG.info("Searching services by query: " + query);
		
		// Get results
		results = searchService.realizarConsultaResultado(sql, ServiceSql.ID_ENTITY, "");
								
		return results;

	}
	
	public Object[] search(String centerId, String query) throws Exception {
		
		Object[] results = null;
				
		LOG.info("Searching services by center " + centerId + " and query: " + query);
		
		String sql = ServiceSql.getSearchQuery(centerId, query);
								
		// Get results
		results = searchService.realizarConsultaResultado(sql, ServiceSql.ID_ENTITY, "");
											
		return results;
	}
	
	
	/**
	 * Return all center's services
	 * @return
	 */
	public Object[] getServices(CentroVO center) {
		
        LOG.info("Getting services");
               
        Object[] services = null;
		try {
			//Calling service
			services = (Object[])service.getServicios(center.getIdCentro());
		} catch (RemoteException e) {
			//if error return null			
		}
                        
		return services;
		
	}
	
	/**
	 * Return service's count
	 * @return
	 * @throws RemoteException 
	 */
	public int getUsersCount(String serviceId) throws RemoteException {
		
        LOG.debug("Getting users");
               
        LOG.debug("Searching users' count by service" + serviceId);
		
		String sql = UserSql.getCount(serviceId);
								
		// Get results
		int results = searchService.realizarConsultaContador(sql);
											
		return results;
		
	}

}
