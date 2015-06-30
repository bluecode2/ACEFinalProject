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
		if(validateForm()){
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		}
	}

	function onBtnBackClick() {
		location.href = "generalCode.do";
	}
	
	function validateForm(){
		var genCodeIndex 	= document.getElementById("txtGenCodeIndex").value;
		var genCodeCaption 	= document.getElementById("txtGenCodeCaption").value;
		var str = "";
		var isValid = true;
		
		if(genCodeIndex.trim() == '') {
			str+= "<li>General Code Index can not be empty!</li>";
			isValid = false;
		}
		else if(isNaN(genCodeIndex)) {
			str+= "<li>General Code Index must be a number!</li>";
			isValid = false;
		}
		
		if(genCodeCaption.trim() == '') {
			str+= "<li>General Code Caption can not be empty!</li>";
			isValid = false;
		}
		else if(genCodeCaption.length > 51) {
			str+= "<li>General Code Caption can not be more than 50 characters!</li>";
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
	<html:form action="/generalCode" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="generalCodeForm" property="task" />
		<html:hidden name="generalCodeForm" property="genCodeBean.genCodeId" />

		<div class="container">
			<div class="container">
				<div class="divContent form-group has-info">
					<div class="text-danger" id="divError" style="display: none">
						Save failed!
						<ul id="errorContent"></ul>
					</div>
					<table width="50%">
						<colgroup>
							<col width="30%" class="tdLabel" />
							<col />
						</colgroup>
						<tr>
							<td class="tdLabel" align="right"><label>General
									Code ID</label></td>
							<td><html:text styleClass="form-control"
									styleId="txtGenCodeId" name="generalCodeForm"
									property="genCodeBean.genCodeId" disabled="true"></html:text></td>
						</tr>
						<tr>
							<td class="tdLabel" align="right"><label>Gen. Code
									Index</label></td>
							<td><html:text styleClass="form-control"
									styleId="txtGenCodeIndex" name="generalCodeForm"
									property="genCodeBean.genCodeIndex">
								</html:text></td>
						</tr>
						<tr>
							<td class="tdLabel" align="right"><label>General
									Code Caption</label></td>
							<td><html:text styleClass="form-control"
									styleId="txtGenCodeCaption" name="generalCodeForm"
									property="genCodeBean.genCodeCaption">
								</html:text></td>
						</tr>
						<tr style="display: none">
							<td class="tdLabel" align="right"><label>Parent ID</label></td>
							<td><html:select property="genCodeBean.parentId"
									styleId="txtParentId" styleClass="form-control">
									<html:option value=""></html:option>
								</html:select></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>