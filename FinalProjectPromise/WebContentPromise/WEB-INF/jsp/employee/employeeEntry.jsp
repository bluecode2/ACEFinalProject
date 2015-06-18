<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Entry</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnBackClick(){
		location.href = "employee.do";
	}
	
	function onBtnSaveClick(){
		document.forms["employeeForm"].task.value = "save";
		document.forms["employeeForm"].submit();
	}
	
	$(document).ready(function() {
		$('.rowSearchDept').on('click', function() {
			var value = $(this).find('td').eq(0).html();
			var text = $(this).find('td').eq(2).html();
			$('#hdnDeptId').val(value);
			$('#txtDept').val(text);
		});
		
		$('.rowSearchSupervisor').on('click', function() {
			var value = $(this).find('td').eq(0).html();
			var text = $(this).find('td').eq(2).html();
			$('#hdnSupervisorId').val(value);
			$('#txtSupervisor').val(text);
		});
	});

</script>

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
			<div class="divContent form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" class="tdLabel" />
						<col />
					</colgroup>
					<tr>
						<td class="tdLabel" align="right"><label>Employee
								Code</label></td>
						<td><html:text styleClass="form-control" name="employeeForm"
								property="selectedEmp.employeeCode" style="width:150px"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Employee
								Name</label></td>
						<td><html:text styleClass="form-control" name="employeeForm"
								property="selectedEmp.employeeName"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Gender</label></td>
						<td>
							<table width="50%">
								<tr align="left">
									<td><html:radio name="employeeForm"
											property="selectedEmp.gender" value="M">Male</html:radio></td>
									<td><html:radio name="employeeForm"
											property="selectedEmp.gender" value="F">Female</html:radio></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Email </label></td>
						<td><html:text styleClass="form-control" name="employeeForm"
								property="selectedEmp.email"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Address</label></td>
						<td><html:textarea styleClass="form-control"
								name="employeeForm" property="selectedEmp.address"></html:textarea></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Phone Number</label></td>
						<td><html:text styleClass="form-control" name="employeeForm"
								property="selectedEmp.phoneNumber"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Department</label></td>
						<td><html:hidden styleId="hdnDeptId"
								name="employeeForm" property="selectedEmp.deptId" />
							<table width="100%">
								<tr>
									<td><html:text styleClass="form-control"
											styleId="txtDept" readonly="true" name="employeeForm"
											property="selectedEmp.deptName"></html:text></td>
									<td align="center"><a href="#" class="text-info"
										data-toggle="modal" data-target="#searchDept"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>

								</tr>
							</table></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Rank</label></td>
						<td><input type="text" name="deptID" class="form-control" /></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Supervisor</label></td>
						<td><html:hidden styleId="hdnSupervisorId"
								name="employeeForm" property="selectedEmp.supervisorId" />
							<table width="100%">
								<tr>
									<td><html:text styleClass="form-control"
											styleId="txtSupervisor" readonly="true" name="employeeForm"
											property="selectedEmp.supervisorName"></html:text></td>
									<td align="center"><a href="#" class="text-info"
										data-toggle="modal" data-target="#searchSupervisor"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>

								</tr>
							</table></td>
					</tr>
				</table>
			</div>

		</div>

		<div class="modal fade" id="searchDept" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Depatment Option</h4>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldDept"
										style="width: 150px">
											<option value="deptCode">Dept Code</option>
											<option value="deptName">Dept Name</option>
									</select></td>
									<td style="padding-left: 15px"><input type="text"
										id="txtSearchValueDept" class="form-control" /></td>
									<td style="padding-left: 15px"><button type="button"
											onclick="searchDepartment();" id="btnSearch"
											class="btn btn-sm btn-info btn-icon" title="Back">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button></td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearch"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>Department Code</th>
									<th>Department Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="listOfDepartment">
								<logic:iterate id="dept" name="listOfDepartment">
									<tr data-dismiss="modal" class="rowSearchDept">
										<td style="display: none"><bean:write name="dept"
												property="deptId" /></td>
										<td width="150px"><bean:write name="dept"
												property="deptCode" /></td>
										<td><bean:write name="dept" property="deptName" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="listOfDepartment">
								<tr>
									<td colspan="2" align="center">No Data Found</td>
								</tr>
							</logic:empty>
						</table>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		
		<div class="modal fade" id="searchSupervisor" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Supervisor Candidate</h4>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldSupervisor"
										style="width: 150px">
											<option value="employeeCode">Employee Code</option>
											<option value="employeeName">Employee Name</option>
											<option value="email">Email</option>
									</select></td>
									<td style="padding-left: 15px"><input type="text"
										id="txtSearchValueSupervisor" class="form-control" /></td>
									<td style="padding-left: 15px"><button type="button"
											onclick="searchSupervisor();" id="btnSearch"
											class="btn btn-sm btn-info btn-icon" title="Back">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button></td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearch"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>Employee Code</th>
									<th>Employee Name</th>
									<th>Email</th>
								</tr>
							</thead>
							<logic:notEmpty name="listOfSupervisor">
								<logic:iterate id="emp" name="listOfSupervisor">
									<tr data-dismiss="modal" class="rowSearchSupervisor">
										<td style="display: none"><bean:write name="emp"
												property="employeeId" /></td>
										<td width="150px"><bean:write name="emp"
												property="employeeCode" /></td>
										<td><bean:write name="emp" property="employeeName" /></td>
										<td><bean:write name="emp" property="email" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="listOfSupervisor">
								<tr>
									<td colspan="3" align="center">No Data Found</td>
								</tr>
							</logic:empty>
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