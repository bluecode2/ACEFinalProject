<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/material.css" rel="stylesheet">
<link href="css/ripples.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<link href="css/jquery.dropdown.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/ripples.min.js"></script>
<script src="js/material.min.js"></script>
<script src="js/jquery.dropdown.js"></script>
    <script>
      $.material.init();
      $(document).ready(function() {
        $(".select").dropdown({"optionClass": "withripple"});
      });
      $().dropdown({autoinit: "select"});
      $("button").click(function() {
        $("body").append("<select class=select><option>hey</option><option>hey</option></select>");
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
				<li class="active"><a href="#">Home</a></li>
				<li class="dropdown"><a href="#" data-toggle="dropdown"
					class="dropdown-toggle">Dropdown <b class="caret"></b></a>
					<ul class="dropdown-menu" id="menu1">
						<li><a href="#">Another action</a></li>
						<li><a href="#">2-level Menu <span
								class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></a>
							<ul class="dropdown-menu sub-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" title="Home"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
				<li class="dropdown">
					
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"><span
								class="glyphicon glyphicon-user" aria-hidden="true" style="margin-right:10px"></span>Username
						<span class="caret"></span>
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