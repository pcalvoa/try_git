package com.tid.service.impl.sql;

import com.tid.Fields;
import com.tid.util.QueryConstructor;

public class ServiceSql {

	public static final String ID_ENTITY = "idServicio";
	
	//QUERY
	private static final String SERVICE_SELECT = "S.idServicio, S.Nombre AS " + Fields.SERVICE_NAME + 
												 ", S.Descripcion AS " 		  + Fields.SERVICE_DESCRIPTION + 
												 ", S.NumLicencias AS " 	  + Fields.LICENSES +
												 ", C.idCentro AS " 		  + Fields.CENTER_ID + 
												 ", C.Nombre AS " 		  	  + Fields.CENTER_NAME;
	private static final String SERVICE_TABLE = "Centro C, Servicio S";
	private static final String SERVICE_WHERE = "S.idCentro = C.idCentro AND S.idServicio > 10";
	private static final String SERVICE_WHERE_WITHOUT_DEFAULT = "S.idCentro = C.idCentro AND S.idServicio > 10 AND S.Nombre <> 'Default'";	
	private static final String SERVICE_FIELD = "S.Nombre";
	private static final String SERVICE_GROUP = "idServicio";
	private static final String SERVICE_ORDER = "S.Nombre";
	
	
	/**
	 * Return a standard query to search
	 * @return
	 */
	public static String getSearchQuery(String query) {
					
		String[] fields = { SERVICE_FIELD };
		String[] values = { query };
		
		String sql = QueryConstructor.getSelect(SERVICE_SELECT, 
				SERVICE_TABLE, 
				SERVICE_WHERE_WITHOUT_DEFAULT,
				SERVICE_GROUP,
				SERVICE_ORDER,
				fields, values);
		
		return sql;

		
	}
	
	/**
	 * Return a standard query to search
	 * @return
	 */
	public static String getSearchQuery(String centerId, String query) {
		
		String[] fields = null;
		String[] values = null;
		
		if (query!=null && !"".equals(query)) {
			fields = new String[1];
			fields[0] = SERVICE_FIELD;
			values = new String[1];
			values[0] = query;
		}
							
		//Custom WHERE without default (Search services view)
		String where = SERVICE_WHERE_WITHOUT_DEFAULT;
		
		//Custom WHERE to get Services from a Center. I want also the default service
		if (centerId != null && !centerId.equals("")) {
			where = SERVICE_WHERE + " AND S.idCentro=" + centerId;
		}
				
		String sql = QueryConstructor.getSelect(SERVICE_SELECT, 
				SERVICE_TABLE, 
				where,
				SERVICE_GROUP,
				SERVICE_ORDER,
				fields, values);
		
		return sql;
	}
	
	/**
	 * Return a standar query to get the service's count by center
	 * @param centerId
	 * @return
	 */
	public static String getCount(String centerId) {
							
		//Custom WHERE
		String where = "";
						
		//Verify parameters
		if(centerId != null && !centerId.equals("")){
			where = "idCentro = " + centerId;
		}

		String sql = QueryConstructor.getSelect("COUNT(*)", 
				"Servicio", 
				where,
				"",
				"",
				null, null);
				
		return sql;
	}
	
}
