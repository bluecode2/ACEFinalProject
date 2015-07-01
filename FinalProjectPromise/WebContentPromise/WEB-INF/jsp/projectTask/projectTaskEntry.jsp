<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Task Entry</title>
<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>
<link href="css/datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">
<script type="text/javascript">
	function onBtnBackClick() {
		location.href = "projectTask.do";
	}

	function onBtnSaveClick() {
		if(validateForm()){
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		}
	}

	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
		registerSearchAssignToEvent();
		registerSearchEmployee();
	});

	function searchEmployee() {
		showLoading();
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
				hideLoading();
			},
			error : function(e) {
				alert("Error: " + e);
				hideLoading();
			}
		});
	}

	function registerSearchAssignToEvent() {
		$('.rowSearch').on('click', function() {
			var value = $(this).find('td').eq(0).html();
			var text = $(this).find('td').eq(2).html();
			$('#hdEmpId').val(value);
			$('#txtAssignedToName').val(text);
		});
	}

	function registerSearchEmployee() {
		$('.rowSearchEmployee').on('click', function() {
			var value = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#hdEmpId').val(value);
			$('#txtAssignedToName').val(text);
		});
	}

	function onSearchAssignToClick() {
		var value = $('.rboIsOutsource:checked').val();
		if (value == 0)
			$('#searchProjMember').modal();
		else
			$('#searchEmployeeId').modal();
	}
	
	function validateForm(){
		var taskName	= document.getElementById("txtTaskName").value;
		var taskDesc 	= document.getElementById("txtTaskDesc").value;
		var estStart 	= document.getElementById("txtEstStartDate").value;
		var estEnd 		= document.getElementById("txtEstEndDate").value;
		var assignedTo 	= document.getElementById("txtAssignedToName").value;
		var estStartProj = document.getElementById("hdnEstStartDateProj").value;
		var estEndProj = document.getElementById("hdnEstEndDateProj").value;
		var str = "";
		var isValid = true;
		
		if(estEnd<estStart){
			str+= "<li>Estimate Start Date of Task must be smaller than Estimate Start Date Project!\n";
			isValid = false;
		}
		
		if (estStart < estStartProj){
			str+= "<li>Estimate Start Date of Task must be bigger than Estimate Start Date Project!\n";
			isValid = false;
		}		
		if (estEnd > estEndProj){
			str+= "<li>Estimate End Date of Task must be smaller than Estimate End Date Project!</li>";
			isValid = false;
		}		
		if(taskName.trim() == '') {
			str+= "<li>Task Name can not be empty!</li>";
			isValid = false;
		}
		else if(taskName.length > 26) {
			str+= "<li>Task Name can not be more than 25 characters!</li>";
			isValid = false;
		}
		
		if(taskDesc.trim() == '') {
			str+= "<li>Task Description can not be empty!</li>";
			isValid = false;
		}
		else if(taskDesc.length > 201) {
			str+= "<li>Task Description can not be more than 200 characters!</li>";
			isValid = false;
		}
		
		if(estStart.trim() == '') {
			str+= "<li>Estimate Start Date can not be empty!</li>";
			isValid = false;
		}
		
		if(estEnd.trim() == '') {
			str+= "<li>Estimate End Date can not be empty!</li>";
			isValid = false;
		}
		
		if(assignedTo.trim() == '') {
			str+= "<li>Assigned To can not be empty!</li>";
			isValid = false;
		}
		
		if(!isValid){
			document.getElementById('errorContent').innerHTML = str;
			document.getElementById("divError").style.display = "block";
		}
		
		return isValid;
	}
</script>
</head>
<body>
	<html:form action="/projectTask" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		<html:hidden property="task" name="projectTaskForm" />
		<html:hidden property="tkBean.taskId" name="projectTaskForm" />
		<html:hidden property="tkBean.assignedBy" name="projectTaskForm"
			styleId="hdSpvId" />
		<html:hidden property="tkBean.createdBy" name="projectTaskForm" />
		<html:hidden property="isAdd" name="projectTaskForm" />
		<html:hidden styleId="hdnProjectId" property="projectId"
			name="projectTaskForm" />
		<html:hidden property="tmpEstStartDateInString" name="projectTaskForm" styleId="hdnEstStartDateProj"/>
		<html:hidden property="tmpEstEndDateInString" name="projectTaskForm" styleId="hdnEstEndDateProj"/>
		<div class="container">

			<div class="divContent form-group has-info">
				<div class="text-danger" id="divError" style="display: none">
					Save failed!
					<ul id="errorContent"></ul>
				</div>
				<table width="50%">
					<colgroup>
						<col width="30%" class="tdLabel" />
						<col />
					</colgroup>
					<tr>
						<td class="tdLabel" align="right"><label>Task Name</label></td>
						<td><html:text styleClass="form-control"
								styleId="txtTaskName" name="projectTaskForm"
								property="tkBean.taskName"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Task Desc</label></td>
						<td><html:textarea property="tkBean.taskDesc"
								name="projectTaskForm" styleId="txtTaskDesc"
								styleClass="form-control"></html:textarea></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Est. Start
								Date</label></td>
						<td><html:text styleClass="form-control datepicker"
								styleId="txtEstStartDate" name="projectTaskForm"
								property="tkBean.estStartDateInString"></html:text></td>
					</tr>

					<tr>
						<td class="tdLabel" align="right"><label>Est. End
								Date</label></td>
						<td><html:text styleClass="form-control datepicker"
								styleId="txtEstEndDate" name="projectTaskForm"
								property="tkBean.estEndDateInString"></html:text></td>
					</tr>
					<tr valign="top">
						<td class="tdLabel" align="right"><label>Assigned To
						</label></td>
						<td>
							<table width="100%">
								<tr>
									<td width="50%"><html:radio name="projectTaskForm"
											property="tkBean.isOutsource" value="0"
											styleClass="rboIsOutsource">Project Member</html:radio></td>
									<td><html:radio name="projectTaskForm"
											property="tkBean.isOutsource" value="1"
											styleClass="rboIsOutsource">Outsource</html:radio></td>
								</tr>
							</table> <html:hidden styleId="hdEmpId" name="projectTaskForm"
								property="tkBean.assignedTo" />
							<table width="100%">
								<tr>
									<td><html:text styleClass="form-control"
											styleId="txtAssignedToName" readonly="true"
											name="projectTaskForm" property="tkBean.assignedToName"></html:text></td>
									<td align="center"><a href="#" class="text-info"
										data-toggle="modal" onclick="onSearchAssignToClick();"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>
								</tr>
							</table>
						</td>
					</tr>

				</table>
			</div>

		</div>

		<!-- popup to take assignedTo -->
		<div class="modal fade" id="searchProjMember" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Assign To</h4>
					</div>
					<div class="modal-body">
						<div style="overflow: auto; height: 350px">
							<table width="100%" id="tblSearch"
								class="table table-striped table-hover table-bordered table-clickable">
								<thead>
									<tr>
										<th>Employee Code</th>
										<th>Employee Name</th>
										<th>Project Role</th>
									</tr>
								</thead>
								<tbody>

									<logic:notEmpty name="listProjMember">
										<logic:iterate id="emp" name="listProjMember">
											<tr data-dismiss="modal" class="rowSearch">
												<td style="display: none"><bean:write name="emp"
														property="employeeId" /></td>
												<td width="120px"><bean:write name="emp"
														property="empCode" /></td>
												<td><bean:write name="emp" property="empName" /></td>
												<td width="150px"><bean:write name="emp"
														property="projRoleName" /></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="listProjMember">
										<tr>
											<td colspan="3" align="center">No Data Found</td>
										</tr>
									</logic:empty>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

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
										<button type="button" onclick="searchEmployee();"
											class="btn btn-sm btn-info btn-icon" title="Search">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button>
									</td>
								</tr>
							</table>
						</div>
						<div style="overflow: auto; height: 350px">
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
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>