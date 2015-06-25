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
	function onBtnAddClick() {
		var allowAdd = document.forms[0].allowAdd.value;
		if (allowAdd == "true") {
			document.forms[0].task.value = "add";
			document.forms[0].submit();
		} else {
			alert("You don't have a supervisor to proposed an Project task!");
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
			if (confirm("Are you sure want to delete proposed Project Task " + nama+" ?")) {
				document.forms[0].submit();
			}
		} else {
			document.forms[0].submit();
		}
	}
</script>
</head>
<body>
	<html:form action="/propProjTask" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="proposeProjectTaskForm" property="task" />
		<html:hidden name="proposeProjectTaskForm" property="allowAdd" />
		<html:hidden name="proposeProjectTaskForm" property="selectedId" />
		<html:hidden name="proposeProjectTaskForm" property="currSearchField" />
		<html:hidden name="proposeProjectTaskForm" property="currSearchValue" />
		<html:hidden name="proposeProjectTaskForm" property="bean.projectId"/>
		
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="proposeProjectTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="propTaskName">Task Name</option>
								<option value="propStatus">Status</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="proposeProjectTaskForm" property="searchValue"
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
							<td>Project Name</td>
							<td>Task Name</td>
							<td>Estimate</td>
							<td>Proposed To</td>
							<td>Status</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="proposeProjectTaskForm" property="arrList">
							<logic:iterate id="reg" name="proposeProjectTaskForm"
								property="arrList">
								<tr>
									<td><bean:write name="reg" property="projectName" />
									<td><bean:write name="reg" property="propTaskName" />
									<td>
										<bean:write name="reg" property="estStartDateInString" />
										&nbsp;To&nbsp;
										<bean:write name="reg" property="estEndDateInString" />
									</td>
									<td><bean:write name="reg" property="propToName" /></td>
									<td><bean:write name="reg" property="propStatusName" /></td>
									<td align="center"><logic:equal name="reg"
											property="propStatus" value="TA_STAT_01">
											<a class="text-success" href="#"
												onclick="actionForm('edit','<bean:write name="reg" property="propTaskId" />');"
												title="Edit"><span class="glyphicon glyphicon-pencil"
												aria-hidden="true"></span></a> &nbsp; <a href="#"
												class="text-danger"
												onclick="actionForm('delete','<bean:write name="reg" property="propTaskId" />','<bean:write name="reg" property="propTaskName" />');"
												title="Delete"><span class="glyphicon glyphicon-trash"
												aria-hidden="true"></span></a>
										</logic:equal></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="proposeProjectTaskForm" property="arrList">
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
		<html:hidden name="proposeProjectTaskForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>