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

//import com.tid.atica.htc.core.dao.remote.ws.LicensesLManager;
//import com.tid.atica.htc.core.dao.remote.ws.LicensesLManagerServiceLocator;
//import com.tid.atica.htc.core.dao.remote.ws.LicensesSoapBindingStub;

import es.tid.ehealth.colabora.licenses.bl.manager.remote.ws.LicensesLManager;
import es.tid.ehealth.colabora.licenses.bl.manager.remote.ws.LicensesLManagerServiceLocator;
import es.tid.ehealth.colabora.licenses.bl.manager.remote.ws.LicensesManagerSoapBindingStub;

import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorServiceLocator;
import com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasManagerSoapBindingStub;

public class LicenseService  {
	
	private static final Logger LOG = Logger.getLogger(LicenseService.class.getName());
		
	private LicensesLManager service = null;
	private ConsultasLManager searchService = null;
	
	public LicenseService(String server, String user, String password) throws Exception {
		
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
		
		//License service
		LicensesLManagerServiceLocator locator = new LicensesLManagerServiceLocator(config);
		locator.setEndpointAddress(Constants.SERVICE_LICENSES_NAME, server + Constants.SERVICE_LICENSES_ADDRESS);
		
		//Security		
        remote = locator.getPort(LicensesLManager.class);
        axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "com.tid.service.PWCallback");
               
        service = (LicensesManagerSoapBindingStub)axisPort;
        
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
        
        LOG.debug("Initialized WS connection");
        	
	}
	
	
	/**
	 * Return if there are enabled licenses for the user
	 * @param user
	 * @return
	 * @throws RemoteException 
	 */
	public boolean isEnabledLicenses(UserVO user) {
		boolean result = false;
		
		try {
			result =  service.enabledLicenses(user);
		} catch (RemoteException e) {
			result = false;
			LOG.error(e);
		}		
		return result;

	}
	
	/**
	 * Return enabled licenses for the center
	 * @param center
	 * @return
	 */
	public int getEnabledLicensesCenter(int centerId) {
		int result = 0;
				
		try {
			result = service.numberAvailableLicensesCenter(centerId);
		} catch (RemoteException e) {
			result = Errors.GENERIC_ERROR;
			LOG.error(e);
		}		
		return result;
	}
		
	
	/**
	 * Return used licenses in center
	 * @param center
	 * @return
	 */
	public int getUsedLicensesCenter(int centerId) {
		int result = 0;				
		
		try {
			result = service.getUsedLicensesCenter(centerId);
		} catch (RemoteException e) {
			result = Errors.GENERIC_ERROR;
			LOG.error(e);
		}
				
		return result;
	}
	
	/**
	 * Return used licenses in service
	 * @param center
	 * @return
	 */
	public int getUsedLicensesService(int serviceId) {
		int result = 0;				
		
		try {
			result = service.getUserByService(serviceId);
		} catch (RemoteException e) {
			result = Errors.GENERIC_ERROR;
			LOG.error(e);
		}
				
		return result;
	}
	
	/**
	 * Return enabled licenses for the service
	 * @param center
	 * @return
	 */
	public int getEnabledLicensesService(int centerid, int serviceId) {
		int result = 0;
		
		try {
			result = service.numberAvailableLicensesService(centerid, serviceId);
		} catch (RemoteException e) {
			result = Errors.GENERIC_ERROR;
			LOG.error(e);
		}
		
		return result;
	}
	
	/**
	 * Return enabled licenses for the client
	 * @return
	 */
	public int getLicenses() {
		int licenses = -1;
		
		LOG.debug("Getting licenses");
		
		try {
			licenses = service.getLicenses();
		} catch (RemoteException e) {			
			LOG.error("Error on getting licenses: " + e.getMessage());
		}
		
		return licenses;
	}
		
	/**
	 * Return used licenses in client
	 * @return
	 */
	public int getUsedLicenses() {
		int licenses = -1;
								
		try {						
			licenses = service.getUsedLicensesClient();
			
		} catch (RemoteException e) {
			LOG.error("Error on getting used licenses: " + e.getMessage());
		}
		
		return licenses;
	}
	
	/**
	 * Set licenses in client
	 * @param licenses
	 */
	public int setLicenses(int licenses) {
		
		int result = Errors.GENERIC_ERROR;		
		
		try {
			
			if (licenses<service.getUsedLicensesClient()) {
				result = Errors.MIN_LICENSES;
			}
			else {			
				service.setLicenses(licenses);
				result = licenses;
			}
		} catch (RemoteException e) {
			LOG.error("Error on setting licenses: " + e.getMessage());
			result = Errors.GENERIC_ERROR;
		}
		
		return result;
		
	}
	
	

}
