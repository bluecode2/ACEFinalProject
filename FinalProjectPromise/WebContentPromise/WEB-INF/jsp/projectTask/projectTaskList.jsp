<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Task List</title>
<script src="js/jquery.js"></script>

<script type="text/javascript">
	function onBtnBackClick() {
		window.location.href = "project.do";
	}

	function onBtnAddClick() {
		document.forms[0].task.value = "add";
		document.forms[0].submit();
	}

	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}
	$(document).ready(
			function() {
				$('.firstBtn').each(
						function() {

							var taskCode = $(this).closest('tr').find(
									'.hdTaskStatus').val();
							if (taskCode == 'TA_STAT_02') {
								$(this).addClass('glyphicon glyphicon-pencil');
								$(this).attr("title", "Edit Task");
							} else if (taskCode == 'TA_STAT_04') {
								$(this).addClass('glyphicon glyphicon-ok');
								$(this).attr("title", "Approve Task");
							}

							else
								$(this).hide();
						});
				$('.secondBtn').each(
						function() {
							var taskCode = $(this).closest('tr').find(
									'.hdTaskStatus').val();
							if (taskCode == 'TA_STAT_02') {
								$(this).addClass('glyphicon glyphicon-remove');
								$(this).attr("title", "Cancel Task");
							} else if (taskCode == 'TA_STAT_04') {
								$(this).addClass('glyphicon glyphicon-remove');
								$(this).attr("title", "Decline Task");
							}

							else
								$(this).hide();
						});

				$('.secondBtn').on(
						'click',
						function() {
							var taskId = $(this).closest('tr')
									.find('.hdTaskId').val();
							var taskName = $(this).closest('tr').find(
									'.hdTaskName').val();
							var taskStatus = $(this).closest('tr').find(
									'.hdTaskStatus').val();
							$('#txtValueTaskId').val(taskId);
							$('#txtValueTaskStatus').val(taskStatus);
							$('#txtValueTaskName').val(taskName);

							$('#addRemarks').modal();

						});
				$('.linkDesc').on(
						'click',
						function() {
							var taskDesc = $(this).closest('tr').find(
									'.hdTaskDesc').val();
							var taskName = $(this).closest('tr').find(
									'.hdTaskName').val();
							$('#txtSearchFieldDesc').val(taskDesc);
							$('#txtValueTaskNameDesc').val(taskName);

							$('#showDesc').modal();

						});
				$('.lnkRemarks').on(
						'click',
						function() {
							var remarks = $(this).closest('tr').find(
									'.hdRemarks').val();
							var taskName = $(this).closest('tr').find(
									'.hdTaskName').val();
							$('#txtRemarks').val(remarks);
							$('#txtValueTaskNameRemarks').val(taskName);
							
							$('#showRemarks').modal();

						});
				$('.goInsert').on(
						'click',
						function() {
							document.forms[0].selectedId.value = $(
									'#txtValueTaskId').val(); //task ID
							document.forms[0].task.value = "secondEdit";
							document.forms[0].remarksRecord.value = $(
									'#selSearchFieldRemark').val();
							var taskStatus = $('#txtValueTaskStatus').val();
							if (taskStatus == 'TA_STAT_02') { //Function for cancel task; change status and remarks
								document.forms[0].selectedEdit.value = "0";

							} else if (taskStatus == 'TA_STAT_04') { //Function for decline task; change status and remarks
								document.forms[0].selectedEdit.value = "1";

							}

							document.forms[0].submit();
						});
				$('.linkActivity').on('click',function(){
					var taskId = $(this).closest('tr').find('.hdTaskId').val();
					$.ajax({
						type : "POST",
						url : "activity.do",
						data : "task=viewActivity&taskId="
								+ taskId,
						success : function(response) {
							$("#tblShow").find("tr:gt(0)").remove();
							$("#tblShow").append(response);
							$('#showMember').modal();
						},
						error : function(e) {
							alert("Error: " + e);
						}
		
					});
		
					var projectName = $('#txtProjectName').val();
					var taskName = $(this).closest('tr').find('.hdTaskName').val();
					var assignedTo = $(this).closest('tr').find('.hdAssignedToName').val();
					
					$('#txtActivityProjectName').val(projectName);
					$('#txtActivityTaskName').val(taskName);
					$('#txtActivityAssignTo').val(assignedTo);
					$('#showActivity').modal();
				});
			});
	function changeStatusFirstBtn(taskId, taskStatus) {
		document.forms[0].task.value = "firstEdit";
		document.forms[0].selectedId.value = taskId;

		if (taskStatus == 'TA_STAT_02') { //Function for edit task; change name and desc
			document.forms[0].selectedEdit.value = "0";

		} else if (taskStatus == 'TA_STAT_04') { //Function for approve task; change status 
			document.forms[0].selectedEdit.value = "1";

		}

		document.forms[0].submit();
	}
</script>
</head>
<body>
	<html:form action="/projectTask" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden property="task" name="projectTaskForm" />
		<html:hidden property="selectedEdit" name="projectTaskForm" />
		<html:hidden property="currSearchField" name="projectTaskForm" />
		<html:hidden property="currSearchValue" name="projectTaskForm" />
		<html:hidden property="currPage" name="projectTaskForm" />
		<html:hidden property="selectedId" name="projectTaskForm" />
		<html:hidden property="remarksRecord" name="projectTaskForm" />

		<div class="container">
			<div class="form-group has-info" style="margin-top:40px">
				<table width="100%">
					<tr>
						<td width="45%">
							<table width="100%">
								<colgroup>
									<col width="40%" />
									<col />
								</colgroup>
								<tr>
									<td>Project Code</td>
									<td><html:text name="projectTaskForm"
											property="prjBean.projectCode" styleClass="form-control"
											disabled="true"></html:text></td>
								</tr>
								<tr>
									<td>Project Name</td>
									<td><html:text name="projectTaskForm" styleId="txtProjectName"
											property="prjBean.projectName" styleClass="form-control"
											disabled="true"></html:text></td>
								</tr>
								
							</table>
						</td>
						<td>&nbsp;</td>
						<td width="45%">
							<table width="100%">
								<colgroup>
									<col width="40%" />
									<col />
								</colgroup>
								<tr>
									<td>Project Manager</td>
									<td><html:text name="projectTaskForm"
											property="prjBean.employeeName" styleClass="form-control"
											disabled="true"></html:text></td>
								</tr>
								<tr>
									<td>Estimated Date</td>
									<td>
										<table width="100%">
											<colgroup>
												<col width="40%" />
												<col />
												<col width="40%" />
											</colgroup>
											<tr>
												<td><html:text name="projectTaskForm"
														property="prjBean.estStartDateInString"
														styleClass="form-control" disabled="true"></html:text></td>
												<td align="center">to</td>
												<td><html:text name="projectTaskForm"
														property="prjBean.estEndDateInString"
														styleClass="form-control" disabled="true"></html:text></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="projectTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="taskName">Task Name</option>
								<option value="assignToName">Assign To</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="projectTaskForm" property="searchValue"
								styleClass="form-control" /></td>
						<td style="padding-left: 15px">
							<button type="button" onclick="search();" id="btnSearch"
								class="btn btn-info btn-icon" title="Search">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</td>
					</tr>
				</table>
			</div>

			<div class="divContent">
				<table class="table table-bordered" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td>Task Name</td>
							<td>Assign To</td>
							<td align="center" width="200px">Estimate Date</td>
							<td align="center" width="200px">Actual Date</td>

							<td align="center">Status</td>
							<td align="center" width="100px">Activity</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectTaskForm" property="arrList">
							<logic:iterate id="reg" name="projectTaskForm" property="arrList">
								<tr>
									<td><html:hidden property="taskDesc" name="reg"
											styleClass="hdTaskDesc" /> 
											<html:hidden property="taskId"
											name="reg" styleClass="hdTaskId" /> 
											<html:hidden property="taskName" name="reg" styleClass="hdTaskName" /> 
											<html:hidden property="assignedTo" name="reg" styleClass="hdAssignedToName"/>
											<a
										href="#" class="text-info linkDesc">
										
										<html:hidden
											property="remarks" name="reg" styleClass="hdRemarks" /> <a
										href="#" class="text-info linkDesc"> <bean:write
												name="reg" property="taskName" />
									</a></td>
									<td><bean:write name="reg" property="assignedToName" /> <logic:equal name="reg" property="isOutsource" value="1">(Out)</logic:equal></td>
									<td align="center"><bean:write name="reg" property="estStartDateInString" /> to <bean:write name="reg" property="estEndDateInString" /></td>
									<td align="center"><bean:write name="reg" property="actStartDateInString" /> to <bean:write name="reg" property="actEndDateInString" /></td>
									<td align="center"><html:hidden name="reg" property="taskStatus"
											styleClass="hdTaskStatus" /> <bean:write name="reg"
											property="taskStatusName" /> : <bean:write name="reg" property="taskProgress" />%<logic:notEqual name="reg" property="remarks" value=""><br/><a href="#" class="lnkRemarks text-info">Remarks</a></logic:notEqual></td>
									<td align="center"><a href="#" class="text-info linkActivity">View Activity</a></td>
									<td align="center"><a class="text-success firstBtn"
										href="#" id="tes"
										onclick="changeStatusFirstBtn('<bean:write name="reg" property="taskId" />','<bean:write name="reg" property="taskStatus"/>')">
											<span aria-hidden="true"></span>
									</a> &nbsp; <a href="#" class="text-danger secondBtn"> <span
											aria-hidden="true"></span>
									</a></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="projectTaskForm" property="arrList">
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

		<!-- popup to give remarks -->
		<div class="modal fade" id="addRemarks" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Task Issue</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<table width="100%">
								<tr>
									<td style="padding-left: 15px">Task</td>
									<td style="padding-left: 15px"><input type="hidden"
										id="txtValueTaskId" /> <input type="hidden"
										id="txtValueTaskStatus" /> <input type="text"
										id="txtValueTaskName" class="form-control" disabled="disabled" />

									</td>

								</tr>
								<tr>
									<td style="padding-left: 15px">Remarks</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="selSearchFieldRemark"></textarea>
										</button></td>
								</tr>
							</table>
							<center>
								<input type="button" class="goInsert btn btn-sm btn-info"
									value="Save">

							</center>
						</div>

					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<!-- popup to show DESC -->
		<div class="modal fade" id="showDesc" tabindex="-1" role="dialog"
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
						<div class="form-group">
							<table width="100%">
								<tr>
									<td style="padding-left: 15px">Task</td>
									<td style="padding-left: 15px"><input type="text"
										id="txtValueTaskNameDesc" class="form-control"
										disabled="disabled" /></td>

								</tr>
								<tr>
									<td style="padding-left: 15px">Remarks</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="txtSearchFieldDesc"
											disabled="disabled"></textarea>
										</button></td>
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
		
		<!-- popup to show Remarks -->
		<div class="modal fade" id="showRemarks" tabindex="-1" role="dialog"
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
						<div class="form-group">
							<table width="100%">
								<tr>
									<td style="padding-left: 15px">Task</td>
									<td style="padding-left: 15px"><input type="text"
										id="txtValueTaskNameRemarks" class="form-control"
										disabled="disabled" /></td>

								</tr>
								<tr>
									<td style="padding-left: 15px">Task Desc</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="txtRemarks"
											disabled="disabled"></textarea>
										</button></td>
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
							<h4 class="modal-title">Project Member</h4>
							<br/>
						</div>
						<div class="modal-body">
						<div class="container form-group">
								<table>
									<tr>
										<td>Project Name</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtActivityProjectName" class="form-control" disabled="true"/>
										</td>
									</tr>
									<tr>
										<td>Task Name</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtActivityTaskName" class="form-control" disabled="true"/>
										</td>
									</tr>
									<tr>
										<td>Assign To</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtActivityAssignTo" class="form-control" disabled="true"/>
										</td>
									</tr>
								</table>
							</div>
						
							<div class="form-group">
								<table class="table table-bordered" cellspacing="0" id="tblShow" style="margin-top: 10px;" width="100%" class="tableContent">
									<tr>
										<th style="padding-left: 15px">Activity Description</th>
										<th style="padding-left: 15px">Completed</th>
										
									</tr>
										<logic:notEmpty name="projectTaskForm" property="arrActivity">
											<logic:iterate id="reg" name="projectTaskForm" property="arrActivity">
											<tr>
												
											</tr>
											</logic:iterate>
										</logic:notEmpty>
								
								</table>
							</div>

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