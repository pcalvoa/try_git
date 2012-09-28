<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
	<head>
		<title>
			<decorator:title default="Side by Side - Administration" />
		</title>
		<%@include file="../include/head_code.jsp" %>
		<%@include file="../include/header_session_basic.jsp" %>		
		<decorator:head />
	</head>

	<body>
		<div class="parent">
			<div id="wrap" style="width:800px;height:800px;margin: 0 auto;">
				<%@include file="../include/header_client.jsp" %>				
				<div id="bodycontent" style="background-color:#fff">
					
					<div id="content">
						<decorator:body/>
					</div>
				</div>
				<%@include file="../include/foot.jsp" %>
				<%@include file="../include/dialog.jsp" %>
			</div>
		</div>
	</body>
</html>
