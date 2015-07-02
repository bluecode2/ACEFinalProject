<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="icon" href="icon/favicon.png" type="image/png" />
<link rel="shortcut icon" href="favicon.ico" />
<!-- CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/material.ripples.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<!-- JavaScript -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/material.ripples.min.js"></script>
<script>
	$(document).ready(function() {
		$.material.init();
		
		$('.notificationNode').on('click',function(){
			var notificationId = $(this).closest('li').find('.hdnNotifId').val();
			post('notification.do',{task: 'select', selectedId: notificationId});
		});
	});
	
	function post(path, params, method) {
	    method = method || "post"; // Set method to post by default if not specified.

	    // The rest of this code assumes you are not using a library.
	    // It can be made less wordy if you use one.
	    var form = document.createElement("form");
	    form.setAttribute("method", method);
	    form.setAttribute("action", path);

	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);

	            form.appendChild(hiddenField);
	         }
	    }

	    document.body.appendChild(form);
	    form.submit();
	}
	
	function onChangePasswordClick(){
		post('users.do',{task: 'changePassword'});
	}
	
	function showLoading() {
		var elem = document.getElementById("loading");
		elem.style.visibility = 'visible';
	}
	
	function hideLoading() {
		var elem = document.getElementById("loading");
		elem.style.visibility = 'hidden';
	}
	
</script>