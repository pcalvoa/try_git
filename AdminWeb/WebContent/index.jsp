<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>
<%@ page import="com.tid.pojo.ApplicationUser"%>
<%@ page import="com.tid.thread.UserActivate"%>
<%@ page import="com.tid.thread.PasswordRenew"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%  String currentLang = "";

	if (request.getParameter("lang") != null) { 
		currentLang = request.getParameter("lang");
	} else {
		String cookieName = "lang";
		Cookie cookies [] = request.getCookies ();
		Cookie myCookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies [i].getName().equals (cookieName)) {
					myCookie = cookies[i];	
					currentLang = myCookie.getValue();												
					break;
				}				
			}
		} 
	 
	}

	if (currentLang==null) {
		currentLang = "en_EN";
	}
	
	Cookie cookie = new Cookie ("lang", currentLang);		
	response.addCookie(cookie);
	
	session.setAttribute( "lang", currentLang );
%> 


<fmt:setBundle basename="com.tid.bundles.bundle" scope="session"/> 

<%
	String redirect = "login.jsp";

	/*if (request.getParameter(Constants.PARAM_EMAIL) != null && !"".equals(request.getParameter(Constants.PARAM_EMAIL))) {
		//Send new password to user								
		Thread thread = new PasswordRenew(request.getParameter(Constants.PARAM_EMAIL), request.getRequestURL().append("?code=").toString() );
		thread.start();	
		
		redirect = "information.jsp";
		
	}
	else*/ if (request.getParameter(Constants.PARAM_CODE) != null && !("".equals(request.getParameter(Constants.PARAM_CODE)))) {
		
		//If password too
		
		if (request.getParameter(Fields.USER_PASSWORD) != null && !("".equals(request.getParameter(Fields.USER_PASSWORD)))) {
						
			String newPassword = request.getParameter(Fields.USER_PASSWORD);
			String oldPassword = request.getParameter(Fields.USER_PASSWORD + "_OLD");
			String code = request.getParameter(Constants.PARAM_CODE);
			Thread thread = new UserActivate(code, oldPassword, newPassword);
			thread.start();
					
		}
		else {				
			//Validate password
			redirect = "validate.jsp";
			
			//Capture login error
			String code = request.getParameter(Constants.PARAM_CODE);
			if (code!=null && !("".equals(code))) {
				redirect += "?" + Constants.PARAM_CODE + "=" + code;
			}
		  							
		}	
		
	}		
				
	//Capture login error
	String error = request.getParameter(Constants.PARAM_ERROR);
	if (error!=null && !("".equals(error))) {
		redirect += "?" + Constants.PARAM_ERROR + "=" + error;
	}
	
	if (!"".equals(redirect)) {
		response.sendRedirect(redirect);
	}
%>