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
	$(document).ready(function() {
		if ($('#hdnIsAdd').value() == 'true')
			$('#txtGeneralCodeId').attr('disabled', 'disabled');
		else
			$('#txtGeneralCodeId').removeAttr('disabled');
	});

	function onBtnSaveClick() {
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}
	function onBtnBackClick() {
		document.forms[0].task.value = '';
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<html:form action="/generalCode" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch" style="float: right;">
				<!-- untuk pengganti search -->
				
			</div>

			<div class="divContent">
				<!-- kemungkinan untuk tempat entry -->

			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>