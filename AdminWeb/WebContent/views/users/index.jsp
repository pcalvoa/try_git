<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
	<title><fmt:message key="page.user_title"/></title>
	
	<!-- ADD CURRENT CENTER -->
	<script type="text/javascript">
		function addCurrentCenter(field, value) {
		
			//Update administrate centers 																		
			$('.OwnCenter').attr("selected", false).removeClass('OwnCenter');	
		
			//Add current center															
			$("#" + field).val(value).attr("selected", true);
			$("#" + field + " option").each(function() {
				if ($(this).val() == value) {
					$(this).addClass('OwnCenter');
				}
			});
			
			$("#"+field).trigger('change');			
						
		}

	</script>
		
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