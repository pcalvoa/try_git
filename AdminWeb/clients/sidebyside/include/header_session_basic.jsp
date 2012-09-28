<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_session" class="mt-crossbar">	
	<div id="language" class="container_800"  style="margin:0 auto;">
		<div class="left">
			<a class="mt-linked-text" href="<c:url value=""/>?lang=en_EN"><fmt:message key="lang.english"/> |</a>				
			<a class="mt-linked-text" href="<c:url value=""/>?lang=es_ES"><fmt:message key="lang.spanish"/></a>
		</div>
				
	</div>
</div>
