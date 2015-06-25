<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Role Entry</title>

<script type="text/javascript">
	function onBtnSaveClick() {
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}

	function onBtnBackClick() {
		location.href = "userRole.do";
	}
</script>
</head>
<body>
	<html:form action="/userRole" method="post">
		<html:hidden name="userRoleForm" property="task" />
		<html:hidden name="userRoleForm" property="isAdd" />
		<html:hidden name="userRoleForm" property="userRoleBean.userRoleId" />

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" class="tdLabel" />
						<col />
					</colgroup>
					<tr>
						<td class="tdLabel" align="right">User Role Code</td>
						<td><html:text styleClass="form-control"
								styleId="txtUserRoleCode" name="userRoleForm"
								property="userRoleBean.userRoleCode"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right">User Role Name</td>
						<td><html:text styleClass="form-control"
								styleId="txtUserRoleName" name="userRoleForm"
								property="userRoleBean.userRoleName"></html:text></td>
					</tr>
					<tr></tr>
				</table>
			</div>
		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>
>
