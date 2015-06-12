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
</head>
<body>
	<html:form action="/generalParam" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
			</div>
			<br />
			<div class="divContent">
				<table>
					<tr>
						<td>General Parameter Desc</td>
						<td><html:text property="bean.genParamDesc" name="generalParamForm" styleClass="form-control"></html:text></td>
					</tr>
					<tr>
						<td>General Parameter Value</td>
						<td><html:text property="bean.genParamValue" name="generalParamForm" styleClass="form-control"></html:text></td>
					</tr>
					<tr>
						<td>Is Active</td>
						<td>
							<html:checkbox property="bean.isActive" name="generalParamForm" styleClass="form-control"></html:checkbox> &nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp; 
					</tr>
				</table>
				<%-- <jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include> --%>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>