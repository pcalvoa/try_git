<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>
<%@ page import="com.tid.Errors"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="newContainer" class="container-form" style="height:270px;"> 
		
	 <!-- scroll content block -->
	<div id="userPanel" class="container_700 scroll scroll-pane-new" style="height:225px;">
					<form id="newForm" class="form" method="post">
						<fieldset>
							<div>
				    			<legend class="mt-legend-text"><fmt:message key="user.personal_information_title"/></legend>
				    		</div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_NAME%>"><fmt:message key="user.name"/>* </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_NAME%>" 
						    		name="<%=Fields.USER_NAME%>" tabindex="1"/>
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_SURNAME%>"><fmt:message key="user.surname"/>* </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_SURNAME%>" 
						    		name="<%=Fields.USER_SURNAME%>" tabindex="2"/>
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_USER%>"><fmt:message key="user.user"/>* </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_USER%>" 
						    		name="<%=Fields.USER_USER%>" tabindex="3"/>
						    </div>			    			   
							<!-- 
						    <div class="mt-login-right-side-element-warning">                    			
                    			<span class="mt-body-text-small" style="margin-left:200px;"><fmt:message key="msg.choose_password"/></span>
                     		</div>
							-->

						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_PASSWORD%>"><fmt:message key="user.password"/>* </label>
						    	<input class="edit mt-input-text" type="password" id="<%=Fields.USER_PASSWORD%>" 
						    		name="<%=Fields.USER_PASSWORD%>" tabindex="4"/>
						    </div>						    						    
                     		
						    
						    <div>
						    	<label class="mt-label-text" for="rpassword"><fmt:message key="user.rpassword"/>* </label>
						    	<input class="edit mt-input-text" type="password" id="rpassword" 
						    		name="rpassword" tabindex="5"/>
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_SECURITY_QUESTION%>"><fmt:message key="user.security_question"/>* </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_SECURITY_QUESTION%>" 
						    		name="<%=Fields.USER_SECURITY_QUESTION%>" tabindex="6"/>
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_SECURITY_ANSWER%>"><fmt:message key="user.security_answer"/>* </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_SECURITY_ANSWER%>" 
						    		name="<%=Fields.USER_SECURITY_ANSWER%>" tabindex="7"/>
						    </div>
						    
							<div>
						    	<label class="mt-label-text" for="<%=Fields.USER_PHONE%>"><fmt:message key="user.phone"/> </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_PHONE%>" 
						    		name="<%=Fields.USER_PHONE%>" tabindex="8"/>
						    </div>
			
							<div>
						    	<label class="mt-label-text" for="<%=Fields.USER_MOBILE%>"><fmt:message key="user.mobile"/> </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_MOBILE%>" 
						    		name="<%=Fields.USER_MOBILE%>" tabindex="9"/>
						    </div>
			
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_EMAIL%>"><fmt:message key="user.email"/>* </label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_EMAIL%>" 
						    		name="<%=Fields.USER_EMAIL%>" tabindex="10"/>
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_EMAIL_USER%>"><fmt:message key="user.email_user"/></label>
						    	<input class="edit mt-input-text" type="text" id="<%=Fields.USER_EMAIL_USER%>" 
						    		name="<%=Fields.USER_EMAIL_USER%>" tabindex="11"/>
						    </div>
						    			    
						     <div>
						    	<label class="mt-label-text" for="<%=Fields.USER_EMAIL_PASSWORD%>"><fmt:message key="user.email_password"/></label>
						    	<input class="edit mt-input-text" type="password" id="<%=Fields.USER_EMAIL_PASSWORD%>" 
						    		name="<%=Fields.USER_EMAIL_PASSWORD%>" tabindex="12"/>
						    </div>
						    
						    			    
							<div>
				    			<legend class="mt-legend-text"><fmt:message key="user.professional_information_title"/></legend>
				    		</div>
				    		
				    		<div>
						    	<label class="mt-label-text" for="centerName"><fmt:message key="center.name"/></label>
						    	<select class="edit mt-input-text" name="<%=Fields.CENTER_ID%>" id="<%=Fields.CENTER_ID%>" tabindex="13">	
									<option value="" selected="selected"><fmt:message key="msg.select"/></option>      		   			
				   				</select>					   						    
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="serviceName"><fmt:message key="service.name"/></label>
						    	<select class="edit mt-input-text"  name="<%=Fields.SERVICE_ID%>" id="<%=Fields.SERVICE_ID%>" tabindex="14">	
									<option value="" selected="selected"><fmt:message key="msg.select"/></option>      		   			
				   				</select>				    	
						    </div>
						    
						    <div>
						    	<label class="mt-label-text" for="<%=Fields.PROFILE_ID%>"><fmt:message key="user.profile"/>* </label>			    		
						    	<select class="edit"  name="<%=Fields.PROFILE_ID%>" id="<%=Fields.PROFILE_ID%>" tabindex="15">	
									<option class="mt-combo-text" value="<%=Constants.PROFILE_USER%>" selected="selected"><fmt:message key="profile.user"/></option>      		   			
									<option class="mt-combo-text" value="<%=Constants.PROFILE_ADMINISTRATOR%>"><fmt:message key="profile.administrator"/></option>
									<option class="mt-combo-text" value="<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>"><fmt:message key="profile.local_administrator"/></option>
				   				</select>
						    </div>		
						    
						    <div id="centerAdministration">	  
						    						    						    
							    <div>
					    			<legend class="mt-legend-text"><fmt:message key="user.admin_centers"/></legend>
					    		</div>
							    
							    <div>
							    	<label class="mt-label-text" for="<%=Fields.USER_ADMINISTRATED_CENTERS%>"><fmt:message key="center.name"/></label>
							    	<select class="edit" name="<%=Fields.USER_ADMINISTRATED_CENTERS%>" id="<%=Fields.USER_ADMINISTRATED_CENTERS%>" multiple="multiple" tabindex="16">									    		 									     		   		
					   				</select>	
							    					    
						    	</div>
							
							</div>
						      							    			    						    			    			    			    			   
						    <div style="display:block;float:left;">
							    <div>
					    			<legend class="mt-legend-text"><fmt:message key="user.remark"/></legend>
					    		</div>
					    		
					    		<div>			    			    
									<label class="mt-label-text" style="visibility:hidden;" for="<%=Fields.USER_OBSERVATIONS%>"><fmt:message key="user.remark"/></label>
							    	<textarea class="styletextarea edit short mt-input-text"  rows="5"
							    		id="<%=Fields.USER_OBSERVATIONS%>" name="<%=Fields.USER_OBSERVATIONS%>" 
											tabindex="17"></textarea>
								</div>					
							</div>
							
					    	<input type="hidden" name="<%=Constants.PARAM_OP%>" value="<%=Constants.VALUE_INSERT%>">
						</fieldset>
					</form>	
	</div>
	
	<div class="mt-low-interest-text" style="padding:10px 0 0 250px;"><fmt:message key="msg.mandatory"/></div>											    		
				
				
</div>
	



<div id="foot" class="rounded-corners-down">
	<div id="rightButton" class="mt-button-foot-right">
		<a id="btnSaveNew" href="#" class="acceptDarkButton" style="margin:0;" tabindex="14"><span><fmt:message key="label.save"/></span></a>
	</div>


</div>

<!-- TEMPLATES -->  
<script id="tmplCenter" type="text/x-jquery-tmpl">
{{if name=="Default"}}
	<!-- Default center No lo añadimos-->
	<!--<option class="mt-combo-text" value="${id}"><fmt:message key="msg.select"/></option>-->	
{{else}}
	<option class="mt-combo-text" value="${id}">${name}</option>	
{{/if}}>		  			  		                        				  			
</script>
  
<script id="tmplService" type="text/x-jquery-tmpl">
{{if name=="Default"}}
	<-!-- Default service -->
	<option class="mt-combo-text" value="${id}"><fmt:message key="msg.select"/></option>
{{else}}
	<option class="mt-combo-text" value="${id}">${name}</option>		
{{/if}}>	


		  			  		                        				  			
</script>


<!-- Showscroll -->
<script type="text/javascript">
	function showScrollNew() {
		var settings = {			
			verticalDragMinHeight: 50,
			verticalDragMaxHeight: 50,
			showArrows: true		
		};
		var pane = $('.scroll-pane-new')
		pane.jScrollPane(settings);
		var api = pane.data('jsp');
		api.reinitialise();
	}
</script>

<!-- CENTERS LOAD -->
<script type="text/javascript">
	$(document).ready(function() {
				
		//Set disabled
		$("#<%=Fields.CENTER_ID%>").attr('disabled', 'disabled');		
										 		
		//start the ajax
		$.ajax({
    		//this process the request
    		url: '<%=request.getScheme() + "://" + 
					request.getServerName() + ":" + request.getServerPort() + 
					request.getContextPath() + Constants.SERVLET_CENTER%>', 
     
    		//GET method is used            		     		
    		dateType: 'json',

    		//pass the data         
    		data: '',     
     
    		//Do not cache the page
    		cache: false,
     
    		//success
    		success: function(json) { 																								
						//Init administrated centers
						$("#<%=Fields.USER_ADMINISTRATED_CENTERS%>").empty();
						$("#tmplCenter").tmpl(json.data).prependTo("#<%=Fields.USER_ADMINISTRATED_CENTERS%>");

						administrateList = $("#<%=Fields.USER_ADMINISTRATED_CENTERS%>").asmSelect({
							addItemTarget: 'bottom',
							animate: false,
							highlight: false,
							sortable: true,
							exceptionClass: 'OwnCenter',
							hiddenClass: 'hidden'
						});
																							
						//Centers
						//$("#<%=Fields.CENTER_ID%>").empty();
						$("#tmplCenter").tmpl(json.data).prependTo("#<%=Fields.CENTER_ID%>");
						
						//Set enabled
						$("#<%=Fields.CENTER_ID%>").attr('disabled', '');
			},
			
			error: function (XMLHttpRequest, textStatus, error) {            				
				//Set enabled
				$("#<%=Fields.CENTER_ID%>").attr('disabled', '');
			}                 	                	      
		});
	});
</script>


<!-- SERVICES LOAD -->
<script type="text/javascript">
    $("#<%=Fields.CENTER_ID%>").change(function () {          
          $("#<%=Fields.CENTER_ID%> option:selected").each(function () {
                var value = $(this).val();
				if (value!=null && value!="") {
				
					$("#<%=Fields.SERVICE_ID%>").attr('disabled', '');								
					addCurrentCenter("<%=Fields.USER_ADMINISTRATED_CENTERS%>", value); 							
									
					$("#dlgWait").dialog('open');

					//start the ajax
					$.ajax({
			    		//this process the request
			    		url: '<%=request.getScheme() + "://" + 
								request.getServerName() + ":" + request.getServerPort() + 
								request.getContextPath() + Constants.SERVLET_SERVICE%>', 
			     
			    		//GET method is used            		     		
			    		dateType: 'json',

			    		//pass the data         
			    		data: '<%=Fields.CENTER_ID%>=' + value,     
			     
			    		//Do not cache the page
			    		cache: false,
			     
			    		//success
			    		success: function(json) { 																				
									//Init results' form			            		 								
									$("#<%=Fields.SERVICE_ID%>").empty();												
									$("#tmplService").tmpl(json.data).prependTo("#<%=Fields.SERVICE_ID%>");
									$("#dlgWait").dialog('close');
						},
						
						error: function (XMLHttpRequest, textStatus, error) {            				
							$("#dlgWait").dialog('close');
							//ERROR Dialog
							$("#btnOkError").bind("click", function() {
								$("#dlgError").dialog("close"); 
							});
							$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="msg.error"/>");							
							$("#dlgError").dialog('open');
						}                 	                	      
					});
				}
				
				else {
					$("#<%=Fields.SERVICE_ID%>").empty();
					$("#<%=Fields.SERVICE_ID%>").attr('disabled', 'disabled');	
				}   
          });                    
    });
    
</script>



<!-- CHANGE PROFILE -->
<script type="text/javascript">
	 $("#<%=Fields.PROFILE_ID%>").change(function() {    
	  	$("#<%=Fields.PROFILE_ID%> option:selected").each(function () {	 		
			//if selected local administrator show administrated centers			
			if ($(this).val()=="<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>") {															
				$("#centerAdministration").show();	
				//Add current center															
				addCurrentCenter("<%=Fields.USER_ADMINISTRATED_CENTERS%>", $("#<%=Fields.CENTER_ID%>").val()); 					
			}
			else {
				$("#centerAdministration").hide();				
			}
			
			//Show scroll
			showScrollNew();
		});		 			
    });
</script>

<script type="text/javascript">		
	$(document).ready(function() {
		$("#<%=Fields.USER_ADMINISTRATED_CENTERS%>").hide();
			
		$("#<%=Fields.PROFILE_ID%> option:selected").each(function () {	 		
			//if selected local administrator show administrated centers			
			if ($(this).val()=="<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>") {															
				$("#centerAdministration").show();		
				//Add current center															
				addCurrentCenter("<%=Fields.USER_ADMINISTRATED_CENTERS%>", $("#<%=Fields.CENTER_ID%>").val());
								
			}
			else {
				$("#centerAdministration").hide();
			}
			
			//Show scroll
			showScrollNew();
			
		});		 
    });
</script>

<script>
	$("#<%=Fields.USER_ADMINISTRATED_CENTERS%>").change(function() {
		//Show scroll
		showScrollNew();
	});
</script>
		



<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#btnSaveNew").click(function() {
		$("#newForm").submit();
	});
</script>


<!-- FORM VALIDATION -->
<script type="text/javascript">		
	$(document).ready(function() {
			
		//Show scroll
		showScrollNew();
		
		$.validator.addMethod("usertmpl",function(value,element) {
			return this.optional(element) || /^[\w\d\_\.\-]{2,}$/i.test(value); 
			},"<fmt:message key="msg.invalid_chars"/>");

		$.validator.addMethod("nametmpl",function(value,element) {
			return this.optional(element) || /^[\s\w\d\_\.\-áéíóúÁÉÍÓÚnÑ]{2,}$/i.test(value); 
			},"<fmt:message key="msg.invalid_chars"/>");
		
		// validate signup form on keyup and submit
		$("#newForm").validate({
			rules: {
				<%=Fields.USER_USER%>: {
					required: true,
					minlength: 2,
					maxlength: 16,
					usertmpl: true
				},	
				<%=Fields.USER_NAME%>: {
					required: true,
					minlength: 2,					
					maxlength: 32,
					nametmpl: true
				},	
				<%=Fields.USER_SURNAME%>: {
					required: true,
					minlength: 2,
					maxlength: 64,
					nametmpl: true
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
				},	
				<%=Fields.USER_SECURITY_QUESTION%>: {
					required: true,
					minlength: 5,
					maxlength: 32
				},
				<%=Fields.USER_SECURITY_ANSWER%>: {
					required: true,
					minlength: 2,
					maxlength: 32
				},	
				<%=Fields.USER_PHONE%>: {
					maxlength: 16
				},
				<%=Fields.USER_MOBILE%>: {
					maxlength: 16
				},
				<%=Fields.USER_EMAIL%>: {
					required: true,
					maxlength: 64,
					email: true
				},
				<%=Fields.USER_EMAIL_USER%>: {
					maxlength: 16					
				},
				<%=Fields.USER_EMAIL_PASSWORD%>: {
					maxlength: 32
				},
				<%=Fields.PROFILE_ID%>: "required",
				//<%=Fields.CENTER_ID%>: "required",										
				//<%=Fields.SERVICE_ID%>: "required",
				<%=Fields.USER_OBSERVATIONS%>: {
					maxlength: 255
				}									
			},
			messages: {
				<%=Fields.USER_USER%>: {
					required: 	"<fmt:message key="msg.user"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.USER_NAME%>: {
					required: 	"<fmt:message key="msg.name"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 32 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.USER_SURNAME%>: {
					required: 	"<fmt:message key="msg.surname"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 64 <fmt:message key="msg.chars"/>"
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
				},	
				<%=Fields.USER_SECURITY_QUESTION%>: {
					required: 	"<fmt:message key="msg.security_question"/>",
					minlength:  "<fmt:message key="msg.min"/> 5 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 32 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.USER_SECURITY_ANSWER%>: {
					required: 	"<fmt:message key="msg.security_answer"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 32 <fmt:message key="msg.chars"/>"
				},	
				<%=Fields.USER_PHONE%>: {
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.USER_MOBILE%>: {
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.USER_EMAIL%>: {
					required: 	"<fmt:message key="msg.email"/>",					
					maxlength:  "<fmt:message key="msg.max"/> 64 <fmt:message key="msg.chars"/>",
					email:		"<fmt:message key="msg.format"/>"
				},		
				<%=Fields.USER_EMAIL_USER%>: {
					maxlength:  "<fmt:message key="msg.max"/> 16 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.USER_EMAIL_PASSWORD%>: {
					maxlength:  "<fmt:message key="msg.max"/> 32 <fmt:message key="msg.chars"/>"				
				},
				<%=Fields.PROFILE_ID%>: "<fmt:message key="msg.profile"/>",
				//<%=Fields.CENTER_ID%>: "<fmt:message key="msg.center"/>",
				//<%=Fields.SERVICE_ID%>: "<fmt:message key="msg.service"/>"				
			},	
			invalidHandler: function(e, validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					$("div.error").show();
				} else {
					$("div.error").hide();
				}
			},					
			submitHandler: function() {
				$("div.error").hide();				
				$("#dlgWait").dialog('open');
				
				//start the ajax
				$.ajax({
            		//this process the request
            		url: '<%=request.getScheme() + "://" + 
							request.getServerName() + ":" + request.getServerPort() + 
							request.getContextPath() + Constants.SERVLET_USER%>', 
             
            		//GET method is used
            		type: "POST",
 
            		//pass the data         
            		data: $('#newForm').serialize(),     
             
            		//Do not cache the page
            		cache: false,
             
            		complete : function(xhr, status) {		                            			            			
            			switch(status)
		                {
		                	case "success":
			                    $("#dlgWait").dialog('close');            
								
								//OK result							
								//Capture dialog's ok button
								$("#btnOkInfo").bind("click", function() {
									$("#dlgInfo").dialog("close"); 
								});
	
								$("#dlgInfo .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="user.search"/> <fmt:message key="msg.created"/>");							
	
                     			$('#newForm').each (function(){
                     			  	this.reset();
                     			});      

								$("#dlgInfo").dialog('open');								
															
								break;
		                    case "error":
		                        	
		                        //Get error information 
								$("#dlgWait").dialog('close');
	                			//ERROR Dialog
								$("#btnOkError").bind("click", function() {
									$("#dlgError").dialog("close"); 
								});
									
								if (xhr.status==<%=Errors.MAX_LICENSES_ERROR%>) {
									//Max licenses
									$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="msg.max_licenses"/>");
								}
								else {
									//Generic Error
									$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="msg.error"/>");
								}
								
															
								$("#dlgError").dialog('open');
		                        
		                }
			        }             	                	      
        		});
         
        		//cancel the submit button default behaviours
        		return false;
				
			}	
		});
		                              
	});

</script>



