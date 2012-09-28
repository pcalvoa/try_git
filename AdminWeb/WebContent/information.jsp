<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.thread.PasswordRenew"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%	

	if (request.getParameter(Constants.PARAM_EMAIL) != null && !"".equals(request.getParameter(Constants.PARAM_EMAIL))) {
		//Send new password to user		
		String url = request.getRequestURL().append("?code=").toString().replace("information.jsp", "index.jsp");
		
		Thread thread = new PasswordRenew(request.getParameter(Constants.PARAM_EMAIL), url );
		thread.start();							
	}

%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>Information</title>	
</head>
<body>


<div class="mt-general-box-inner">
 
 <div class="mt-general-box-top-login"></div>
		<div class="mt-general-box-content-login">
   		<div class="container_12">            
                <div class="grid_4 alpha">                  
                        <div class="mt-login-p">
                            <div class="gwt-HTML" style="white-space: nowrap; ">
									<h3 class="mt-page-headline-big mt-header-display-inline"><fmt:message key="msg.welcome"/></h3>
									<h3 class="mt-page-headline-big-bold mt-header-display-inline"><fmt:message key="product.name"/></h3>							
                            </div>
                        </div>
                        
                        <div class="mt-login-list-paragraph">
                            <div>
                                <ul class="mt-login-ul mt-body-text">
                                    <li><fmt:message key="product.info1"/></li>
                                    <li><fmt:message key="product.info2"/></li>
                                    <li><fmt:message key="product.info3"/></li>
                                </ul>
                            </div>
                        </div>                  
                </div>
                
                <div class="grid_3 omega mt-login-right-side">
                    <div class="grid_2 omega prefix_1"> 

	                	<div class="mt-login-p" style="white-space: nowrap;">
							<h3 class="mt-page-big mt-header-display-inline"><fmt:message key="msg.request_activation"/></h3>						
						</div>		
						<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
	                        <div class="mt-main-tabs bold"><fmt:message key="forgot.1"/></div>
	                    </div> 	
						<div class="mt-login-right-side-element-warning">
	                        <div class="mt-main-tabs"><fmt:message key="forgot.2"/></div>
	                    </div>
						<div class="mt-login-right-side-element-line">
	                        <div class="mt-body-text"><fmt:message key="forgot.3"/></div>
	                    </div>										
	                   
                	</div>
            	</div>
   		</div>
      </div>	   <!--fin container_12-->
    <div class="mt-general-box-bottom-loggin"></div><!--footer bottom-->  
 
 </div>			

</body>
</html>