<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_client" class="container_800">	
	<div>
		<img class="left" src="<c:url value="/images/logos/logo.png"/>" style="margin-top:12px;"/>
	</div>
	<div style="margin-top:32px;">
		<a class="mt-product-name right" href="#"><fmt:message key="product.name"/></a>
	</div>
</div>