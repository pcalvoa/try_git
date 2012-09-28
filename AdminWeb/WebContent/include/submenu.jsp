<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

					
<div id="submenu" class="container_800 submenu" > 
	<div id="submenuButtons" class="mt-modal-windows-header-small" style="padding:15px 5px 0 75px;">		
  		<a id="newbutton" class="button newbutton selected"><span><fmt:message key="label.new"/></span></a>
		<a id="searchbutton" class="button searchbutton"><span><fmt:message key="label.search"/></span /></a>
	</div> 			
</div>	

<div class="separator submenu"></div>


<!-- MENU BUTTONS -->
<script type="text/javascript">			
	//Buttons actions
	$('#newbutton').click(function(){
		//function to perform on new button click
		changeState('new');

		$("#searchbutton").removeClass("selected")
		$(this).addClass("selected");
		
		//Update scroll
		showScroll();		
		
	});
</script>

<script type="text/javascript">			
	//Buttons actions
	$('#searchbutton').click(function(){
		//function to perform on new button click
		changeState('search');

		$("#newbutton").removeClass("selected")
		$(this).addClass("selected");		
		
	}); 	
</script>