<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Entry</title>

<script type="text/javascript">
	function onBtnBackClick() {
		location.href = "department.do";
	}

	function onBtnSaveClick() {
		document.forms[0].task.value = "save";
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<html:form action="/department" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="departmentForm" property="task" />
		<html:hidden name="departmentForm" property="isAdd" />
		<html:hidden name="departmentForm" property="selectedDept.deptId" />

		<div class="container">

			<div class="divContent form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" class="tdLabel" />
						<col />
					</colgroup>
					<tr>
						<td class="tdLabel" align="right"><label>Department
								Code</label></td>
						<td><html:text styleClass="form-control"
								styleId="txtDeptCode" name="departmentForm"
								property="selectedDept.deptCode" style="width:150px"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Department
								Name</label></td>
						<td><html:text styleClass="form-control"
								styleId="txtDeptName" name="departmentForm"
								property="selectedDept.deptName"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Department
								Head</label></td>
						<td><html:hidden styleId="hdnDeptHeadId"
								name="departmentForm" property="selectedDept.deptHeadId" /> <html:text
								styleClass="form-control" styleId="txtDeptHead"
								name="departmentForm" property="selectedDept.deptHeadId"></html:text>
						</td>
					</tr>
				</table>
			</div>

		</div>

		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Employee</h4>
					</div>
					<div class="modal-body">
						<p>One fine body&hellip;</p>
						input in popup: <input type="text" class="form-control getValue"
							id="myPopupInput" />
						<table width="100%">
							<tr>
								<td>Employee Code</td>
								<td>Employee Name</td>
								<td>Email</td>
							</tr>
							<logic:notEmpty name="lstEmployees">
								<logic:iterate id="emp" name="lstEmployees">
									<tr>
										<td><bean:write name="emp" property="employeeCode"/></td>
										<td><bean:write name="emp" property="employeeName"/></td>
										<td><bean:write name="emp" property="email"/></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>

						</table>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>