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

		<html:hidden name="employeeForm" property="task" />
		<html:hidden name="employeeForm" property="isAdd" />
		<html:hidden name="employeeForm" property="selectedEmp.employeeId" />

		<div class="container">
			<div class="divSearch form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" class="tdLabel" />
						<col />
					</colgroup>
					<tr>
						<td class="tdLabel" align="right"><label>Employee
								Code</label></td>
						<td><html:text styleClass="form-control"
							 name="employeeForm"
								property="selectedEmp.employeeCode" style="width:150px"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Employee
								Name</label></td>
						<td><html:text styleClass="form-control"
								name="employeeForm"
								property="selectedEmp.employeeName"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Gender</label></td>
						<td><input type="radio"
							name="gender" value="L"> L </input> &nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="gender"
							value="P"> P </input></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Email </label></td>
						<td><html:text styleClass="form-control"
								name="employeeForm"
								property="selectedEmp.email"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Address</label></td>
						<td>
							<html:textarea styleClass="form-control" name="employeeForm" property="selectedEmp.address"></html:textarea></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Phone Number</label></td>
						<td><html:text styleClass="form-control"
								name="employeeForm"
								property="selectedEmp.phoneNumber"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Department</label></td>
						<td><input type="text"
							name="deptID" class="form-control" /></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Rank</label></td>
						<td><input type="text"
							name="deptID" class="form-control" /></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Supervisor</label></td>
						<td><input type="text"
							name="spvID" class="form-control" /></td>
					</tr>
				</table>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>