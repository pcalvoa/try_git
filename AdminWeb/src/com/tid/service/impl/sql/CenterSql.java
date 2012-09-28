package com.tid.service.impl.sql;

import com.tid.Constants;
import com.tid.Fields;
import com.tid.util.QueryConstructor;

public class CenterSql {
	
	public static final String ID_ENTITY = "idCentro";
	
	//QUERY
	static final private String CENTER_SELECT = "C.idCentro AS " + Fields.CENTER_ID + 
												", C.Nombre AS " + Fields.CENTER_NAME + 
												", C.Descripcion AS " + Fields.CENTER_DESCRIPTION + 
												", C.NumLicencias AS " + Fields.LICENSES;
	private static final String CENTER_TABLE = "Centro C";
	private static final String CENTER_ADMINISTRATED_TABLE = "Centros_Administrados A";
	//private static final String CENTER_WHERE = "C.idCentro > 10";
	private static final String CENTER_WHERE_WITHOUT_DEFAULT = "C.idCentro > 11";
	private static final String CENTER_FIELD = "C.Nombre";
	private static final String CENTER_GROUP = "C.idCentro";	
	private static final String CENTER_ORDER = "C.idCentro";
	
	
	
	/**
	 * Return a standard query to search
	 * @return
	 */
	public static String getSearchQuery(String query) {
		
		String[] fields = null;
		String[] values = null;		
		
		if (query!=null && !"".equals(query)) {
			fields = new String[1];
			fields[0] = CENTER_FIELD;
			values = new String[1];
			values[0] = query;
		}
		
		String sql = QueryConstructor.getSelect(CENTER_SELECT, 
				CENTER_TABLE, 
				CENTER_WHERE_WITHOUT_DEFAULT,
				CENTER_GROUP,
				CENTER_ORDER,
				fields, values);	
		
		return sql;

		
	}
	
	/**
	 * Return a standard query to search
	 * @return
	 */
	public static String getSearchAdministrated(String userId) {
		
		String where = CENTER_WHERE_WITHOUT_DEFAULT + " AND C.idCentro = A.idCentro AND A.idUsuario = " + userId; 
							
		String sql = QueryConstructor.getSelect(CENTER_SELECT, 
				CENTER_TABLE + ", " + CENTER_ADMINISTRATED_TABLE, 
				where,
				CENTER_GROUP,
				CENTER_ORDER,
				null, null);	
		
		return sql;
	}
	
}
