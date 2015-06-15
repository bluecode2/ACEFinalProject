<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function onBtnAddClick() {
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
		  document.forms[0].empId.value = id;

		  if (task == "delete") {
		   if (confirm("Are u sure want to delete General Code " + nama + " ?")) {
		    document.forms[0].submit();
		   }
		  } else {
		   document.forms[0].submit();
		  }

		 }
</script>
</head>
<body>
	<html:form action="/generalCode" method="post">

		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden property="task" name="generalCodeForm" />
		<html:hidden property="currColumn" name="generalCodeForm"/>
		<html:hidden property="currInput" name="generalCodeForm"/>
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;">
							<html:select name="generalCodeForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="genCodeId">Gen. Code ID</option>
								<option value="genCodeCaption">Gen. Code Caption</option>
								<option value="parentId">Parent ID</option>
								<option value="isActive">Status Active</option>
							</html:select>
						</td>
						<td style="padding-left: 15px">
							<html:text name="generalCodeForm" property="searchValue" styleClass="form-control" />
						</td>
						<td style="padding-left: 15px">
							<button type="button" onclick="search();" id="btnSearch"
								class="btn btn-info btn-icon" title="Back">
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
							<td>General Code Index</td>
							<td>General Code Caption</td>
							<td>Parent ID</td>
							<td>is Active ?</td>						
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="genCodeForm" property="arrCodeBean">
							<logic:iterate id="reg" name="genCodeForm" property="arrCodeBean">
								<tr>
									<td><bean:write name="reg" property="genCodeIndex" /></td>
									<td><bean:write name="reg" property="genCodeCaption" /></td>
									<td><bean:write name="reg" property="parentId" /></td>
									<td><bean:write name="reg" property="isActive" /></td>
									
									<td align="center"><a href="#"
										onclick="actionForm'<bean:write name="reg" property="deptId" />');"
										title="Edit">Edit</a> <a href="#"
										onclick="deleteDepartment('<bean:write name="reg" property="deptId" />','<bean:write name="reg" property="deptName" />');"
										title="Delete">Delete</a></td>
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

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>