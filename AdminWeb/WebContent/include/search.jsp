<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- SEARCH FUNCTION -->
<script type="text/javascript">
	function search(type, query) {
	
		//Wait message
		changeState('loading');
					
		var url = "<%=request.getScheme() + "://" + 
				request.getServerName() + ":" + request.getServerPort() + 
				request.getContextPath() + Constants.SERVLET_SEARCH%>";		
		
		//Search params 'Constants' is used
		url += "?<%=Constants.PARAM_TYPE%>=" + type;
		//Search name
		url += "&<%=Constants.PARAM_QUERY%>=" + query;	  
		
		//Get json result
		$.getJSON('<c:url value="' + url + '"/>', function(json) {  
			$("#results").empty();
			$("#tmplResult").tmpl(json.data).prependTo("#results");	
			$('#resultsContainer').pajinate({
					num_page_links_to_display : <%=pageContext.getServletContext().getAttribute(Constants.PAG_LINKS_TO_DISPLAY)%>,
					items_per_page : <%=pageContext.getServletContext().getAttribute(Constants.PAG_ITEMS_PAGE)%>	
				});	
			changeState('search');				
		});				
			 				 	     		  
	} 
</script>  