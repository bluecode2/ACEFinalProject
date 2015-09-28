<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KlikBCA Individu</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
</head>
<body style="background-color: #f5f5f5">
	<div class="container-fluid">

		<div class="row row-offcanvas row-offcanvas-left">

			<div class="col-sm-3 col-md-8 sidebar-offcanvas" id="sidebar"
				role="navigation">

				<table border="0" width="100%">
					<tr height="320">
						<td style="padding: 5px" width="100%"
							height="320">
						<img width="100%" src="pictures\\haloBCA.jpg">
						</td>
					</tr>
				</table>

			</div>
			<!--/span-->

			<div class="col-sm-9 col-md-4 main form-group" style="background-color: #f5f5f5">

				<div class="table-responsive page-header" style="border: 0px">
					<table border="0" cellpadding="15" cellspacing="15" width="100%">
					    <tr align="center">
					    	<td>
								<table border="0" width="100%">
					    			<tr>
					    				<td  width="40%"><img src="images\\logoKBI.png" width="90%"></td>
					    				<td width="60%"><font face="Verdana" size="3"
								color="#000090">I N D I V I D U A L</font></td>
					    			</tr>
					    		</table>
					    		
					    	</td>
					    </tr>
					    <tr height="5">
					    	<td>&nbsp;</td>
					    </tr>
						<tr align="center" style="padding-top: 5">
							<td><input type="text" name="userId"
								maxlength="12" size="24" placeholder="User ID" class="form-control" /></td>
						</tr>
						<tr>
					    	<td>&nbsp;</td>
					    </tr>
						<tr>
						<tr align="center">
							<td><input type="password" name="pass" size="24"
								maxlength="6" placeholder="PIN" class="form-control"></td>
						</tr>
						<tr>
					    	<td>&nbsp;</td>
					    </tr>
						<tr align="center">
							<td><input type="Submit" value="LOGIN"
								name="submit" class="btn btn-primary btn-block"/></td>
						</tr>
					</table>
					<br>
					<table border="0" cellpadding="5" cellspacing="0" width="100%"
						align="center">
						<tr>
							<td width="50%" align="right"><img width="80%"
								src="pictures\\TipsKeamanan.gif"></td>
							<td width="50%" align="Left"><img width="80%"
								src="pictures\\CyberTrust.gif"></td>
						</tr>
					</table>

				</div>

			</div>
			<!--/row-->
		</div>
		<div class="col-sm-3 col-md-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
		&nbsp;
		</div>
		<div class="col-sm-9 col-md-6" main>
			<table border="0" cellpadding="5" cellspacing="0" width="100%">
				<tr align="center">
				 	<td><img width="100%"
								src="pictures\\WaspadaVirus.gif"></td>
				</tr>			
			</table>
		</div>
	</div>
	<!--/.container-->


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>