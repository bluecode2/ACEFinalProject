<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<html:form action="/employee" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: left;">
				<table>
				
					<tr align="left">
						<td>Employee Name</td>
						<td style="padding-left:15px;">
							<input type="text" name="employeeName" class="form-control" />
						</td>
					</tr>
					<tr align="left">
						<td>Gender</td>
						<td style="padding-left:15px;">
							<input type="radio" name="gender" value="L"> L </input>
							&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="gender" value="P"> P </input>
						</td>
					</tr>
					<tr align="left">
						<td>Email</td>
						<td style="padding-left:15px;">
							<input type="text" name="email" class="form-control" />
						</td>
					</tr>
					<tr align="left">
						<td>Address</td>
						<td style="padding-left:15px;">
							<textarea style="width: 300px; height: 50px;" class="form-control"></textarea>
							<%-- <html:textarea property=""></html:textarea> --%>
						</td>
					</tr>
					<tr align="left">
						<td>Phone Number</td>
						<td style="padding-left:15px;">
							<input type="text" name="phoneNumber" class="form-control" />
						</td>
					</tr>
					<tr align="left">
						<td>Department ID</td>
						<td style="padding-left:15px;">
							<input type="text" name="deptID" class="form-control" />
						</td>
					</tr>
					<tr align="left">
						<td>Supervisor ID</td>
						<td style="padding-left:15px;">
							<input type="text" name="spvID" class="form-control" />
						</td>
					</tr>
					<tr align="left">
						<td>Project ID</td>
						<td style="padding-left:15px;">
							<input type="text" name="projectID" class="form-control" />
						</td>
					</tr>
				</table>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>