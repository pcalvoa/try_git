<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%		
	//Capture default client selection
	String client = request.getParameter(Constants.CURRENT_CLIENT);
		
	//Set value for session
  	session.setAttribute(Constants.CURRENT_CLIENT, client);
  	session.setAttribute(Constants.CLIENT_UPDATED_CENTER, true);
  	session.setAttribute(Constants.CLIENT_UPDATED_SERVICE, true);
  	session.setAttribute(Constants.CLIENT_UPDATED_USER, true);
  	session.setAttribute(Constants.CLIENT_UPDATED_LICENSE, true);
  	session.setAttribute(Constants.CLIENT_UPDATED_RECOVERY, true);
  	response.sendRedirect("centers/index.jsp");
%>


