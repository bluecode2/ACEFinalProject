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
	
	function getStyleClass(){
		$('.firstBtn').each(function() {
			var projStat = $(this).closest('tr').find('.hdnProjStatus').val();
			var projProg = $(this).closest('tr').find('.hdnProjProg').val();
			if (projStat == 'PR_STAT_01') {
				$(this).addClass('glyphicon glyphicon-play');
				$(this).attr("title","Start");
			} else if (projStat == 'PR_STAT_02' && projProg == '100.0') {
				$(this).addClass('glyphicon glyphicon-ok-circle');
				$(this).attr("title","Submit");
			} else if (projStat == 'PR_STAT_02') {
				$(this).addClass('glyphicon glyphicon-pause');
				$(this).attr("title","Pause");
			} else if (projStat == 'PR_STAT_05') {
				$(this).addClass('glyphicon glyphicon-chevron-right');
				$(this).attr("title","Resume");
			} else
				$(this).hide();
		});
		$('.thirdBtn').each(function() {
			var projStat = $(this).closest('tr').find('.hdnProjStatus').val();
			var projProg = $(this).closest('tr').find('.hdnProjProg').val();
			if (projStat == 'PR_STAT_02' && projProg != '100.0') {
				$(this).addClass('glyphicon glyphicon-remove-circle');
				$(this).attr("title","Force Close");
			} else if (projStat == 'PR_STAT_01') {
				$(this).addClass('glyphicon glyphicon-remove');
				$(this).attr("title","Cancel");
			} else
				$(this).hide();
		});
		$('.secondBtn').each(function() {
			var projStat = $(this).closest('tr').find('.hdnProjStatus').val();
			var projProg = $(this).closest('tr').find('.hdnProjProg').val();
			if (projStat == 'PR_STAT_01') {
				$(this).addClass('glyphicon glyphicon-pencil');
				$(this).attr("title","Edit");
			} else
				$(this).hide();
		});
		
	}

	$(document).ready(
			function() {
				getStyleClass()
			});
	
	function actionForm(task, id, nama, projStatus, projProg) {
		
		document.forms[0].selectedId.value = id;
		if (task == 'firstBtn') {
			if (projStatus == 'PR_STAT_01'){
				if (confirm("Start Project " + nama +" ?")) {
					document.forms[0].task.value = 'start';
					document.forms[0].submit();
				}
			}
			if (projStatus == 'PR_STAT_02' && projProg != 100.0){
				if (confirm("Pause " + nama +" ?")) {
					document.forms[0].task.value = 'pause';
					document.forms[0].submit();
				}
			}
			if (projStatus == 'PR_STAT_02' && projProg == 100.0){
				if (confirm("Submit " + nama +" ?")) {
					document.forms[0].task.value = 'submit';
					document.forms[0].submit();
				}
			}
			if (projStatus == 'PR_STAT_05'){
				if (confirm("Resume " + nama +" ?")) {
					document.forms[0].task.value = 'resume';
					document.forms[0].submit();
				}
			}
		} 
		else if (task == 'thirdBtn') {
			if (projStatus == 'PR_STAT_01'){
				if (confirm("Are you sure want to cancel " + nama +" ?")) {
					document.forms[0].task.value = 'cancel';
					document.forms[0].submit();
				}
			}
			if (projStatus == 'PR_STAT_02'){
				if (confirm("Are you sure want to Force Close " + nama +" ?")) {
					document.forms[0].task.value = 'forceClose';
					document.forms[0].submit();
				}
			}
		} 
		else if (task == 'edit'){
			document.forms[0].task.value = 'edit';
			document.forms[0].submit();
		}
	}
	
</script>
</head>
<body>
	<html:form action="/project" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		
		<html:hidden name="projectForm" property="task" />
		<html:hidden name="projectForm" property="selectedId" />
		<html:hidden name="projectForm" property="currSearchField" />
		<html:hidden name="projectForm" property="currSearchValue" />
		<html:hidden property="currPage" name="projectForm"/>
		
		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;">
						<html:select
								name="projectForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="projectCode">Project Code</option>
								<option value="projectName">Project Name</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="projectForm" property="searchValue"
								styleClass="form-control" /></td>
						<td style="padding-left: 15px"><button type="button"
								onclick="search();" id="btnSearch" class="btn btn-info btn-icon"
								title="Back">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button></td>
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
							<td>Estimate Date</td>
							<td>Actual Date</td>
							<td>Project Manager</td>
							<td>Department Name</td>
							<td>Progress</td>
							<td>Member</td>
							<td>Define Task</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectForm" property="listOfProject">
							<logic:iterate id="proj" name="projectForm" property="listOfProject">
								<tr>
								<html:hidden property="projectStatus" name="proj" styleClass="hdnProjStatus"/>
								<html:hidden property="projectProgress" name="proj" styleClass="hdnProjProg"/>
									<td><bean:write name="proj" property="projectCode" /></td>
									<td><bean:write name="proj" property="projectName" /></td>
									<td><bean:write name="proj" property="estStartDateInString" /> to 
										<bean:write name="proj" property="estEndDateInString" /></td>
									<td><bean:write name="proj" property="actStartDateInString" /> to 
										<bean:write name="proj" property="actEndDateInString" /></td>
									<td><bean:write name="proj" property="employeeName" /></td>
									<td><bean:write name="proj" property="deptName" /></td>
									<td><bean:write name="proj" property="statusCaption" /> : 
										<bean:write name="proj" property="projectProgress" />%</td>
									<td>Member</td>
									<td>Task</td>
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
						<logic:empty name="projectForm" property="listOfProject">
							<tr>
								<td colspan="10" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		<html:hidden name="projectForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>