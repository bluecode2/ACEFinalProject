<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
				<a class="navbar-brand" href="#"><img src="images/logoKBI.png" width="55px" /></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Transaksi <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Transfer Dana</a></li>
						<li><a href="#">Pembelian</a></li>
						<li><a href="#">Pembayaran</a></li>
						<li><a href="#">E-Commerce</a></li>
						<li><a href="#">Status Transaksi</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Informasi <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Informasi Rekening</a></li>
						<li><a href="#">Informasi Kartu Kredit</a></li>
						<li><a href="#">Informasi Kredit Konsumen</a></li>
						<li><a href="#">Informasi Produk Investasi</a></li>
						<li><a href="#">Status Lainnya</a></li>
					</ul></li>
				<li><a href="#">History</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-home"
						style="margin-right: 10px"></span>Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><span
						class="glyphicon glyphicon-user" style="margin-right: 10px"></span>
						Nama Nasabah <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Administrasi</a></li>
						<li><a href="#">E-mail</a></li>
						<li class="separator"></li>
						<li><a href="#">Logout</a></li>
					</ul></li>
			</ul>
		</div>

	</div>
	</nav>

	<div class="container-fluid">

		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar"
				role="navigation">

				<ul class="nav nav-sidebar">
					<li class="disabled"><a href="" class="regular-text">Menu Favorit</a></li>
					<li class="nav-header"><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
					<li><a href="">Nav item</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>

			</div>
			<!--/span-->

			<div class="col-sm-9 col-md-10 main">

				<!--toggle sidebar button-->
				<p class="visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">
						<i class="glyphicon glyphicon-chevron-left"></i>
					</button>
				</p>
			</div>
		</div>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>