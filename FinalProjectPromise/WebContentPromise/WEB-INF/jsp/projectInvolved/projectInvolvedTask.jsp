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
	
	function getStyleBtn(){
				$('.firstBtn').each(function() {
					var assgTo = $(this).closest('tr').find('td').eq(0).html().trim();
					var currUser = $('#users_empId').val();
					var taskStat = $(this).closest('tr').find('td').eq(6).html().trim();
					var taskProg = $(this).closest('tr').find('td').eq(7).html().trim();
						if (assgTo == currUser){
							if (taskStat == 'TA_STAT_02') {
								$(this).addClass('glyphicon glyphicon-play');
								$(this).attr("title","Start");
							} else if (taskStat == 'TA_STAT_03' && taskProg == '100.0') {
								$(this).addClass('glyphicon glyphicon-ok-circle');
								$(this).attr("title","Submit");
							} else if (taskStat == 'TA_STAT_03') {
								$(this).addClass('glyphicon glyphicon-pause');
								$(this).attr("title","Pause");
							} else if (taskStat == 'TA_STAT_06') {
								$(this).addClass('glyphicon glyphicon-chevron-right');
								$(this).attr("title","Resume");
							
							} else{
								$(this).hide();
							}
						}
				});
	}
	
	$(document).ready(
			function() {
				getStyleBtn();
				//LINK MANAGE ACTIVITY
				$('.lnkMngActivity').each(function (){
					var assignTo = $(this).closest('tr').find('td').eq(0).html().trim();
					var currEmpId = $('#users_empId').val();
					if(assignTo == currEmpId)
						$(this).show();
					else
						$(this).hide();
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
	
	//action for button Action Task
	function updateTask(id, nama, taskStat, taskProg){
		document.forms[0].testingId.value = id;
		//alert(document.forms[0].testingId.value+' id sebenarnya '+id);
		if (taskStat == 'TA_STAT_02'){ // if not started
			if (confirm("Start Task " + nama +" ?")) {
				document.forms[0].task.value = 'startTask';
				document.forms[0].submit();
			}
		}
		else if (taskStat == 'TA_STAT_03' && taskProg == '100.0'){ // if on going and progress 100%
			if (confirm("Submit Task " + nama +" ?")) {
				document.forms[0].task.value = 'submitTask';
				document.forms[0].submit();
			}
		}
		else if (taskStat == 'TA_STAT_03'){ // if on going and progress < 100%
			if (confirm("Pause Task " + nama +" ?")) {
				document.forms[0].task.value = 'pauseTask';
				document.forms[0].submit();
			}
		}
		else if (taskStat == 'TA_STAT_06'){ // if resume
			if (confirm("Resume Task " + nama +" ?")) {
				document.forms[0].task.value = 'resumeTask';
				document.forms[0].submit();
			}
		}
	}
	
</script>
</head>
<body>
	<html:form action="/projectInvolvedTask" method="post">
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
		<html:hidden property="empId" name="projectTaskForm" styleId="users_empId"/>
		<html:hidden property="testingId" name="projectTaskForm"/>

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
									<td><html:text name="projectTaskForm"
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
								<td style="display:none"> <bean:write name="reg" property="assignedTo"/> </td>
									<td><html:hidden property="assignedTo" name="reg" styleId="assTo"/>
									<html:hidden property="taskDesc" name="reg" styleClass="hdTaskDesc" /> 
									<html:hidden property="taskId" name="reg" styleClass="hdTaskId" /> 
									<html:hidden property="taskName" name="reg" styleClass="hdTaskName" /> 
									<a href="#" class="text-info linkDesc">
									<html:hidden property="remarks" name="reg" styleClass="hdRemarks" /> 
										<bean:write name="reg" property="taskName" />
									</a>
	
									
									</td>
									<td><bean:write name="reg" property="assignedToName" /> <logic:equal name="reg" property="isOutsource" value="1">(Out)</logic:equal></td>
									<td align="center"><bean:write name="reg" property="estStartDateInString" /> to <bean:write name="reg" property="estEndDateInString" /></td>
									<td align="center"><bean:write name="reg" property="actStartDateInString" /> to <bean:write name="reg" property="actEndDateInString" /></td>
									<td align="center"><html:hidden name="reg" property="taskStatus"
											styleClass="hdTaskStatus" styleId="hdnTaskStat"/> 
											<html:hidden name="reg" property="taskProgress"
											styleClass="hdTaskProg" styleId="hdnTaskProg"/>
											<bean:write name="reg"
											property="taskStatusName" /> : 
											<bean:write name="reg" property="taskProgress" />%<logic:notEqual name="reg" property="remarks" value=""><br/><a href="#" class="lnkRemarks text-info">Remarks</a></logic:notEqual></td>
									<td style="display:none"><bean:write name="reg" property="taskStatus" /></td>
									<td style="display:none"><bean:write name="reg" property="taskProgress" /></td>
									<td align="center"><a href="#" class="text-info lnkMngActivity" id="mngAct">Manage Activity</a>
									</td>
									<td align="center">
										<a href="#" onclick="updateTask('<bean:write name="reg" property="taskId" />', '<bean:write name="reg" property="taskName" />', '<bean:write name="reg" property="taskStatus" />', '<bean:write name="reg" property="taskProgress" />');">
											<span class="firstBtn" aria-hidden="true" id="firstBtn" ></span>
										</a> &nbsp; 
									</td>
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
		

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>