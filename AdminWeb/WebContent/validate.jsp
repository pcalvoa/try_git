<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%if (request.getParameter("lang") != null) { %>
	<fmt:setLocale value="<%= request.getParameter(\"lang\") %>"/> 
<%    } else { %>
	<fmt:setLocale value="en_EN"/>
<%    }  %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title><fmt:message key="page.common_title"/></title>	
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
                <form id="validatePasswordForm" class="container" method="post" action="index.jsp">
                
	                <div class="grid_3 omega mt-login-right-side">
	                    <div class="grid_2 omega prefix_1"> 
	
		                	<div class="mt-login-p" style="white-space: nowrap;">
								<h3 class="mt-page-headline-big mt-header-display-inline"><fmt:message key="msg.request_activation"/></h3>						
							</div>
																											                                                                                								 					
								<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="<%=Fields.USER_PASSWORD%>_OLD"><fmt:message key="user.activation_code"/>* </label>
							    	<input class="edit" type="password" id="<%=Fields.USER_PASSWORD%>_OLD" 
							    		name="<%=Fields.USER_PASSWORD%>_OLD" tabindex="1"/>
							    </div>
								<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="<%=Fields.USER_PASSWORD%>"><fmt:message key="label.new"/> <fmt:message key="user.password"/>* </label>
							    	<input class="edit" type="password" id="<%=Fields.USER_PASSWORD%>" 
							    		name="<%=Fields.USER_PASSWORD%>" tabindex="2"/>
							    </div>			    
							    <div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="rpassword"><fmt:message key="user.rpassword"/>* </label>
							    	<input class="edit" type="password" id="rpassword" 
							    		name="rpassword" tabindex="3"/>
							    </div>
							    
								<input id="<%=Constants.PARAM_CODE%>" style="visibility: hidden;" type="hidden" name="<%=Constants.PARAM_CODE%>" value="<%=request.getParameter(Constants.PARAM_CODE)%>"/ />			    
							    <input id="validatePasswordFormSubmit" style="visibility: hidden;" type="submit" name="submit"/ />								
								
								 <div class="mt-login-right-side-element-line">
								 	<div class="mt-newdialogbox-BottomMiddle-left">
								 		<div class="ButtonGenericLeft">
											<div id="rightButton" class= "search">
												<a id="validateButton" href="#" class="acceptDarkButton" style="margin:0;" tabindex="4"><span><fmt:message key="label.acept"/></span></a>
											</div>
										</div>
									</div>	
								</div>
								
																<div class="mt-login-right-side-element-line mt-low-interest-text"><fmt:message key="msg.mandatory"/></div>
			                			                    
	                	</div>
	            	</div>
            	</form>
            	
				
   		</div>
      </div>	   <!--fin container_12-->
    <div class="mt-general-box-bottom-loggin"></div><!--footer bottom-->  
 
 </div>		





<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#validateButton").click(function() {		
		$("#validatePasswordForm").submit();		
	});
</script>

<!-- FORM VALIDATION -->
<script type="text/javascript">
	$(document).ready(function() {
		
		// validate signup form on keyup and submit
		$("#validatePasswordForm").validate({
			rules: {
				<%=Fields.USER_PASSWORD%>_OLD: {
					required: true
				},
				<%=Fields.USER_PASSWORD%>: {
					required: true,
					minlength: 8,
					maxlength: 16
				},					
				rpassword: {
					required: true,
					minlength: 8,
					maxlength: 16,
					equalTo: "#<%=Fields.USER_PASSWORD%>"
				}
			},
			messages: {				
				<%=Fields.USER_PASSWORD%>_OLD: {
					required: 	"<fmt:message key="msg.password"/>"
				},
				<%=Fields.USER_PASSWORD%>: {
					required: 	"<fmt:message key="msg.password"/>",
					minlength:  "<fmt:message key="msg.min"/> 8 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>"
				},
				rpassword: {
					required: 	"<fmt:message key="msg.password"/>",
					minlength:  "<fmt:message key="msg.min"/> 8 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>",
					equalTo:	"<fmt:message key="msg.equal"/>"
				}
			},
			submitHandler: function() {
				//window.location.href = "index.jsp";						
				
				$("#validatePasswordFormSubmit").click();				
	        	return false;		
			}
		});	
		                              
	});

</script>

</body>
</html>