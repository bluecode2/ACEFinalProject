<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department List</title>
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
			if (confirm("Are you sure want to delete Department " + nama + "?")) {
				document.forms[0].submit();
			}
		} else {
			document.forms[0].submit();
		}

	}
</script>
</head>
<body>
	<html:form action="/department" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="departmentForm" property="task" />
		<html:hidden name="departmentForm" property="selectedId" />
		<html:hidden name="departmentForm" property="currSearchField" />
		<html:hidden name="departmentForm" property="currSearchValue" />
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="departmentForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="deptCode">Dept. Code</option>
								<option value="deptName">Dept. Name</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="departmentForm" property="searchValue"
								styleClass="form-control" /></td>
						<td style="padding-left: 15px"><button type="button"
								onclick="search();" id="btnSearch" class="btn btn-raised btn-info btn-icon"
								title="Search">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button></td>
					</tr>
				</table>
			</div>
					<logic:notEmpty name="validationMessage">
						<br/>
							<div class="col-md-6 alert alert-dismissable alert-<bean:write name="validationType" />" role="alert">
								<button type="button" class="close" data-dismiss="alert">�</button>
								<strong><bean:write name="validationMessage" /></strong>
							</div>
					</logic:notEmpty>
			<div class="divContent">
				<table class="table table-striped table-bordered table-hover"
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<thead >
						<tr class="panel panel-info">
							<td width="150px" class="align-center">Department Code</td>
							<td class="align-center">Department Name</td>
							<td class="align-center">Department Head</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="departmentForm" property="arrList">
							<logic:iterate id="reg" name="departmentForm" property="arrList">
								<tr>
									<td><bean:write name="reg" property="deptCode" /></td>
									<td><bean:write name="reg" property="deptName" /></td>
									<td><bean:write name="reg" property="deptHeadName" /></td>
									<td align="center"><a class="text-success" href="#"
										onclick="actionForm('edit','<bean:write name="reg" property="deptId" />');"
										title="Edit"><span class="glyphicon glyphicon-pencil"
											aria-hidden="true"></span></a> &nbsp; <a href="#" class="text-danger" 
										onclick="actionForm('delete','<bean:write name="reg" property="deptId" />','<bean:write name="reg" property="deptName" />');"
										title="Delete"><span class="glyphicon glyphicon-trash"
											aria-hidden="true"></span></a></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="departmentForm" property="arrList">
							<tr>
								<td colspan="4" align="center" style="padding: 10px">No
									Data Found</td>
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
