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
	$(document).ready(
		function() {
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
	});

	function onBtnAddClick() {
		var allowAdd = document.forms[0].allowAdd.value;
		if (allowAdd == "true") {
			document.forms[0].task.value = "add";
			document.forms[0].submit();
		} else {
			alert("You don't have a supervisor to proposed an independent task!");
		}

	}

	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}

	function actionForm(task, id, nama) {

		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;

		if (task == "delete") {
			if (confirm("Are you sure want to delete proposed Task " + nama)) {
				document.forms[0].submit();
			}
		} else {
			document.forms[0].submit();
		}
	}
</script>
</head>
<body>
	<html:form action="/proposedTask" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="proposedTaskForm" property="task" />
		<html:hidden name="proposedTaskForm" property="allowAdd" />
		<html:hidden name="proposedTaskForm" property="selectedId" />
		<html:hidden name="proposedTaskForm" property="currSearchField" />
		<html:hidden name="proposedTaskForm" property="currSearchValue" />
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="proposedTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="propTaskName">Task Name</option>
								<option value="propStatus">Status</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="proposedTaskForm" property="searchValue"
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
							<td class="align-center">Estimate Date</td>
							<td class="align-center">Proposed To</td>
							<td class="align-center">Status</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="proposedTaskForm" property="arrList">
							<logic:iterate id="reg" name="proposedTaskForm"
								property="arrList">
								<tr>
									<td>
										<html:hidden property="remarks" name="reg" styleClass="hdRemarks"/>
										<html:hidden property="propTaskName" name="reg" styleClass="hdTaskName"/>
										<bean:write name="reg" property="propTaskName" />
									</td>
									<td align="center">
							          <logic:notEmpty name="reg" property="estStartDateInString">
							           <bean:write name="reg" property="estStartDateDisplay" /> to 
							            <logic:notEmpty name="reg" property="estEndDateInString">
							             <bean:write name="reg" property="estEndDateDisplay" />
							            </logic:notEmpty>
							            <logic:empty  name="reg" property="estEndDateInString">
							             -
							            </logic:empty>
							            <br/>(<bean:write name="reg" property="estMainDays" /> main days)
							          </logic:notEmpty>
							          <logic:empty name="reg" property="estStartDateInString">
							           -
							          </logic:empty>
							         </td>
									<td><bean:write name="reg" property="propToName" /></td>
									<td>
										<bean:write name="reg" property="propStatusName" /> 
										<logic:notEqual name="reg" property="remarks" value="">
											<br/><a href="#" class="text-info lnkRemarks">Remarks</a>
										</logic:notEqual>
									</td>
									<td align="center">
										<logic:equal name="reg" property="propStatus" value="TA_STAT_01">
											<a class="text-success" href="#" onclick="actionForm('edit','<bean:write name="reg" property="propTaskId" />');" title="Edit">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
											</a> &nbsp; 
												<a href="#"
												class="text-danger"
												onclick="actionForm('delete','<bean:write name="reg" property="propTaskId" />','<bean:write name="reg" property="propTaskName" />');"
												title="Delete"><span class="glyphicon glyphicon-trash"
												aria-hidden="true"></span></a>
										</logic:equal></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="proposedTaskForm" property="arrList">
							<tr>
								<td colspan="5" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		<html:hidden name="proposedTaskForm" property="currPage" />
		
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
						<h4 class="modal-title">Remarks Description</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<table width="100%">
								<tr>
									<td style="padding-left: 15px" valign="top">Remarks Name</td>
									<td style="padding-left: 15px"><input type="text"
										id="txtValueTaskNameRemarks" class="form-control"
										disabled="disabled" /></td>

								</tr>
								<tr>
									<td style="padding-left: 15px" valign="top">Remarks Description</td>
									<td style="padding-left: 15px"><textarea rows="3" cols="3"
											class="form-control" id="txtRemarks"
											disabled="disabled"></textarea>
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
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>