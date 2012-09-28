<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
<div id="tabs" class="tabs menu mt-main-tabs">
	<ul>
		<li><a class="space" href="#"></a></li>
		<li><a class="mt-login-headline" title="" href="<%=request.getContextPath()%>/views/users" rel="menu0"><div><fmt:message key="page.user_title"/></div></a></li>
		<li><a class="mt-login-headline" title="" href="<%=request.getContextPath()%>/views/services" rel="menu0"><div><fmt:message key="page.service_title"/></div></a></li>
		<li><a class="mt-login-headline" title="" href="<%=request.getContextPath()%>/views/centers" rel="menu0"><div><fmt:message key="page.center_title"/></div></a></li>
		<li><a class="mt-login-headline" title="" href="<%=request.getContextPath()%>/views/licenses" rel="menu0"><div><fmt:message key="page.licenses_title"/></div></a></li>		
	</ul>		
</div>

<!-- TAB BUTTONS -->
<script type="text/javascript">	
//function activeTab(page) {
$(document).ready(function() {
	$('.tabs A').each(function() {
		//Current page
		var suffix = $(this).attr('href');
		var str  = document.URL;
		if (str.indexOf(suffix) !== -1) {			 		    
	    	$(this).addClass("selected").removeClass("unselected");
	    	
		}
	    else {
	    	$(this).addClass("unselected").removeClass("selected");
	    }
	});
	
});
</script>

