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

<link href="css/datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
	function onBtnSaveClick(){
		if(validateForm()){
			var str = "";
			$(".checkDays:checked").each(function(){
				if(str != "") str += ",";
				str += $(this).val();
			});
			
			document.forms[0].checkDays.value = str;
			document.forms[0].task.value = 'generate';
			document.forms[0].submit();
		}
	}
	
	function onBtnBackClick(){
		location.href = "generalHoliday.do";
	}
	
	function validateForm(){
		var genHolStartDate	= document.getElementById("txtGenStartDate").value;
		var genHolEndDate 	= document.getElementById("txtGenEndDate").value;
		
		var str = "";
		var isValid = true;
		
		if(genHolStartDate.trim() == '') {
			str+= "<li>Start Date can not be empty!</li>";
			isValid = false;
		}
		
		if(genHolEndDate.trim() == '') {
			str+= "<li>End Date can not be empty!</li>";
			isValid = false;
		}
		
		if(!isValid){
			document.getElementById('errorContent').innerHTML = str;
			document.getElementById("divError").style.display = "block";
		}
		
		return isValid;
	}
	
	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
	});
</script>
</head>
<body>
	<html:form action="/generalHoliday" method="post">
		<html:hidden name="generalHolidayForm" property="task" />
		<html:hidden name="generalHolidayForm" property="isAdd" />
		<html:hidden name="generalHolidayForm" property="genHolidayBean.genHolidayId" />
		<html:hidden property="checkDays" name="generalHolidayForm"/>
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
					<tr>
						<td valign="top">Start Date</td>
						<td><html:text styleClass="form-control datepicker"
								styleId="txtGenStartDate" name="generalHolidayForm"
								property="startDateInString"></html:text></td>
					</tr>
					<tr>
						<td valign="top">End Date</td>
						<td><html:text styleClass="form-control datepicker"
								styleId="txtGenEndDate" name="generalHolidayForm"
								property="endDateInString"></html:text></td>
					</tr>
					<tr>
						<td valign="top">Weekend</td>
						<td>
							<table>
								<tr>
									<td><input type="checkbox" name="checkDays1" value="1"
										class="checkDays">Monday</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="checkDays2" value="2"
										class="checkDays">Tuesday</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="checkDays3" value="3"
										class="checkDays">Wednesday</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="checkDays4" value="4"
										class="checkDays">Thursday</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="checkDays5" value="5"
										class="checkDays">Friday</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="checkDays6" value="6"
										class="checkDays">Saturday</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="checkDays7" value="7"
										class="checkDays">Sunday</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>
