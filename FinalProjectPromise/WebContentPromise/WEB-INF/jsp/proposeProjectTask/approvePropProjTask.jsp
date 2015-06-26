<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proposed Task List</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function actionForm(task, id) {
		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;
 		document.forms[0].submit();
	}
	function getTaskDesc(taskDesc) {
		$('#txtTaskDesc').html(taskDesc);
		$('#taskDesc').modal();
	}
	function getEmpList(empList) {
		$('#txtEmpList').html(empList);
		$('#empList').modal();
	}
	function getValue(id,name) {
		document.forms[0].propTo.value = id;
		document.getElementById("propTo").text = name;
	}
	
	var currLink;
	var currHdnField;
	
	$(document).ready(function() {
		$('.lnkAssignTo').on('click',function(){
			currLink = $(this);
			currHdnField = $(this).closest('td').find('.hdnAssignTo');
			$("#empList").modal();
		});
		
		$('.btnApprove').on('click',function(){
			var taskId = $(this).closest('tr').find('td').eq(0).html().trim();
			var assignToId = $(this).closest('tr').find('.hdnAssignTo').val();
			document.forms[0].task.value = 'approve';
			document.forms[0].selectedId.value = taskId;
			document.forms[0].assignTo.value = assignToId;
			document.forms[0].submit();
		});
		
		registerSearchAssignToEvent();
	});
	
	function registerSearchAssignToEvent(){
		$('.rowSearch').on('click',function(){
			var empId = $(this).closest('tr').find('td').eq(0).html();
			var empName = $(this).closest('tr').find('td').eq(2).html();
			currHdnField.val(empId);
			currLink.html(empName);
		});
	}
	
	function search() {
		var spvId = $('#hdnEmpId').val();
		var searchField = $('#selSearchFieldAssignTo').val();
		var searchValue = $('#txtSearchValueAssignTo').val();

		$.ajax({
			type : "POST",
			url : "searchAssignTo.do",
			data : "spvId=" + spvId + "&searchField=" + searchField
					+ "&searchValue=" + searchValue,
			success : function(response) {
				$("#tblSearch").find("tr:gt(0)").remove();
				$("#tblSearch").append(response);
				registerSearchAssignToEvent();
			},
			error : function(e) {
				alert("Error: " + e);
			}
		});
	}

</script>
</head>
<body>
	<html:form action="/appPropProjTask" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="approvePropProjForm" property="task" />
		<html:hidden name="approvePropProjForm" property="selectedId" />
		<html:hidden name="approvePropProjForm" property="currSearchField" />
		<html:hidden name="approvePropProjForm" property="currSearchValue" />
		<html:hidden name="approvePropProjForm" property="propTo"/>
		<html:hidden name="approvePropProjForm" property="empId" styleId="hdnEmpId" />
		<html:hidden name="approvePropProjForm" property="assignTo" styleId="hdnAssignTo" />
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="approvePropProjForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="propTaskName">Task Name</option>
								<option value="propBy">Proposed By</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="approvePropProjForm" property="searchValue"
								styleClass="form-control" /></td>
						<td style="padding-left: 15px"><button type="button"
								onclick="search();" id="btnSearch" class="btn btn-info btn-icon"
								title="Search">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button></td>
					</tr>
				</table>
			</div>

			<div class="divContent">
				<table class="table table-striped table-bordered table-hover"
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<thead>
						<tr class="panel panel-info">
							<td>Task Name</td>
							<td>Estimate Start Date</td>
							<td>Estimate End Date</td>
							<td>Estimate Main Days</td>
							<td>Proposed By</td>
							<td>Assign To</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="approvePropProjForm" property="arrList">
							<logic:iterate id="reg" name="approvePropProjForm" property="arrList">
								<tr>
									<td style="display: none"><bean:write name="reg" property="propTaskId" /></td>
									<td><a href="#" class="text-info" 
									onclick="getTaskDesc('<bean:write name="reg" property="propTaskDesc" />');" data-target="taskDesc">
									<bean:write name="reg" property="propTaskName" /></a>
									<td><bean:write name="reg" property="estStartDateInString" /> to </td>
									<td><bean:write name="reg" property="estEndDateInString" /></td>
									<td><bean:write name="reg" property="estMainDays" /></td>
									<td><bean:write name="reg" property="propByName" /></td>
									<td><input type="hidden" class="hdnAssignTo" value="<bean:write name="reg" property="propBy" />" /><a href="#" class="text-info lnkAssignTo">
									<bean:write name="reg" property="propByName" /></a></td>
									<td align="center"><a class="text-success btnApprove" href="#"
										title="Approve"><span class="glyphicon glyphicon-ok"
											aria-hidden="true"></span></a> &nbsp; <a href="#"
										class="text-danger btnDecline"
										onclick="actionForm('decline','<bean:write name="reg" property="propTaskId" />');"
										title="Decline"><span class="glyphicon glyphicon-remove"
											aria-hidden="true"></span></a></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="approvePropProjForm" property="arrList">
							<tr>
								<td colspan="7" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>

		<div class="modal fade" id="taskDesc" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Task Description</h4>
					</div>
					<div class="modal-body">
						<hr />
						<br>
						<p id="txtTaskDesc"></p>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		<!-- popup to take assignedTo -->
		<div class="modal fade" id="empList" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<html:form action="searchAssignTo" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Assign To</h4>
						</div>
						<div class="modal-body">
							<div class="container form-group">
								<table>
									<tr>
										<td>Search</td>
										<td style="padding-left: 15px"><select
											class="form-control" id="selSearchFieldAssignTo"
											style="width: 150px">
												<option value="employeeCode">Employee Code</option>
												<option value="employeeName">Employee Name</option>
										</select></td>
										<td style="padding-left: 15px"><input type="text"
											id="txtSearchValueAssignTo" class="form-control" /></td>
										<td style="padding-left: 15px"><button type="button"
												onclick="search();" id="btnSearch"
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
								<tbody>
									<logic:notEmpty name="approveTaskForm" property="eBean">
										<logic:iterate id="emp" name="approveTaskForm" property="eBean">
											<tr data-dismiss="modal" class="rowSearch">
												<td style="display: none"><bean:write name="emp" property="employeeId" /></td>
												<td width="150px"><bean:write name="emp" property="employeeCode" /></td>
												<td><bean:write name="emp" property="employeeName" /></td>
												<td width="150px"><bean:write name="emp" property="email" />
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="approveTaskForm" property="eBean">
										<tr>
											<td colspan="3" align="center">No Data Found</td>
										</tr>
									</logic:empty>
								</tbody>
							</table>
						</div>
					</html:form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		
		<html:hidden name="approvePropProjForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>