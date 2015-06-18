<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Role</title>

<script type="text/javascript">
	function onBtnAddClick(){
		document.forms[0].task.value = 'add';
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
			if (confirm("Are u sure want to delete General Holiday " + nama + " ?")) {
			    document.forms[0].submit();
			}	
		} 
		else {
			document.forms[0].submit();
		}
	}
</script>
</head>
<body>
	<html:form action="/userRole" method="post">
		<html:hidden name="userRoleForm" property="task" />
		<html:hidden name="userRoleForm" property="selectedId"/>
		
		<html:hidden property="currSearchValue" name="userRoleForm"/>
		<html:hidden property="currSearchField" name="userRoleForm"/>
		<html:hidden property="currPage" name="userRoleForm"/>
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;">
							<html:select name="userRoleForm" property="searchField" styleId="selSearchField" styleClass="form-control">
								<option value="userRoleCode">User Role Code</option>
								<option value="userRoleName">User Role Name</option>
							</html:select>
						</td>
						<td style="padding-left: 15px">
							<html:text
								name="userRoleForm" property="searchValue" styleClass="form-control">
							</html:text>
						</td>
						<td style="padding-left: 15px">
							<button type="button"
								onclick="search();" id="btnSearch" class="btn btn-info btn-icon"
								title="Search">
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
							<td>User Role Code</td>
							<td>User Role Name</td>
				
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="userRoleForm" property="arrList">
							<logic:iterate id="reg" name="userRoleForm" property="arrList">
								<tr>
									<td><bean:write name="reg" property="userRoleCode" /></td>
									<td><bean:write name="reg" property="userRoleName" /></td>
				
									<td align="center">
										<a class="text-success" href="#"
										onclick="actionForm('edit','<bean:write name="reg" property="userRoleId" />');"
										title="Edit"><span class="glyphicon glyphicon-pencil"
											aria-hidden="true"></span></a>
										&nbsp; 
										<a href="#" class="text-danger" 
										onclick="actionForm('delete','<bean:write name="reg" property="userRoleId" />','<bean:write name="reg" property="userRoleName" />');"
										title="Delete"><span class="glyphicon glyphicon-trash"
											aria-hidden="true"></span></a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="userRoleForm" property="arrList">
							<tr>
								<td colspan="4" align="center" style="padding: 10px">No Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		<html:hidden name="userRoleForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>