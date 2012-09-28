<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_client" class="container_800">
	<div class="left" style="margin:32px 5px 0 0;">
		<a  href="<c:url value="/views/client.jsp"/>">
			<span class="mt-product-name" id="title"><fmt:message key="product.name"/></span><span class="mt-product-client"></span>
		</a>
	</div>		
	
	<div class="right" style="margin:18px 0 0 5px;">
		<img src="<c:url value="/images/logos/logo.png"/>"/>
	</div>	
	
</div>
