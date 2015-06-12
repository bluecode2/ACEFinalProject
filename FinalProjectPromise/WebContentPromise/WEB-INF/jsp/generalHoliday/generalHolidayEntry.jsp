<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>General Holiday</title>
</head>
<body>
	<html:form action="/generalHoliday" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent">
				<table>
					<!-- <tr>
						<td>General Holiday ID</td>
						<td><input type="text" id="txtGoToPage" class="form-control"/></td>
					</tr> -->
					<tr>
						<td>General Holiday Name</td>
						<td><input type="text" id="txtGoToPage" class="form-control" /></td>
					</tr>
					<tr>
						<td>General Holiday Date</td>
						<td><input type="text" id="txtGoToPage" class="form-control" /></td>
					</tr>
					<tr>
						<td>Is Generated</td>
						<td>
							<input type="radio" name="gender" value="Yes"> Yes</input> &nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="gender" value="No"> No</input></td>
					</tr>
				</table>
				<%-- <jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include> --%>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>