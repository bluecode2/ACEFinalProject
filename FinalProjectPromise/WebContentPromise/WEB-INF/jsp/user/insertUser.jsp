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
			var nPass = document.getElementById('newPass').value;
			var reTypePass = document.getElementById('reNewPass').value;
			var oPass = document.getElementById('oldPass').value;
		if (document.forms[0].task.value == "add") {
			if (nPass == reTypePass) {
				document.forms[0].passwordUser.value = nPass;
				document.forms[0].task.value = "save";
				document.forms[0].submit();
			} else {
				alert('Password and Re-type Password must be same');
			}
		} else if (document.forms[0].task.value == "edit") {
				if (nPass == reTypePass) {
					document.forms[0].passwordUser.value = nPass;
					document.forms[0].oldPassword.value = oPass;
					document.forms[0].task.value = "save";
					document.forms[0].submit();
				} else {
					alert('Password and Re-type Password must be same');
				}
		}

	}

	function onLoadForm() {
				
		if (document.forms[0].val.value == "0"){
			if (document.forms[0].task.value == "add") {
				document.getElementById('newPassword').style.display = 'none';
				document.getElementById('oldPassword').style.display = 'none';
			} else if (document.forms[0].task.value == "Edit") {
				document.getElementById('newPassword').style.display = 'block';
				document.getElementById('oldPassword').style.display = 'block';
			}
		}
		if (document.forms[0].val.value == "1"){
			alert("Old Password is wrong");
		}
	}
	
	$(document).ready(
			function() {
				onLoadForm();
				registerSearchUserRole();
				registerSearchEmployee();
			});
	
	function registerSearchUserRole(){
	
		$('.rowSearchUserRole').on(
				'click',
				function() {
					var value = $(this).find('td').eq(0).html().trim();
					var text = $(this).find('td').eq(2).html().trim();
					$('#txtUserRoleId').val(value);
					$('#roleIdDisplay').val(text);
				});
	}
	
	function registerSearchEmployee(){
		$('.rowSearchEmployee').on(
				'click',
				function() {
					var value = $(this).find('td').eq(0).html().trim();
					var text =  $(this).find('td').eq(2).html().trim();
					$('#txtEmployeeId').val(value);
					$('#employeeIdDisplay').val(text);
				});
	}
	
	function resetPass(){
		document.forms[0].task.value = "resetPass";
		document.forms[0].submit();
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
		<html:hidden property="oldPassword" name="userForm"/>
		<html:hidden property="uBean.passwordUser" name="userForm"/>
		<html:hidden property="uBean.userId" name="userForm"/>
		<html:hidden property="val" name="userForm"/>
		
		
		
		<div class="container">
			<div class="divSearch form-group has-info" style="float: left;">
				<table>

					<tr align="left">
						<td>User Role ID</td>
						<td style="padding-left: 15px;">
								<table>
									<tr>
										<td>
										<html:hidden property="uBean.userRoleId" name="userForm" styleId="txtUserRoleId" />
										</td>
										<td>
											<html:text styleClass="form-control"
											styleId="roleIdDisplay" readonly="true" name="userForm"
											property="uBean.userRoleName"></html:text>
										</td>
										<td>
											<a href="#" class="text-info"
											data-toggle="modal" data-target="#searchUserRoleId"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
										</td>
									</tr>
								</table>
						</td>
					</tr>
					<tr align="left">
						<td>Employee ID</td>
						<td style="padding-left: 15px;">
								<table>
									<tr>
										<td>
											<html:hidden property="uBean.employeeId" name="userForm" styleId="txtEmployeeId"/>
										</td>
										<td>
											<html:text styleClass="form-control"
											styleId="employeeIdDisplay" readonly="true" name="userForm"
											property="uBean.employeeName"></html:text>
										</td>
										<td>
											<a href="#" class="text-info"
											data-toggle="modal" data-target="#searchEmployeeId"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
										</td>
									</tr>
								</table>
						</td>
					</tr>
					<tr align="left">
						<td>User Name</td>
						<td style="padding-left: 15px;"><html:text name="userForm"
								property="uBean.username" styleClass="form-control"></html:text>
						</td>
					</tr>
					<tr align="left" id="oldPassword">
						<td>Old Password</td>
						<td style="padding-left: 15px;"><input type="password"
							id="oldPass" Class="form-control" /></td>
					</tr>
					<tr align="left">
						<td><label id="newPassword">New</label> Password</td>
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
					<logic:equal value="true" name="show">
					<tr align="left" id="resetBtn">
						<td>&nbsp;</td>
						<td style="padding-left: 15px;"  ><input type="button"
							onclick="resetPass()" class="btn-info" value="Reset Password"/></td>
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
									<td style="padding-left: 15px">
									<input type="text" id="txtSearchValueRoleId" class="form-control" /></td>
									<td style="padding-left: 15px">
									<button type="button" onclick="search();" id="btnSearch"
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
									<th>User Role Code</th>
									<th>User Role Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="lstUserRole">
								<logic:iterate id="uRole" name="lstUserRole">
									<tr data-dismiss="modal" class="rowSearchUserRole">
										<td style="display: none">
										<bean:write name="uRole" property="userRoleId" /></td>
										<td width="150px">
										<bean:write name="uRole" property="userRoleCode" /></td>
										<td width="150px">
										<bean:write name="uRole" property="userRoleName" /></td>
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
										class="form-control" id="selSearchFieldRoleId"
										style="width: 150px">
											<option value="employeeCode">Employee Code</option>
											<option value="employeeName">Employee Name</option>
									</select></td>
									<td style="padding-left: 15px">
									<input type="text" id="txtSearchValueRoleId" class="form-control" /></td>
									<td style="padding-left: 15px">
									<button type="button" onclick="search();" id="btnSearch"
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
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstEmployeeId">
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