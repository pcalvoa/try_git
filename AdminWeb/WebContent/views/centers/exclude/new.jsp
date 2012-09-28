<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>
<%@ page import="com.tid.Errors"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="newContainer" class="container-form">
	<form id="newForm" class="form" method="post">
		<fieldset>
			<div>
	    		<legend class="mt-legend-text"><fmt:message key="center.title"/></legend>
	    	</div>
		    <div>
		    	<label class="mt-label-text" for="<%=Fields.CENTER_NAME%>"><fmt:message key="center.name"/>* </label>
		    	<input class="edit mt-input-text" type="text" id="<%=Fields.CENTER_NAME%>" 
		    		name="<%=Fields.CENTER_NAME%>" tabindex="1"/>
		    </div>
		    
		    <div>
		    	<label class="mt-label-text" for="<%=Fields.LICENSES%>"><fmt:message key="label.license"/> </label>
		    	<input class="edit mt-input-text" type="text" id="<%=Fields.LICENSES%>"  value="0"
		    		name="<%=Fields.LICENSES%>" tabindex="2"/>
		    </div>
		    
		    <div>
		    	<label class="mt-label-text" for="description"><fmt:message key="center.description"/></label>		    	
		    	<textarea class="styletextarea edit large mt-input-text" rows="3" style="width:450;"   
		    		id="<%=Fields.CENTER_DESCRIPTION%>" name="<%=Fields.CENTER_DESCRIPTION%>" 
						tabindex="3"></textarea>
			</div>	
	    	<input type="hidden" name="<%=Constants.PARAM_OP%>" value="<%=Constants.VALUE_INSERT%>">
	    	
	    	<div class="mt-low-interest-text" style="padding-left:200px;"><fmt:message key="msg.mandatory"/></div>
		</fieldset>
	</form>

	<%@include file="../../../include/error.jsp" %>
</div>



<div id="foot" class="rounded-corners-down">
	<div id="rightButton" class="mt-button-foot-right">
		<a id="btnSaveNew" href="#" class="acceptDarkButton" style="margin:0;" tabindex="4"><span><fmt:message key="label.save"/></span></a>
	</div>

</div>


<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#btnSaveNew").click(function() {
		$("#newForm").submit();
	});
</script>

<!-- FORM VALIDATION -->
<script type="text/javascript">
	$(document).ready(function(){
		
		$.validator.addMethod("nametmpl",function(value,element) {
			return this.optional(element) || /^[\s\w\d\_\.\-áéíóúÁÉÍÓÚnÑ]{2,}$/i.test(value); 
			},"<fmt:message key="msg.invalid_chars"/>");
		
		$.validator.addMethod("greaterThanZero", function(value, element) {
		    return this.optional(element) || (parseFloat(value) >= 0);
		}, "<fmt:message key="msg.licenses_zero"/>");
						
		$("#newForm").validate({
			rules: {
				<%=Fields.CENTER_NAME%>: {
					required: true,
					nametmpl: true
				},	
				<%=Fields.LICENSES%>: {
					number: true,
					greaterThanZero : true
				},	
				<%=Fields.CENTER_DESCRIPTION%>: {
					maxlength: 64
				}
			
			},
			messages: {				
				<%=Fields.CENTER_NAME%>: {
					required: 	"<fmt:message key="msg.user"/>",
					minlength:  "<fmt:message key="msg.min"/> 2 <fmt:message key="msg.chars"/>",
					maxlength:  "<fmt:message key="msg.max"/> 64 <fmt:message key="msg.chars"/>"
				},
				<%=Fields.LICENSES%>: {
					number: 	"<fmt:message key="msg.number"/>",
					greaterThanZero : "<fmt:message key="msg.licenses_zero"/>"
				},
				<%=Fields.CENTER_DESCRIPTION%>: {
					maxlength:  "<fmt:message key="msg.max"/> 64 <fmt:message key="msg.chars"/>"
				}
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
							request.getContextPath() + Constants.SERVLET_CENTER%>', 
             
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

								//OK Dialog
								$("#btnOkInfo").bind("click", function() {
									$("#dlgInfo").dialog("close"); 
								});
								$("#dlgInfo .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="center.name"/> <fmt:message key="msg.created"/>");							
								$("#dlgInfo").dialog('open');

								             
	                     		$("#newForm").each (function(){
	                     			  this.reset();
	                     		});         
		                	
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
            		            		
            		/*success: function () {  
							$("#dlgWait").dialog('close');

							//OK Dialog
							$("#btnOkInfo").bind("click", function() {
								$("#dlgInfo").dialog("close"); 
							});
							$("#dlgInfo .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="center.name"/> <fmt:message key="msg.created"/>");							
							$("#dlgInfo").dialog('open');

							             
                     		$("#newForm").each (function(){
                     			  this.reset();
                     		});               				
                	},
        			error: function (XMLHttpRequest, textStatus, error) {
                			$("#dlgWait").dialog('close');

							//ERROR Dialog
							$("#btnOkError").bind("click", function() {
								$("#dlgError").dialog("close"); 
							});
							$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.generic"/>");							
							$("#dlgError").dialog('open');
                			            				
        			}   */              	                	      
        		});
         
        		//cancel the submit button default behaviours
        		return false;
				
			}	
		});                  
	});
</script>