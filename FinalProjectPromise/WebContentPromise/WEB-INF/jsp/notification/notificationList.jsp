<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Notifications</title>
<script src="js/jquery.js"></script>
</head>
<body>
	<html:form action="/notification" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="notificationForm" property="task" />
		<html:hidden name="notificationForm" property="selectedId" />

		<div class="container divContent">
			<logic:iterate id="notif" name="notificationForm" property="lstBean">
				<div class="rowSearch" style="cursor: pointer;">
					<bean:write name="notif" property="notificationDesc" />
				</div>
			</logic:iterate>
		</div>

		<div class="container">
			<hr>
			<logic:notEqual name="currPage" value="1">
				<a href='#' class="text-info" style="padding-right: 20px">prev</a>
			</logic:notEqual>
			<logic:notEqual name="currPage" value="${pageCount}">
				<a href="#" class="text-info">next</a>
			</logic:notEqual>
		</div>
		<html:hidden name="notificationForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>