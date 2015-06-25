<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Task List</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}

	function actionForm(task, id, nama) {

		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;
		if (confirm("Are you sure want to " + task + " Task " + nama + "?")) {
			document.forms[0].submit();
		}
	}

	function getTaskDesc(taskDesc) {
		$('#txtTaskDesc').html(taskDesc);
		$('#taskDesc').modal();
	}

	$(document).ready(
			function() {
				$('.linkActivity').on(
						'click',
						function() {

							var taskId = $(this).closest('tr')
									.find('.hdTaskId').val();

							$
									.ajax({
										type : "POST",
										url : "activity.do",
										data : "task=viewActivity&taskId="
												+ taskId,
										success : function(response) {
											$("#tblShow").find("tr:gt(0)")
													.remove();
											$("#tblShow").append(response);
											$('#showMember').modal();
										},
										error : function(e) {
											alert("Error: " + e);
										}

									});

							var taskName = $(this).closest('tr').find(
									'.hdTaskName').val();
							var assignedTo = $(this).closest('tr').find(
									'.hdAssignedToName').val();

							$('#txtActivityTaskName').val(taskName);
							$('#txtActivityAssignTo').val(assignedTo);
							$('#showActivity').modal();
						});
			});
</script>
</head>
<body>
	<html:form action="/myCurrentTask" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>

		<html:hidden name="myCurrentTaskForm" property="task" />
		<html:hidden name="myCurrentTaskForm" property="selectedId" />
		<html:hidden name="myCurrentTaskForm" property="currSearchField" />
		<html:hidden name="myCurrentTaskForm" property="currSearchValue" />
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="myCurrentTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="propTaskName">Task Name</option>
								<option value="propStatus">Status</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="myCurrentTaskForm" property="searchValue"
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
							<td class="align-center">Task Name</td>
							<td class="align-center">Estimate Start Date</td>
							<td class="align-center">Estimate End Date</td>
							<td class="align-center">Progress</td>
							<td class="align-center">Status</td>
							<td class="align-center">Activity</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="myCurrentTaskForm" property="arrList">
							<logic:iterate id="reg" name="myCurrentTaskForm"
								property="arrList">
								<tr>
									<html:hidden styleClass="hdTaskId" name="reg" property="taskId"/>
									<html:hidden styleClass="hdTaskName" name="reg" property="taskName"/>
									<html:hidden styleClass="hdAssignedToName" name="reg" property="assignedToName"/>
									<td><a href="#" class="text-info"
										onclick="getTaskDesc('<bean:write name="reg" property="taskDesc" />');"
										data-target="taskDesc"> <bean:write name="reg"
												property="taskName" /></a></td>
									<td><bean:write name="reg" property="estStartDateInString" /></td>
									<td><bean:write name="reg" property="estEndDateInString" /></td>
									<td><bean:write name="reg" property="taskProgress" /></td>
									<td><bean:write name="reg" property="taskStatusName" /></td>
									<td align="center"><a href="#"
										class="text-info linkActivity">Manage Activity</a></td>
									<td align="center"><logic:equal name="reg"
											property="taskStatus" value="TA_STAT_02">
											<a class="text-success" href="#"
												onclick="actionForm('start','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');"
												title="Start"><span class="glyphicon glyphicon-play"
												aria-hidden="true"></span></a>
										</logic:equal> <logic:equal name="reg" property="taskStatus"
											value="TA_STAT_03">
											<a class="text-success" href="#"
												onclick="actionForm('pause','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');"
												title="Pause"><span class="glyphicon glyphicon-pause"
												aria-hidden="true"></span></a>
										</logic:equal> <logic:equal name="reg" property="taskStatus"
											value="TA_STAT_06">
											<a class="text-success" href="#"
												onclick="actionForm('start','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');"
												title="Pause"><span class="glyphicon glyphicon-play"
												aria-hidden="true"></span></a>
										</logic:equal> <logic:equal name="reg" property="taskProgress" value="100">
											<a class="text-success" href="#"
												onclick="actionForm('submit','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');"
												title="Pause"><span class="glyphicon glyphicon-ok"
												aria-hidden="true"></span></a>
										</logic:equal></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="myCurrentTaskForm" property="arrList">
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

		<!-- popup to show Activity -->
		<div class="modal fade" id="showActivity" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Project Member</h4>
						<br />
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Task Name</td>
									<td style="padding-left: 15px"><input type="text"
										id="txtActivityTaskName" class="form-control" disabled="true" />
									</td>
								</tr>
								<tr>
									<td>Assign To</td>
									<td style="padding-left: 15px"><input type="text"
										id="txtActivityAssignTo" class="form-control" disabled="true" />
									</td>
								</tr>
							</table>
						</div>

						<div class="form-group">
							<table class="table table-bordered" cellspacing="0" id="tblShow"
								style="margin-top: 10px;" width="100%" class="tableContent">
								<tr>
									<th style="padding-left: 15px">Activity Description</th>
									<th style="padding-left: 15px">Completed</th>
								</tr>
							</table>
						</div>

					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<html:hidden name="myCurrentTaskForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>