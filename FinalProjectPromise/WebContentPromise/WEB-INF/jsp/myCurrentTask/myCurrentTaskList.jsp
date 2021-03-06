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
							showLoading();
							var taskId = $(this).closest('tr')
									.find('.hdTaskId').val();
							var taskStat = $(this).closest('tr').find('.hdTaskStatus').val();

							$.ajax({
								type : "POST",
								url : "activity.do",
								data : "task=manageActivity&taskId=" + taskId,
								success : function(response) {
									$("#tblShow").find("tr:gt(0)").remove();
									$("#tblShow").append(response);
									if (taskStat == 'TA_STAT_04' ||taskStat == 'TA_STAT_05' ||
											taskStat == 'TA_STAT_07' ||taskStat == 'TA_STAT_98'
											||taskStat == 'TA_STAT_99'){
										$('#btnShowEntry').hide();
									}
									$('#showMember').modal();
									hideLoading();
								},
								error : function(e) {
									alert("Error: " + e);
									hideLoading();
								}
							});

							var taskName = $(this).closest('tr').find(
									'.hdTaskName').val();
							var assignedTo = $(this).closest('tr').find(
									'.hdAssignedToName').val();

							$('#hdnModalTaskId').val(taskId);
							$('#txtActivityTaskName').val(taskName);
							$('#txtActivityAssignTo').val(assignedTo);
							$('#manageActivity').modal();
						});
				
				$('.viewActivity').on('click',function(){
					showLoading();
					var taskId = $(this).closest('tr').find('.hdTaskId').val();
					$.ajax({
						type : "POST",
						url : "activity.do",
						data : "task=viewActivity&taskId="
								+ taskId,
						success : function(response) {
							$("#tblShow2").find("tr:gt(0)").remove();
							$("#tblShow2").append(response);
							$('#showMember').modal();
							hideLoading();
						},
						error : function(e) {
							alert("Error: " + e);
							hideLoading();
						}
					});

					var taskName = $(this).closest('tr').find('.hdTaskName').val();
					var assignedTo = $(this).closest('tr').find('.hdAssignedToName').val();
					
					$('#txtActivityTaskName1').val(taskName);
					$('#txtActivityAssignTo1').val(assignedTo);
					$('#showActivity').modal();
				});

				$('.viewRemarks').on('click',function(){
					var remarks = $(this).closest('tr').find('.hdRemarks').val();
					var taskName = $(this).closest('tr').find('.hdTaskName').val();
					
					$('#txtValueRemarksTaskRemarks').val(remarks);
					$('#txtValueRemarksTaskName').val(taskName);
					$('#showRemarks').modal();
				});
				
				$('#btnShowEntry').on('click', function() {
					$('#txtActivityDesc').val('');
					$('#divActivityEntry').show();
				});
				$('#btnCancel').on('click', function() {
					$('#divActivityEntry').hide();
				});

				$('#btnSaveActivity').on(
						'click',
						function() {
							showLoading();
							var taskId = $('#hdnModalTaskId').val();
							var activityDesc = $('#txtActivityDesc').val();

							$.ajax({
								type : "POST",
								url : "activity.do",
								data : "task=addActivity&taskId=" + taskId
										+ "&actDesc=" + activityDesc,
								success : function(response) {
									$('.emptyRow').remove();
									$("#tblShow").append(response);
									$('#divActivityEntry').hide();
									registerBtnActivityEvent();
									hideLoading();
								},
								error : function(e) {
									alert("Error: " + e);
									hideLoading();
								}
							});	
						});

				$('#manageActivity').on('shown.bs.modal', function() {
					registerBtnActivityEvent();
				});
				
				$('#manageActivity').on('hidden.bs.modal', function() {
					window.location.href = "myCurrentTask.do";
				});
				hideLoading();
			});

	function registerBtnActivityEvent() {
		$('.btnActivityDelete').off('click');
		$('.btnComplete').off('click');
		$('.btnUndoComplete').off('click');
		
		$('.btnActivityDelete')
				.on(
						'click',
						function() {
							if (confirm('Are you sure you want to delete this activity?')) {
								showLoading();
								var activityId = $(this).closest('tr').find(
										'.hdnActivityId').val();
								var row = $(this).closest('tr');

								$.ajax({
									type : "POST",
									url : "activity.do",
									data : "task=deleteActivity&selectedId="
											+ activityId,
									success : function(response) {
										row.remove();
										hideLoading();
									},
									error : function(e) {
										alert("Error: " + e);
										hideLoading();
									}
								});
							}
						});
		$('.btnComplete').on(
				'click',
				function() {
					showLoading();
					var activityId = $(this).closest('tr').find(
							'.hdnActivityId').val();
					var row = $(this).closest('tr');

					$.ajax({
						type : "POST",
						url : "activity.do",
						data : "task=updateActivity&selectedId=" + activityId + "&isCompleted=1",
						success : function(response) {
							row.find('td').eq(2).remove();
							row.find('td').eq(1).remove();
							row.append(response);
							registerBtnActivityEvent();
							hideLoading();
						},
						error : function(e) {
							alert("Error: " + e);
							hideLoading();
						}
					});
				});
		$('.btnUndoComplete').on(
				'click',
				function() {
					showLoading();
					var activityId = $(this).closest('tr').find(
							'.hdnActivityId').val();
					var row = $(this).closest('tr');

					$.ajax({
						type : "POST",
						url : "activity.do",
						data : "task=updateActivity&selectedId=" + activityId + "&isCompleted=0",
						success : function(response) {
							row.find('td').eq(2).remove();
							row.find('td').eq(1).remove();
							row.append(response);
							registerBtnActivityEvent();
							hideLoading();
						},
						error : function(e) {
							alert("Error: " + e);
							hideLoading();
						}
					});
				});
	}
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
								<option value="taskName">Task Name</option>
								<option value="taskStatusName">Status</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="myCurrentTaskForm" property="searchValue"
								styleClass="form-control" /></td>
						<td style="padding-left: 15px"><button type="button"
								onclick="search();" id="btnSearch" class="btn btn-raised btn-info btn-icon"
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
							<td class="align-center">Assigned By</td>
							<td class="align-center">Estimate Date</td>
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
									<html:hidden styleClass="hdTaskId" name="reg" property="taskId" />
									<html:hidden styleClass="hdTaskName" name="reg"
										property="taskName" />
									<html:hidden styleClass="hdAssignedToName" name="reg"
										property="assignedToName" />
										<html:hidden property="remarks" name="reg" styleClass="hdRemarks"/>
									<html:hidden name="reg" property="taskStatus" styleClass="hdTaskStatus" />
									<td><a href="#" class="text-info"
										onclick="getTaskDesc('<bean:write name="reg" property="taskDesc" />');"
										data-target="taskDesc"> <bean:write name="reg"
												property="taskName" /></a></td>
									<td><bean:write name="reg"
												property="assignedByName" /></td>
									<td align="center">
							          <logic:notEmpty name="reg" property="estStartDateInString">
							           <bean:write name="reg" property="estStartDateDisplay" /> to 
							            <logic:notEmpty name="reg" property="estEndDateInString">
							             <bean:write name="reg" property="estEndDateDisplay" />
							            </logic:notEmpty>
							            <logic:empty  name="reg" property="estEndDateInString">
							             -
							            </logic:empty>
							            <br/>(<bean:write name="reg" property="estMainDays" /> man days)
							          </logic:notEmpty>
							          <logic:empty name="reg" property="estStartDateInString">
							           -
							          </logic:empty>
							         </td>
									<td align="center">
										<bean:write name="reg" property="taskStatusName" /> : <bean:write name="reg" property="taskProgress" />%<br/>
										<logic:notEmpty name="reg" property="remarks">
											<a href="#" class="text-info viewRemarks">Remarks</a>
										</logic:notEmpty>
									</td>
									<td align="center">
									<!-- edit di sini -->
									<logic:equal name="reg" value="TA_STAT_04" property="taskStatus">
										<a href="#" class="text-info viewActivity">View Activity</a>
									</logic:equal>
									<logic:notEqual name="reg" value="TA_STAT_04" property="taskStatus">
										<a href="#" class="text-info linkActivity">Manage Activity</a>
									</logic:notEqual>
									<!-- sampe sini -->
									</td>
									<td align="center">
									<logic:equal name="reg" property="taskStatus" value="TA_STAT_02">
										<a class="text-success" href="#" onclick="actionForm('start','<bean:write name="reg" property="taskId" />', '<bean:write name="reg" property="taskName" />');" title="Start">
												<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
										</a>
									</logic:equal> 
									<logic:equal name="reg" property="taskStatus" value="TA_STAT_03">
											<a class="text-success" href="#"
												onclick="actionForm('pause','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');"
												title="Pause"><span class="glyphicon glyphicon-pause"
												aria-hidden="true"></span></a>
									</logic:equal> 
									<logic:equal name="reg" property="taskStatus" value="TA_STAT_06">
											<a class="text-success" href="#" onclick="actionForm('start','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');" title="Pause">
												<span class="glyphicon glyphicon-play" aria-hidden="true">
												</span>
											</a>
									</logic:equal> 
									<logic:equal name="reg" property="taskProgress" value="100">
										<logic:equal name="reg" property="taskStatus" value="TA_STAT_03">
											<a class="text-success" href="#"
												onclick="actionForm('submit','<bean:write name="reg" property="taskId" />',
												'<bean:write name="reg" property="taskName" />');"
												title="Submit"><span class="glyphicon glyphicon-ok"
												aria-hidden="true"></span></a>
										</logic:equal>
									</logic:equal>
									</td>
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

			<!-- pop up to show remarks -->	
			<div class="modal fade" id="showRemarks" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Task Remarks</h4>
						</div>
						<div class="modal-body">
							<div class="form-group has-info">
								<table width="100%">
									<tr>
										<td style="padding-left: 15px">Task Name</td>
										<td >
											<input type="text" id="txtValueRemarksTaskName" class="form-control" readonly="readonly" />
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px" valign="top">
											Task Desc 
										</td>
										<td >
											<textarea rows="3" cols="3" class="form-control"  id="txtValueRemarksTaskRemarks" readonly="readonly"></textarea>
										</td>
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

		<!-- popup to manage Activity -->
		<div class="modal fade" id="manageActivity" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Manage Activity</h4>
						<br />
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<input type="hidden" id="hdnModalTaskId" />
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

						<div class="form-group" style="overflow-y: auto; height: 350px">
							<a class="text-info" id="btnShowEntry" href="#"
								title="Add Activity"><span class="glyphicon glyphicon-plus"
								aria-hidden="true"></span></a>
							<div id="divActivityEntry"
								style="margin-top: 20px; padding: 10px; display: none"
								class="panel form-group has-info">
								<h4>Activity Entry</h4>
								<hr>
								<table width="100%">
									<tr>
										<td>Activity Desc</td>
										<td><input type="text" class="form-control"
											id="txtActivityDesc" /></td>
									</tr>
									<tr>
										<td colspan="2" align="right">
											<button type="button" id="btnSaveActivity"
												class="btn btn-raised btn-sm btn-info btn-80">Save</button>
											<button type="button" id="btnCancel" class="btn btn-raised btn-sm btn-80">Cancel</button>
										</td>
									</tr>
								</table>
							</div>
							<table class="table table-striped table-bordered table-hover" cellspacing="0" id="tblShow"
								style="margin-top: 10px;" width="100%" class="tableContent">
								<tr>
									<th style="padding-left: 15px">Activity Description</th>
									<th align="center" width="100px">Completed</th>
									<th align="center" width="100px">Action</th>
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
		
		<!-- popup to show Activity -->
			<div class="modal fade" id="showActivity" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">View Activity</h4>
							<br/>
						</div>
						<div class="modal-body">
						<div class="container form-group">
								<table>
									<tr>
										<td>Task Name</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtActivityTaskName1" class="form-control" disabled="true"/>
										</td>
									</tr>
									<tr>
										<td>Assign To</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtActivityAssignTo1" class="form-control" disabled="true" />
										</td>
									</tr>
								</table>
							</div>
						
							<div class="form-group">
								<table class="table table-striped table-bordered table-hover" cellspacing="0" id="tblShow2" style="margin-top: 10px;" width="100%" class="tableContent">
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
