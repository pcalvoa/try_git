package com.tid.service.impl.sql;

import com.tid.Constants;
import com.tid.Fields;
import com.tid.util.QueryConstructor;

public class UserSql {

	static final private String ID_ENTITY = "idUsuario";
	
	// QUERY
	static final private String USER_SELECT = "U.idUsuario AS "
			+ Fields.USER_ID + ", U.Usuario AS " + Fields.USER_USER
			+ ", U.Nombre AS " + Fields.USER_NAME + ", U.Apellidos AS "
			+ Fields.USER_SURNAME + ", U.Contrasenna AS "
			+ Fields.USER_PASSWORD + ", U.Correo AS " + Fields.USER_EMAIL
			+ ", U.Telefono AS " + Fields.USER_PHONE + ", U.Movil AS "
			+ Fields.USER_MOBILE + ", U.Observaciones AS "
			+ Fields.USER_OBSERVATIONS + ", U.Idioma AS " + Fields.USER_LANG
			+ ", P.idPerfil AS " + Fields.PROFILE_ID + ", P.Nombre AS "
			+ Fields.PROFILE_NAME + ", S.idServicio AS " + Fields.SERVICE_ID
			+ ", S.Nombre AS " + Fields.SERVICE_NAME + ", C.idCentro AS "
			+ Fields.CENTER_ID + ", C.Nombre AS " + Fields.CENTER_NAME
			+ ", D.Usuario AS " + Fields.USER_EMAIL_USER
			+ ", D.Contrasenna AS " + Fields.USER_EMAIL_PASSWORD	
	  		+ ", U.PreguntaSeguridad AS " + Fields.USER_SECURITY_QUESTION
	  		+ ", U.RespuestaSeguridad AS " + Fields.USER_SECURITY_ANSWER
	  		+ ", U.CodigoSeguridad AS " + Fields.USER_RECOVERY_CODE
	  		+ ", U.FechaCaducidad AS " + Fields.USER_RECOVERY_EXPIRATION_DATE;
	 
	
	static final private String USER_TABLE = "Usuario U INNER JOIN Servicio S ON ( U.idServicio=S.idServicio ) INNER JOIN Centro C ON ( S.idCentro=C.idCentro ) INNER JOIN Perfil P ON ( U.idPerfil=P.idPerfil ) LEFT OUTER JOIN Datos_Correo D ON ( D.idUsuario=U.idUsuario )";

	static final private String USER_WHERE = "U.idUsuario > 10 AND U.Contrasenna <> ''";

	static final private String USER_FIELD1 = "U.Nombre";
	static final private String USER_FIELD2 = "U.Apellidos";
	static final private String USER_FIELD3 = "U.Usuario";

	static final private String USER_GROUP = "U.idUsuario";

	static final private String USER_ORDER = "U.Apellidos, U.Nombre";
	
	public static String getEntity() {
		return ID_ENTITY;
	}
	
	/**
	 * Return a standard query to search
	 * @return
	 */
	public static String getSearchQuery(String query) {
		
		String[] fields = { USER_FIELD1, USER_FIELD2, USER_FIELD3 };
		String[] values = { query, query, query };

		String sql = QueryConstructor.getSelect(USER_SELECT, USER_TABLE,
				USER_WHERE, USER_GROUP, USER_ORDER, fields, values);
		
		return sql;

		
	}
	
	/**
	 * Return a standard query to search by recovery password's code
	 * @return
	 */
	public static String getSearchCode(String code) {
						
		String where = USER_WHERE + " AND U.CodigoSeguridad='" + code
			+ "' AND DATEDIFF(NOW(),U.FechaCaducidad) <= "  + Constants.CONFIG_VALID_RECOVERY_PASSWORD_DAYS;
										
		//FIXME Probar
		String sql = QueryConstructor.getSelect(USER_SELECT, USER_TABLE,
				where, USER_GROUP, USER_ORDER, null, null);
		
		return sql;
		
	}
	
	/**
	 * Return a standard query to search by email
	 * @return
	 */
	public static String getSearchEmail(String email) {
				
		String where = USER_WHERE + " AND U.Correo= '" + email + "'";
									
		//FIXME Probar
		String sql = QueryConstructor.getSelect(USER_SELECT, USER_TABLE,
			where, USER_GROUP, USER_ORDER, null, null);
	
		return sql;
		
	}
	
	/**
	 * Return a standard query to search
	 * @return
	 */
	public static String getSearchQuery(String centerId, String serviceId, String query) {

		String[] fields = null;
		String[] values = null;

		if (query != null && !"".equals(query)) {
			fields = new String[3];
			fields[0] = USER_FIELD1;
			fields[1] = USER_FIELD2;
			fields[2] = USER_FIELD3;
			values = new String[3];
			values[0] = values[1] = values[2] = query;
		}

		// Custom WHERE
		String where = USER_WHERE;

		// Verify parameters
		if (serviceId != null && !"".equals(serviceId)) {
			where += " AND U.idServicio=" + serviceId + " AND S.idCentro="
					+ centerId;
		} else if (centerId != null && !"".equals(centerId)) {
			where += " AND S.idCentro=" + centerId;
		}

		String sql = QueryConstructor.getSelect(USER_SELECT, USER_TABLE, where,
				USER_GROUP, USER_ORDER, fields, values);
		
		return sql;
	}
	
	
	/**
	 * Return a standar query to get the users' count by service
	 * @param centerId
	 * @return
	 */
	public static String getCount(String serviceId) {
							
		//Custom WHERE
		String where = "";
						
		//Verify parameters
		if(serviceId!=null && !"".equals(serviceId)){
			where = "idServicio = " + serviceId;
		}
		
		String sql = QueryConstructor.getSelect("COUNT(*)", 
				"Usuario", 
				where,
				"",
				"",
				null, null);
				
		return sql;
	}
}
