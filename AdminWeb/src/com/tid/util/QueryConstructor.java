package com.tid.util;

import com.tid.Constants;

public class QueryConstructor {

	static public String getSelect(String select, String table, String where, String group, String order, String[] fields, String[] values) {
		StringBuilder query = new StringBuilder(Constants.QUERY_SELECT);
		query.append(" ").append(select).append(" ").append(Constants.QUERY_FROM).append(" ").append(table);
				
		//WHERE
		StringBuilder strWhere = new StringBuilder();			
		
		if (where != null && !("".equals(where))) {
			
			strWhere.append(" ").append(Constants.QUERY_WHERE);
			strWhere.append(" ").append(where);							
			
		}		
		
		if (fields!=null && values !=null 
				&& fields.length > 0 && fields.length == values.length) {
		
			if ("".equals(strWhere.toString())) {
				strWhere.append(" ").append(Constants.QUERY_WHERE);
			}
			else {
				strWhere.append(" AND ");
			}
			
			strWhere.append("(");
															
			for (int i=0;i<fields.length;i++) {							
				strWhere.append(" ").append(fields[i]).append(" ");
				strWhere.append(Constants.QUERY_OP_BEGIN).append(values[i]);
				strWhere.append(Constants.QUERY_OP_END);
				
				//Add connector
				if (i+1< fields.length) strWhere.append(" ").append(Constants.QUERY_CONNECTOR);
			}
			
			strWhere.append(") ");
			
		}
		
		//Add definitive conditions
		query.append(strWhere);		
				
		//GROUP
		if (group != null && !("".equals(group))) {
			query.append(" ").append(Constants.QUERY_GROUP).append(" ").append(group);
		}
		
		//ORDER
		if (order != null && !("".equals(order))) {
			query.append(" ").append(Constants.QUERY_ORDER).append(" ").append(order);
		}
		
		return query.toString();
	}
	
}
