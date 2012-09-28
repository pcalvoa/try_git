/**
 * 
 */
package com.tid.service.impl.mock;

import java.rmi.Remote;
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
import com.tid.atica.htc.core.bean.PerfilVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.dao.remote.ws.UserLManager;
import com.tid.atica.htc.core.dao.remote.ws.UserLManagerServiceLocator;
import com.tid.atica.htc.core.dao.remote.ws.UserManagerSoapBindingStub;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManager;
import com.tid.atica.htc.filtro.consultas.db.remote.ws.ConsultasLManagerServiceLocator;
import com.tid.service.Service;

/**
 * @author fdelatorre
 *
 */
public class UserServiceMock implements Service {

	private static final Logger LOG = Logger.getLogger(UserServiceMock.class.getName());
	
	private UserLManager service = null;
	
	//FIXME Test code
	private ArrayList<UserVO> testResult = null;
			
	public UserServiceMock(String server, String user, String password) throws Exception {
		super();
		//Init conection con ws
		EngineConfiguration config = new FileProvider(Constants.CONFIG_SECURITY_FILE);
		
		//Center service
		UserLManagerServiceLocator locator = new UserLManagerServiceLocator(config);
		locator.setEndpointAddress(Constants.SERVICE_USER_NAME, server + Constants.SERVICE_USER_ADDRESS);
		
		//Security		
        Remote remote = locator.getPort(UserLManager.class);
        Stub axisPort = (Stub)remote;
        axisPort._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        axisPort._setProperty(UsernameToken.PASSWORD_TYPE, WSConstants.PASSWORD_DIGEST);
        axisPort._setProperty(WSHandlerConstants.USER, user);	        	        
        axisPort._setProperty(WSHandlerConstants.PW_CALLBACK_CLASS, "com.tid.service.PWCallback");
               
        service = (UserManagerSoapBindingStub)axisPort;
        
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
		testResult = new ArrayList<UserVO>();
		
		CentroVO usrCenter = new CentroVO();
		usrCenter.setIdCentro(0);
		usrCenter.setNombre("Test Center");
		
		ServicioVO usrService = new ServicioVO();
		usrService.setCentro(usrCenter);
		usrService.setIdServicio(0);
		usrService.setNombre("Test Service");
		
		
		for (int i=0;i<25;i++) {
			UserVO usr = new UserVO();
			usr.setApellidos("Surname");
			usr.setNombre("Dr. name " + i);
			usr.setCentrosAdministrados(null);
			usr.setCorreo("test@mail.com");
			usr.setIdUsuario(i);
			usr.setObservaciones("Lorem ipsum ad his scripta blandit partiendo, eum fastidii accumsan euripidis in, eum liber hendrerit an. Qui ut wisi vocibus suscipiantur, quo dicit ridens inciderint id. Quo mundi lobortis reformidans eu, legimus senserit definiebas an eos. ");
			usr.setPerfil(new PerfilVO());
			usr.setServicio(usrService);					
			usr.setTelefono("333333333");
			usr.setUsuario("user" + i);
			testResult.add(usr);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tid.service.Service#create(java.lang.Object)
	 */
	@Override
	public int create(Object value) throws Exception {
		int idUser = -1;
		
		//Verify class		
		if (value.getClass()!= UserVO.class) 
			throw new TypeException("Not valid class"); 
					
		UserVO user = (UserVO)value;
		
		//TODO: Validate center information
					
        LOG.debug("Calling service...");
        
        //Calling service
        idUser = service.insertUser(user);
              									
		return idUser;	

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object value) throws Exception {
		//Verify class		
		if (value.getClass()!=UserVO.class) 
			throw new TypeException("Not valid class"); 

		//TODO: Validate center information
					
        LOG.debug("Calling service...");
        
        //Calling service
        
        //TODO no existe función para borrado de usuario
		

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#update(java.lang.Object)
	 */
	@Override
	public int update(Object value) throws Exception {
		int idUser = -1;
		
		//Verify class		
		if (value.getClass()!=UserVO.class) 
			throw new TypeException("Not valid class"); 
					
		UserVO user = (UserVO)value;
		
		//TODO: Validate center information					
        LOG.debug("Calling service...");

        //Calling service
        idUser = service.modifyUser(user);
                				
		return idUser;

	}

	/* (non-Javadoc)
	 * @see com.tid.service.Service#search(java.lang.String)
	 */
	@Override
	public Object[] search(String query) {
		// TODO Auto-generated method stub
		/*
		System.out.println("Calling service...");
        
		UserVO[] users = null;
		try {
			//Calling service
			//java.lang.String idCentro, java.lang.String idServicio, java.lang.String filtro,java.lang.String nombreCampo,java.lang.String valorCampo
			users = (UserVO[]])service.getUsers(arg0, arg1, arg2, arg3, arg4)
		} catch (RemoteException e) {
			//if error return null			
		}
                                
		return users;
		*/
		return new Object[0];
	}
	
	
	public Object[] search(String centerId, String serviceId, String query) {
		//TODO Define parameters
		//return searchService.realizarConsultaResultado(query, "", "");
		
		//FIXME Test code
		if ("error".equals(query)){
			return null;
		}
		else {			
			UserVO[] result = new UserVO[testResult.size()];
			testResult.toArray(result);
			return result;
		}
	}
	
	/**
	 * Return all users
	 * @return
	 */
	public UserVO[] getUsers() {		
		//TODO Add code
		return new UserVO[0];
	}

	/**
	 * Return center's users
	 * @return
	 */
	public ArrayList<UserVO> getUsers(CentroVO center) {
		//TODO Add code
		return null;
	}
	
	/**
	 * Return service's users
	 * @return
	 */
	public ArrayList<UserVO> getUsers(CentroVO center, ServicioVO service) {
		//TODO Add code
		return null;
	}


	public class TypeException extends Exception {

		public TypeException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public TypeException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
	
		
	}
	
}
