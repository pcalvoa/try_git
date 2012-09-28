<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.tid.Constants"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<META http-equiv="Content-Type" content="text/html;charset=UTF-8">

<link rel="icon" href="<c:url value="/images/icons/client.ico"/>" type="image/x-icon" />  
<link rel="shortcut icon" href="<c:url value="/images/icons/client.ico"/>" type="image/x-icon" />  

<link rel="stylesheet" href="<c:url value="/css/buttons.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/commons.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/global.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/login.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/800_col.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/960_12_col.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/staff.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/sbscontents.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/sbsforms.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/sbstabs.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/sbsborder.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/sbsdialog.css"/>" type="text/css" media="screen" />

<link rel="stylesheet" href="<c:url value="/css/jquery.asmselect.css"/>" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/ui.spinner.css"/>" type="text/css" media="screen" />

<script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.tmpl.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.pajinate.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.validate.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.maskedinput.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/additional-methods.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.easing.1.3.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/state.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/ui.spinner.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.bgiframe-2.1.2.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.mousewheel.js" />"></script>
<!-- <script type="text/javascript" src="<c:url value="/js/jquery.form.js" />"></script>  -->

<script type="text/javascript" src="<c:url value="/js/jquery.asmselect.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/curvycorners.js" />"></script>

<!-- the jScrollPane script -->
<link rel="stylesheet" href="<c:url value="/css/jquery.jscrollpane.custom.css"/>" type="text/css" media="all" />
<script type="text/javascript" src="<c:url value="/js/jquery.jscrollpane.js" />"></script>	

<script type="text/javascript">
	function showScroll() {
		var settings = {			
			verticalDragMinHeight: 50,
			verticalDragMaxHeight: 50,
			showArrows: true		
			
			
		};
		var pane = $('.scroll-pane')
		pane.jScrollPane(settings);
		var api = pane.data('jsp');
		api.reinitialise();
	}
</script>
