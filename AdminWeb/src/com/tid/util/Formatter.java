package com.tid.util;

import com.tid.Fields;
import com.tid.atica.htc.core.bean.CentroVO;
import com.tid.atica.htc.core.bean.ServicioVO;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.pojo.Client;
import com.tid.pojo.Licenses;

/**
 * Format objects as json
 * 
 * @author fdelatorre
 *
 */
public class Formatter {

	public static String toJSON(Object value, Class type, int count) {
		 				
		if (type.equals(CentroVO.class)) {
			return getJSONCenter(value, count);
		}
		else if (type.equals(ServicioVO.class)) {
			return getJSONService(value, count);
		} 
		else if (type.equals(UserVO.class)) {
			return getJSONUser(value, count);
		}
		else if (type.equals(Client.class)) {
			return getJSONClient(value, count);
		}
		else if (type.equals(Licenses.class)) {
			return getJSONLicenses(value, count);
		}

		return null;
		
	}
	
	public static String getJSONClient(Object client, int count) {	
		
		Client value = Client.class.cast(client);
		
		StringBuilder json = new StringBuilder("{");
		json.append("\"count\":").append("\"").append(count).append("\"").append(",");
		json.append("\"name\":").append("\"").append(value.getName()).append("\"").append(",");
		json.append("\"description\":").append("\"").append(value.getDescription()).append("\"");
		json.append("}");
		return json.toString();
	}
	
	public static String getJSONLicenses(Object licenses, int count) {	
		
		Licenses value = Licenses.class.cast(licenses);
		
		StringBuilder json = new StringBuilder("{");
		json.append("\"count\":").append("\"").append(count).append("\"").append(",");
		json.append("\"total\":").append("\"").append(value.getTotalLicenses()).append("\"").append(",");
		json.append("\"used\":").append("\"").append(value.getUsedLicenses()).append("\"");
		json.append("}");
		return json.toString();
	}
	
	public static String getJSONCenter(Object center, int count) {
		 		
		StringBuilder json = new StringBuilder("{");
		
		if (center.getClass().equals(Entidad.class)) { 
		
			Entidad value = Entidad.class.cast(center);
						
			json.append("\"count\":").append("\"").append(count).append("\"").append(",");
			json.append("\"id\":").append("\"").append(getValue(value,Fields.CENTER_ID)).append("\"").append(",");
			json.append("\"name\":").append("\"").append(getValue(value,Fields.CENTER_NAME)).append("\"").append(",");			
			json.append("\"licenses\":").append("\"").append(getValue(value,Fields.LICENSES)).append("\"").append(",");
			json.append("\"description\":").append("\"").append(getValue(value,Fields.CENTER_DESCRIPTION)).append("\"");
		}
		else if (center.getClass().equals(CentroVO.class)) {
			CentroVO value = CentroVO.class.cast(center);
			
			json.append("\"count\":").append("\"").append(count).append("\"").append(",");
			json.append("\"id\":").append("\"").append(value.getIdCentro()).append("\"").append(",");
			json.append("\"name\":").append("\"").append(value.getNombre()).append("\"").append(",");			
			json.append("\"licenses\":").append("\"").append(value.getLicencias()).append("\"").append(",");
			json.append("\"description\":").append("\"").append(value.getDescripcion()).append("\"");
		}
			
		json.append("}");
		return json.toString();
	}
	
	public static String getJSONService(Object service, int count) {
		
		StringBuilder json = new StringBuilder("{");
		
		if (service.getClass().equals(Entidad.class)) { 			
			Entidad value = Entidad.class.cast(service);
			
			json.append("\"count\":").append("\"").append(count).append("\"").append(",");
			json.append("\"id\":").append("\"").append(getValue(value,Fields.SERVICE_ID)).append("\"").append(",");
			json.append("\"centerid\":").append("\"").append(getValue(value,Fields.CENTER_ID)).append("\"").append(",");
			json.append("\"centername\":").append("\"").append(getValue(value,Fields.CENTER_NAME)).append("\"").append(",");
			json.append("\"name\":").append("\"").append(getValue(value,Fields.SERVICE_NAME)).append("\"").append(",");
			json.append("\"licenses\":").append("\"").append(getValue(value,Fields.LICENSES)).append("\"").append(",");
			json.append("\"description\":").append("\"").append(getValue(value,Fields.SERVICE_DESCRIPTION)).append("\"");
		}
		else if (service.getClass().equals(ServicioVO.class)) {
			ServicioVO value = ServicioVO.class.cast(service);
			
			json.append("\"count\":").append("\"").append(count).append("\"").append(",");
			json.append("\"id\":").append("\"").append(value.getIdServicio()).append("\"").append(",");
			json.append("\"centerid\":").append("\"").append(value.getCentro().getIdCentro()).append("\"").append(",");
			json.append("\"centername\":").append("\"").append(value.getCentro().getNombre()).append("\"").append(",");
			json.append("\"name\":").append("\"").append(value.getNombre()).append("\"").append(",");			
			json.append("\"licenses\":").append("\"").append(value.getLicencias()).append("\"").append(",");
			json.append("\"description\":").append("\"").append(value.getDescripcion()).append("\"");
		}
		
		json.append("}");
		return json.toString();
	}
	
	public static String getJSONUser(Object user, int count) {				
		
		Entidad value = Entidad.class.cast(user);
		
		StringBuilder json = new StringBuilder("{");
		json.append("\"count\":").append("\"").append(count).append("\"").append(",");
		json.append("\"id\":").append("\"").append(getValue(value,Fields.USER_ID).toString()).append("\"").append(",");
		json.append("\"user\":").append("\"").append(getValue(value,Fields.USER_USER).toString()).append("\"").append(",");
		json.append("\"name\":").append("\"").append(getValue(value,Fields.USER_NAME).toString()).append("\"").append(",");
		json.append("\"surname\":").append("\"").append(getValue(value,Fields.USER_SURNAME).toString()).append("\"").append(",");								
		json.append("\"password\":").append("\"").append(getValue(value,Fields.USER_PASSWORD).toString()).append("\"").append(",");
		json.append("\"email\":").append("\"").append(getValue(value,Fields.USER_EMAIL).toString()).append("\"").append(",");
		json.append("\"emailname\":").append("\"").append(getValue(value,Fields.USER_EMAIL_USER).toString()).append("\"").append(",");
		json.append("\"emailpassword\":").append("\"").append(getValue(value,Fields.USER_EMAIL_PASSWORD).toString()).append("\"").append(",");
		json.append("\"phone\":").append("\"").append(getValue(value,Fields.USER_PHONE).toString()).append("\"").append(",");
		json.append("\"mobile\":").append("\"").append(getValue(value,Fields.USER_MOBILE).toString()).append("\"").append(",");
		json.append("\"observations\":").append("\"").append(getValue(value,Fields.USER_OBSERVATIONS).toString()).append("\"").append(",");
		json.append("\"profile\":").append("\"").append(getValue(value,Fields.PROFILE_ID).toString()).append("\"").append(",");
		json.append("\"profilename\":").append("\"").append(getValue(value,Fields.PROFILE_NAME).toString()).append("\"").append(",");
		json.append("\"serviceid\":").append("\"").append(getValue(value,Fields.SERVICE_ID).toString()).append("\"").append(",");
		json.append("\"servicename\":").append("\"").append(getValue(value,Fields.SERVICE_NAME).toString()).append("\"").append(",");
		json.append("\"centername\":").append("\"").append(getValue(value,Fields.CENTER_NAME).toString()).append("\"").append(",");
		json.append("\"centerid\":").append("\"").append(getValue(value,Fields.CENTER_ID).toString()).append("\"").append(",");
		
		json.append("\"recoverycode\":").append("\"").append(getValue(value,Fields.USER_RECOVERY_CODE).toString()).append("\"").append(",");
		json.append("\"expirationdate\":").append("\"").append(getValue(value,Fields.USER_RECOVERY_EXPIRATION_DATE).toString()).append("\"").append(",");
		json.append("\"securityquestion\":").append("\"").append(getValue(value,Fields.USER_SECURITY_QUESTION).toString()).append("\"").append(",");
		json.append("\"securityanswer\":").append("\"").append(getValue(value,Fields.USER_SECURITY_ANSWER).toString()).append("\"");
				 			
		json.append("}");
		return json.toString();
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


