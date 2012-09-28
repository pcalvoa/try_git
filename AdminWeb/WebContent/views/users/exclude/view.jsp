<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>
<%@ page import="com.tid.Errors"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="wrapper">

	<div id="viewContainer" class="container-form" style="height:380px;">	
		 
		<div id="userEditPanel" class="container_700 scroll scroll-pane-view" style="height:330px;">
			
				<form id="viewForm" class="form"  method="post">		
					<fieldset>				
						<div id="view"> </div>
						<input id="userFormSubmit" class="submit" style="visibility: hidden;" type="submit" name="submit"/ />
						<input type="hidden" name="<%=Constants.PARAM_OP%>" value="<%=Constants.VALUE_UPDATE%>">
					</fieldset>
				</form>		
							
		</div>			
								
		<div class="mt-low-interest-text" style="float:left;padding:10px 0 0 250px;"><fmt:message key="msg.mandatory"/></div>
	</div>
	
	<div id="foot" class="rounded-corners-down">
		<div id="leftButton" class="mt-button-foot-left">
			<a id="btnBack" href="#" class="cancelDarkButton view" style="margin:0;" tabindex="15"><span><fmt:message key="label.back"/></span></a>
			<a id="btnCancel" href="#" class="cancelDarkButton edit" style="margin:0;" tabindex="15"><span><fmt:message key="label.cancel"/></span></a>
		</div>
		
		<div id="rightButton" class="mt-button-foot-right">
			<a id="btnEdit" href="#" class="acceptDarkButton view" style="margin:0;" tabindex="16"><span><fmt:message key="label.edit"/></span></a>
			<a id="btnSaveEdit" href="#" class="acceptDarkButton edit" style="margin:0;" tabindex="16"><span><fmt:message key="label.save"/></span></a>
		</div>
		
	</div>			
</div>

<!-- TEMPLATES -->  
<script id="tmplView" type="text/x-jquery-tmpl">	
				<input type="hidden" id="e<%=Fields.USER_ID%>" value="${id}"
			    		name="<%=Fields.USER_ID%>"/>				
				<div>
	    			<div class="legend mt-legend-text" style="margin-top:25px;"><fmt:message key="user.personal_information_title"/></div>
					<div class="separator mini" style="margin-left:40px;"></div>
	    		</div>
				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_NAME%>"><fmt:message key="user.name"/>* </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_NAME%>_VIEW" value="${name}"
			    		name="<%=Fields.USER_NAME%>_VIEW" tabindex="1" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_NAME%>" value="${name}"
			    		name="<%=Fields.USER_NAME%>" tabindex="1"/>
			    </div>
			    
			    <div>
			    	<label class="mt-label-text" for="<%=Fields.USER_SURNAME%>"><fmt:message key="user.surname"/>* </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_SURNAME%>_VIEW" value="${surname}"
			    		name="<%=Fields.USER_SURNAME%>_VIEW" tabindex="2" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_SURNAME%>" value="${surname}"
			    		name="<%=Fields.USER_SURNAME%>" tabindex="2"/>
			    </div>
			    <div>
			    	<label class="mt-label-text" for="<%=Fields.USER_USER%>"><fmt:message key="user.user"/>* </label>
					<input class="view mt-input-text" type="text" id="<%=Fields.USER_USER%>_VIEW" value="${user}" 
			    		name="<%=Fields.USER_USER%>_VIEW" tabindex="3" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_USER%>" value="${user}"
			    		name="<%=Fields.USER_USER%>" tabindex="3"/>
			    </div>

  				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_PASSWORD%>"><fmt:message key="user.password"/>* </label>
			    	<input class="view mt-input-text" type="password" id="<%=Fields.USER_PASSWORD%>_VIEW" 
			    		name="<%=Fields.USER_PASSWORD%>_VIEW" tabindex="4" READONLY/>
			    	<input class="edit mt-input-text" type="password" id="e<%=Fields.USER_PASSWORD%>" value="${password}"
			    		name="e<%=Fields.USER_PASSWORD%>" tabindex="4"/>					
			    </div>
			    
			    <div>
			    	<label class="mt-label-text" class="edit" for="rpassword"><fmt:message key="user.rpassword"/>* </label>
			    	<input class="view mt-input-text" type="password" id="r<%=Fields.USER_PASSWORD%>_VIEW" value="${password}"
			    		name="r<%=Fields.USER_USER%>_VIEW" tabindex="5" style="visibility:hidden;"/>
			    	<input class="edit mt-input-text" type="password" id="er<%=Fields.USER_PASSWORD%>" value="${password}"
			    		name="er<%=Fields.USER_PASSWORD%>" tabindex="5"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_SECURITY_QUESTION%>"><fmt:message key="user.security_question"/>* </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_SECURITY_QUESTION%>_VIEW" value="${securityquestion}"
			    		name="<%=Fields.USER_SECURITY_QUESTION%>_VIEW" tabindex="6" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_SECURITY_QUESTION%>" value="${securityquestion}"
			    		name="<%=Fields.USER_SECURITY_QUESTION%>" tabindex="6"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_SECURITY_ANSWER%>"><fmt:message key="user.security_answer"/>* </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_SECURITY_ANSWER%>_VIEW" value="${securityanswer}"
			    		name="<%=Fields.USER_SECURITY_ANSWER%>_VIEW" tabindex="7" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_SECURITY_ANSWER%>" value="${securityanswer}"
			    		name="<%=Fields.USER_SECURITY_ANSWER%>" tabindex="7"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_PHONE%>"><fmt:message key="user.phone"/> </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_PHONE%>_VIEW" value="${phone}"
			    		name="<%=Fields.USER_PHONE%>_VIEW" tabindex="8" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_PHONE%>" value="${phone}"
			    		name="<%=Fields.USER_PHONE%>" tabindex="8"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_MOBILE%>"><fmt:message key="user.mobile"/> </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_MOBILE%>_VIEW" value="${mobile}"
			    		name="<%=Fields.USER_MOBILE%>_VIEW" tabindex="9" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_MOBILE%>" value="${mobile}"
			    		name="<%=Fields.USER_MOBILE%>" tabindex="9"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_EMAIL%>"><fmt:message key="user.email"/>* </label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_EMAIL%>_VIEW" value="${email}" 
			    		name="<%=Fields.USER_EMAIL%>_VIEW" tabindex="10" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_EMAIL%>" value="${email}"
			    		name="<%=Fields.USER_EMAIL%>" tabindex="10"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_EMAIL_USER%>"><fmt:message key="user.email_user"/></label>
			    	<input class="view mt-input-text" type="text" id="<%=Fields.USER_EMAIL_USER%>_VIEW" value="${emailname}" 
			    		name="<%=Fields.USER_EMAIL_USER%>_VIEW" tabindex="11" READONLY/>
			    	<input class="edit mt-input-text" type="text" id="e<%=Fields.USER_EMAIL_USER%>" value="${emailname}"
			    		name="<%=Fields.USER_EMAIL_USER%>" tabindex="11"/>
			    </div>

				<div>
			    	<label class="mt-label-text" for="<%=Fields.USER_EMAIL_PASSWORD%>"><fmt:message key="user.email_password"/></label>
			    	<input class="view mt-input-text" type="password" id="<%=Fields.USER_EMAIL_PASSWORD%>_VIEW" value="${emailpassword}" 
			    		name="<%=Fields.USER_EMAIL_PASSWORD%>_VIEW" tabindex="12" READONLY/>
			    	<input class="edit mt-input-text" type="password" id="e<%=Fields.USER_EMAIL_PASSWORD%>" value="${emailpassword}"
			    		name="<%=Fields.USER_EMAIL_PASSWORD%>" tabindex="12"/>
			    </div>

				<div>
	    			<div class="legend mt-legend-text" style="margin-top:25px;"><fmt:message key="user.professional_information_title"/></div>
					<div class="separator mini" style="margin-left:40px;"></div>
	    		</div>
			    
	    		<div>
			    	<label class="mt-label-text" for="centerName"><fmt:message key="center.name"/></label>
			    	{{if centername=="Default"}}
						<input class="view mt-input-text" type="text" id="<%=Fields.CENTER_ID%>_VIEW" value="" 
			    			name="<%=Fields.CENTER_ID%>_VIEW" tabindex="13" READONLY/>	
					{{else}}
						<input class="view mt-input-text" type="text" id="<%=Fields.CENTER_ID%>_VIEW" value="${centername}" 
			    			name="<%=Fields.CENTER_ID%>_VIEW" tabindex="13" READONLY/>				    	
					{{/if}}
			    	
			    	<select class="edit" name="<%=Fields.CENTER_ID%>" id="ecenterId" tabindex="13">
						<option class="mt-input-text" value="11" selected="selected"><fmt:message key="msg.select"/></option>			    		    	      						   		   	
	   				</select>				    	
			    </div>
			    
			    <div>
			    	<label class="mt-label-text" for="serviceName"><fmt:message key="service.name"/></label>	
			    	{{if servicename=="Default"}}
						<input class="view mt-input-text" type="text" id="<%=Fields.SERVICE_ID%>_VIEW" value="" 
			    			name="<%=Fields.SERVICE_ID%>_VIEW" tabindex="14" READONLY/>
					{{else}}
						<input class="view mt-input-text" type="text" id="<%=Fields.SERVICE_ID%>_VIEW" value="${servicename}" 
			    			name="<%=Fields.SERVICE_ID%>_VIEW" tabindex="14" READONLY/>
					{{/if}}
					
			    	<select class="edit"  name="<%=Fields.SERVICE_ID%>" id="eserviceId" tabindex="14">			    	
			    		{{if servicename=="Default"}}
							<option class="mt-input-text" value="${serviceid}" selected="selected"><fmt:message key="msg.select"/></option>
						{{else}}
							<option class="mt-input-text" value="${serviceid}" selected="selected">${servicename}</option>
						{{/if}}			    				    							       		  		   		
	   				</select>	
	   							    	
			    </div>

			    <div>
			    	<label class="mt-label-text" for="<%=Fields.PROFILE_ID%>"><fmt:message key="user.profile"/>* </label>
					<input class="view mt-input-text" type="text" id="<%=Fields.PROFILE_ID%>_VIEW" value="${profilename}" 
			    		name="<%=Fields.PROFILE_ID%>_VIEW" tabindex="15" READONLY/>
					<select class="edit"  name="<%=Fields.PROFILE_ID%>" id="e<%=Fields.PROFILE_ID%>" tabindex="15"> 
						<option class="mt-input-text" {{if profile == "<%=Constants.PROFILE_USER%>" }} selected {{/if}}
							value="<%=Constants.PROFILE_USER%>"><fmt:message key="profile.user"/></option>      		   			
						<option class="mt-input-text" {{if profile == "<%=Constants.PROFILE_ADMINISTRATOR%>" }} selected {{/if}} 
							value="<%=Constants.PROFILE_ADMINISTRATOR%>"><fmt:message key="profile.administrator"/></option>
						<option class="mt-input-text" {{if profile == "<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>" }} selected {{/if}} 
							value="<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>"><fmt:message key="profile.local_administrator"/></option>
	   				</select>
			    </div>			    
			    
			     <div id="ecenterAdministration">	  
						    						    						    
				    <div>
		    			<legend class="mt-legend-text"><fmt:message key="user.admin_centers"/></legend>
		    		</div>
				    
				    <div class="view" >
				    	<label class="mt-label-text" for="e<%=Fields.USER_ADMINISTRATED_CENTERS%>"><fmt:message key="center.name"/></label>
		   				<ul class="asmList" id="e<%=Fields.USER_ADMINISTRATED_CENTERS%>_VIEW"></ul>					    					    
			    	</div>

				    <div class="edit" >
				    	<label class="mt-label-text" for="e<%=Fields.USER_ADMINISTRATED_CENTERS%>"><fmt:message key="center.name"/></label>
		   				<select name="<%=Fields.USER_ADMINISTRATED_CENTERS%>" id="e<%=Fields.USER_ADMINISTRATED_CENTERS%>" multiple="multiple" tabindex="16">									    		 									     		   		
		   				</select>					    					    
			    	</div>
							
				</div>
			    							    			   			    			    			    			    			   
			    <div>
	    			<div class="legend mt-legend-text" style="margin-top:25px;"><fmt:message key="user.remark"/></div>
					<div class="separator mini" style="margin-left:40px;"></div>
	    		</div>
	    		
	    		<div>			    			    
					<label style="visibility:hidden;" for="<%=Fields.USER_OBSERVATIONS%>"><fmt:message key="user.remark"/></label>
			    	<textarea class="styletextarea view large mt-input-text"  rows="3"
			    		id="<%=Fields.USER_OBSERVATIONS%>_VIEW" name="<%=Fields.USER_OBSERVATIONS%>_VIEW" 
							tabindex="16" READONLY>${observations}</textarea>
			    	<textarea class="styletextarea edit short mt-input-text"  rows="5"
			    		id="e<%=Fields.USER_OBSERVATIONS%>" name="<%=Fields.USER_OBSERVATIONS%>" 
							tabindex="16">${observations}</textarea>

				</div>	
</script>

<!-- Showscroll -->
<script type="text/javascript">
	function showScrollView() {
		var settings = {			
			verticalDragMinHeight: 50,
			verticalDragMaxHeight: 50,
			showArrows: true		
			
			
		};
		var pane = $('.scroll-pane-view')
		pane.jScrollPane(settings);
		var api = pane.data('jsp');
		api.reinitialise();
	}
</script>


<!-- BUTTONS -->
<script type="text/javascript">
	$("#btnSaveEdit").click(function() {		
		$("#viewForm").submit();			
	});
</script>

<!-- TEMPLATES -->  
<script id="tmplCenter" type="text/x-jquery-tmpl">	
		<option class="mt-input-text" value="${id}" selected="selected">${name}</option> 	
</script>
  
<script id="tmplService" type="text/x-jquery-tmpl">
	<option class="mt-input-text" value="${id}">${name}</option>		  			  		                        				  			
</script>

<script id="tmplAdministratedCenter" type="text/x-jquery-tmpl">
	<li class="asmListItem mt-input-text">${name}</li> 
</script>


<!-- CENTERS LOAD -->
<script type="text/javascript">
	function loadCenters() {
		
		$("#dlgWait").dialog('open');

		//Set disabled
		$("#ecenterId").attr('disabled', 'disabled');
		
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
						$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>").empty();						
						$("#tmplCenter").tmpl(json.data).prependTo("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>");
						
						//Add one select
						if ($('.asmSelect').length<=1) {
						
							//Load administratedcenters
							$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>").asmSelect({
								addItemTarget: 'bottom',
								animate: false,
								highlight: false,
								sortable: true,
								exceptionClass: 'OwnCenter'
							});																					
						}
						
						//load administrated centers
						$.ajax({
				    		//this process the request
				    		url: '<%=request.getScheme() + "://" + 
									request.getServerName() + ":" + request.getServerPort() + 
									request.getContextPath() + Constants.SERVLET_CENTER%>', 
				     
				    		//GET method is used            		     		
				    		dateType: 'json',
				
				    		//pass the data         
				    		data: '<%=Fields.USER_ID%>=' + $('#e<%=Fields.USER_ID%>').val(),     
				     
				    		//Do not cache the page
				    		cache: false,
				     
				    		//success
				    		success: function(json) { 			
				    		    	    		 																	
								$('.OwnCenter').removeClass('OwnCenter').attr("selected", false);
																																																	  																																																	
								//Load all administrated centers into selection
								jQuery.each(json.data, function() {  					
				  					
				  					var value = this.id;
				  					var currentCenter = $('#ecenterId').val();  					
				  					
				  					//Add center																								
									$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%> option").each(function() {
										if ($(this).val() == value) {
											$(this).attr("selected", true);
																						
											//Only for current center											
											if ($(this).val() == currentCenter) {
												$(this).addClass('OwnCenter');
											}																						
										}
									});
									
									$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>").trigger('change');
																															    		  					  					  					
								});	
								
								//Load view mode								
								$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>_VIEW").empty();
								$("#tmplAdministratedCenter").tmpl(json.data).prependTo("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>_VIEW");																	
												
							},
							error: function (XMLHttpRequest, textStatus, error) {            				
								
							}                 	                	      
						});
											
												
						//Init results' form	
						$("#tmplCenter").tmpl(json.data).appendTo("#ecenterId");
						
						//Select current center																														
						$("#ecenterId option").each(function() {							
							if ($(this).text() == $("#<%=Fields.CENTER_ID%>_VIEW").val()) {								
								$(this).attr("selected", true);																																															
							}
						});
						
						//Reload services						
						$("#ecenterId").trigger('change');

						//close wait dialog
						$("#dlgWait").dialog('close');
						
						//Set enabled
						$("#ecenterId").attr('disabled', '');
			},
			error: function (XMLHttpRequest, textStatus, error) {            				
				$("#dlgWait").dialog('close');
				//Set enabled
				$("#ecenterId").attr('disabled', '');
			}                 	                	      
		});
		
		
		
	}
</script>

<!-- SERVICES LOAD -->
<script type="text/javascript">
    $("#ecenterId").live("change", function () {          
          $("#ecenterId option:selected").each(function () {
                var value = $(this).val();

				if (value!=null && value!="") {
				
					//Update administrate centers 																		
					$('.OwnCenter').removeClass('OwnCenter').attr("selected", false);
																																													  																													
					//Add current center															
					$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>").val(value).attr("selected", true);
					$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%> option").each(function() {
						if ($(this).val() == value) {
							$(this).addClass('OwnCenter');
						}
					});
					
					$("#e<%=Fields.USER_ADMINISTRATED_CENTERS%>").trigger('change');		
					
					$("#dlgWait").dialog('open');

					//Set disabled
					$("#eserviceId").attr('disabled', 'disabled');

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
									$("#eserviceId").empty();												
									$("#tmplService").tmpl(json.data).prependTo("#eserviceId");
																											
									//Select current service																													
									$("#eserviceId option").each(function() {
																													
										if ($("#<%=Fields.SERVICE_ID%>_VIEW").val() == "" && $(this).text() == "<fmt:message key="msg.select"/>") {
											$(this).attr("selected", true);										
										}										
										else if ($(this).text() == $("#<%=Fields.SERVICE_ID%>_VIEW").val()) {																														
											$(this).attr("selected", true);		
										}
									});
																											
									//Set enabled
									$("#eserviceId").attr('disabled', '');

									$("#dlgWait").dialog('close');									
						},
						error: function (XMLHttpRequest, textStatus, error) {            				
							//Set enabled
							$("#eserviceId").attr('disabled', '');
							
							$("#dlgWait").dialog('close');
							
						}                 	                	      
					});					
				}
                
          });                    
    });
    
</script>

<!-- CHANGE PROFILE -->
<script type="text/javascript">
	 $("#e<%=Fields.PROFILE_ID%>").live('change', function() {
	
		$("#e<%=Fields.PROFILE_ID%> option:selected").each(function () {	 		
			//if selected local administrator show administrated centers			
			if ($(this).val()=="<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>") {															
				$("#ecenterAdministration").show();
				//Add current center				
				addCurrentCenter("e<%=Fields.USER_ADMINISTRATED_CENTERS%>", $("#ecenterId").val()); 		
			}
			else {
				$("#ecenterAdministration").hide();
			}
		});		 			
    });
</script>

<!-- BEFORE SHOW VIEW -->
<script type="text/javascript">		
	$("#viewForm").bind('beforeShowView', function() {				
		//if selected local administrator show administrated centers
		if ($("#<%=Fields.PROFILE_ID%>_VIEW").val()=="<fmt:message key="profile.local_administrator"/>") {																
			$("#ecenterAdministration").show();															
		}
		else {
			$("#ecenterAdministration").hide();
		}		 
    });
</script>

<!-- BEFORE SHOW EDIT -->
<script type="text/javascript">		
	$("#viewForm").bind('beforeShowEdit', function() {				
		$("#e<%=Fields.PROFILE_ID%> option:selected").each(function () {	 		
			//if selected local administrator show administrated centers			
			if ($(this).val()=="<%=Constants.PROFILE_AMINISTRATOR_LOCAL%>") {																			
				$("#ecenterAdministration").show();															
			}
			else {
				$("#ecenterAdministration").hide();
			}
		});		 
    });
</script>

<!-- VALIDATION -->
<script type="text/javascript">
	$(document).ready(function() {

		showScrollView();
		
		$("#viewForm").validate({
			rules: {
				<%=Fields.USER_USER%>: {
					required: true,
					minlength: 2,
					maxlength: 16
				},	
				<%=Fields.USER_NAME%>: {
					required: true,
					minlength: 2,					
					maxlength: 32
				},	
				<%=Fields.USER_SURNAME%>: {
					required: true,
					minlength: 2,
					maxlength: 64
				},	
				e<%=Fields.USER_PASSWORD%>: {
					required: true,
					minlength: 8					
				},	
				er<%=Fields.USER_PASSWORD%>: {
					required: true,
					minlength: 8,
					equalTo: "#e<%=Fields.USER_PASSWORD%>"
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
				<%=Fields.CENTER_ID%>: "required",										
				<%=Fields.SERVICE_ID%>: "required",
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
				e<%=Fields.USER_PASSWORD%>: {
					required: 	"<fmt:message key="msg.password"/>",
					minlength:  "<fmt:message key="msg.min"/> 8 <fmt:message key="msg.chars"/>"			
				},
				er<%=Fields.USER_PASSWORD%>: {
					required: 	"<fmt:message key="msg.password"/>",
					minlength:  "<fmt:message key="msg.min"/> 8 <fmt:message key="msg.chars"/>",				
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
				<%=Fields.CENTER_ID%>: "<fmt:message key="msg.center"/>",
				<%=Fields.SERVICE_ID%>: "<fmt:message key="msg.service"/>"				
			},				

			submitHandler: function() {

				//Open wait dialog
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
            		data: $('#viewForm').serialize(),     
             
            		//Do not cache the page
            		cache: false,
             
            		complete : function(xhr, status) {		                            			            			
            			switch(status)
		                {
		                	case "success":
		                		$("#dlgWait").dialog('close');
								$("#searchForm").submit(); 
								changeState('search');						
															
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

		/* BUTTONS */

		$("#btnBack").click(function() {
			changeState('search');	
			showScrollView();											
		});

		$("#btnCancel").click(function() {
			//Prepare view for user
 			$("#viewForm").trigger('beforeShowView');			
			viewMode();
													
		});
		
		$("#btnEdit").click(function() {
			//Prepare view for user
 			$("#viewForm").trigger('beforeShowEdit');			
			editMode();
			loadCenters();			

		});
				
	});
		
</script>


