<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login</title>

<!-- CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">

<!-- HTML5 shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>
<body>
	<table align="center">
		<tr>
			<td colspan="2" height="170px" class="bg-primary"
				style="border-radius: 6px" valign="middle">
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-3 text-center" style="margin-top: 0">
					<img alt="Logo" src="icon/logo-outline.png" width="90px" height="120px" />
					</div>
					<div class="col-md-8">
					<h1>PROMISE</h1>
					<p class="lead">Project Management Information System</p></div>
					<div class="col-md-1"></div>
				</div>
			</td>
		</tr>

		<tr>
			<td><img src="icon/bgTable.jpg" /></td>
			<td width="250px" align="center">

				<form class="form-signin">
					<!-- <h3 class="form-signin-heading">Silahkan Login</h3> -->
					<img src="icon/key.png" width="80px" height="80px" class="block-center"/> 
					<label
						for="inputEmail" class="sr-only">Username</label> <input
						type="text" id="inputEmail" class="form-control"
						placeholder="Username" required autofocus> <label
						for="inputPassword" class="sr-only">Password</label> <input
						type="password" id="inputPassword" class="form-control"
						placeholder="Password" required>
					<div class="checkbox" align="left">
						<label> <input type="checkbox" value="remember-me" >
							Ingat Saya
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="bg-primary"
				style="border-radius: 4px">Copyright &copy; 2015 | ACE 21 - Kelompok 1</td>
		</tr>
	</table>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>