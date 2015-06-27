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
		if(validateForm()){
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		}
	}
	
	function onBtnBackClick(){
		location.href = "generalParam.do";
	}
	
	function validateForm(){
		
		var genParamDesc	= document.getElementById("txtGenParamDesc").value;
		var genParamValue 	= document.getElementById("txtGenParamValue").value;
		
		var str = "";
		var isValid = true;
		
		if(genParamDesc.trim() == '') {
			str+= "General Parameter Description can not be empty!\n";
			isValid = false;
		}
		else if(genParamDesc.length > 101) {
			str+= "General Parameter Description can not more than 100!\n";
			isValid = false;
		}
		
		if(genParamValue.trim() == '') {
			str+= "General Parameter Value can not be empty!\n";
			isValid = false;
		}
		else if(genParamValue.length > 51) {
			str+= "General Parameter Value can not more than 50!\n";
			isValid = false;
		}
		
		if(!isValid){
			alert(str);
		}
		
		return isValid;
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
			<div class="divContent form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" />
						<col />
					</colgroup>
					<tr>
						<td>Gen. Parameter ID</td>
						<td><html:text property="bean.genParamId" name="generalParamForm" styleClass="form-control" disabled="true"></html:text></td>
					</tr>
					<tr>
						<td>Gen. Parameter Desc</td>
						<td><html:text property="bean.genParamDesc" name="generalParamForm" styleClass="form-control" styleId="txtGenParamDesc"></html:text></td>
					</tr>
					<tr>
						<td>Gen. Parameter Value</td>
						<td><html:text property="bean.genParamValue" name="generalParamForm" styleClass="form-control" styleId="txtGenParamValue"></html:text></td>
					</tr>
				</table>
				<%-- <jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include> --%>
			</div>

		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>