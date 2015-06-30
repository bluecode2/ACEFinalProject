<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>General Holiday</title>

<script type="text/javascript">
	function onBtnSaveClick(){
		if(validateForm()){
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		}
	}
	
	function onBtnBackClick(){
		location.href = "projectRole.do";
	}
	
	function validateForm(){
		var projRoleCode	= document.getElementById("txtProjectRoleCode").value;
		var projRoleName 	= document.getElementById("txtProjectRoleName").value;
		
		var str = "";
		var isValid = true;
		
		if(projRoleCode.trim() == '') {
			str+= "<li>Project Role Code can not be empty!</li>";
			isValid = false;
		}
		else if(projRoleCode.length > 11) {
			str+= "<li>Project Role Code can not be more than 10 characters!</li>";
			isValid = false;
		}
		
		if(projRoleName.trim() == '') {
			str+= "<li>Project Role Name can not be empty!</li>";
			isValid = false;
		}
		else if(projRoleName.length > 26) {
			str+= "<li>Project Role Name can not be more than 25 characters!</li>";
			isValid = false;
		}
		
		if(!isValid){
			document.getElementById('errorContent').innerHTML = str;
			document.getElementById("divError").style.display = "block";
		}
		
		return isValid;
	}
</script>
</head>
<body>
	<html:form action="/projectRole" method="post">
		<html:hidden name="projectRoleForm" property="task" />
		<html:hidden name="projectRoleForm" property="isAdd" />
		<html:hidden name="projectRoleForm" property="selectedId" />
		<html:hidden name="projectRoleForm" property="projectRoleBean.projectRoleId" />
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent">
				<div class="text-danger" id="divError" style="display: none">
					Save failed!
					<ul id="errorContent"></ul>
				</div>
				<table>
					<!-- <tr>
						<td>General Holiday ID</td>
						<td><input type="text" id="txtGoToPage" class="form-control"/></td>
					</tr> -->
					<tr>
						<td>Project Role Code</td>
						<td>
							<html:text styleClass="form-control" styleId="txtProjectRoleCode" name="projectRoleForm" property="projectRoleBean.projectRoleCode"></html:text>
						</td>
					</tr>
					<tr>
						<td>Project Role Name</td>
						<td>
							<html:text styleClass="form-control" styleId="txtProjectRoleName" name="projectRoleForm" property="projectRoleBean.projectRoleName"></html:text>
						</td>
					</tr>
					<%-- <tr>
						<td>Is Generated</td>
						<td>
							<html:radio name="generalHolidayForm" property="genHolidayBean.isGenerated" value="1">Yes</html:radio>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<html:radio name="generalHolidayForm" property="genHolidayBean.isGenerated" value="0">No</html:radio>
						</td>
					</tr> --%>
				</table>
				<%-- <jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include> --%>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>