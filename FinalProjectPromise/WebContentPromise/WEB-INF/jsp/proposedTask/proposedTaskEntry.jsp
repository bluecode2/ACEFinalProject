<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proposed Task Entry</title>

<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
	function onBtnSaveClick(){
		if(validateForm()){
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		}
	}
	
	function onBtnBackClick(){
		location.href = "proposedTask.do";
	}
	
	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
// 		$(".datepicker").attr("data-date-start-date", new Date());
	});
	
	function validateForm(){
		
		var taskName	= document.getElementById("txtTaskName").value;
		var taskDesc 	= document.getElementById("txtTaskDesc").value;
		var estStart 	= document.getElementById("txtEstStartDate").value;
		var estEnd 		= document.getElementById("txtEstEndDate").value;
		
		var str = "";
		var isValid = true;
		
		if(estEnd<estStart){
			str+= "<li>Estimate Start Date of Task must be smaller than Estimate Start Date Project!\n";
			isValid = false;
		}
		
		if(taskName.trim() == '') {
			str+= "<li>Task Name can not be empty!</li>";
			isValid = false;
		}
		else if(taskName.length > 26) {
			str+= "<li>Task Name can not be more than 25 characters!</li>";
			isValid = false;
		}
		
		if(taskDesc.trim() == '') {
			str+= "<li>Task Description can not be empty!\</li>";
			isValid = false;
		}
		else if(taskDesc.length > 201) {
			str+= "<li>Task Description can not be more than 200 characters!</li>";
			isValid = false;
		}
		
		if(estStart.trim() == '') {
			str+= "<li>Estimate Start Date can not be empty!</li>";
			isValid = false;
		}
		
		if(estEnd.trim() == '') {
			str+= "<li>Estimate End Date can not be empty!</li>";
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
	<html:form action="/proposedTask" method="post">
		<html:hidden name="proposedTaskForm" property="task" />
		<html:hidden name="proposedTaskForm" property="isAdd" />
		<html:hidden name="proposedTaskForm" property="bean.propTaskId" />
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent form-group has-info">
				<div class="text-danger" id="divError" style="display: none">
					Save failed!
					<ul id="errorContent"></ul>
				</div>
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
							<html:text styleClass="form-control" styleId="txtTaskName" name="proposedTaskForm" property="bean.propTaskName"></html:text>
						</td>
					</tr>
					<tr>
						<td>Task Desc</td>
						<td>
							<html:textarea styleClass="form-control" styleId="txtTaskDesc" name="proposedTaskForm" property="bean.propTaskDesc"></html:textarea>
						</td>
					</tr>
					<tr>
						<td>Estimate Start Date</td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtEstStartDate"
								name="proposedTaskForm" property="bean.estStartDateInString" style="width: 150px"></html:text>
						</td>
					</tr>
					<tr>
						<td>Estimate End Date</td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtEstEndDate"
								name="proposedTaskForm" property="bean.estEndDateInString" style="width: 150px"></html:text>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>Is Generated</td> -->
<!-- 						<td> -->
<%-- 							<html:radio name="generalHolidayForm" property="genHolidayBean.isGenerated" value="1">Yes</html:radio> --%>
<!-- 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  -->
<%-- 							<html:radio name="generalHolidayForm" property="genHolidayBean.isGenerated" value="0">No</html:radio> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
				<%-- <jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include> --%>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>
