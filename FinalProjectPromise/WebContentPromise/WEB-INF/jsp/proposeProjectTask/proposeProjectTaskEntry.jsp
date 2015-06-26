<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proposed Project Task Entry</title>

<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
	function onBtnSaveClick(){
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}
	
	function onBtnBackClick(){
		location.href = "propProjTask.do";
	}
	
	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
// 		$(".datepicker").attr("data-date-start-date", new Date());
	});
</script>
</head>
<body>
	<html:form action="/propProjTask" method="post">
		<html:hidden name="proposeProjectTaskForm" property="task" />
		<html:hidden name="proposeProjectTaskForm" property="isAdd" />
		<html:hidden name="proposeProjectTaskForm" property="bean.propTaskId" />
		
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" />
						<col />
					</colgroup>
					<tr>
						<td>Project Name</td>
						<td><html:text property="projectName" name="getProject" disabled="true" styleClass="form-control"></html:text></td>
					</tr>
					<tr>
						<td>Manager Name</td>
						<td><html:text property="employeeName" name="getProject" disabled="true" styleClass="form-control"></html:text></td>
					</tr>
					<tr>
						<td>Estimate Date</td>
						<td><html:text property="estStartDateInString" name="getProject" disabled="true" styleClass="form-control"></html:text>
							&nbsp;To&nbsp;
							<html:text property="estEndDateInString" name="getProject" disabled="true" styleClass="form-control"></html:text></td>
					</tr>
				</table>
				<table width="50%">
					<colgroup>
						<col width="30%" />
						<col />
					</colgroup>
					<!-- <tr>
						<td>General Holiday ID</td>
						<td><input type="text" id="txtGoToPage" class="form-control"/></td>
					</tr> -->
					<tr>
						<td>Task Name</td>
						<td>
							<html:text styleClass="form-control" styleId="txtTaskName" name="proposeProjectTaskForm" property="bean.propTaskName"></html:text>
						</td>
					</tr>
					<tr>
						<td>Task Desc</td>
						<td>
							<html:textarea styleClass="form-control" styleId="txtTaskDesc" name="proposeProjectTaskForm" property="bean.propTaskDesc"></html:textarea>
						</td>
					</tr>
					<tr>
						<td>Estimate Start Date</td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtEstStartDate"
								name="proposeProjectTaskForm" property="bean.estStartDateInString" style="width: 150px"></html:text>
						</td>
					</tr>
					<tr>
						<td>Estimate End Date</td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtEstEndDate"
								name="proposeProjectTaskForm" property="bean.estEndDateInString" style="width: 150px"></html:text>
						</td>
					</tr>
				</table>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>
