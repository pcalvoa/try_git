<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_session">	
	<div class="container_800 mt-header">
		<div class="mt-subheader1element right">
			<a class="mt-linked-text" href="<c:url value="/logout.jsp" />"><fmt:message key="label.close"/></a>                                
        </div>
		<div class="mt-subheader1point right">
			<img src="<c:url value="/images/footer/separator_header.png" />" />
		</div>
		<div class="mt-subheader1element right">
        	<a class="mt-header-text"><fmt:message key="msg.hello"/> <%= request.getUserPrincipal().getName()%></a>
		</div>
 
	</div>
	
</div>





