<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>
<%@ page import="com.tid.Errors"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="searchContainer" class="container-form search">	
	<form id="searchForm" class="form" method="post">
	    <fieldset>
	    	<div>
	    		<legend class="mt-legend-text"><fmt:message key="find.service"/></legend>
	    	</div>
		    <div class="inline">
		    	<label class="mt-label-text" for="name"><fmt:message key="service.name"/> </label>
		    	<input class="edit mt-input-text" type="text" id="<%=Constants.PARAM_QUERY%>" name="<%=Constants.PARAM_QUERY%>" tabindex="1" /> 			    	 			    
		    </div>
					    			
		</fieldset>	    	        
	</form>
</div>

<div id="resultsContainer" class="container-form newsearch">	
	<form id="resultsForm" class="form">
	    <fieldset>	    		    				    		    			    
		    <div class="inline legend mt-legend-text" id="queryResults"></div>
	    	<div class="separator mini" style="margin-left:40px;"></div>
	    	
			<div id="resultsList" class="container_600 scroll scroll-pane-search" style="height:165px;margin-left:40px;">
				<div id="results"></div>	
			</div>
											    		
		</fieldset>	    	        
	</form>	
</div>	

<div id="foot" class="rounded-corners-down">
	<div id="rightButton" class="mt-button-foot-right search">
		<a id="searchButton" href="#" class="acceptDarkButton" style="margin:0;" tabindex="2"><span><fmt:message key="label.search"/></span></a>
	</div>
	<div id="rightButton" class="mt-button-foot-right newsearch">
		<a id="btnNewSearch" href="#" class="acceptDarkButton" style="margin:0;" tabindex="2"><span><fmt:message key="label.newsearch"/></span></a>
	</div>
	

</div>

<!-- TEMPLATES -->  
<script id="tmplResult" type="text/x-jquery-tmpl">	
<div 
{{if count % 2}}
	class="greyRow mt-input-text"
{{else}}
	class="whiteRow mt-input-text"
{{/if}}>	
	<div style="padding-bottom:5px;padding-top:5px;">
		<div class="inline" style="vertical-align:middle;padding:0 0 0 15px;"> 
			<a style="float:left;">${name}</a>
			<a id="button_delete" class="btnDeleteService" style="float:right;margin-right:5px;display:inline;" value="${id}#${centerid}"></a>
			<a id="button_view"   class="btnViewService"   style="float:right;margin-right:5px;display:inline;" value="${id}#${centerid}"></a>			
		</div>
	</div>
</div>
</script>

<!-- Showscroll -->
<script type="text/javascript">
	function showScrollSearch() {
		var settings = {			
			verticalDragMinHeight: 50,
			verticalDragMaxHeight: 50,
			showArrows: true		
			
			
		};
		var pane = $('.scroll-pane-search');
		pane.jScrollPane(settings);
		var api = pane.data('jsp');
		api.reinitialise();
	}
</script>

<!-- FORM SUBMIT -->
<script type="text/javascript">
	$("#searchButton").click(function() {
		$("#searchForm").submit();
	});
</script>

<!--DIALOG EVENTS -->
<script type="text/javascript">
$(document).ready(function() {

	showScrollSearch();
	
	$('#btnCancelDelete').bind('click', function() {
		$("#dlgDelete").dialog("close"); 
	});

	//Capture dialog's ok button
	$('#btnOkDelete').bind('click', function() {
		$("#dlgDelete").dialog("close"); 
		$("#dlgWait").dialog('open');

		//Capture information from servlet				
  		$.ajax({
      		//this process the request
      		url: '<%=request.getScheme() + "://" + 
						request.getServerName() + ":" + request.getServerPort() + 
						request.getContextPath() + Constants.SERVLET_SERVICE%>', 
       
      		//GET method is used            		     		
      		type: 'POST',

      		//pass the data        
      		data: "<%=Fields.SERVICE_ID%>=" + window.deleteServiceId + 
		  	  	  "&<%=Fields.CENTER_ID%>=" + window.deleteCenterId + 
      		      "&<%=Constants.PARAM_OP%>=<%=Constants.VALUE_DELETE%>",     

      		//Do not cache the page
      		cache: false,
      		
      		
      		//success
	        complete : function(xhr, status) {
	            switch(status)
	            {
	            	case "success":
	                   $("#dlgWait").dialog('close');
						$("#searchForm").submit();  							
													
						break;
	                case "error":
	                    	
	                    //Get error information
	                    $("#dlgWait").dialog('close');   
	                     
						
	        			//ERROR Dialog
						$("#btnOkError").bind("click", function() {
							$("#dlgError").dialog("close"); 
						});
							
						if (xhr.status==<%=Errors.EXIST_USERS_ERROR%>) {
							//Exist services
							$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.exist_users"/>");
						}
						else {
							//Generic Error
							$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="msg.error"/>");
						}
						
													
						$("#dlgError").dialog('open');
	                    
	            }
	        }    
                     	                	      
  		});
   		
    	return false; 
	});
	
});
</script>
	


<!-- VALIDATIONS -->
<script type="text/javascript">
	$(document).ready(function() {
		//Init config	
		$(".newsearch").hide();
		 							
		// validate signup form on keyup and submit
		var v = $("#searchForm").validate({
			// specifying a submitHandler prevents the default submit			
			submitHandler: function() {			

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
            		data: $('#searchForm').serialize(),     
             
            		//Do not cache the page
            		cache: false,
             
            		//success
            		success: function(json) { 								
								//Init results' form
								$('#queryResults').text("<fmt:message key="msg.query_results"/>: " + $('#<%=Constants.PARAM_QUERY%>').val());			            		 								
								$("#results").empty();
								
								if (json.data.length>0) {
									$("#tmplResult").tmpl(json.data).prependTo("#results");
								}
								else {
									//No results
									 $("#results").append("<div class='mt-notification-body'><fmt:message key="msg.no_results"/></div>");									
								}																																	
								
								//Hide search form
								$('.search').hide();
								$('.newsearch').show();				
								//changeState('search');
								$("#dlgWait").dialog('close');											
					},
        			error: function (XMLHttpRequest, textStatus, error) {            				
						 $("#dlgWait").dialog('close');		
						//ERROR Dialog
						$("#btnOkError").bind("click", function() {
							$("#dlgError").dialog("close"); 
						});
						$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.generic"/>");							
						$("#dlgError").dialog('open');
        			},
        			complete: function() {
            			//reload scroll
            			showScrollSearch();
        			}                 	                	      
        		});

//BUTTONS  		
        		jQuery("#resetSearch").click(function() {
        			v.resetForm();
        			$("#results").empty();	
        			$(".newsearch").hide();
        								
        		});
		
        		
        		$("#btnNewSearch").click(function() {
        			v.resetForm();
        			$("#results").empty();	
        			$(".newsearch").hide();
        			$(".search").show();
        			return false;						
        		});

        		$(".btnViewService").live( "click", function () {
        			 var id = $(this).attr('value').split('#');

        			 if (id!=null && id.length>1) {
        			 
					   serviceId= id[0];
					   centerId= id[1];

					   if (serviceId!=null && centerId!=null) {	 
        			 
        			 
        				 $("#dlgWait").dialog('open');
        				//Capture information from servlet				
 		        		$.ajax({
 		            		//this process the request
 		            		url: '<%=request.getScheme() + "://" + 
 									request.getServerName() + ":" + request.getServerPort() + 
 									request.getContextPath() + Constants.SERVLET_SERVICE%>', 
 		             
 		            		//GET method is used            		     		
 		            		dateType: 'json',
 		 
 		            		//pass the data         
 		            		data: "<%=Fields.SERVICE_ID%>=" + serviceId + 
	            			  	  "&<%=Fields.CENTER_ID%>=" + centerId,     
 		             
 		            		//Do not cache the page
 		            		cache: false,
 		             
 		            		//success
 		            		success: function(json) {  								
 										$("#view").empty();										
 										$("#tmplView").tmpl(json.data).prependTo("#view");

										//Search service's users															 																								
 										$.ajax({
				 		            		//this process the request
				 		            		url: '<%=request.getScheme() + "://" + 
				 									request.getServerName() + ":" + request.getServerPort() + 
				 									request.getContextPath() + Constants.SERVLET_USER%>', 
				 		             
				 		            		//GET method is used            		     		
				 		            		dateType: 'json',
				 		 
				 		            		//pass the data         
				 		            		data: "<%=Fields.SERVICE_ID%>=" + serviceId + 
				 		            			  "&<%=Fields.CENTER_ID%>=" + centerId,     
				 		             
				 		            		//Do not cache the page
				 		            		cache: false,
				 		             
				 		            		//success
				 		            		success: function(jsonClient) {  								
				 										$("#users").empty();										
				 										$("#tmplUser").tmpl(jsonClient.data).prependTo("#users");																													
				 										//Hide menus
				 										$("#dlgWait").dialog('close');
				 										changeState('view');
				 										 				 														
				 							},
				 		        			error: function (XMLHttpRequest, textStatus, error) {            				
				 								$("#dlgWait").dialog('close'); 	
				 								//ERROR Dialog
				 								$("#btnOkError").bind("click", function() {
				 									$("#dlgError").dialog("close"); 
				 								});
				 								$("#dlgError .<%=Constants.DIALOG_MSG%>").html("<fmt:message key="error.generic"/>");							
				 								$("#dlgError").dialog('open');
				 		        			},
				 		        			complete: function() {
				 		            			//reload scroll
				 		            			showScrollView(); 
				 		        			}                  	                	      
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
 		        			}                 	                	      
 		        		});
					  }
 	        		 }
        			 else {

            		 }

				});

//DELETE DIALOG        		
				
        		$(".btnDeleteService").live( "click", function () {
        			// Holds the product ID of the clicked element
        			 var id = $(this).attr('value').split('#');

        			 if (id!=null && id.length>1) {

        			 	window.deleteServiceId = id[0];
        			  	window.deleteCenterId = id[1];
            			 
					   	if (window.deleteServiceId!=null && window.deleteCenterId!=null) {						
							//Open dialog
   							$( "#dlgDelete" ).dialog( "open" );							       						
  					   	}
  					   	else {								  		  		 	   	
  							changeState('new');
  					   	}
        			 }
        			 else {
        				changeState('new');
            		 }

        			 return false;	
        			          			  
        		});
                
        		//cancel the submit button default behaviours
        		return false;				
			}
		});		
		                              
	});
</script>







