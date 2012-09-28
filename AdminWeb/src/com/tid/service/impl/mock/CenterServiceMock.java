/**
 * 
 */
package com.tid.service.impl.mock;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import net.java.sip.communicator.util.Logger;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.message.token.UsernameToken;

import com.tid.Constants;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.dao.remote.ws.CentroLManager;
import com.tid.atica.htc.core.dao.remote.ws.CentroLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.CentroManagerSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.service.Service;


/**
 * Centers' service
 * 
 * @author fdelatorre
 *
 */
public class CenterServiceMock implements Service {

	private static final Logger LOG = Logger.getLogger(CenterServiceMock.class.getName());
	
	private CentroLManager service = null;
	
	//FIXME Test code
	private ArrayList<CentroVO> testResult = null;
	
	public CenterServiceMock(String server, String user, String password) throws Exception {
		super();
		
		//Init conection con ws
		EngineConfiguration config = new FileProvider(Constants.CONFIG_SECURITY_FILE);
		
		//Center service
		CentroLManagerServiceLocator locator = new CentroLManagerServiceLocator(config);
		locator.setEndpointAddress(Constants.SERVICE_CENTER_NAME, server + Constants.SERVICE_CENTER_ADDRESS);
		
		//Security		
        Remote remote = locator.getPort(CentroLManager.class);
        Stub axisPort = (Stub)remote;
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
               
        //searchService = (ConsultasManagerSoapBindingStub)axisPort;                                
		
        //FIXME Test code
		testResult = new ArrayList<CentroVO>();
		for (int i=0;i<25;i++) {
			testResult.add(new CentroVO(i, "Centro " + i, "Lorem ipsum ad his scripta blandit partiendo, eum fastidii accumsan euripidis in, eum liber hendrerit an. Qui ut wisi vocibus suscipiantur, quo dicit ridens inciderint id. Quo mundi lobortis reformidans eu, legimus senserit definiebas an eos. ", 0));
		}
        
	}

	//private CentroManager centroManager = CentroManager.createCentroManager ();
	
	/* (non-Javadoc)
	 * @see com.tid.service.Service#create(java.lang.Object)
	 */
	@Override
	public int create(Object value) throws Exception {
		
		int idCenter = -1;
		
		//Verify class		
		if (value.getClass()!=CentroVO.class) 
			throw (new Exception("Not valid class")); 
					
		CentroVO center = (CentroVO)value;
		
		//TODO: Validate center information

		LOG.debug("Calling service");
		                
        //Calling service
        //idCenter = service.insertCentro(center);
        
        //FIXME Test code
        idCenter = testResult.get(testResult.size()-1).getIdCentro()+1; 
        center.setIdCentro(idCenter);
        testResult.add(center);
                      									
		return idCenter;								
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
		
		//TODO: Validate center information
					
        LOG.debug("Calling service...");
        
        //Calling service
		//service.deleteCentro(center);
        
        //FIXME Test code
        for (CentroVO c:testResult) {
        	if (c.getIdCentro()==center.getIdCentro()) {
        		testResult.remove(c);
        		break;
        	}
        }
        

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#edit(java.lang.Object)
	 */
	@Override
	public int update(Object value) throws Exception{
		int idCenter = -1;
		
		//Verify class		
		if (value.getClass()!=CentroVO.class) 
			throw (new Exception("Not valid class")); 
					
		CentroVO center = (CentroVO)value;
		
		//TODO: Validate center information					
        LOG.debug("Calling service...");

        //Calling service
        //idCenter = service.updateCentro(center);
        
        //FIXME Test code
        for (int i=0;i<testResult.size();i++) {
        	if (testResult.get(i).getIdCentro()==center.getIdCentro()) {
        		testResult.set(i, center);
        		idCenter = center.getIdCentro();
        		break;
        	}        		
        }
                				
		return idCenter;

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#search(java.lang.String)
	 */
	@Override
	public Object[] search(String query) throws Exception{
		//TODO Define parameters
		//return searchService.realizarConsultaResultado(query, "", "");
		
		//FIXME Test code
		if ("error".equals(query)){
			return null;
		}
		else {			
			CentroVO[] result = new CentroVO[testResult.size()];
			testResult.toArray(result);
			return result;
		}
				
	}
	
	/**
	 * Return all centers
	 * @return
	 */
	public CentroVO[] getCenters( )  {
					
		//ArrayList<CentroVO> centers = new ArrayList<CentroVO>();
							
        LOG.debug("Calling service...");
        
       
        CentroVO[] centers = null;
		try {
			//Calling service
			centers = (CentroVO[])service.getCentros();
		} catch (RemoteException e) {
			//if error return null			
		}
                
        /*
        //Calling service
		try {
			for (Object c: service.getCentros()) {
				centers.add((CentroVO)c);
			}
		} catch (RemoteException e) {
			//if error return null
			centers = null;			
		}
		*/
        
		return centers;
		
	}

}
