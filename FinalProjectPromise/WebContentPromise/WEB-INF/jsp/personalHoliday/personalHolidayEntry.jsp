<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Holiday</title>

<script type="text/javascript">
	function onBtnSaveClick(){
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}
	
	function onBtnBackClick(){
		location.href = "personalHoliday.do";
	}
</script>
</head>
<body>
	<html:form action="/personalHoliday" method="post">
		<html:hidden name="personalHolidayForm" property="task" />
		<html:hidden name="personalHolidayForm" property="isAdd" />
		<html:hidden name="personalHolidayForm" property="persHolidayBean.holidayId" />
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent">
				<table>
					<tr>
						<td>Personal Holiday Name</td>
						<td>
							<html:text styleClass="form-control" styleId="txtPersHolCode" name="personalHolidayForm" property="persHolidayBean.holidayDesc"></html:text>
						</td>
					</tr>
					<tr>
						<td>Personal Holiday Date</td>
						<td>
							<html:text styleClass="form-control" styleId="txtPersHolDate" name="personalHolidayForm" property="persHolidayBean.holidayDateInString"></html:text>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>