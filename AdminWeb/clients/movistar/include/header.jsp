<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="header" class="rounded-corners-up">
	<div id="header_round" "class="container_800" style="height:10px;"> 
	</div>	
	
	<div class="container_800" style="height:65px;">
		<div class="left" style="padding-top:12px;"><span class="mt-co-branding" style="padding-left: 15px;"><fmt:message key="page.header_title"/></span></div>
		<div class="right" style="padding-top:12px;"><span class="mt-product-client-conector"><fmt:message key="label.for"/></span> <span id="client_title" class="mt-product-client"><%=session.getAttribute(Constants.CURRENT_CLIENT)%></span></div>		
	</div>
	
	<div class="container_800" style="height:20px;">
		<img src="<c:url value="/images/common/head_degraded.png"/>" style="margin:0;"/>
	</div>
</div>

<!-- VERIFY CLIENT -->
<script type="text/javascript">	
$(document).ready(function() {
	
	//If client is null		
	if($("#client_title").text().indexOf("null")>=0) {
		//Redirect to client selection		
		window.location.href = "<c:url value="/views/client.jsp" />";  	         				
	}	
	return false;
});
</script>


<!-- ROUND CORNER'S 
<script type="text/JavaScript">

  curvyCorners.addEvent(window, 'load', initCorners);

  function initCorners() {
    var settings = {
      tl: { radius: 10 },
      tr: { radius: 10 },
      bl: { radius: 10 },
      br: { radius: 10 },
      antiAlias: true
    }

    curvyCorners(settings, "#header_round");
  }

</script>
-->

