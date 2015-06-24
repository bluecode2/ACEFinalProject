<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Involved</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}

	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
		
		$('.linkMember').on('click',function(){
			var selecId = $(this).closest('tr').find('.hdProjId').val();
			$.ajax({
				type : "POST",
				url : "projectInvolved.do",
				data : "task=listMembers&selectedId="
						+ selecId,
				success : function(response) {
					$("#tblShow").find("tr:gt(0)").remove();
					$("#tblShow").append(response);
					$('#searchProjMember').modal();
				},
				error : function(e) {
					alert("Error: " + e);
				}

			});
		});

	});

	function getProjDesc(projDesc) {
		$('#txtProjectDesc').html(projDesc);
		$('#projDesc').modal();
	}
	
	function toTask(task, projectId){
		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = projectId;
		document.forms[0].submit(); 
	}
</script>
</head>
<body>
	<html:form action="/projectInvolved" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="projectInvolvedForm" property="task" />
		<html:hidden name="projectInvolvedForm" property="selectedId"/>
		<html:hidden name="projectInvolvedForm" property="currSearchField" />
		<html:hidden name="projectInvolvedForm" property="currSearchValue" />

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="projectInvolvedForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="projectCode">Project Code</option>
								<option value="projectName">Project Name</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="projectInvolvedForm" property="searchValue"
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
							<td class="align-center">Project Code</td>
							<td class="align-center">Project Name</td>
							<td class="align-center">Estimate Date</td>
							<td class="align-center">Actual Date</td>
							<td class="align-center">Project Manager</td>
							<td class="align-center">Department Name</td>
							<td class="align-center">Progress</td>
							<td class="align-center">Member</td>
							<td class="align-center">View Task</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectInvolvedForm"
							property="listOfProjectInvolved">
							<logic:iterate id="proj" name="projectInvolvedForm"
								property="listOfProjectInvolved">
								<tr>
									<html:hidden property="projectStatus" name="proj"
										styleClass="hdnProjStatus" />
									<html:hidden property="projectProgress" name="proj"
										styleClass="hdnProjProg" />
									
										
									<td><html:hidden styleClass="hdProjId" name="proj"
										property="projectId" />
									<bean:write name="proj" property="projectCode" /></td>
									<td><a href="#" class="text-info"
										onclick="getProjDesc('<bean:write name="proj" property="projectDesc" />');"
										data-target="#projDesc"> <bean:write name="proj"
												property="projectName" /></a></td>
									<td><bean:write name="proj"
											property="estStartDateInString" /> to <bean:write
											name="proj" property="estEndDateInString" /></td>
									<td><bean:write name="proj"
											property="actStartDateInString" /> to <bean:write
											name="proj" property="actEndDateInString" /></td>
									<td><bean:write name="proj" property="employeeName" /></td>
									<td><bean:write name="proj" property="deptName" /></td>
									<td><bean:write name="proj" property="statusCaption" /> :
										<bean:write name="proj" property="projectProgress" />%</td>
									<td align="center"><a href="#" class="text-info linkMember"> View
											Member</a>
									</td>
									<td align="center"><a href="#" class="text-info"
										   onclick="toTask('toTask','<bean:write name="proj" property="projectId" />');">
											Task</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="projectInvolvedForm"
							property="listOfProjectInvolved">
							<tr>
								<td colspan="9" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>
		</div>

		<div class="modal fade" id="projDesc" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Project Description</h4>
					</div>
					<div class="modal-body">
						<hr />
						<br>
						<p id="txtProjectDesc"></p>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<div class="modal fade" id="searchProjMember" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Project Member</h4>
					</div>
					<div class="modal-body">
						<table width="100%" id="tblShow"
							class="table table-striped table-hover table-bordered table-clickable">

								<tr>
									<th class="align-center">Employee Name</th>
									<th class="align-center">Role Name</th>
								</tr>
	
								<logic:notEmpty name="arrMember">
									<logic:iterate id="projMember" name="arrMember">
									
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="arrMember">
									<tr>
										<td colspan="2" align="center">No Data Found</td>
									</tr>
								</logic:empty>
						
						</table>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<html:hidden name="projectInvolvedForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>