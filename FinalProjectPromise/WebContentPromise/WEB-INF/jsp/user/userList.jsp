<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DepartmentList</title>
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

	function actionForm(task, id, nama) {

		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;

		if (task == "delete") {
			if (confirm("Are you sure want to delete Department " + nama)) {
				document.forms[0].submit();
			}
		} else {
			document.forms[0].submit();
		}

	}
</script>
</head>
<body>
	<html:form action="/users" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="userForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="uBean.userId">User ID</option>
								<option value="uBean.userRoleId">User Role ID</option>
								<option value="uBean.employeeId">Employee ID</option>
								<option value="uBean.username">Username</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="departmentForm" property="searchValue"
								styleClass="form-control" /></td>
						<td style="padding-left: 15px"><button type="button"
								onclick="search();" id="btnSearch" class="btn btn-info btn-icon"
								title="Back">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button></td>
					</tr>
				</table>
			</div>

			<div class="divContent">
				<table class="table table-striped table-bordered table-hover"
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td>User ID</td>
							<td>User Role ID</td>
							<td>Employee ID</td>
							<td>Username</td>
							<td>Is Active</td>
							<td>Created By</td>
							<td>Created Date</td>
							<td>Updated By</td>
							<td>Updated Date</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="userForm" property="listOfUser">
							<logic:iterate id="reg" name="departmentForm" property="listOfUser">
								<tr>
									<td><bean:write name="reg" property="userId" /></td>
									<td><bean:write name="reg" property="userRoleId" /></td>
									<td><bean:write name="reg" property="employeeId" /></td>
									<td><bean:write name="reg" property="username" /></td>
									<td><bean:write name="reg" property="isActive" /></td>
									<td><bean:write name="reg" property="createBy" /></td>
									<td><bean:write name="reg" property="createDate" /></td>
									<td><bean:write name="reg" property="updateBy" /></td>
									<td><bean:write name="reg" property="updateDate" /></td>
									<td align="center">
									<a href="#"	onclick="actionForm('edit','<bean:write name="reg" property="userId" />');"
										title="Edit"><span class="glyphicon glyphicon-pencil"
											aria-hidden="true"></span></a> &nbsp; 
									<a href="#" onclick="actionForm('delete','<bean:write name="reg" property="userId" />','<bean:write name="reg" property="username" />');"
										title="Delete"><span class="glyphicon glyphicon-trash"
											aria-hidden="true"></span></a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="userForm" property="listOfUser">
							<tr>
								<td colspan="4" align="center" style="padding: 10px">No
									Data Not Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		<html:hidden name="departmentForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>