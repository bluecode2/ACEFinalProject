<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Involved Task List</title>
<link href="css/datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">


	function showTask(task) {
		document.forms[0].showDiv.value = task;
	}

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
	
	function search2() {
		document.forms[0].currSearchField2.value = document.forms[0].searchField2.value;
		document.forms[0].currSearchValue2.value = document.forms[0].searchValue2.value;

		changePage(1);
	}

	function getStyleBtn() {

		$('.firstBtn').each(function() {
			var assgTo = $(this).closest('tr').find('.assTo').val();
			var currUser = $('#users_empId').val();
			var taskStat = $(this).closest('tr').find('.hdTaskStatus').val();
			var taskProg = $(this).closest('tr').find('.hdTaskProg').val();
			if (assgTo == currUser) {
				if (taskStat == 'TA_STAT_02') {
					$(this).addClass('glyphicon glyphicon-play');
					$(this).attr("title", "Start");
				} else if (taskStat == 'TA_STAT_03' && taskProg == '100.0') {
					$(this).addClass('glyphicon glyphicon-ok-circle');
					$(this).attr("title", "Submit");
				} else if (taskStat == 'TA_STAT_03') {
					$(this).addClass('glyphicon glyphicon-pause');
					$(this).attr("title", "Pause");
				} else if (taskStat == 'TA_STAT_06') {
					$(this).addClass('glyphicon glyphicon-chevron-right');
					$(this).attr("title", "Resume");
				} else {
					$(this).hide();
				}
			}
		});
	}

	$(document)
			.ready(
					function() {
						showLoading();
						$('.statusCheck').each(
								function() {
									var statusRemark = $(this).closest('td')
											.find('.propStatusId').val();
									if (statusRemark != 'TA_STAT_98') {
										$(this).hide();
									}
								});

						getStyleBtn();
						$(".datepicker").attr("data-provide", "datepicker");

						//link to show popup Desc
						$('.linkDesc').on(
								'click',
								function() {

									var taskName = $(this).closest('tr').find(
											'.hdTaskName').val();
									var taskDesc = $(this).closest('tr').find(
											'.hdTaskDesc').val();

									$('#txtValueTaskDescName').val(taskName);
									$('#txtValueTaskDescDesc').val(taskDesc);
									$('#showDesc').modal();
								});

						//link to show popup remarks
						$('.lnkRemarks')
								.on(
										'click',
										function() {
											var taskName = $(this)
													.closest('tr').find(
															'.hdTaskName')
													.val();
											var taskRemarks = $(this).closest(
													'tr')
													.find('.hdTaskRemakrs')
													.val();
											$('#txtValueTaskNameRemarks').val(
													taskName);
											$('#txtValueTaskRemarksRemarks')
													.val(taskRemarks);

											$('#showRemarks').modal();
										});

						//link to manage first button on going
						$('.firstBtn')
								.on(
										'click',
										function() {
											var assgTo = $(this).closest('tr')
													.find('.assTo').val();
											var currUser = $('#users_empId')
													.val();
											var taskStat = $(this)
													.closest('tr').find(
															'.hdTaskStatus')
													.val();
											var taskProg = $(this)
													.closest('tr').find(
															'.hdTaskProg')
													.val();
											var nama = $(this).closest('tr')
													.find('.hdTaskName').val();
											var taskId = $(this).closest('tr')
													.find('.hdTaskId').val();
											document.forms[0].testingId.value = taskId;

											if (assgTo == currUser) {
												if (taskStat == 'TA_STAT_02') {//this task for start task
													if (confirm("Start Task "
															+ nama + " ?")) {
														document.forms[0].task.value = 'startTask';
														document.forms[0]
																.submit();
													}
												} else if (taskStat == 'TA_STAT_03'
														&& taskProg == '100.0') {// this task for submit task
													if (confirm("Submit Task "
															+ nama + " ?")) {
														document.forms[0].task.value = 'submitTask';
														document.forms[0]
																.submit();
													}
												} else if (taskStat == 'TA_STAT_03') {//this task for pause task
													if (confirm("Pause Task "
															+ nama + " ?")) {
														document.forms[0].task.value = 'pauseTask';
														$(
																'#txtAddRemarksValueTaskId')
																.val(taskId);
														$(
																'#txtAddRemarksValueTaskName')
																.val(nama);
														$(
																'#txtAddRemarksValueTaskStatus')
																.val(taskStat);
														$('#addRemarks')
																.modal();

													}
												} else if (taskStat == 'TA_STAT_06') {//this task for resume
													if (confirm("Resume Task "
															+ nama + " ?")) {
														document.forms[0].task.value = 'resumeTask';
														document.forms[0]
																.submit();
													}
												} else {
													$(this).hide();
												}
											}
										});

						$('.insertRemarks').on(
								'click',
								function() {
									document.forms[0].remarksRecord.value = $(
											'#txtAddRemarksValueTaskRemarks')
											.val();
									document.forms[0].submit();
								});

						//LINK MANAGE ACTIVITY
						$('.lnkMngActivity').each(
								function() {
									var assignTo = $(this).closest('tr').find(
											'.assTo').val();
									var currEmpId = $('#users_empId').val();
									if (assignTo == currEmpId)
										$(this).show();
									else
										$(this).hide();
								});

						$('.lnkMngActivity').on(
								'click',
								function() {

									var taskId = $(this).closest('tr').find(
											'.hdTaskId').val();

									$.ajax({
										type : "POST",
										url : "activity.do",
										data : "task=manageActivity&taskId="
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
									var assignedTo = $(this).closest('tr')
											.find('.hdAssignedToName').val();

									$('#hdnModalTaskId').val(taskId);
									$('#txtActivityTaskName').val(taskName);
									$('#txtActivityAssignTo').val(assignedTo);
									$('#showActivity').modal();

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
									var taskId = $('#hdnModalTaskId').val();
									var activityDesc = $('#txtActivityDesc')
											.val();

									$.ajax({
										type : "POST",
										url : "activity.do",
										data : "task=addActivity&taskId="
												+ taskId + "&actDesc="
												+ activityDesc,
										success : function(response) {
											$('.emptyRow').remove();
											$("#tblShow").append(response);
											$('#divActivityEntry').hide();
											registerBtnActivityEvent();
										},
										error : function(e) {
											alert("Error: " + e);
										}

									});
								});

						$('#showActivity').on('shown.bs.modal', function() {
							registerBtnActivityEvent();
						});
						$('#showActivity').on('hidden.bs.modal', function() {
							window.location.href = "projectInvolvedTask.do";
						});

						$('#btnAddPropTask').on('click', function() {
							$('#hdnPropTaskId').val('');
							$('#txtTaskName').val('');
							$('#txtTaskDesc').val('');
							$('#txtEstStartDate').val('');
							$('#txtEstEndDate').val('');
							$('#modalPropTaskEntry').modal();
						});

						$('.btnEditProposeTask')
								.on(
										'click',
										function() {
											var propTaskId = $(this).closest(
													'tr').find(
													'.hdnListPropTaskId').val();
											var propTaskName = $(this).closest(
													'tr').find(
													'.hdnListPropTaskName')
													.val();
											var propTaskDesc = $(this).closest(
													'tr').find(
													'.hdnListPropTaskDesc')
													.val();
											var propTaskStartDate = $(this)
													.closest('tr')
													.find(
															'.hdnListPropTaskStartDate')
													.val();
											var propTaskEndDate = $(this)
													.closest('tr')
													.find(
															'.hdnListPropTaskEndDate')
													.val();

											$('#hdnPropTaskId').val(propTaskId);
											$('#txtTaskName').val(propTaskName);
											$('#txtTaskDesc').val(propTaskDesc);
											$('#txtEstStartDate').val(
													propTaskStartDate);
											$('#txtEstEndDate').val(
													propTaskEndDate);
											$('#modalPropTaskEntry').modal();
										});

						$('#btnCancelAdd').on('click', function() {
							$('#modalPropTaskEntry').modal('hide');
						});

						$('#btnSaveProposeTask').on('click', function() {
							if(validateForm()){
								document.forms[0].taskForProp.value = "save";
	
								if ($("#hdnPropTaskId").val().length > 0)
									document.forms[0].isAdd.value = false;
								else
									document.forms[0].isAdd.value = true;
	
								
									document.forms[0].submit();
							}
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
									},
									error : function(e) {
										alert("Error: " + e);
									}

								});
							}
						});
		$('.btnComplete').on(
				'click',
				function() {
					var activityId = $(this).closest('tr').find(
							'.hdnActivityId').val();
					var row = $(this).closest('tr');

					$.ajax({
						type : "POST",
						url : "activity.do",
						data : "task=updateActivity&selectedId=" + activityId
								+ "&isCompleted=1",
						success : function(response) {
							row.find('td').eq(2).remove();
							row.find('td').eq(1).remove();
							row.append(response);
							registerBtnActivityEvent();
						},
						error : function(e) {
							alert("Error: " + e);
						}

					});
				});
		$('.btnUndoComplete').on(
				'click',
				function() {
					var activityId = $(this).closest('tr').find(
							'.hdnActivityId').val();
					var row = $(this).closest('tr');

					$.ajax({
						type : "POST",
						url : "activity.do",
						data : "task=updateActivity&selectedId=" + activityId
								+ "&isCompleted=0",
						success : function(response) {
							row.find('td').eq(2).remove();
							row.find('td').eq(1).remove();
							row.append(response);
							registerBtnActivityEvent();
						},
						error : function(e) {
							alert("Error: " + e);
						}

					});
				});
	}

	//function untuk edit dan delete dari propose project task
	function actionForm(task, id, nama) {

		document.forms[0].taskForProp.value = task;
		document.forms[0].selectTaskId.value = id;

		if (task == "delete") {
			if (confirm("Are you sure want to delete proposed Project Task "
					+ nama + " ?")) {
				document.forms[0].submit();
			}
		} else {
			document.forms[0].submit();
		}
	}

	function btnAddProp(task) {
		// 		var allowAdd = document.forms[0].allowAdd.value;
		// 		if (allowAdd == "true") {
		// 			document.forms[0].taskForProp.value = task;
		// 			document.forms[0].submit();
		// 		} else {
		// 			alert("You don't have a supervisor to proposed an Project task!");
		// 		}

	}

	function showRemarksProp(remarks) {
		$('#txtRemarksDec').html(remarks);
		$('#remarksProp').modal();
	}
	
	function validateForm(){
		
		var taskName	= document.getElementById("txtTaskName").value;
		var taskDesc 	= document.getElementById("txtTaskDesc").value;
		var startDate 	= document.getElementById("txtEstStartDate").value;
		var endDate 	= document.getElementById("txtEstEndDate").value;
		
		var str = "";
		var isValid = true;
		
		if(taskName.trim() == '') {
			str+= "Task Name can not be empty!\n";
			isValid = false;
		}
		else if(taskName.length > 26) {
			str+= "Task Name can not be more than 25 characters!\n";
			isValid = false;
		}
		
		if(taskDesc.trim() == '') {
			str+= "Task Description can not be empty!\n";
			isValid = false;
		}
		else if(taskDesc.length > 201) {
			str+= "Task Description can not be more than 200 characters!\n";
			isValid = false;
		}
		
		if(startDate.trim() == '') {
			str+= "Estimate Start Date can not be empty!\n";
			isValid = false;
		}
		
		if(endDate.trim() == '') {
			str+= "Estimate End Date can not be empty!\n";
			isValid = false;
		}
		
		if(!isValid){
			alert(str);
		}
		
		return isValid;
	}
</script>
</head>
<body>
	<html:form action="/projectInvolvedTask" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden property="task" name="projectTaskForm" />
		<html:hidden property="taskForProp" name="projectTaskForm" />
		
		
		<html:hidden property="selectedEdit" name="projectTaskForm" />
		<html:hidden property="currSearchField" name="projectTaskForm" />
		<html:hidden property="currSearchValue" name="projectTaskForm" />
		<html:hidden property="currPage" name="projectTaskForm" />
		
		<html:hidden property="currSearchField2" name="projectTaskForm" />
		<html:hidden property="currSearchValue2" name="projectTaskForm" />
		<html:hidden property="currPage2" name="projectTaskForm" />
		
		
		<html:hidden property="selectedId" name="projectTaskForm" />
		<html:hidden property="remarksRecord" name="projectTaskForm" />
		<html:hidden property="empId" name="projectTaskForm"
			styleId="users_empId" />
		<html:hidden property="testingId" name="projectTaskForm" />
		<html:hidden property="showDiv" name="projectTaskForm" />
		<html:hidden property="selectTaskId" name="projectTaskForm" />
		
		<html:hidden property="allowAdd" name="projectTaskForm" />
		<html:hidden property="isAdd" name="projectTaskForm" />
		<html:hidden styleId="hdnPropTaskId" property="bean.propTaskId"
			name="projectTaskForm" value="" />

		<div class="container divContent">
			<div class="form-group has-info">
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

			<div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="#projectTask" data-toggle="tab">Project
							Task</a></li>
					<li><a href="#proposeTask" data-toggle="tab">Propose Task</a></li>
				</ul>
			</div>

			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade active in" id="projectTask">
					<div class="divSearch form-group has-info" align="right">
						<table width="100%">
							<tr valign="middle">
								<td width="60%"></td>
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
					<div id="projTask">
						<table class="table table-bordered" cellspacing="0" width="100%"
							class="">
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
									<logic:iterate id="reg" name="projectTaskForm"
										property="arrList">
										<tr>
											<td style="display: none"><html:hidden
													property="assignedTo" name="reg" styleClass="assTo" /> <html:hidden
													property="taskDesc" name="reg" styleClass="hdTaskDesc" />
												<html:hidden property="taskId" name="reg"
													styleClass="hdTaskId" /> <html:hidden property="taskName"
													name="reg" styleClass="hdTaskName" /> <html:hidden
													property="remarks" name="reg" styleClass="hdTaskRemakrs" />
												<html:hidden property="assignedToName" name="reg"
													styleClass="hdAssignedToName" /> <html:hidden name="reg"
													property="taskProgress" styleClass="hdTaskProg" /> <html:hidden
													name="reg" property="taskStatus" styleClass="hdTaskStatus" />

												<bean:write name="reg" property="assignedTo" /></td>
											<td><a href="#" class="text-info linkDesc"> <bean:write
														name="reg" property="taskName" />
											</a></td>
											<td><bean:write name="reg" property="assignedToName" />
												<logic:equal name="reg" property="isOutsource" value="1">(Out)</logic:equal></td>
											<td align="center"><bean:write name="reg"
													property="estStartDateDisplay" /> to <bean:write
													name="reg" property="estEndDateDisplay" />
													<br/>(<bean:write
													name="reg" property="estMainDays" /> main days)
											</td>
											<%-- <td align="center">
												<logic:notEmpty name="reg" property="actStartDateInString">
													<bean:write name="reg" property="actStartDateDisplay" /> to 
														<logic:notEmpty name="reg" property="actEndDateInString">
															<bean:write name="reg" property="actEndDateDisplay" />
														</logic:notEmpty>
														<logic:empty  name="reg" property="actEndDateInString">
															-
														</logic:empty>
														<br/>(
															<logic:notEmpty name="reg" property="actMainDays">
																<bean:write name="reg" property="actMainDays" />
															</logic:notEmpty>
															<logic:empty name="reg" property="actMainDays">
																-
															</logic:empty>
														 main days)
												</logic:notEmpty>
												<logic:empty name="reg" property="actStartDateInString">
													-
												</logic:empty>
											</td> --%>
											
											<td align="center"><bean:write name="reg"
													property="actStartDateInString" /> to <bean:write
													name="reg" property="actEndDateInString" /></td>
											<td align="center"><bean:write name="reg"
													property="taskStatusName" /> : <bean:write name="reg"
													property="taskProgress" />%<logic:notEqual name="reg"
													property="remarks" value="">
													<br />
													<a href="#" class="lnkRemarks text-info">Remarks</a>
												</logic:notEqual></td>
											<td style="display: none"><bean:write name="reg"
													property="taskStatus" /></td>
											<td style="display: none"><bean:write name="reg"
													property="taskProgress" /></td>
											<td align="center"><a href="#"
												class="text-info lnkMngActivity" id="mngAct">Manage
													Activity</a></td>
											<td align="center"><a href="#" onclick=""> <span
													class="firstBtn" aria-hidden="true"></span>
											</a> &nbsp; <!-- 	<a href="#" onclick="">
											<span class="secondBtn" aria-hidden="true" ></span>
										</a>  --></td>
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


				<div class="tab-pane fade" id="proposeTask">
					<%-- 			<logic:equal value="show" name="viewAddEdit"> --%>
					<div id="divProposeTaskEntry" class="panel form-group has-info"
						style="padding: 20px; display: none">
						<h4>Propose Task Entry</h4>
						<hr>

					</div>
					<%-- 			</logic:equal> --%>

					<div class="divSearch form-group has-info" align="right">
						<table width="100%">
							<tr valign="middle">
								<td width="60%"><div align="left">
										<button type="button" href="#" id="btnAddPropTask"
											class="btn btn-raised btn-info btn-icon"
											title="Proposed Task">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										</button>
									</div></td>
								<td>Search by</td>
								<td style="padding-left: 15px;"><html:select
										name="projectTaskForm" property="searchField2"
										styleId="selSearchField2" styleClass="form-control">
										<option value="propTaskName">Task Name</option>
									</html:select></td>
								<td style="padding-left: 15px"><html:text
										name="projectTaskForm" property="searchValue2"
										styleClass="form-control" /></td>
								<td style="padding-left: 15px"><button type="button"
										onclick="search2();" id="btnSearch2"
										class="btn btn-info btn-icon" title="Search">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									</button></td>
							</tr>
						</table>
					</div>
					<div>
						<table class="table table-striped table-bordered table-hover"
							cellspacing="0" style="margin-top: 10px;" width="100%"
							class="tableContent">
							<thead>
								<tr class="panel panel-info">
									<td width="200px">Task Name</td>
									<td>Task Desc</td>
									<td align="center" width="190px">Estimate</td>
									<td>Proposed To</td>
									<td align="center">Status</td>
									<td class="align-center">Action</td>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="projectTaskForm" property="arrListProp">
									<logic:iterate id="reg" name="projectTaskForm"
										property="arrListProp">
										<tr>
											<html:hidden styleClass="hdnListPropTaskId" name="reg"
												property="propTaskId" />
											<html:hidden styleClass="hdnListPropTaskDesc" name="reg"
												property="propTaskDesc" />
											<html:hidden styleClass="hdnListPropTaskName" name="reg"
												property="propTaskName" />
											<html:hidden styleClass="hdnListPropTaskStartDate" name="reg"
												property="estStartDateInString" />
											<html:hidden styleClass="hdnListPropTaskEndDate" name="reg"
												property="estStartDateInString" />


											<td><bean:write name="reg" property="propTaskName" />
											<td><bean:write name="reg" property="propTaskDesc" />
											<td align="center"><bean:write name="reg"
													property="estStartDateInString" /> &nbsp;to&nbsp; <bean:write
													name="reg" property="estEndDateInString" /></td>
											<td><bean:write name="reg" property="propToName" /></td>
											<td align="center"><bean:write name="reg" property="propStatusName" /></td>
											<td align="center"><logic:equal name="reg"
													property="propStatus" value="TA_STAT_01">
													<a class="text-success btnEditProposeTask" href="#"
														title="Edit"><span class="glyphicon glyphicon-pencil"
														aria-hidden="true"></span></a> &nbsp; <a href="#"
														class="text-danger btnDeleteProposeTask"
														onclick="actionForm('delete','<bean:write name="reg" property="propTaskId" />','<bean:write name="reg" property="propTaskName" />');"
														title="Delete"><span class="glyphicon glyphicon-trash"
														aria-hidden="true"></span></a>
												</logic:equal></td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="projectTaskForm" property="arrListProp">
									<tr>
										<td colspan="6" align="center" style="padding: 10px">No
											Data Found</td>
									</tr>
								</logic:empty>
							</tbody>
						</table>
						<jsp:include page="/WEB-INF/jsp/include/pagination2.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- popup to give remarks  -->
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
										id="txtAddRemarksValueTaskId" /> <input type="hidden"
										id="txtAddRemarksValueTaskStatus" /> <input type="text"
										id="txtAddRemarksValueTaskName" class="form-control"
										disabled="disabled" /></td>

								</tr>
								<tr>
									<td style="padding-left: 15px">Remarks</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="txtAddRemarksValueTaskRemarks"></textarea>
									</td>
								</tr>
							</table>
							<div class="modal-footer">
								<table align="right">
									<tr>
										<td><input type="button"
											class="insertRemarks btn btn-sm btn-info" value="Save">
										</td>
										<td><input type="button" class="btn btn-sm "
											data-dismiss="modal" value="Cancel"></td>
									</tr>
								</table>

							</div>

						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
		</div>
		<!-- /.modal  -->

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
										id="txtValueTaskDescName" class="form-control"
										disabled="disabled" /></td>

								</tr>
								<tr>
									<td style="padding-left: 15px">Description</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="txtValueTaskDescDesc"
											disabled="disabled"></textarea></td>
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

		<!-- popup to show Remark -->
		<div class="modal fade" id="showRemarks" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
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
						<div class="form-group">
							<table width="100%">
								<tr>
									<td style="padding-left: 15px">Task</td>
									<td style="padding-left: 15px"><input type="text"
										id="txtValueTaskNameRemarks" class="form-control"
										disabled="disabled" /></td>

								</tr>
								<tr>
									<td style="padding-left: 15px" valign="top">Task Remarks</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="txtValueTaskRemarksRemarks"
											disabled="disabled">
									</textarea></td>
								</tr>
							</table>
						</div>

					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal  -->

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
												class="btn btn-sm btn-info">Save</button>
											<button type="button" id="btnCancel" class="btn btn-sm">Cancel</button>
										</td>
									</tr>
								</table>
							</div>
							<table class="table table-bordered" cellspacing="0" id="tblShow"
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

		<div class="modal fade" id="remarksProp" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Remarks Description</h4>
					</div>
					<div class="modal-body">
						<hr />
						<br>
						<p id="txtRemarksDec"></p>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<div class="modal fade" id="modalPropTaskEntry" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Propose Task Entry</h4>
						<hr />
					</div>
					<div class="modal-body">
						<table width="80%" class="form-group has-info">
							<colgroup>
								<col width="30%" />
								<col />
							</colgroup>
							<tr>
								<td>Task Name</td>
								<td><html:text styleClass="form-control"
										styleId="txtTaskName" name="projectTaskForm"
										property="bean.propTaskName"></html:text></td>
							</tr>
							<tr>
								<td>Task Desc</td>
								<td><html:textarea styleClass="form-control"
										styleId="txtTaskDesc" name="projectTaskForm"
										property="bean.propTaskDesc"></html:textarea></td>
							</tr>
							<tr>
								<td>Estimate Start Date</td>
								<td><html:text styleClass="form-control datepicker"
										styleId="txtEstStartDate" name="projectTaskForm"
										property="bean.estStartDateInString" style="width: 150px"></html:text>
								</td>
							</tr>
							<tr>
								<td>Estimate End Date</td>
								<td><html:text styleClass="form-control datepicker"
										styleId="txtEstEndDate" name="projectTaskForm"
										property="bean.estEndDateInString" style="width: 150px"></html:text>
								</td>
							</tr>
						</table>
						<div align="right">
							<button type="button" id="btnSaveProposeTask" class="btn btn-sm btn-info">Save</button>
							&nbsp;
							<button type="button" id="btnCancelAdd" class="btn btn-sm">Cancel</button>
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
