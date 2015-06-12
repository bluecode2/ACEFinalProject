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
					<tr>
						<td>Employee Name</td>
						<td style="padding-left:15px;" align="left">
							<input type="text" name="employeeName" class="form-control" />
						</td>
					</tr>
					<tr>
						<td>Gender</td>
						<td style="padding-left:15px;" align="left">
							<input type="radio" name="gender" value="L"> L </input>
							&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="gender" value="P"> P </input>
						</td>
					</tr>
					<tr>
						<td>Email</td>
						<td style="padding-left:15px;" align="left">
							<input type="text" name="email" class="form-control" />
						</td>
					</tr>
					<tr>
						<td>Address</td>
						<td style="padding-left:15px;" align="left">
							<textarea style="width: 300px; height: 50px;" class="form-control"></textarea>
							<%-- <html:textarea property=""></html:textarea> --%>
						</td>
					</tr>
					<tr>
						<td>Phone Number</td>
						<td style="padding-left:15px;" align="left">
							<input type="text" name="phoneNumber" class="form-control" />
						</td>
					</tr>
				</table>
			</div>
			
			<div class="divContent">
				<table class="table table-bordered" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td>Employee ID</td>
							<td>Employee Name</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="employeeForm" property="listOfEmployee">
							<logic:iterate id="reg" name="employeeForm" property="listOfEmployee">
								<tr>
									<td></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="employeeForm" property="listOfEmployee">
							<tr>
								<td colspan="4" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>