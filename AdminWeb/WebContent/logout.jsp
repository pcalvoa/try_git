<%@ page import="com.tid.Constants"%>

<%

  	session.setAttribute("sUserID",null);
	session.setAttribute("sUserRole",null);
	session.setAttribute("sUserName",null);
 	session.invalidate();
  	response.sendRedirect("index.jsp");
%>
