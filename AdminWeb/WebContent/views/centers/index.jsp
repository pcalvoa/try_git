<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
	<title><fmt:message key="page.center_title"/></title>
									
</head>
<body>					
	<div id="layer_new" >										
		<%@include file="./exclude/new.jsp" %>			
	</div>	
	<div id="layer_search" style="display:none;">
		<%@include file="./exclude/search.jsp" %>	
	</div>
	<div id="layer_view" style="display:none;">	
		<%@include file="./exclude/view.jsp" %>	
	</div>
	
	
	
</body>