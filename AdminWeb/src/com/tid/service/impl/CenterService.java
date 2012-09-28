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

import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.dao.remote.ws.CentroLManager;
import com.tid.atica.htc.core.dao.remote.ws.CentroLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.CentroManagerSoapBindingStub;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorServiceLocator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasManagerSoapBindingStub;

import com.tid.service.Service;
import com.tid.service.impl.sql.CenterSql;
import com.tid.service.impl.sql.ServiceSql;

/**
 * Centers' service
 * 
 * @author fdelatorre
 *
 */
public class CenterService implements Service {

	private static final Logger LOG = Logger.getLogger(CenterService.class.getName());
	private CentroLManager service = null;	
	private ConsultasLManager searchService = null;
	private LicenseService licenseService = null;
		
	
	public CenterService(String server, String user, String password) throws Exception {
		super();
							
		LOG.debug("Initializing ws connection");
		
		RemoteActivator remoteActivator = null;
		
		//Init conection con ws
		EngineConfiguration config = new FileProvider(Constants.CONFIG_SECURITY_FILE);
		
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
		CentroLManagerServiceLocator locator = new CentroLManagerServiceLocator(config);
		locator.setEndpointAddress(Constants.SERVICE_CENTER_NAME, server + Constants.SERVICE_CENTER_ADDRESS);
		
		//Security		
        remote = locator.getPort(CentroLManager.class);
        axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "com.tid.service.PWCallback");
               
        service = (CentroManagerSoapBindingStub)axisPort;
        
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

	//private CentroManager centroManager = CentroManager.createCentroManager ();
	
	/* (non-Javadoc)
	 * @see com.tid.service.Service#create(java.lang.Object)
	 */
	@Override
	public int create(Object value) throws Exception {
					
		//Default error
		int result = Errors.NO_ERROR;
						
		//Verify class		
		if (value.getClass()!=CentroVO.class) 
			throw (new Exception("Not valid class")); 
					
		CentroVO center = (CentroVO)value;
				
		// Verify used licenses
		LOG.info("Verifying licenses");
		
		//Verificamos que existan suficientes licencias disponibles para 
		//la solicitud realizada		
		if (licenseService.getEnabledLicensesCenter(Constants.NOT_CREATED)<center.getLicencias()) {
			result = Errors.MAX_LICENSES;
		}		
		
		if (result >=0){
			LOG.debug("Calling create center");
	        
	        //Calling service
	        result = service.insertCentro(center);
		}
				                                      								
		return result;								
	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object value) throws Exception{
		
		//Verify class		
		if (value.getClass()!=CentroVO.class) 
			throw (new Exception("Not valid class")); 
					
		CentroVO center = (CentroVO)value;
		
		LOG.debug("Calling delete center");
        
        //Calling service
		service.deleteCentro(center);
       
	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#edit(java.lang.Object)
	 */
	@Override
	public int update(Object value) throws Exception{

		//Default error
		int result = Errors.NO_ERROR;
						
		
		//Verify class		
		if (value.getClass()!=CentroVO.class) 
			throw (new Exception("Not valid class")); 
					
		CentroVO center = (CentroVO)value;
		
		// Verify used licenses
		LOG.info("Verifying licenses");

		//Verificamos que existan suficientes licencias disponibles para 
		//la solicitud realizada		
		if (licenseService.getEnabledLicensesCenter(center.getIdCentro())<center.getLicencias()) {
			result = Errors.MAX_LICENSES;
		}
		//Verificamos que no se indique un número de licencias menor del ya usado	
		else if (licenseService.getUsedLicensesCenter(center.getIdCentro())>center.getLicencias()) {
			result = Errors.MIN_LICENSES;
		}	
		
		if (result >=0){		
			LOG.debug("Calling update center");
	        //Calling service
	        result = service.updateCentro(center);
		}
                                		
		return result;

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#search(java.lang.String)
	 */
	@Override
	public Object[] search(String query) throws Exception{		
		Object[] results = null;
									
		String sql = CenterSql.getSearchQuery(query);
		
		LOG.debug("Searching centers by query: " + query);
				
		// Get results
		results = searchService.realizarConsultaResultado(sql, CenterSql.ID_ENTITY, "");
								
		//return searchService.realizarConsultaResultado(query, "", "");
		return results;
						
	}
	
	public Object[] searchAdministrated(String userId) throws Exception{		
		Object[] results = null;
									
		String sql = CenterSql.getSearchAdministrated(userId);
		
		LOG.debug("Searching administrated centers by userId: " + userId);
				
		// Get results
		results = searchService.realizarConsultaResultado(sql, CenterSql.ID_ENTITY, "");
								
		//return searchService.realizarConsultaResultado(query, "", "");
		return results;
						
	}
	
	/**
	 * Return all centers
	 * @return
	 */
	public Object[] getCenters( )  {
												
		  LOG.debug("Getting centers");
               
        Object[] centers = null;
		try {
			//Calling service
			centers = (Object[])service.getCentros();
		} catch (RemoteException e) {
			//if error return null			
		}
                        
		return centers;
		
	}
		
	
	/**
	 * Return service's count
	 * @return
	 * @throws RemoteException 
	 */
	public int getServicesCount(String centerId) throws RemoteException {
		
        LOG.debug("Getting services"); 
        LOG.info("Searching services' count by center " + centerId);
	
		String sql = ServiceSql.getCount(centerId);
								
		// Get results
		int results = searchService.realizarConsultaContador(sql);
	
        //Don't count Default service
		results = results - 1;
			
		LOG.info("Number of services " + results);
		return results;
		
	}

}
