<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/icon/favicon.png" type="image/png" />
<link rel="shortcut icon" href="/favicon.ico" />
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

</head>
<body>
	<nav class="navbar navbar-material-blue navbar-static-top shadow-z-1">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home.do"><span class="logo"><img
						src="icon/logo2.png"></span><b>PROMISE</b></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<logic:iterate id="menuLvl1" name="arrMenuLvl1">
						<li class="dropdown"><a href="<bean:write name="menuLvl1" property="menuUrl" />" data-toggle="dropdown"
							class="dropdown-toggle"><bean:write name="menuLvl1"
									property="menuCaption" /> <logic:notEqual name="menuLvl1" property="menuCode" value="${reportMenuCode}"><b class="caret"></b></logic:notEqual> </a>
							<ul class="dropdown-menu" id="menu1">
								<logic:iterate id="menuLvl2" name="arrMenuLvl2">
									<logic:equal name="menuLvl2" property="parentId"
										value="${menuLvl1.menuId}">
										<li><a
											href="<bean:write name="menuLvl2" property="menuUrl" />"><bean:write
													name="menuLvl2" property="menuCaption" /></a>
											<ul class="dropdown-menu sub-menu"><logic:iterate 
												id="menuLvl3" name="arrMenuLvl3"><logic:equal name="menuLvl3" property="parentId"
														value="${menuLvl2.menuId}"><li><a
															href="<bean:write name="menuLvl3" property="menuUrl" />"><bean:write
																	name="menuLvl3" property="menuCaption" /></a></li>
													</logic:equal>
												</logic:iterate>
											</ul></li>
									</logic:equal>
								</logic:iterate>
							</ul></li>
					</logic:iterate>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="home.do" title="Home"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
					<logic:notEmpty name="unreadNotification">
					<li class="dropdown"><a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-expanded="false" title="Notification"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span> 
							<span class="badge" id="badge"><bean:write name="unreadCount" /></span></a>
						<ul class="dropdown-menu dropdown-menu-notif">
							<logic:iterate id="notif" name="unreadNotification">
								<li><html:hidden styleClass="hdnNotifId" name="notif" property="notificationId" /><a href="#" class="notificationNode"><bean:write name="notif" property="notificationDesc" /></a></li>
							</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="unreadNotification">
					<li class="dropdown"><a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-expanded="false" title="Notification"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;
							</a>
						<ul class="dropdown-menu dropdown-menu-notif" style="min-width: 200px;">
							<li><span>No Unread Notification</span></li>
					</logic:empty>
							<li role="separator" class="divider"></li>
							<li style="text-align: center"><a href="notification.do" class="text-info">See All</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"> <span
							class="glyphicon glyphicon-user" aria-hidden="true"
							style="margin-right: 10px"></span> <logic:notEmpty
								name="userName">
								<bean:write name="userName" /> &nbsp;
								<span class="caret">
							</logic:notEmpty> </span>
					</a>
						<ul class="dropdown-menu dropdown-menu-right" role="menu">
							<li><span><bean:write name="userName" /> (<bean:write name="userRoleName" />)</span></li>
							<li role="separator" class="divider"></li>
							<logic:equal name="isActiveDirectory" value="0">
								<li><a href="#" onclick="onChangePasswordClick();">Change Password</a></li>
							</logic:equal>
							<li><a href="logout.do">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<div class="full" id="loading" style="visibility: hidden">
		<div class="full-inner">
			<h2>Loading...</h2>
			<div class="loader">Loading...</div>
		</div>
	</div>
</body>