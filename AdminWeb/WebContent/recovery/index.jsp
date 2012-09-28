<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="en_EN"/>

<%if (request.getParameter("lang") != null) { %>
	<fmt:setLocale value="<%= request.getParameter(\"lang\") %>"/> 
<%    }  %>

<fmt:setBundle basename="com.tid.bundles.bundle" scope="session"/> 

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

				<div id="formPanel" style="display: none;" >
             		<form id="validatePasswordForm" class="container" method="post" action="information.jsp">
	                	<div class="grid_5 omega mt-login-right-side">
                    		<div class="grid_3 omega prefix_1">
                                <div class="mt-login-p" style="white-space: nowrap;">
									<h3 class="mt-page-big mt-header-display-inline"><fmt:message key="msg.request_activation"/></h3>						
								</div>  
								<div class="mt-login-right-side-element-warning">
									<div class="mt-body-text-highlight"><fmt:message key="msg.please_recovery"/></div>
								</div>	
								<div id="formContent"></div>
                                <div class="mt-login-right-side-element-line">
								 	<div class="mt-newdialogbox-BottomMiddle-left">
								 		<div class="ButtonGenericLeft">
											<div id="rightButton" class= "search">
												<a id="sendButton" href="#" class="acceptDarkButton" style="margin:0;" tabindex="5"><span><fmt:message key="label.send"/></span></a>
											</div>
										</div>
									</div>	
								</div> 
								<div class="mt-login-right-side-element-line mt-low-interest-text"><fmt:message key="msg.mandatory"/></div>								
								<input id="<%=Constants.PARAM_CODE%>" style="visibility: hidden;" type="hidden" name="<%=Constants.PARAM_CODE%>" value="<%=request.getParameter(Constants.PARAM_CODE)%>"/ />			    
							    <input id="validatePasswordFormSubmit" style="visibility: hidden;" type="submit" name="submit"/ />								
								
							</div>
                                   
                		</div>
		
          			</form>									
				</div>     
				
				<div id="errorPanel"  style="display: none;">
					 <div class="grid_3 omega mt-login-right-side">
	                    <div class="grid_2 omega prefix_1"> 
	
		                	<div class="mt-login-p" style="white-space: nowrap;">
								<h3 class="mt-page-headline-big mt-header-display-inline"><fmt:message key="msg.request_activation"/></h3>						
							</div>		
							<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
		                        <div class="mt-main-tabs bold"><fmt:message key="recovery.error.1"/></div>
		                    </div> 	
							<div class="mt-login-right-side-element-warning" style="width:250px;">
		                        <div class="mt-main-tabs"><fmt:message key="recovery.error.2"/></div>
		                    </div>		                   
	                	</div>
	            	</div>
				</div>       
				
				<div id="informationPanel"  style="display: none;">
					 <div class="grid_3 omega mt-login-right-side">
	                    <div class="grid_2 omega prefix_1"> 
	
		                	<div class="mt-login-p" style="white-space: nowrap;">
								<h3 class="mt-page-headline-big mt-header-display-inline"><fmt:message key="msg.request_activation"/></h3>						
							</div>		
							<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
		                        <div class="mt-main-tabs bold"><fmt:message key="recovery.1"/></div>
		                    </div> 	
							<div class="mt-login-right-side-element-warning" style="width:250px;">
		                        <div class="mt-main-tabs"><fmt:message key="recovery.2"/></div>
		                    </div>		                   
	                	</div>
	            	</div>
				</div>       
				
			
   		</div> <!--fin container_12-->
      </div>	   
 	  <div class="mt-general-box-bottom-loggin"></div><!--footer bottom-->  
</div>		


<!-- TEMPLATES -->  
<script id="tmplResult" type="text/x-jquery-tmpl">								                                
								<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="<%=Fields.USER_USER%>"><fmt:message key="user.user"/>*</label>
									<p><br></br></p>
							    	<input  class="edit" type="text"  id="<%=Fields.USER_USER%>"  style="display:block;"
							    		name="<%=Fields.USER_USER%>" tabindex="1"/>
							    </div>
                               
								<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="<%=Fields.USER_PASSWORD%>">${securityquestion}?*</label>
									<p><br></br></p>
							    	<input class="edit" type="text" id="<%=Fields.USER_SECURITY_ANSWER%>" style="display:block;"  
							    		name="<%=Fields.USER_SECURITY_ANSWER%>" tabindex="2"/>
							    </div>	

                                <div class="mt-login-right-side-element-warning">
                                    <span class="mt-body-text-small"><fmt:message key="msg.choose_password"/></span>
                                </div>
								<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="<%=Fields.USER_PASSWORD%>"><fmt:message key="label.new"/><fmt:message key="user.password"/>* </label>
									<p><br></br></p>
							    	<input class="edit" type="password" id="<%=Fields.USER_PASSWORD%>"
							    		name="<%=Fields.USER_PASSWORD%>" tabindex="3"/>
							    </div>			    
							    <div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label for="rpassword"><fmt:message key="user.rpassword"/>* </label>
							    	<p><br></br></p>
									<input class="edit" type="password" id="rpassword"  
							    		name="rpassword" tabindex="4"/>
							    </div>                    
                                                                      
</script>



<!-- INIT SESSION -->
<script type="text/javascript">
	$(document).ready(function() {

		//start the ajax
		$.ajax({
    		//this process the request
    		url: '<%=request.getScheme() + "://" + 
					request.getServerName() + ":" + request.getServerPort() + 
					request.getContextPath()%>/recoveryservlet', 
     
    		//GET method is used            		     		
    		dateType: 'json',

    		//pass the data         
    		data: "<%=Constants.PARAM_CODE%>=<%=request.getParameter("code")%>" +     		
		      	  "&<%=Constants.PARAM_CLIENT%>=<%=request.getParameter("system")%>",  
     
    		//Do not cache the page
    		cache: false,
     
    		//success
    		success: function(json) { 		

						if (json!="") {
			      		
							$("#errorPanel").hide();
							$("#formPanel").show();
									
							//Init results' form
							//$('#queryResults').text("<fmt:message key="msg.query_results"/>: " + $('#<%=Constants.PARAM_QUERY%>').val());			            		 								
							$("#formContent").empty();
															
							if (json.data.length>0) {									
								$("#tmplResult").tmpl(json.data).prependTo("#formContent");																			
							}
							else {
								//No results
								$("#errorPanel").show();
								$("#formPanel").hide();													
							}		
						}else {
							//No results
							$("#errorPanel").show();
							$("#formPanel").hide();		
						}								 											
						
			},
			error: function (XMLHttpRequest, textStatus, error) {            				
				$("#errorPanel").show();
				$("#formPanel").hide();
														
					
			},
			complete: function() {
			}  
		});  
							
	});
	
</script>



<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#sendButton").click(function() {		
		$("#validatePasswordForm").submit();		
	});
</script>

<!-- FORM VALIDATION -->
<script type="text/javascript">
	$(document).ready(function() {

		$.validator.addMethod("usertmpl",function(value,element) {
			return this.optional(element) || /^[\w\d\_\.\-]{2,}$/i.test(value); 
			},"<fmt:message key="msg.invalid_chars"/>");
		
		// validate signup form on keyup and submit
		$("#validatePasswordForm").validate({
			rules: {
				<%=Fields.USER_USER%>: {
					required: true,
					minlength: 2,
					maxlength: 16,
					usertmpl: true
				},
				<%=Fields.USER_SECURITY_ANSWER%>: {
					required: true
				},				
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
				<%=Fields.USER_USER%>: {
					required: 	"<fmt:message key="msg.user"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>"
				},		
				<%=Fields.USER_SECURITY_ANSWER%>: {
					required: 	"<fmt:message key="msg.security_answer"/>"
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
				//$("#validatePasswordFormSubmit").click();
				
				//Show processing panel
				$("#formPanel").hide();
				$("#waitingPanel").show();
				
				//start the ajax				
				$.ajax({
		    		//this process the request
		    		url: '<%=request.getScheme() + "://" + 
							request.getServerName() + ":" + request.getServerPort() + 
							request.getContextPath()%>/recoveryservlet', 
		     
		    		//GET method is used            		     		
		    		dateType: 'json',
		
		    		//pass the data         
            		data: $('#validatePasswordForm').serialize(),   
		     
		    		//Do not cache the page
		    		cache: false,
		     
		    		//success
		    		success: function(json) { 		
								//$("#errorPanel").attr("visibility", "hidden");
								//$("#formPanel").attr("visibility", "visible");
								$("#waitingPanel").hide();
								$("#informationPanel").show();				 											
							
					},
					error: function (XMLHttpRequest, textStatus, error) {   
						$("#waitingPanel").hide();      				
						$("#errorPanel").show();																												
					},
					complete: function() {						
					}  
				});  
												
	        	return false;		
			}
		});	
		                              
	});

</script>

</body>
</html>