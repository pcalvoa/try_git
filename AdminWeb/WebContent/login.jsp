<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%if (session.getAttribute("lang") != null) { %>
	<fmt:setLocale value="<%= session.getAttribute(\"lang\") %>" scope="session"/> 
<%    } else { 
			session.setAttribute("lang", request.getLocale());
%>
	<fmt:setLocale value="<%=request.getLocale()%>" scope="session"/>
<%    }  %>

<fmt:setBundle basename="com.tid.bundles.bundle" scope="session"/> 

<html>
<head>
	<title><fmt:message key="page.common_title"/></title>
		
</head>
<body>

<div class="mt-general-box-inner">
 
 <div class="mt-general-box-top-login"></div>
		<div class="mt-general-box-content-login">
   		<div class="container_12">            
                <div class="grid_4 alpha">                  
                        <div class="mt-login-p">
                            <div style="white-space: nowrap; ">
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
                                                
                <form id="loginForm" action="<%=response.encodeURL("j_security_check")%>" method="post">
                
	                <div class="grid_3 omega mt-login-right-side">
	                    <div class="grid_3 omega prefix_1"> 
	
		                	<div class="mt-login-p" style="white-space: nowrap;">
								<h3 class="mt-page-big mt-header-display-inline"><fmt:message key="msg.signin"/></h3>						
							</div>
						
						
                                                                                                                                                                      								 
		                    <div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text" >
		                    	<label class="mt-body-text-higlight"><fmt:message key="label.username"/></label>	                        
								<input class="mt-wizardForm-Input220 border" type="text" id="j_username" name="j_username" 
									tabindex="1" value="<fmt:message key="valid.username"/>"/>
		                    </div> 
		                    <div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
		                       	<label class="mt-body-text-higlight"><fmt:message key="label.password"/></label>	                       
			                   	<input class="mt-wizardForm-Input220 border" type="password" id="j_password" name="j_password" 
									tabindex="2" value="<fmt:message key="valid.password"/>"/>
		                    </div> 	
		                    <div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
		                       <a class="mt-linked-text" class="mt-linked-text" href="forgot.jsp"><fmt:message key="msg.password_forgotten"/></a>
		                    </div> 					
			                    								
							<input id="loginFormSubmit" style="visibility: hidden;" type="submit" name="submit"/ />                    
		                   	
		                   	<div class="mt-login-right-side-element-line">
							 	<div class="mt-newdialogbox-BottomMiddle-left">
							 		<div class="ButtonGenericLeft">
										<div id="rightButton" class= "search">
											<a id="loginButton" href="#" class="acceptDarkButton" style="margin:0;" tabindex="2"><span><fmt:message key="label.send"/></span></a>
										</div>
									</div>
								</div>	
							</div>
			                    	
		               
	                   
                   		<div id="errorPanel" class="mt-login-right-side-element-line mt-color-red mt-error-notification error">
							<div class="inline" style="text-align:center;padding:30px 0 0 0;">															
								<span id="errorMsg">
						    		<% //Capture login error
										String error = request.getParameter(Constants.PARAM_ERROR);
										if (error!=null && !("".equals(error))) { 
									%>
									<img src="<c:url value="/images/icons/ico_user_status_red.png"/>" />
									<fmt:message key="msg.invalid_login"/>
									<%}%>
								</span>
							</div>
                   		</div>
                                      
                
                	</div>
            	</div>
             </form>
   		</div>
      </div>	   <!--fin container_12-->
    <div class="mt-general-box-bottom-loggin"></div><!--footer bottom-->  
 
 </div>			


<!-- SUBMIT FORM --> 
<script type="text/javascript">
$('input').live('keypress', function (e) {
	if ( e.which == 13 ) {
		$("#dlgWait").dialog('open');
		<!--$("#loginForm").submit();-->	
		$("#loginFormSubmit").click();	
		return false;	
	}		
});
</script>

<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#loginButton").click(function() {
		$("#dlgWait").dialog('open');		
		<!--$("#loginForm").submit();-->	
		$("#loginFormSubmit").click();	
		return false;	
	});
</script>

<!-- FORM VALIDATION 
<script type="text/javascript">
	$(document).ready(function() {
		
		// validate signup form on keyup and submit
		$("#loginForm").validate({
				
			submitHandler: function() {

				$("#loginFormSubmit").click();				
	        	return false;		
			}
		});
		                              
	});

</script>-->

</body>
</html>