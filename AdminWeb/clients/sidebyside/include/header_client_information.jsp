<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_client" class="container_800">	
	<div>
		<img class="left" src="<c:url value="/images/logos/logo.png"/>" style="margin-top:12px;"/>
	</div>
	<div class="right" style="margin-top:32px;">
		<a class="mt-product-name" href="<c:url value="/views/client.jsp"/>"><span id="title"><fmt:message key="product.name"/> <fmt:message key="label.for"/> <%=session.getAttribute(Constants.CURRENT_CLIENT)%> </span></a>
	</div>
</div>


<!-- VERIFY CLIENT -->
<script type="text/javascript">	
$(document).ready(function() {
	//If client is null		
	if($("#title").text().indexOf("null")>0) {
		//Redirect to client selection		
		window.location.href = "<c:url value="/views/client.jsp" />";  	         				
	}	
	return false;
});
</script>
