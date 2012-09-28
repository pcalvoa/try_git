package com.tid.util;

import com.tid.Fields;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.PerfilVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;

public class Parser {

	public static UserVO getUser(Entidad value) {				
		
		UserVO user = new UserVO();
				
		user.setIdUsuario(Integer.parseInt(getValue(value,Fields.USER_ID)));
		user.setUsuario(getValue(value,Fields.USER_USER));
		user.setNombre(getValue(value,Fields.USER_NAME));
		user.setApellidos(getValue(value,Fields.USER_SURNAME));
		user.setContrasena(getValue(value,Fields.USER_PASSWORD));
		user.setCorreo(getValue(value,Fields.USER_EMAIL));
		user.setUsuarioCorreo(getValue(value,Fields.USER_EMAIL_USER));
		user.setContrasenaCorreo(getValue(value,Fields.USER_EMAIL_PASSWORD));
		user.setTelefono(getValue(value,Fields.USER_PHONE));
		user.setMovil(getValue(value,Fields.USER_MOBILE));
		user.setObservaciones(getValue(value,Fields.USER_OBSERVATIONS));
		
		PerfilVO profile = new PerfilVO();
		profile.setIdPerfil(Integer.parseInt(getValue(value,Fields.PROFILE_ID)));
		profile.setNombre(getValue(value,Fields.PROFILE_NAME));
		
		user.setPerfil(profile);
		
		CentroVO center = new CentroVO();
		center.setIdCentro(Integer.parseInt(getValue(value,Fields.CENTER_ID)));
		
		ServicioVO service = new ServicioVO();
		service.setIdServicio(Integer.parseInt(getValue(value,Fields.SERVICE_ID)));
		service.setCentro(center);
				
		user.setServicio(service);
		
		user.setCodigoSeguridad(getValue(value,Fields.USER_RECOVERY_CODE));
		//FIXME pendiente de añadir conversor con formato
		//user.setFechaContrasenna(getValue(value,Fields.USER_RECOVERY_EXPIRATION_DATE));
		//user.setFechaCaducidad(fechaCaducidad)
		user.setPreguntaSeguridad(getValue(value,Fields.USER_SECURITY_QUESTION));
		user.setRespuestaSeguridad(getValue(value,Fields.USER_SECURITY_ANSWER));
		
		return user;
	}
	
	
	private static String getValue(Entidad value, String field) {
		String result = "";
		try {
			result = value.getAtributos().get(field).toString();
		}
		catch(Exception e) {		
		}
		
		return result;
	}

}
