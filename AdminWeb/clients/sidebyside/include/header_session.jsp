<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header_session" class="mt-crossbar">	
	<div id="session" class="container_800"  style="margin:0 auto;">
		<div class="right inline">
              <div class="mt-cursor-pointer mt-color-gray5 floatright20" style="padding-top:3px;">              
                 <a href="<c:url value="/logout.jsp" />" class="mt-header-bar-link-light"><fmt:message key="label.close"/></a>                  
              </div>
              <div class="mt-color-gray5 floatright20left20" style="padding-top:0px;">
                  <a  ><fmt:message key="msg.hello"/> <%= request.getUserPrincipal().getName() %></a>
              </div>
 
         </div>
	</div>
	
</div>





