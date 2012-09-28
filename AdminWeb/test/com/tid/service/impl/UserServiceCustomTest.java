package com.tid.service.impl;

import java.util.ArrayList;
import java.util.Calendar;

import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.DispositivoVO;
import com.tid.atica.htc.core.bean.PerfilVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.SubperfilVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.util.encriptacion.MD5;
import com.tid.util.Formatter;

import junit.framework.TestCase;

public class UserServiceCustomTest extends TestCase {

	static final String SERVER = "http://leprechaunvm:8080/axistest";
	static final String USER = "sergio";
	static final String PASSWORD = "sergio";
	static final String searchQuery = "man";

	static final int CENTER_ID = 62;
	static final int SERVICE_ID = 33;

	private UserService service;

	@Override
	protected void setUp() throws Exception {
		service = null;

		try {
			service = new UserService(SERVER, USER, PASSWORD);
		} catch (Exception e) {
			this.fail("Error on service creation");
		}

		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		service = null;
	}

	
	public void testUser() {

		CentroVO center = new CentroVO();
		center.setIdCentro(CENTER_ID);

		this.assertNotNull(center);

		ServicioVO serviceVO = new ServicioVO();
		serviceVO.setIdServicio(SERVICE_ID);
		serviceVO.setCentro(center);

		this.assertNotNull(serviceVO);

		PerfilVO profile = new PerfilVO();

		profile.setIdPerfil(3);

		UserVO user = new UserVO();
				

		user.setUsuario(String.valueOf(Calendar.getInstance().getTimeInMillis())); 
		user.setNombre(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		user.setApellidos(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		user.setContrasena(MD5.digest("user"));
		user.setTelefono("123456789");
		user.setMovil("123456789");
		user.setContrasenaCorreo("user");
		user.setUsuarioCorreo("Usuario test");
		user.setCorreo("test@mail.com");
		user.setPerfil(profile);
		user.setServicio(serviceVO);
		user.setObservaciones("Test remark");
		
		user.setCentrosAdministrados(new ArrayList<CentroVO>());
		user.setClaveMovilidad("");
		user.setDispositivos(new ArrayList<DispositivoVO>());
		user.setFechaAlta(Calendar.getInstance().getTime());
		user.setIdioma("es");
		user.setInformes(0);
		user.setSubperfiles(new ArrayList<SubperfilVO>());
		user.setPreguntaSeguridad("test");
		user.setRespuestaSeguridad("test");
				
		this.assertNotNull(user);

		// Create
		int result = 0;

		try {
			result = service.create(user);

		} catch (Exception e) {
			this.fail("Error on user creation");
		}

		this.assertTrue(result > 0);

		user.setIdUsuario(result);
		user.setNombre("User test modificado");

		try {
			result = service.update(user);

		} catch (Exception e) {
			this.fail("Error on user update");
		}

		this.assertTrue(result > 0);

		user.setContrasena("modificada");

		try {
			service.changePassword(user.getUsuario(), user.getContrasena());

		} catch (Exception e) {
			this.fail("Error on user change password");
		}
		

		try {
			service.delete(user);

		} catch (Exception e) {
			this.fail("Error on user delete");
		}


	}

	
	 public void testSearch() { 
		 Object[] results = null; 
		 try { 
			 results = service.search(searchQuery); 
		 } catch (Exception e) {
			 this.fail("Error on center search"); 
		 }
	 
		 this.assertNotNull(results);
	 
		 try { 
			 for (Object result:results) { 
				 Formatter.getJSONUser(result, 0); 
			 } 
		 }
		 catch (Exception e) { 
			 this.fail("Error on center search"); 
		}
	 
	 }
	 
	 public void testSearchCenterService() {
	 
		 Object[] results = null; 
		 try { 
			 results = service.search(String.valueOf(CENTER_ID), String.valueOf(SERVICE_ID), "");
	 
		 } 
		 catch (Exception e) { 
			 this.fail("Error on center search"); 
		 }
	 
		 this.assertNotNull(results);
	 
		 try { 
			for (Object result:results) { 
				 Formatter.getJSONUser(result, 0); 
			} 
		 }
		 catch (Exception e) { 
			 this.fail("Error on center search"); 
		 }
	 
		 try { 
			 results = service.search(String.valueOf(CENTER_ID), "", ""); 
		 } 
		 catch (Exception e) { 
			 this.fail("Error on center search"); 
		 }
	 
		 this.assertNotNull(results);
	 
		 try { 
			 for (Object result:results) { 
				 Formatter.getJSONUser(result, 0); 
			 } 
		 }	 
		 catch (Exception e) { 
			 this.fail("Error on center search"); 
		 }
	 
	 }
	


}
