<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%if (request.getParameter("lang") != null) { %>
	<fmt:setLocale value="<%= request.getParameter(\"lang\") %>"/> 
<%    } else { %>
	<fmt:setLocale value="en_EN"/>
<%    }  %>

<fmt:setBundle basename="com.tid.bundles.bundle" scope="session"/> 


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
                <form id="forgotForm" action="information.jsp" method="post">
                
                <div class="grid_3 omega mt-login-right-side">
                    <div class="grid_2 omega prefix_1"> 

	                	<div class="mt-login-p" style="white-space: nowrap;">
							<h3 class="mt-page-big mt-header-display-inline"><fmt:message key="msg.request_activation"/></h3>						
						</div>
																									                                                                                								
			                    <div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
			                        <label class="mt-body-text-higlight"><fmt:message key="label.email"/>*</label>	
			                        <input class="mt-wizardForm-Input220 border" type="text" id="<%=Constants.PARAM_EMAIL%>" 
			    						name="<%=Constants.PARAM_EMAIL%>" tabindex="1"/>
			    					<input id="submitButton" type="submit" style="visibility:hidden"></input>   									
			                    </div> 
										
								<input id="forgotFormSubmit" style="visibility: hidden;" type="submit" name="submit"/ />                    
			                   	
			                   	<div class="mt-login-right-side-element-line">
								 	<div>
								 		<div class="ButtonGenericLeft">
											<div id="rightButton" class= "search">
												<a id="sendButton" href="#" class="acceptDarkButton" style="margin:0;" tabindex="2"><span><fmt:message key="label.send"/></span></a>
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
	$("#sendButton").click(function() {		
		$("#forgotForm").submit();		
	});
</script>


<!-- FORM VALIDATION -->
<script type="text/javascript">
	$(document).ready(function() {
		
		// validate signup form on keyup and submit
		var v = $("#forgotForm").validate({
			rules: {
				<%=Constants.PARAM_EMAIL%>: {
					required: true,
					email: true
				}
			},
			messages: {				
				<%=Constants.PARAM_EMAIL%>: {
					required: 	"<fmt:message key="msg.email"/>",
					email:  	"<fmt:message key="msg.email"/>"
				}
			},
			submitHandler: function() {																
				$("#forgotFormSubmit").click();				
	        	return false;			
			}
		});		
			                              
	});
</script>

