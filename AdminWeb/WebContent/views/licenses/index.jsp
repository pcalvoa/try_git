<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Errors"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>
	<title><fmt:message key="page.licenses_title"/></title>		
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".submenu").hide();
		});
	</script>

</head>
<body>					

	<div id="licensesContainer" class="container_750" style="padding:15px 0 0 0;height:325px;">
		<form id="licensesForm" class="form" method="post">
			<fieldset>
				<div>
		    		<legend class="mt-legend-text"><fmt:message key="license.title"/></legend>
		    	</div>
		    	<div id="view"></div>			   			   
		    	<input type="hidden" name="<%=Constants.PARAM_OP%>" value="<%=Constants.VALUE_UPDATE%>">
			</fieldset>
		</form>
	</div>

	<div id="foot" class="rounded-corners-down">
		<div id="rightButton" class="mt-button-foot-right">
			<a id="btnUpdate" href="#" class="acceptDarkButton" style="margin:0;" tabindex="2"><span><fmt:message key="label.update"/></span></a>
		</div>
	
	</div>
	

<!-- TEMPLATES -->
<script id="tmplLicenses" type="text/x-jquery-tmpl">
 <div>
	<label class="mt-label-text" for="name"><fmt:message key="label.license"/>* </label>
	<input class="edit mt-input-text" type="text" id="<%=Constants.PARAM_LICENSES%>" 
		name="<%=Constants.PARAM_LICENSES%>" tabindex="1" value="${total}"/>
</div>
</script>	

<!-- LICENSES LOAD -->
<script type="text/javascript">
	$(document).ready(function() {		
			
		//start the ajax
		$.ajax({
    		//this process the request
    		url: '<%=request.getScheme() + "://" + 
					request.getServerName() + ":" + request.getServerPort() + 
					request.getContextPath() + Constants.SERVLET_LICENSES%>', 
     
    		//GET method is used            		     		
    		dateType: 'json',

    		//pass the data         
    		data: '',     
     
    		//Do not cache the page
    		cache: false,
     
    		//success
    		success: function(json) { 																				
						//Init results' form			            		 																				
						$("#tmplLicenses").tmpl(json.data).prependTo("#view");						
						
			},
			error: function (XMLHttpRequest, textStatus, error) {            				
				
				//ERROR Dialog
				$("#btnOkError").bind("click", function() {
					$("#dlgError").dialog("close"); 
				});
				$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.connection"/>");							
				$("#dlgError").dialog('open');
		
			}                 	                	      
		});
	});
</script>	

<!-- SUBMIT FORM -->
<script type="text/javascript">
	$("#btnUpdate").click(function() {
		$("#licensesForm").submit();
	});
</script>	
	
	
<!-- VALIDATION -->
<script type="text/javascript">
	$(document).ready(function() {
						
		// validate signup form on keyup and submit		
		$("#licensesForm").validate({
			rules: {
				<%=Constants.PARAM_LICENSES%>: {
					required: true,
				    number: true									
				}
			},
			messages: {				
				<%=Constants.PARAM_LICENSES%>: {
					required: 	"<fmt:message key="msg.number"/>",
					number: 	"<fmt:message key="msg.number"/>"
					
				}
			},			

			// specifying a submitHandler prevents the default submit
			submitHandler: function() {

				$("#dlgWait").dialog('open');

				//start the ajax
        		$.ajax({
            		//this process the request
            		url: '<%=request.getScheme() + "://" + 
							request.getServerName() + ":" + request.getServerPort() + 
							request.getContextPath() + Constants.SERVLET_LICENSES%>', 
             
            		//GET method is used
            		type: "POST",
 
            		//pass the data         
            		data: $('#licensesForm').serialize(),     
             
            		//Do not cache the page
            		cache: false,
            		
            		complete : function(xhr, status) {
		                switch(status)
		                {
		                	case "success":
		                		$("#dlgWait").dialog('close');		                	
		                		break;
		                    
		                	case "error":
		                		//Get error information 
								$("#dlgWait").dialog('close');
	                			//ERROR Dialog
								$("#btnOkError").bind("click", function() {
									$("#dlgError").dialog("close"); 
								});
									
								if (xhr.status==<%=Errors.MIN_LICENSES_ERROR%>) {
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
            		
                	
        		});
        			         
        		//cancel the submit button default behaviours
        		return false;
    		    							
			}
		});
		

	});
		
</script>	
	
</body>



