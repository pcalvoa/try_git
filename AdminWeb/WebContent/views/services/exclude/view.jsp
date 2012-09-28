<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>
<%@ page import="com.tid.Errors"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
#viewContainer label.error { 	
	display:block;
	/*float:left;*/
	text-align:left; 
	position: absolute; 
	margin-left:100px;       
}
</style>

<div id="wrapper">
	<div id="viewContainer" class="container-form container_750" style="height:380px;">	
		<form id="viewForm" class="form" method="post">		
			<fieldset>
				<div id="view"></div>
				
				<div style="position:relative;float:left;">
					<div id="userView" class="section inline" style="width:30px;margin:0 0 0 10px;">	
			    		<div class="legend mt-legend-text">User</div>
						<div class="separator micro" style="margin-left:40px;"></div>					
						
						<!--<div id="usersList" class="scroll scroll-pane-view" style="width:300px; height:140px;">
							<div id="users" class="userView" style="margin-left:60px;"></div>																	        	
						</div>-->
						
						<div id="usersList" class="scroll scroll-pane-view"  style="height:140px;">
							<div id="users" class="userView" style="margin-left:20px;width:300px;"></div>			        		
						</div>
					</div>
	 			</div>
	 			
				<input id="serviceFormSubmit" class="submit" style="visibility: hidden;" type="submit" name="submit"/ />
			</fieldset>
		</form>
		
		<!--<div class="mt-low-interest-text" style="float:left;padding-left:50px;"><fmt:message key="msg.mandatory"/></div>-->
	</div>
	
	<div id="foot" class="rounded-corners-down">
		<div id="leftButton" class="mt-button-foot-left">
			<a id="btnBack" href="#" class="cancelDarkButton view" style="margin:0;" tabindex="3"><span><fmt:message key="label.back"/></span></a>
			<a id="btnCancel" href="#" class="cancelDarkButton edit" style="margin:0;" tabindex="4"><span><fmt:message key="label.cancel"/></span></a>
		</div>
		
		<div id="rightButton" class="mt-button-foot-right">
			<a id="btnEdit" href="#" class="acceptDarkButton view" style="margin:0;" tabindex="5"><span><fmt:message key="label.edit"/></span></a>
			<a id="btnSaveEdit" href="#" class="acceptDarkButton edit" style="margin:0;" tabindex="6"><span><fmt:message key="label.save"/></span></a>
		</div>
		
	</div>			
</div>

<!-- TEMPLATES -->  
<script id="tmplView" type="text/x-jquery-tmpl">	
			
			<div class="section block">
	    		<div class="legend mt-legend-text"><fmt:message key="label.information"/></div>
				<div class="separator mini" style="margin-left:40px;"></div>
				<input type="hidden" name="<%=Constants.PARAM_OP%>" value="<%=Constants.VALUE_UPDATE%>">
		    	<input readonly type="hidden" class="mt-input-text" id="<%=Fields.ID%>" name="<%=Fields.SERVICE_ID%>"
					value="${id}"/>
				<input readonly type="hidden" class="mt-input-text" id="<%=Fields.CENTER_ID%>" name="<%=Fields.CENTER_ID%>"
					value="${centerid}"/>
					
				<div class="inline">
					<div class="view inline" style="float:left;margin-left:45px;">
						<label class="view mt-label-text" for="name" style="width:80px;"><fmt:message key="service.name"/> </label>
		    			<input readonly type="text" class="view mt-input-text" id="<%=Fields.SERVICE_NAME%>_VIEW" name="<%=Fields.SERVICE_NAME%>_VIEW" tabindex="1"
						value="${name}"/>
					</div>
					<div class="edit inline" style="float:left;">
						<label class="edit mt-label-text" for="name" style="width:80px;"><fmt:message key="service.name"/>* </label>
						<input type="text" class="edit mt-input-text" id="<%=Fields.SERVICE_NAME%>" name="<%=Fields.SERVICE_NAME%>" tabindex="1"
						value="${name}"/>
					</div>
					<div class="view inline" style="float:left;">
						<label class="view mt-label-text" for="<%=Fields.CENTER_NAME%>_VIEW" style="width:80px;"><fmt:message key="center.name"/> </label>
						<input readonly type="text" class="view mt-input-text" id="<%=Fields.CENTER_NAME%>_VIEW" name="<%=Fields.CENTER_NAME%>_VIEW" 
						value="${centername}"/>
					</div>						
					<div class="edit inline" style="float:left;">
						<label class="edit mt-label-text" for="<%=Fields.CENTER_NAME%>" style="width:80px;"><fmt:message key="center.name"/>* </label>
						<input readonly type="text" class="edit mt-input-text" id="<%=Fields.CENTER_NAME%>" name="<%=Fields.CENTER_NAME%>" 
						value="${centername}"/>
					</div>	
					
				</div>

	    	</div>
	    		    	
	    	<div class="block" style="positon:absolute;margin-top:50px;">
	    	
		    	<div id="description" class="section" style="float:left;width:300px;">
		    		<div class="legend mt-legend-text">Description</div>
					<div class="separator micro" style="margin-left:40px;"></div>
					<div style="margin-left:60px;">
			    		<textarea readonly disabled rows="3" class="styletextarea view short mt-input-text" style="width:300;height:75px;" id="<%=Fields.CENTER_DESCRIPTION%>_VIEW" name="<%=Fields.CENTER_DESCRIPTION%>_VIEW" 
							tabindex="2">${description}</textarea>
			    		<textarea rows="3" class="styletextarea edit short mt-input-text" style="width:300;height:75px;" id="<%=Fields.CENTER_DESCRIPTION%>" name="<%=Fields.CENTER_DESCRIPTION%>" 
							tabindex="2">${description}</textarea>												
					</div>
					<div class="legend mt-legend-text"><fmt:message key="page.licenses_title"/></div>
					<div class="separator micro" style="margin-left:40px;"></div>
					<div style="margin-left:60px;">
						<input readonly type="text" class="view mt-input-text" id="<%=Fields.LICENSES%>_VIEW" name="<%=Fields.LICENSES%>_VIEW" tabindex="4"
						value="${licenses}"/>					
						<input type="text" class="edit mt-input-text" id="<%=Fields.LICENSES%>" name="<%=Fields.LICENSES%>" tabindex="4"
						value="${licenses}"/>
					</div>
		    	</div>					
										    	
	    	</div>
	    	

</script>

<script id="tmplUser" type="text/x-jquery-tmpl">	
<div 
{{if count % 2}}
	class="greyRow mt-input-text"
{{else}}
	class="whiteRow mt-input-text"
{{/if}}>	
	<div style="padding-top:5px;">
	<div class="inline" style="vertical-align:middle;padding:0 0 0 5px;">${surname}, ${name}</div> 
	<!--<div class="edit inline" style="float:right;margin:0 20px;" id="${id}">
		<input type="button" id="button_delete" class="btnDeleteUser innerbutton" value="${id}"/>		
	</div>-->
	</div>
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
		var pane = $('.scroll-pane-view');
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

<!-- VALIDATION -->
<script type="text/javascript">
	$(document).ready(function() {	

		//showScrollView();	
		
		$("#viewForm").validate({
			rules: {
				<%=Fields.SERVICE_NAME%>: {
					required: true,
					minlength: 2
				},
				<%=Fields.LICENSES%>: {
					number: true,
					greaterThanZero : true
				}
			},
			messages: {				
				<%=Fields.SERVICE_NAME%>: {
					required: 	"<fmt:message key="msg.name"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.LICENSES%>: {
					number: 	"<fmt:message key="msg.number"/>",
					greaterThanZero : "<fmt:message key="msg.licenses_zero"/>"
				}
			},			

			submitHandler: function() {

				//Open wait dialog
				$("#dlgWait").dialog('open');

				//start the ajax
        		$.ajax({
            		//this process the request
            		url: '<%=request.getScheme() + "://" + 
							request.getServerName() + ":" + request.getServerPort() + 
							request.getContextPath() + Constants.SERVLET_SERVICE%>', 
             
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
								else if (xhr.status==<%=Errors.MIN_LICENSES_ERROR%>) {
									//Max licenses
									$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="msg.invalid_licenses"/>");
								}
								else {
									//Generic Error
									$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="msg.error"/>");
								}
																							
								$("#dlgError").dialog('open');
		                        
		                }
            		}
            		
            		/*
            		//success
            		success: function () {          
						$("#dlgWait").dialog('close');
						$("#searchForm").submit(); 
						changeState('search');
			            	                			                	
                	},
        			error: function (XMLHttpRequest, textStatus, error) {
                		$("#dlgWait").dialog('close');
                		//ERROR Dialog
						$("#btnOkError").bind("click", function() {
							$("#dlgError").dialog("close"); 
						});
						$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.generic"/>");							
						$("#dlgError").dialog('open');
        			}      
                	*/           	                	      
        		});
        			         
        		//cancel the submit button default behaviours
        		return false;
    		    							
			}
		});

		/* BUTTONS */

		$("#btnBack").click(function() {
			changeState('search');			
			//showScrollView();									
		});

		$("#btnCancel").click(function() {
			viewMode();
													
		});
		
		$("#btnEdit").click(function() {
			editMode();			
		});
					

	});
		
</script>
