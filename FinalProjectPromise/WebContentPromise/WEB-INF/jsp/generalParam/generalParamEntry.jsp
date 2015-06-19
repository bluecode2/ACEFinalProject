<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>General Parameter</title>

<script src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnSaveClick(){
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}
	
	function onBtnBackClick(){
		location.href = "generalParam.do";
	}
	
</script>
</head>
<body>
	<html:form action="/generalParam" method="post">
		<html:hidden name="generalParamForm" property="task" />
		<html:hidden name="generalParamForm" property="isAdd" />
		<html:hidden name="generalParamForm" property="bean.genParamId"/>

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container" style="padding-top: 30px">
			<div class="divContent">
				<table>
					<tr>
						<td>General Parameter Desc</td>
						<td>  </td>
						<td><html:text property="bean.genParamDesc" name="generalParamForm" styleClass="form-control"></html:text></td>
					</tr>
					<tr>
						<td>General Parameter Value</td>
						<td>  </td>
						<td><html:text property="bean.genParamValue" name="generalParamForm" styleClass="form-control"></html:text></td>
					</tr>
					<tr>
						<td>Is Active</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<html:checkbox property="bean.isActive" name="generalParamForm" value="1"></html:checkbox>
						</td> 
					</tr>
				</table>
				<%-- <jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include> --%>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>