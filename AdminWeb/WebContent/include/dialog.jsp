<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>
<%@ page import="com.tid.Fields"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<script type="text/javascript">
	$(document).ready(function() {
		
		$("#dlgDelete").dialog({
			bgiframe: true,
			resizable: false,			
			modal: true,
			autoOpen: false,
			width:290,
			height:320,
			overlay: {
                backgroundColor: '#000',
                opacity: 0.2
            },
            open: function() {
            	// scrollbar fix for IE
            	//$('body').css('overflow','hidden');
            	$(".ui-dialog-titlebar-close").hide();            	
            },
            close: function() {
            	// reset overflow
            	//$('body').css('overflow','auto');
           }             					
		});

		$("#dlgError").dialog({
			bgiframe: true,
			resizable: false,			
			modal: true,
			autoOpen: false,
			width:290,
			height:320,
			overlay: {
                backgroundColor: '#000',
                opacity: 0.2
            },
            open: function() {
            	// scrollbar fix for IE
            	//$('body').css('overflow','hidden');
            	$(".ui-dialog-titlebar-close").hide(); 
            },
            close: function() {
            	// reset overflow
            	//$('body').css('overflow','auto');
           }             					
		});

		$("#dlgInfo").dialog({
			bgiframe: true,
			resizable: false,			
			modal: true,
			autoOpen: false,
			width:290,
			height:320,
			overlay: {
                backgroundColor: '#000',
                opacity: 0.2
            },
            open: function() {
            	// scrollbar fix for IE
            	//$('body').css('overflow','hidden');
            	$(".ui-dialog-titlebar-close").hide(); 
            },
            close: function() {
            	// reset overflow
            	//$('body').css('overflow','auto');
           }             					
		});

		$("#dlgWait").dialog({
			bgiframe: true,
			closeOnEscape: false,			
			resizable: false,
			draggable: false,				
			modal: true,
			autoOpen: false,
			width:129,
			height:160,
			overlay: {
                backgroundColor: '#000',
                opacity: 0.2             
            },
            open: function() {
            	// scrollbar fix for IE
            	//$('body').css('overflow','hidden');
            	$(".ui-dialog-titlebar-close").hide(); 
            	
            },
            close: function() {
            	// reset overflow
            	//$('body').css('overflow','auto');
           }
            	                         					
		});
		
	});
</script>

<div id="dlgWait" class="dialog dlgWait" style="display:none;">
	<div class="mt-modal-body-small" style="width:90px;margin:0 auto;"><fmt:message key="msg.wait"/></div>
</div>

<div id="dlgDelete" class="dialog dlgDefault clearContainer" style="display:none;width:290px;">
	
	<div class="dlghead mt-modal-windows-header-small">
		<div class="dlgInner">		
			<span><fmt:message key="dlg.delete_title"/></span>
		</div>				
	</div>	
	
	<div class="dlgbody">
		<div class="dlgInner">		
			<div class="inline">
				<img src="<c:url value="/images/icons/warning_alert.png"/>">
			</div>
			<div class="inline mt-modal-body">		
				<fmt:message key="msg.delete_confirm"/>				
			</div>
		</div>
	</div>	

	<div class="dlgfoot" id="buttons">
		<div class="dlgInner" style="margin-left:15px;">				
			<a id="btnCancelDelete" href="#" class="cancelDarkButton inline" style="margin-right:22px;" tabindex="2"><span><fmt:message key="label.cancel"/></span></a>
			<a id="btnOkDelete" href="#" class="acceptDarkButton inline" tabindex="1"><span><fmt:message key="label.acept"/></span></a>					
		</div>
	</div>	
	
</div>

<div id="dlgError" class="dialog dlgDefault clearContainer" style="display:none;width:290px;">	
	
	<div class="dlghead mt-modal-windows-header-small">
		<div class="dlgInner">		
			<span><fmt:message key="dlg.error_title"/></span>
		</div>				
	</div>	
	
	<div class="dlgbody">
		<div class="dlgInner">		
			<div class="inline">
				<img src="<c:url value="/images/icons/error_alert.png"/>">
			</div>
			<div class="inline mt-modal-body">		
				<div class="<%=Constants.DIALOG_MSG%>"></div>		
			</div>
		</div>
	</div>	

	<div class="dlgfoot" id="buttons">
		<div class="dlgInner lef_150">				
			<a id="btnOkError" href="#" class="acceptDarkButton inline" tabindex="1"><span><fmt:message key="label.acept"/></span></a>					
		</div>
	</div>	
	
</div>

<div id="dlgInfo" class="dialog dlgDefault clearContainer" style="display:none;width:290px;">
	
	
	<div class="dlghead mt-modal-windows-header-small">
		<div class="dlgInner">		
			<span><fmt:message key="dlg.info_title"/></span>
		</div>				
	</div>	
	
	<div class="dlgbody">
		<div class="dlgInner">		
			<div class="inline">
				<img src="<c:url value="/images/icons/ok_alert.png"/>">
			</div>
			<div class="inline mt-modal-body">		
				<div class="<%=Constants.DIALOG_MSG%>"></div>		
			</div>
		</div>
	</div>	

	<div class="dlgfoot" id="buttons">
		<div class="dlgInner lef_150">				
			<a id="btnOkInfo" href="#" class="acceptDarkButton inline" tabindex="1"><span><fmt:message key="label.acept"/></span></a>					
		</div>
	</div>	
	
</div>
