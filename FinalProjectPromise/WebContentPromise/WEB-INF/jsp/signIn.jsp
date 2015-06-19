<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login</title>
<link rel="icon" href="icon/favicon.png" type="image/x-icon">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/material.ripples.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<!-- JavaScript -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/material.ripples.min.js"></script>

<!-- HTML5 shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">
	$(document).ready(function() {
		$("#inputPassword").attr("placeholder", "Password");
		$("#inputPassword").attr("required", "required");
		$("#inputUsername").attr("placeholder", "Username");
		$("#inputUsername").attr("required", "required");
		$("#inputUsername").attr("autofocus");
	});

	function goLogin(task) {
		document.forms[0].task.value = task;
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<table align="center">
		<tr>
			<td colspan="2" height="170px" class="bg-primary"
				style="border-radius: 10px" valign="middle">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-3 text-center" style="margin-top: 0">
						<img alt="Logo" src="icon/logo-outline.png" width="90px"
							height="120px" />
					</div>
					<div class="col-md-8">
						<h1>PROMISE</h1>
						<p class="lead">Project Management Information System</p>
					</div>
					<div class="col-md-1"></div>
				</div>
			</td>
		</tr>

		<tr>
			<td><img src="icon/bgTable.jpg" /></td>
			<td width="250px" align="center">
			<html:form action="/login"
					method="post">
					<div class="form-group has-info">
					<html:hidden property="task" name="loginForm" />

					<img src="icon/key.png" width="80px" height="80px"
						class="block-center" />
					<label for="inputEmail" class="sr-only">Username</label>

					<html:text property="username" name="loginForm"
						styleId="inputUsername" styleClass="form-control"></html:text>


					<label for="inputPassword" class="sr-only">Password</label>

					<html:password property="password" name="loginForm"
						styleId="inputPassword" styleClass="form-control">

					</html:password>
					<logic:notEmpty name="errorMessage">
						<br/>
							<span class="text-danger">
							<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
								<bean:write name="errorMessage" />
							</span>
					</logic:notEmpty>
					<html:submit styleClass="btn btn-lg btn-info btn-block" onclick="goLogin('validasi')">LOGIN</html:submit>
				</div></html:form></td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="bg-primary"
				style="border-radius:8px;padding:8px;">Copyright &copy; 2015 | ACE 21 -
				Kelompok 1</td>
		</tr>
	</table>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- 	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->

</body>
</html>