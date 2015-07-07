<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<script type="text/javascript">
	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}
</script>
</head>
<body>
	<html:form action="/menu" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden property="task" name="menuForm" />
		<html:hidden property="currSearchField" name="menuForm" />
		<html:hidden property="currSearchValue" name="menuForm" />
		<html:hidden property="currPage" name="menuForm" />

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select name="menuForm"
								property="searchField" styleId="selSearchField"
								styleClass="form-control">
								<option value="menuCode">Menu Code</option>
								<option value="menuCaption">Menu Caption</option>
								<option value="parentName">ParentName</option>
								<option value="menuCrud">Menu Crud</option>
								<option value="menuUrl">Menu URL</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text name="menuForm"
								property="searchValue" styleClass="form-control" /></td>
						<td style="padding-left: 15px">
							<button type="button" onclick="search();" id="btnSearch"
								class="btn btn-raised btn-info btn-icon" title="Search">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</td>
					</tr>
				</table>
			</div>
			<logic:notEmpty name="validationMessage">
				<br/>
				<div class="col-md-6 alert alert-dismissable alert-<bean:write name="validationType" />" role="alert">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong><bean:write name="validationMessage" /></strong>
				</div>
			</logic:notEmpty>
			<div class="divContent">
				<table class="table table-striped table-bordered table-hover" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<!-- <td class="align-center">Index</td> -->
							<td class="align-center">Menu Code</td>
							<td class="align-center">Menu Caption</td>
							<td class="align-center">Parent Name</td>
							<td class="align-center">Menu CRUD</td>
							<td class="align-center">Menu URL</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="menuForm" property="arrList">
							<logic:iterate id="reg" name="menuForm" property="arrList">
								<tr>
									<%-- <td><bean:write name="reg" property="menuIndex" /></td> --%>
									<td><bean:write name="reg" property="menuCode" /></td>
									<td><bean:write name="reg" property="menuCaption" /></td>
									<td><bean:write name="reg" property="parentName" /></td>
									<td><bean:write name="reg" property="menuCrud" /></td>
									<td><bean:write name="reg" property="menuUrl" /></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="menuForm" property="arrList">
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

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>

</body>
</html>