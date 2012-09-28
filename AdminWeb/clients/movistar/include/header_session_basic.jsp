<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_session">	
	<div id="language" class="container_800 mt-header">
			<div class="mt-subheader1element">
				<a class="mt-linked-text" href="<c:url value=""/>?lang=en_EN"><fmt:message key="lang.english"/></a>
			</div>
			<div class="mt-subheader1point">
				<img src="<c:url value="/images/footer/separator_header.png" />" />
			</div>
			<div class="mt-subheader1element">					
				<a class="mt-linked-text" href="<c:url value=""/>?lang=es_ES"><fmt:message key="lang.spanish"/></a>
			</div>				
	</div>
</div>
