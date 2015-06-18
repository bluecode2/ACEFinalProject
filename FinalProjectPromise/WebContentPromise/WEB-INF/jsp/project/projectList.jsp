<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project List</title>

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
						<td style="padding-left: 15px;">
						<html:select
								name="userForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="projectCode">Employee Name</option>
								<option value="projectName">Username</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="userForm" property="searchValue"
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
							
							<td>Employee Name</td>
							<td>Username</td>
							<td>User Role Name</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="userForm" property="listOfUser">
							<logic:iterate id="reg" name="userForm" property="listOfUser">
								<tr>
									
									<td><bean:write name="reg" property="employeeName" /></td>
									<td><bean:write name="reg" property="username" /></td>
									<td><bean:write name="reg" property="userRoleName" /></td>
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
								<td colspan="10" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		<html:hidden name="userForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>