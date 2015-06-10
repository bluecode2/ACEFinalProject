<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="css/menu.css" />
<script type="text/javascript"
	src='js/jquery-1.11.3.min.js'></script>
</head>
<body>
	<ul id="nav">
		<li class="site-name"><a href="#"> </a></li>
		<li class="yahoo"><a href="#">Yahoo</a>
			<ul>
				<li><a href="#">Yahoo Games »</a>
					<ul>
						<li><a href="#">Board Games</a></li>
						<li><a href="#">Card Games</a></li>
						<li><a href="#">Puzzle Games</a></li>
						<li><a href="#">Skill Games »</a>
							<ul>
								<li><a href="#">Yahoo Pool</a></li>
								<li><a href="#">Chess</a></li>
							</ul></li>
					</ul></li>
				<li><a href="#">Yahoo Search</a></li>
				<li><a href="#">Yahoo Answsers</a></li>
			</ul></li>
		<li class="facebook"><a href="#">Facebook</a>
			<ul>
				<li><a href="#">Facebook Pages</a></li>
				<li><a href="#">Facebook Groups</a></li>
			</ul></li>
		<li class="google"><a href="#">Google</a>
			<ul>
				<li><a href="#">Google mail</a></li>
				<li><a href="#">Google Plus</a></li>
				<li><a href="#">Google Search »</a>
					<ul>
						<li><a href="#">Search Images</a></li>
						<li><a href="#">Search Web</a></li>
					</ul></li>
			</ul></li>
		<li class="twitter"><a href="#">Twitter</a>
			<ul>
				<li><a href="#">New Tweets</a></li>
				<li><a href="#">Compose a Tweet</a></li>
			</ul></li>
	</ul>

</body>