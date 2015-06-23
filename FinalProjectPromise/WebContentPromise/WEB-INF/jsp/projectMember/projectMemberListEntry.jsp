<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project List</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnAddClick(){
		document.forms[0].task.value = "add";
		document.forms[0].submit();
	}
	
	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}
	
	$(document).ready(
			function() {
				
			});
	
	function actionForm(task, id, nama, projStatus, projProg) {
		
	}
	
	
</script>
</head>
<body>
	<html:form action="/projectMember" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		
		<html:hidden name="projectMemberForm" property="task" />
		<html:hidden name="projectMemberForm" property="selectedId" />
		<html:hidden property="currPage" name="projectMemberForm"/>
		
		<div class="container">
			<div class="divSearch form-group has-info" style="float: left;">
				<table>
					<tr>
						<td>Project Name
						</td>
						<td style="padding-left: 15px;"><html:text property="projectName" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td>Manager Name
						</td>
						<td style="padding-left: 15px;"><html:text property="employeeName" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td>Estimate Date
						</td>
						<td style="padding-left: 15px;"><html:text property="estStartDateInString" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
						<td style="padding-left: 15px;">To
						</td>
						<td style="padding-left: 15px;"><html:text property="estEndDateInString" name="getProject" styleClass="form-control" readonly="true"></html:text>
						</td>
					</tr>
				</table>
			</div>
			<div class="divContent">
				<table border="0" fon
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<tr>
						<td><hr /></td>
					</tr>
				</table>
				<table cellspacing="0" style="margin-top: 10px;" width="400px"
					class="tableContent">
					<tr>
						<td width="40%">Employee Name
						</td>
						<td style="padding-left: 15px;"  width="50%">
						<html:text property="pMemberbean.empName" name="projectMemberForm" styleClass="form-control"></html:text>
						</td>
						<td width="10%">&nbsp;
						</td>
					</tr>
					<tr>
						<td width="40%">Project Role Name
						</td>
						<td style="padding-left: 15px;" width="50%">
						<html:text property="pMemberbean.projRoleName" name="projectMemberForm" styleClass="form-control"></html:text>
						</td>
						<td width="10%">&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<div class="divContent">
				<table class="table table-striped table-bordered table-hover"
					cellspacing="0" style="margin-top: 10px;" width="100%"
					class="tableContent">
					<thead class="panel panel-info">
						<tr>
							
							<td>Project Code</td>
							<td>Project Name</td>
							<td>Employee Name</td>
							<td>Project Role Name</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectMemberForm" property="listOfProjMember">
							<logic:iterate id="projMember" name="projectMemberForm" property="listOfProjMember">
								<tr>
									<td><bean:write name="projMember" property="projCode" /></td>
									<td><bean:write name="projMember" property="projName" /></td>
									<td><bean:write name="projMember" property="empName" /></td>
									<td><bean:write name="projMember" property="projRoleName" /></td>
									<td align="center">
									
									<a href="#" onclick="actionForm('firstBtn','<bean:write name="proj" property="projectId" />','<bean:write name="proj" property="projectName" />','<bean:write name="proj" property="projectStatus" />','<bean:write name="proj" property="projectProgress" />');"
										><span class="firstBtn"
											aria-hidden="true" id="firstBtn" ></span></a> &nbsp; 
									<a href="#"	onclick="actionForm('edit','<bean:write name="proj" property="projectId" />');"
										title="Edit"><span class="secondBtn"
											aria-hidden="true"></span></a> &nbsp; 
									<a href="#" onclick="actionForm('thirdBtn','<bean:write name="proj" property="projectId" />','<bean:write name="proj" property="projectName" />','<bean:write name="proj" property="projectStatus" />');"
										><span class="thirdBtn"
											aria-hidden="true" id="thirdBtn"></span></a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="projectMemberForm" property="listOfProjMember">
							<tr>
								<td colspan="5" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		
		<html:hidden name="projectMemberForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>