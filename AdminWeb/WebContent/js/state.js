function changeState(newState) {	
	var divs = document.getElementsByTagName("div");
	
	//hide all "layer_..." divs
	for (var i = 0; i < divs.length; i++) {
		var div = divs[i];
		if (div.getAttribute('id') != null &&
			div.getAttribute('id').indexOf('layer_')==0) {
							
				hide(div.getAttribute('id'));                     			
		}
	}
		
	if (newState == 'new') {
		//show layer
		show("layer_new");
		
	}
	else if (newState == 'search') {
		show("layer_search");	
		$(".menu").show();
		$(".submenu").show();
		editMode();
		
	}
	else if (newState == 'view') {
		show("layer_view");		
		$(".menu").hide();
		$(".submenu").hide();
		viewMode();
	}
	else if (newState == 'edit') {
		show("layer_view");		
		$(".menu").show();
		$(".submenu").show();
		editMode();
	}
	
	//Special elementes allways hidden
	$(".hidden").hide();
	
}

function viewMode() {
	$(".view").show();
	$("label.error").hide();
	$(".edit").hide();	
}

function editMode() {
	$(".view").hide();
	$("label.error").show();
	$(".edit").show();	
}


function show(layer_ref) {
	layer = document.getElementById(layer_ref);
	layer.style.display = 'inline';
} 

function hide(layer_ref) {
	layer = document.getElementById(layer_ref);
	layer.style.display = 'none';
} 

