<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project List</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnAddClick(){
		document.forms[0].task.value = "add";
		document.forms[0].submit();
	}
	
	$(document).ready(
			function() {
				registerSearchEmployee();
				registerSearchProjRole();
				empManager()
			});
	
	function actionForm(memberId, nama) {
		if (confirm("Are you sure want to delete Project Member : " + nama +" ?")) {
			document.forms[0].task.value = 'delProjMem';
			document.forms[0].selectedId.value = memberId;
			document.forms[0].submit();
		}
	}
	
	function addProjMember(){
		document.forms[0].task.value = "save";
		document.forms[0].submit();
	}
	
	function registerSearchEmployee(){
		$('.rowSearchEmployee').on(
				'click',
				function() {
					var valueEmp = $(this).find('td').eq(0).html().trim();
					var text =  $(this).find('td').eq(2).html().trim();
					$('#txtEmployeeId').val(valueEmp);
					$('#empIdDisplay').val(text);
				});
	}
	
	function registerSearchProjRole(){
		$('.rowSearchProjRole').on(
				'click',
				function() {
					var valueEmp = $(this).find('td').eq(0).html().trim();
					var text =  $(this).find('td').eq(2).html().trim();
					$('#txtRoled').val(valueEmp);
					$('#txtRoleIdDisplay').val(text);
				});
	}
	
	function search() {
			var searchField = $('#selSearchFieldEmpId').val();
			var searchValue = $('#txtSearchValueEmpId').val();
	
			$.ajax({
				type : "POST",
				url : "searchEmp.do",
				data : "&searchField=" + searchField
						+ "&searchValue=" + searchValue,
				success : function(response) {
					$("#tblSearchEmp").find("tr:gt(0)").remove();
					$("#tblSearchEmp").append(response);
					registerSearchEmployee();
				},
				error : function(e) {
					alert("Error: " + e);
				}
			});
	}
	
	function empManager(){
			var empMan = document.getElementById('empManager').value;
			var empId = document.getElementById('txtEmpId').value;
			if (empMan == empId){
				document.getElementById('delBtn').style.display = 'none';
			}
			else {
				document.getElementById('delBtn').style.display = 'block';
			}
	}
</script>
</head>
<body>
	<html:form action="/projectMember" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		
		<html:hidden name="projectMemberForm" property="task" />
		<html:hidden name="projectMemberForm" property="selectedId" />
		<html:hidden property="currPage" name="projectMemberForm"/>
		<html:hidden property="employeeId" name="getProject" styleId="empManager"/>
		
		<div class="container">
			<div class="divSearch form-group has-info" style="float: left;">
				<table>
					<tr>
						<td>Project Name
						</td>
						<td style="padding-left: 15px;"><html:text property="projectName" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td>Manager Name
						</td>
						<td style="padding-left: 15px;"><html:text property="employeeName" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td>Estimate Date
						</td>
						<td style="padding-left: 15px;"><html:text property="estStartDateInString" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
						<td style="padding-left: 15px;">To
						</td>
						<td style="padding-left: 15px;"><html:text property="estEndDateInString" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
					</tr>
				</table>
			</div>
		<logic:equal value="true" name="showAdd">
			<div class="divContent">
				<table border="0" fon
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<tr>
						<td><hr /></td>
					</tr>
				</table>
				<table cellspacing="0" style="margin-top: 10px;" width="400px"
					class="tableContent">
					<tr>
						<td width="40%">Employee Name
						</td>
						<td style="padding-left: 15px;"  width="50%">
						<html:hidden property="pMemberbean.employeeId" name="projectMemberForm" styleId="txtEmployeeId"/>
						<html:text property="pMemberbean.empName" name="projectMemberForm" styleClass="form-control" styleId="empIdDisplay"></html:text>
						</td>
						<td width="10%">
							<a href="#" class="text-info"
											data-toggle="modal" data-target="#searchEmployeeId"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
						</td>
					</tr>
					<tr>
						<td width="40%">Project Role Name
						</td>
						<td style="padding-left: 15px;" width="50%">
						<html:hidden property="pMemberbean.projectRoleId" name="projectMemberForm" styleId="txtRoled"/>
						<html:text property="pMemberbean.projRoleName" name="projectMemberForm" styleClass="form-control" styleId="txtRoleIdDisplay"></html:text>
						</td>
						<td width="10%">
							<a href="#" class="text-info"
											data-toggle="modal" data-target="#searchRoleId"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
						</td>
					</tr>
					<tr>
						<td width="40%">&nbsp;
						</td>
						<td style="padding-left: 15px;" width="50%">
						<button id="btnSave" type="button" onclick="addProjMember();"
							class="btn btn-info btn-icon" title="Add Member">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
						</td>
						<td width="10%">&nbsp;
						</td>
					</tr>
				</table>
			</div>
		</logic:equal>
			<div class="divContent">
				<table class="table table-striped table-bordered table-hover"
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<thead class="panel panel-info">
						<tr>
							
							<td>Project Code</td>
							<td>Project Name</td>
							<td>Employee Name</td>
							<td>Project Role Name</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectMemberForm" property="listOfProjMember">
							<logic:iterate id="member" name="projectMemberForm" property="listOfProjMember">
								<tr>
									<td><html:hidden property="employeeId" name="member" styleId="txtEmpId"/>
									<bean:write name="member" property="projCode" /></td>
									<td><bean:write name="member" property="projName" /></td>
									<td><bean:write name="member" property="empName" /></td>
									<td><bean:write name="member" property="projRoleName" /></td>
									<td><a href="#" onclick="actionForm('<bean:write name="member" property="memberId" />','<bean:write name="member" property="empName" />');" id="delBtn"
										><span class="glyphicon glyphicon-remove"
											aria-hidden="true"></span></a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="projectMemberForm" property="listOfProjMember">
							<tr>
								<td colspan="5" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		
		<div class="modal fade" id="searchEmployeeId" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Employee</h4>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldEmpId"
										style="width: 150px">
											<option value="employeeCode">Employee Code</option>
											<option value="employeeName">Employee Name</option>
									</select></td>
									<td style="padding-left: 15px">
									<input type="text" id="txtSearchValueEmpId" class="form-control" /></td>
									<td style="padding-left: 15px">
									<button type="button" onclick="search();" id="btnSearchEmp"
											class="btn btn-sm btn-info btn-icon" title="BackEmp" value="btnEmp">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button></td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearchEmp"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>Employee Code</th>
									<th>Employee Name</th>
									<th>Department Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="lstEmployeeId">
								<logic:iterate id="emp" name="lstEmployeeId">
									<tr data-dismiss="modal" class="rowSearchEmployee">
										<td style="display: none">
										<bean:write name="emp" property="employeeId" /></td>
										<td width="150px">
										<bean:write name="emp" property="employeeCode" /></td>
										<td width="150px">
										<bean:write name="emp" property="employeeName" /></td>
										<td width="150px">
										<bean:write name="emp" property="deptName" /></td>
										<td style="display: none">
										<bean:write name="emp" property="deptId" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstEmployeeId">
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
		
		<div class="modal fade" id="searchRoleId" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Project Role</h4>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldRoleId"
										style="width: 150px">
											<option value="projRoleCode">Project Role Code</option>
											<option value="projRoleName">Project Role Name</option>
									</select></td>
									<td style="padding-left: 15px">
									<input type="text" id="txtSearchValueRoleId" class="form-control" /></td>
									<td style="padding-left: 15px">
									<button type="button" onclick="search();" id="btnSearchRole"
											class="btn btn-sm btn-info btn-icon" title="BackRole" value="btnRole">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button></td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearchProjRole"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>Project Role Code</th>
									<th>Project Role Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="lstProjectRole">
								<logic:iterate id="role" name="lstProjectRole">
									<tr data-dismiss="modal" class="rowSearchProjRole">
										<td style="display: none"><bean:write name="role" property="projectRoleId" /></td>
										<td width="150px"><bean:write name="role" property="projectRoleCode" /></td>
										<td><bean:write name="role" property="projectRoleName" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstProjectRole">
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
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>