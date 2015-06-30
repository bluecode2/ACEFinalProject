<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Entry</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnBackClick() {
		location.href = "users.do";
	}

	function onBtnSaveClick() {
		if (document.forms[0].task.value == "add") {
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		} else if (document.forms[0].task.value == "edit") {
			document.forms[0].task.value = "save";
			document.forms[0].submit();

		} else if (document.forms[0].task.value == "changePassword") {
			var nPass = document.getElementById('newPass').value;
			var reTypePass = document.getElementById('reNewPass').value;
			var oPass = document.getElementById('oldPass').value;
			if (nPass == reTypePass && nPass != "" && oPass != "") {
				document.forms[0].passwordUser.value = nPass;
				document.forms[0].oldPassword.value = oPass;
				document.forms[0].task.value = "saveChangePassword";
				document.forms[0].submit();
			} else {
				alert('Password and Re-type Password must be same');
			}
		}

	}

	function onLoadForm() {

		if (document.forms[0].val.value == "1") {
			alert("Old Password is wrong");
		}

		if (document.forms[0].task.value == "edit")
			$('#txtUserName').attr("disabled", "disabled");
	}

	$(document).ready(function() {
		onLoadForm();
		registerSearchUserRole();
		registerSearchEmployee();
		chkActiveDirectory();
	});

	function registerSearchUserRole() {
		$('.rowSearchUserRole').on('click', function() {
			var value = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#txtUserRoleId').val(value);
			$('#roleIdDisplay').val(text);
		});
	}

	function registerSearchEmployee() {
		$('.rowSearchEmployee').on('click', function() {
			var value = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#txtEmployeeId').val(value);
			$('#employeeIdDisplay').val(text);
		});
	}

	function resetPass() {
		if (confirm("Are you sure you want to reset this user password?")) {
			document.forms[0].task.value = "resetPass";
			document.forms[0].submit();
		}
	}

	function search() {

		if (document.getElementById('btnSearchUserRole').value == 'btnRole') {
			var userRoleID = $('#txtUserRoleId').val();
			var searchField = $('#selSearchFieldRoleId').val();
			var searchValue = $('#txtSearchValueRoleId').val();

			$.ajax({
				type : "POST",
				url : "searchUserRole.do",
				data : "userRoleID=" + userRoleID + "&searchField="
						+ searchField + "&searchValue=" + searchValue,
				success : function(response) {
					$("#tblSearchUserRole").find("tr:gt(0)").remove();
					$("#tblSearchUserRole").append(response);
					registerSearchUserRole();
				},
				error : function(e) {
					alert("Error: " + e);
				}
			});
		}
		if (document.getElementById('btnSearchEmp').value == 'btnEmp') {
			var searchField = $('#selSearchFieldEmpId').val();
			var searchValue = $('#txtSearchValueEmpId').val();

			$.ajax({
				type : "POST",
				url : "searchEmp.do",
				data : "&searchField=" + searchField + "&searchValue="
						+ searchValue,
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
	}
	
	function chkActiveDirectory(){
		showLoading();
		var username = $('#txtUserName').val();
		
		$.ajax({
			type : "POST",
			url : "users.do",
			data : "task=chkActiveDirectory&username=" + username,
			success : function(response) {
				if(response == "1"){
					$("#divValidAD").show();
					$("#divInvalidAD").hide();
					$("#hdnIsActiveDirectory").val("1");
				}
				else{
					$("#divValidAD").hide();
					$("#divInvalidAD").show();
					$("#hdnIsActiveDirectory").val("0");
				}
				hideLoading();
			},
			error : function(e) {
				alert("Error: " + e);
				hideLoading();
			}
		});
	}
</script>
</head>
<body>
	<html:form action="/users" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="userForm" property="task" />
		<html:hidden name="userForm" property="passwordUser" />
		<html:hidden property="isAdd" name="userForm" />
		<html:hidden property="oldPassword" name="userForm" />
		<html:hidden property="uBean.passwordUser" name="userForm" />
		<html:hidden property="uBean.userId" name="userForm" />
		<html:hidden styleId="hdnIsActiveDirectory" property="uBean.isActiveDirectory" name="userForm" />
		<html:hidden property="val" name="userForm" />


		<div class="container">
			<div class="divSearch form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" />
						<col />
					</colgroup>
					<logic:notEqual value="true" name="changePassword">
						<tr align="left">
							<td>User Name</td>
							<td style="padding-left: 15px;">
								<table width="100%">
									<tr>
										<td><html:text name="userForm" property="uBean.username"
												styleId="txtUserName" styleClass="form-control">
											</html:text></td>
										<td><button type="button" id="btnCheckActiveDirectory"
												class="btn btn-xs btn-info" onclick="chkActiveDirectory();">
												Check<br />Act. Dir.
											</button></td>
										<td width="20%">
											<div id="divValidAD" class="text-success" align="left" style="display: none">
												<span class="glyphicon glyphicon-ok" aria-hidden="true" />
												ActDir
											</div>

											<div id="divInvalidAD" class="text-danger" align="left" style="display: none">
												<span class="glyphicon glyphicon-remove" aria-hidden="true" />
												Regular
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="left">
							<td>User Role ID</td>
							<td style="padding-left: 15px;">
								<table width="100%">
									<tr>
										<td><html:hidden property="uBean.userRoleId"
												name="userForm" styleId="txtUserRoleId" /></td>
										<td><html:text styleClass="form-control"
												styleId="roleIdDisplay" readonly="true" name="userForm"
												property="uBean.userRoleName"></html:text></td>
										<td><a href="#" class="text-info" data-toggle="modal"
											data-target="#searchUserRoleId"> <span
												class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="left">
							<td>Employee ID</td>
							<td style="padding-left: 15px;">
								<table width="100%">
									<tr>
										<td><html:hidden property="uBean.employeeId"
												name="userForm" styleId="txtEmployeeId" /></td>
										<td><html:text styleClass="form-control"
												styleId="employeeIdDisplay" readonly="true" name="userForm"
												property="uBean.employeeName"></html:text></td>
										<td><a href="#" class="text-info" data-toggle="modal"
											data-target="#searchEmployeeId"> <span
												class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<logic:notEqual name="userForm" property="isAdd" value="true">
							<logic:notEqual property="uBean.isActiveDirectory"
								name="userForm" value="1">
								<tr align="left" id="resetBtn">
									<td>&nbsp;</td>
									<td style="padding-left: 15px;"><input type="button"
										onclick="resetPass()" class="btn btn-info"
										value="Reset Password" /></td>
								</tr>
							</logic:notEqual>
						</logic:notEqual>

					</logic:notEqual>

					<logic:equal value="true" name="changePassword">
						<tr align="left">
							<td>User Name</td>
							<td style="padding-left: 15px;"><html:text name="userForm"
									property="uBean.username" styleClass="form-control"
									disabled="true">
								</html:text></td>
						</tr>
						<tr align="left">
							<td>User Role</td>
							<td style="padding-left: 15px;"><html:text
									styleClass="form-control" styleId="roleIdDisplay"
									readonly="true" name="userForm" property="uBean.userRoleName"
									disabled="true"></html:text></td>
						</tr>
						<tr align="left">
							<td>Employee</td>
							<td style="padding-left: 15px;"><html:text
									styleClass="form-control" styleId="employeeIdDisplay"
									readonly="true" name="userForm" property="uBean.employeeName"
									disabled="true"></html:text></td>
						</tr>

						<tr align="left">
							<td>Old Password</td>
							<td style="padding-left: 15px;"><input type="password"
								id="oldPass" Class="form-control" /></td>
						</tr>
						<tr align="left">
							<td>New Password</td>
							<td style="padding-left: 15px;"><input type="password"
								id="newPass" Class="form-control" /></td>
						</tr>
						<tr align="left">
							<td>Re-Type Password</td>
							<td style="padding-left: 15px;"><input type="password"
								id="reNewPass" Class="form-control" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>


					</logic:equal>
				</table>
			</div>

		</div>



		<div class="modal fade" id="searchUserRoleId" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">User Role</h4>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldRoleId"
										style="width: 150px">
											<option value="userRoleCode">User Role Code</option>
											<option value="userRoleName">User Role Name</option>
									</select></td>
									<td style="padding-left: 15px"><input type="text"
										id="txtSearchValueRoleId" class="form-control" /></td>
									<td style="padding-left: 15px">
										<button type="button" onclick="search();"
											id="btnSearchUserRole" class="btn btn-sm btn-info btn-icon"
											title="BackRole" value="btnRole">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button>
									</td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearchUserRole"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>User Role Code</th>
									<th>User Role Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="lstUserRole">
								<logic:iterate id="uRole" name="lstUserRole">
									<tr data-dismiss="modal" class="rowSearchUserRole">
										<td style="display: none"><bean:write name="uRole"
												property="userRoleId" /></td>
										<td width="150px"><bean:write name="uRole"
												property="userRoleCode" /></td>
										<td width="150px"><bean:write name="uRole"
												property="userRoleName" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstUserRole">
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
									<td style="padding-left: 15px"><input type="text"
										id="txtSearchValueEmpId" class="form-control" /></td>
									<td style="padding-left: 15px">
										<button type="button" onclick="search();" id="btnSearchEmp"
											class="btn btn-sm btn-info btn-icon" title="BackEmp"
											value="btnEmp">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button>
									</td>
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
										<td style="display: none"><bean:write name="emp"
												property="employeeId" /></td>
										<td width="150px"><bean:write name="emp"
												property="employeeCode" /></td>
										<td width="150px"><bean:write name="emp"
												property="employeeName" /></td>
										<td width="150px"><bean:write name="emp"
												property="deptName" /></td>
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

	
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>