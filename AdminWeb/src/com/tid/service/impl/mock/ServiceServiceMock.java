/**
 * 
 */
package com.tid.service.impl.mock;

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
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.dao.remote.ws.ServicioLManager;
import com.tid.atica.htc.core.dao.remote.ws.ServicioLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.ServicioManagerSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.service.Service;

/**
 * Services' service
 * 
 * @author fdelatorre
 *
 */
public class ServiceServiceMock implements Service {

	private static final Logger LOG = Logger.getLogger(ServiceServiceMock.class.getName());
	
	private ServicioLManager service = null;
	
	public ServiceServiceMock(String server, String user, String password) throws Exception {
		super();
		//Init conection con ws
		EngineConfiguration config = new FileProvider(Constants.CONFIG_SECURITY_FILE);
		
		//Center service
		ServicioLManagerServiceLocator locator = new ServicioLManagerServiceLocator(config);
		locator.setEndpointAddress(Constants.SERVICE_SERVICE_NAME, server + Constants.SERVICE_SERVICE_ADDRESS);
		
		//Security		
        Remote remote = locator.getPort(ServicioLManager.class);
        Stub axisPort = (Stub)remote;
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
               
        //searchService = (ConsultasManagerSoapBindingStub)axisPort; 
	}
	
	/* (non-Javadoc)
	 * @see com.tid.service.Service#create(java.lang.Object)
	 */
	@Override
	public int create(Object value) throws Exception {
		int idService = -1;
		
		//Verify class		
		if (value.getClass()!=ServicioVO.class) 
			throw new Exception("Not valid class"); 
					
		ServicioVO serviceVO = (ServicioVO)value;
		
		//TODO: Validate center information
					
        LOG.debug("Calling service...");
        
        //Calling service
        idService = service.insertServicio(serviceVO);
              									
		return idService;		

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
		
		//TODO: Validate center information
					
        LOG.debug("Calling service...");
        
        //Calling service
		service.deleteServicio(serviceVO);

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#update(java.lang.Object)
	 */
	@Override
	public int update(Object value) throws Exception {
		int idService = -1;
		
		//Verify class		
		if (value.getClass()!=ServicioVO.class) 
			throw (new Exception("Not valid class")); 
					
		ServicioVO serviceVO = (ServicioVO)value;
		
		//TODO: Validate center information					
        LOG.debug("Calling service...");

        //Calling service
        idService = service.updateServicio(serviceVO);
                				
		return idService;

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#search(java.lang.String)
	 */
	@Override
	public Object[] search(String query) throws Exception {
		//TODO Define parameters
		//return searchService.realizarConsultaResultado(query, "", "");
		
		//TODO: Eliminar Test condition
		//Create test centers
		if ("error".equals(query)){
			return null;
		}
		else {
			ServicioVO[] result = new ServicioVO[25];
			CentroVO center = new CentroVO(1, "Centro 1", "Descripción", 0);
			for (int i=0;i<25;i++) {				
				ServicioVO serviceVO = new ServicioVO();
				serviceVO.setIdServicio(i);
				serviceVO.setDescripcion("description");
				serviceVO.setNombre("service " + i);
				serviceVO.setCentro(center);
				result[i] = serviceVO;				
			}
			return result;
		}
	}
	
	/**
	 * Return all center's services
	 * @return
	 */
	public ServicioVO[] getServices(CentroVO center) {
		
		LOG.debug("Calling service...");
               
		ServicioVO[] services = null;
		try {
			//Calling service
			services = (ServicioVO[])service.getServicios(center.getIdCentro());
		} catch (RemoteException e) {
			//if error return null			
		}
                                
		return services;
	}

}
