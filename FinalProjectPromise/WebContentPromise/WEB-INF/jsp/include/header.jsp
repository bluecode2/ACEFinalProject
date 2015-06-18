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
<link rel="icon" href="icon/favicon.png" type="image/x-icon">
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
	});
</script>

</head>
<body>

	<nav class="navbar navbar-material-blue navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">PROMISE</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<logic:iterate id="menuLvl1" name="arrMenuLvl1">
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle"><bean:write name="menuLvl1"
									property="menuCaption" /> <b class="caret"></b></a>
							<ul class="dropdown-menu" id="menu1">
								<logic:iterate id="menuLvl2" name="arrMenuLvl2">
									<logic:equal name="menuLvl2" property="parentId"
										value="${menuLvl1.menuId}">
										<li><a
											href="<bean:write name="menuLvl2" property="menuUrl" />"><bean:write
													name="menuLvl2" property="menuCaption" /></a>
											<ul class="dropdown-menu sub-menu">
												<logic:iterate id="menuLvl3" name="arrMenuLvl3">
													<logic:equal name="menuLvl3" property="parentId"
														value="${menuLvl2.menuId}">
														<li><a
															href="<bean:write name="menuLvl2" property="menuUrl" />"><bean:write
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
					<li><a href="#" title="Home"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
					<li><a href="#" title="Home"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"><span
							class="glyphicon glyphicon-user" aria-hidden="true"
							style="margin-right: 10px"></span>Username <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Change Password</a></li>
							<li><a href="#">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>


</body>