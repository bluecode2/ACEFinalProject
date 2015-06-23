<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Task List</title>
<script src="js/jquery.js"></script>

<script type="text/javascript">
function onBtnAddClick(){
	document.forms[0].task.value = "add";
	document.forms[0].submit();
}

function search() {
	document.forms[0].currSearchField.value = document.forms[0].searchField.value;
	document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

	changePage(1);
}
	$(document).ready(function() {
		$('.firstBtn').each(function() {

			var taskCode = $(this).closest('tr').find('.hdTaskStatus').val();
			if (taskCode == 'TA_STAT_02') {
				$(this).addClass('glyphicon glyphicon-pencil');
				$(this).attr("title","Edit Task");
			} 		
			else if (taskCode == 'TA_STAT_04') {
				$(this).addClass('glyphicon glyphicon-ok');
				$(this).attr("title","Approve Task");
			}

			else
				$(this).hide();
		});
		$('.secondBtn').each(function() {
			var taskCode = $(this).closest('tr').find('.hdTaskStatus').val();
			if (taskCode == 'TA_STAT_02') {
				$(this).addClass('glyphicon glyphicon-remove');
				$(this).attr("title","Cancel Task");
			} 
			else if (taskCode == 'TA_STAT_04') {
				$(this).addClass('glyphicon glyphicon-remove');
				$(this).attr("title","Decline Task");
			}

			else
				$(this).hide();
		});
		
		$('.secondBtn').on('click',function(){
			var taskId = $(this).closest('tr').find('.hdTaskId').val();
			var taskName = $(this).closest('tr').find('.hdTaskName').val();
			var taskStatus = $(this).closest('tr').find('.hdTaskStatus').val(); 
			$('#txtValueTaskId').val(taskId);
			$('#txtValueTaskStatus').val(taskStatus);
			$('#txtValueTaskName').val(taskName);
			
			$('#addRemarks').modal();
			
		});
		$('.linkDesc').on('click',function(){
			var taskDesc = $(this).closest('tr').find('.hdTaskDesc').val();
			var taskName = $(this).closest('tr').find('.hdTaskName').val();
			$('#txtSearchFieldDesc').val(taskDesc);
			$('#txtValueTaskNameDesc').val(taskName);
			
			$('#showDesc').modal();
			
		});
		$('.goInsert').on('click',function(){
			document.forms[0].selectedId.value = $('#txtValueTaskId').val(); //task ID
			document.forms[0].task.value = "secondEdit";
			document.forms[0].remarksRecord.value = $('#selSearchFieldRemark').val();
			var taskStatus = $('#txtValueTaskStatus').val();
			alert(taskStatus);
			if(taskStatus == 'TA_STAT_02'){ //Function for cancel task; change status and remarks
				document.forms[0].selectedEdit.value = "0";
				
			}
			else if(taskStatus == 'TA_STAT_04'){ //Function for decline task; change status and remarks
				document.forms[0].selectedEdit.value = "1";
				
			}
			
			document.forms[0].submit();
		});
	});
	function changeStatusFirstBtn(taskId, taskStatus) {
		document.forms[0].task.value = "firstEdit";
		document.forms[0].selectedId.value = taskId;

		if(taskStatus == 'TA_STAT_02'){ //Function for edit task; change name and desc
			document.forms[0].selectedEdit.value = "0";
			
		}
		else if(taskStatus == 'TA_STAT_04'){ //Function for approve task; change status 
			document.forms[0].selectedEdit.value = "1";
			
		}
		
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<html:form action="/assignTask" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden property="task" name="assignTaskForm" />
		<html:hidden property="selectedEdit" name="assignTaskForm"/>
		<html:hidden property="currSearchField" name="assignTaskForm" />
		<html:hidden property="currSearchValue" name="assignTaskForm" />
		<html:hidden property="currPage" name="assignTaskForm" />
		<html:hidden property="selectedId" name="assignTaskForm"/>
		<html:hidden property="remarksRecord" name="assignTaskForm"/>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="assignTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<html:option value="">-- All --</html:option>
								<option value="taskName">task Name</option>
								<option value="taskDesc">task DESC</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="assignTaskForm" property="searchValue"
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
							<td>Estimate Start Date</td>
							<td>Estimate End Date</td>
							
							<td>Task Progress</td>
							<td>Task Status</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="assignTaskForm" property="arrList">
							<logic:iterate id="reg" name="assignTaskForm" property="arrList">
								<tr>
									<td>
										<html:hidden property="taskDesc" name="reg" styleClass="hdTaskDesc"/>
										<html:hidden property="taskId" name="reg" styleClass="hdTaskId"/>
										<html:hidden property="taskName" name="reg" styleClass="hdTaskName"/>
										<a href="#" class="text-info linkDesc">
											<bean:write name="reg" property="taskName" />
										</a>
									</td>
									<td><bean:write name="reg" property="assignedToName" /></td>
									<td><bean:write name="reg" property="estStartDateInString" /></td>
									<td><bean:write name="reg" property="estEndDateInString" /></td>
									<td><bean:write name="reg" property="taskProgress" /></td>
									<td><html:hidden name="reg" property="taskStatus" styleClass="hdTaskStatus" /> 
										<bean:write name="reg" property="taskStatusName" />
									</td>
									<td align="center">
										<a class="text-success firstBtn" href="#" id="tes"
										onclick="changeStatusFirstBtn('<bean:write name="reg" property="taskId" />','<bean:write name="reg" property="taskStatus"/>')">
											<span aria-hidden="true"></span>
										</a>
										&nbsp; 
										<a href="#" class="text-info secondBtn">
											<span aria-hidden="true"></span>
										</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="assignTaskForm" property="arrList">
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
		<div class="modal fade" id="addRemarks" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
										<td style="padding-left: 15px">
										<input type="hidden" id="txtValueTaskId" />
										<input type="hidden" id="txtValueTaskStatus" />
										<input type="text" id="txtValueTaskName" class="form-control" disabled="disabled" />
											
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px">
											Remarks 
										</td>
										<td style="padding-left: 15px">
											<textarea rows="3" cols="3" class="form-control"  id="selSearchFieldRemark"></textarea>
										</button></td>
									</tr>
								</table>
								<center>
								<input type="button" class="goInsert btn btn-sm btn-info" value="Save">

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
		<div class="modal fade" id="showDesc" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
										<td style="padding-left: 15px">
											<input type="text" id="txtValueTaskNameDesc" class="form-control" disabled="disabled" />
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px">
											Task Desc 
										</td>
										<td style="padding-left: 15px">
											<textarea rows="3" cols="3" class="form-control"  id="txtSearchFieldDesc" disabled="disabled"></textarea>
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