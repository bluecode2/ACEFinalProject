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
<script type="text/javascript">
	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}

	function actionForm(task, id) {
		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;
		document.forms[0].propTo.value = document.getElementById("propTo").text;
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
</script>
</head>
<body>
	<html:form action="/approveTask" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="approveTaskForm" property="task" />
		<html:hidden name="approveTaskForm" property="selectedId" />
		<html:hidden name="approveTaskForm" property="currSearchField" />
		<html:hidden name="approveTaskForm" property="currSearchValue" />
		<html:hidden name="approveTaskForm" property="propTo"/>
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="approveTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="propTaskName">Task Name</option>
								<option value="propBy">Proposed By</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="approveTaskForm" property="searchValue"
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
							<td>Proposed By</td>
							<td>Assign To</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="approveTaskForm" property="arrList">
							<logic:iterate id="reg" name="approveTaskForm" property="arrList">
								<tr>
									<td><a href="#" class="text-info" 
									onclick="getTaskDesc('<bean:write name="reg" property="propTaskDesc" />');" data-target="taskDesc">
									<bean:write name="reg" property="propTaskName" /></a>
									<td><bean:write name="reg" property="estStartDateInString" /></td>
									<td><bean:write name="reg" property="estEndDateInString" /></td>
									<td><bean:write name="reg" property="propByName" /></td>
									<td><a href="#" class="text-info" id="propTo"
									onclick="getEmpList('<bean:write name="reg" property="propTaskDesc" />');" data-target="empList">
									list</a></td>
									<td align="center"><a class="text-success" href="#"
										onclick="actionForm('approve','<bean:write name="reg" property="propTaskId" />');"
										title="Approve"><span class="glyphicon glyphicon-ok"
											aria-hidden="true"></span></a> &nbsp; <a href="#"
										class="text-danger"
										onclick="actionForm('decline','<bean:write name="reg" property="propTaskId" />');"
										title="Decline"><span class="glyphicon glyphicon-remove"
											aria-hidden="true"></span></a></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="approveTaskForm" property="arrList">
							<tr>
								<td colspan="6" align="center" style="padding: 10px">No
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
		
		<div class="modal fade" id="empList" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
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
						<hr />
						<br>
						<p id="txtTaskDesc"></p>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		<html:hidden name="approveTaskForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>