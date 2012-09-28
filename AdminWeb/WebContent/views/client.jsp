<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%if (session.getAttribute("lang") != null) { %>
	<fmt:setLocale value="<%= session.getAttribute(\"lang\") %>" scope="session"/> 
<%    } else { 
			session.setAttribute("lang", request.getLocale());
%>
	<fmt:setLocale value="<%=request.getLocale()%>" scope="session"/>
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
                <form id="clientForm" class="container" method="post" action="">
                
	                <div class="grid_3 omega mt-login-right-side">
	                    <div class="grid_2 omega prefix_1"> 
	
		                	<div class="mt-login-p" style="white-space: nowrap;">
								<h3 class="mt-page-big mt-header-display-inline"><fmt:message key="msg.client_select"/></h3>						
							</div>
																											                                                                                								 					
								<div class="mt-login-right-side-element-line mt-body-text-highlight mt-body-text">
							    	<label><fmt:message key="label.client"/></label>
									<select id="clientSelect" class="edit" name="select" tabindex="1">	
										<option value="" selected="selected"><fmt:message key="msg.select"/></option>      		   			
					    			</select>	
							    </div>
							    											    
							    <input id="clientFormSubmit" style="visibility: hidden;" type="submit" name="submit"/ />								
								
								 <div class="mt-login-right-side-element-line">
								 	<div class="mt-newdialogbox-BottomMiddle-left">
								 		<div class="ButtonGenericLeft">
											<div id="rightButton" class= "search">
												<a id="selectButton" href="#" class="acceptDarkButton" style="margin:0;" tabindex="4"><span><fmt:message key="label.select"/></span></a>
											</div>
										</div>
									</div>	
								</div>
			                			                    
	                	</div>
	            	</div>
            	</form>
            	
				
   		</div>
      </div>	   <!--fin container_12-->
    <div class="mt-general-box-bottom-loggin"></div><!--footer bottom-->  
 
 </div>		


<!-- TEMPLATES -->  
<script id="tmplClient" type="text/x-jquery-tmpl">
	<option value="${name}">${name}</option>		  			  		                        				  			
</script>


<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#selectButton").click(function() {
		$("#clientForm").submit();
	});
</script>

<script>
    $("#clientForm").submit(function() {
   	  var selection = $("#clientSelect").val();
      window.location.href = "admin.jsp?" + "<%=Constants.CURRENT_CLIENT%>=" + selection;  
      return false;      
    });
</script>

<!-- DATA LOAD -->
<script type="text/javascript">
	$(document).ready(function() {

		//start the ajax
		$.ajax({
    		//this process the request
    		url: '<%=request.getScheme() + "://" + 
					request.getServerName() + ":" + request.getServerPort() + 
					request.getContextPath() + Constants.SERVLET_CLIENT%>', 
     
    		//GET method is used            		     		
    		dateType: 'json',

    		//pass the data         
    		data: '',     
     
    		//Do not cache the page
    		cache: false,
     
    		//success
    		success: function(json) { 														
						//Init results' form			            		 								
						$("#clientSelect").empty();												
						$("#tmplClient").tmpl(json.data).prependTo("#clientSelect");
			},
			error: function (XMLHttpRequest, textStatus, error) {            				
				//ERROR Dialog
				$("#btnOkError").bind("click", function() {
					$("#dlgError").dialog("close"); 
				});
				$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.generic"/>");							
				$("#dlgError").dialog('open');
			}                 	                	      
		});
	});
</script>


