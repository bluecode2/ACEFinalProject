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
		location.href = "generalHoliday.do";
	}
	
	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
// 		$(".datepicker").attr("data-date-start-date", new Date());
	});
</script>
</head>
<body>
	<html:form action="/generalHoliday" method="post">
		<html:hidden name="generalHolidayForm" property="task" />
		<html:hidden name="generalHolidayForm" property="isAdd" />
		<html:hidden name="generalHolidayForm" property="genHolidayBean.genHolidayId" />
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		

		<div class="container">
			<div class="divContent">
				<table>
					<!-- <tr>
						<td>General Holiday ID</td>
						<td><input type="text" id="txtGoToPage" class="form-control"/></td>
					</tr> -->
					<tr>
						<td>General Holiday Name</td>
						<td>
							<html:text styleClass="form-control" styleId="txtGenHolName" name="generalHolidayForm" property="genHolidayBean.genHolidayName"></html:text>
						</td>
					</tr>
					<tr>
						<td>General Holiday Date</td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtGenHolDate"
								name="generalHolidayForm" property="genHolidayBean.genHolidayDateInString"></html:text>
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
