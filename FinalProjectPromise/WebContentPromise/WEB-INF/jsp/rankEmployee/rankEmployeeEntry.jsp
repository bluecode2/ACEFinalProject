<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>General Code Entry</title>
<script type="text/javascript">
	function onBtnSaveClick() {
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}

	function onBtnBackClick() {
		location.href = "rankEmployee.do";
	}
</script>
</head>
<body>
	<html:form action="/generalCode" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="rankEmpForm" property="task" />
		<html:hidden name="rankEmpForm" property="bean.rankId" />

		<div class="container">
			<div class="container">
				<div class="divContent form-group has-info">
					<table width="50%">
						<colgroup>
							<col width="30%" class="tdLabel" />
							<col />
						</colgroup>
						<tr>
							<td class="tdLabel" align="right"><label>Rank Code</label></td>
							<td><html:text styleClass="form-control"
									styleId="txtRankCode" name="generalCodeForm"
									property="bean.rankCode"></html:text></td>
						</tr>
						<tr>
							<td class="tdLabel" align="right"><label>Rank Name</label></td>
							<td><html:text styleClass="form-control"
									styleId="txtRankName" name="generalCodeForm"
									property="bean.rankName">
								</html:text></td>
						</tr>
						<tr>
							<td class="tdLabel" align="right"><label>Rank Level</label></td>
							<td><html:text styleClass="form-control"
									styleId="txtRankLevel" name="generalCodeForm"
									property="bean.rankLevel">
								</html:text></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>