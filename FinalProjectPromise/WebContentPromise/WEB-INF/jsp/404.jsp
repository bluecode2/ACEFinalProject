<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Oops.. Page not found.</title>
</head>
<body>
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 text-center" style="padding-right: 40px;">
					<h1 style="font-size: 404px">404</h1>
				</div>
				<div class="col-md-4 text-center">
					<p style="font-size: 111px">Page not found.</p>
				</div>
			</div>
			<div class="row text-center">
					<a href="home.do">
					<button class="btn btn-lg btn-danger">Home</button>
					</a>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
</body>
</html>