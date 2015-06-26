<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Entry</title>

<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
	function onBtnBackClick() {
		location.href = "project.do";
	}

	function onBtnSaveClick() {
			document.forms[0].task.value = "save";
			document.forms[0].submit();
	}

	$(document).ready(
			function() {
				$(".datepicker").attr("data-provide", "datepicker");
				registerSearchEmployee();
				var newTask = document.forms[0].task.value;
				if (newTask == 'add' || newTask == 'edit'){
					$('#readAbleCode').attr("disabled",false);
					$('#readAbleName').attr("disabled",false);
					$('#readAbleDesc').attr("disabled",false);
					$('#readAbleEstStart').attr("disabled",false);
					$('#readAbleEstEnd').attr("disabled",false);
					document.getElementById('popView').style.display = 'block';
				} else {
					$('#readAbleCode').attr("disabled",true);
					$('#readAbleName').attr("disabled",true);
					$('#readAbleDesc').attr("disabled",true);
					$('#readAbleEstStart').attr("disabled",true);
					$('#readAbleEstEnd').attr("disabled",true);
					document.getElementById('popView').style.display = 'none';
				}
			});
	
	function registerSearchEmployee(){
		$('.rowSearchEmployee').on(
				'click',
				function() {
					var valueEmp = $(this).find('td').eq(0).html().trim();
					var valueDept = $(this).find('td').eq(4).html().trim();
					var text =  $(this).find('td').eq(2).html().trim();
					var text2 = $(this).find('td').eq(3).html().trim();
					$('#txtEmployeeId').val(valueEmp);
					$('#txtDeptId').val(valueDept);
					$('#empIdDisplay').val(text);
					$('#deptIdDisplay').val(text2);
				});
	}
	
function search() {
			var searchField = $('#selSearchFieldEmpId').val();
			var searchValue = $('#txtSearchValueEmpId').val();
	
			$.ajax({
				type : "POST",
				url : "searchEmp.do",
				data : "&searchField=" + searchField
						+ "&searchValue=" + searchValue,
				success : function(response) {
					$("#tblSearchEmp").find("tr:gt(0)").remove();
					$("#tblSearchEmp").append(response);
					registerSearchEmployee();
				},
				error : function(e) {
					alert("Error: " + e);
				}
			});
	}
</script>                 
</head>
<body>
	<html:form action="/project" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		
		<html:hidden property="task" name="projectForm"/>
		<html:hidden property="pBean.projectId" name="projectForm"/>
		<html:hidden property="isProc" name="projectForm"/>
		<html:hidden property="pBean.projectStatus" name="projectForm"/>
		<html:hidden property="pBean.projectProgress" name="projectForm"/>
		<html:hidden property="pBean.actStartDateInString" name="projectForm"/>
		<html:hidden property="pBean.actEndDateInString" name="projectForm"/>
		<html:hidden property="pBean.estMainDays" name="projectForm"/>
		<html:hidden property="pBean.actMainDays" name="projectForm"/>
				
		<div class="container">
			<div class="divSearch form-group has-info" style="float: left;">
				<table>
					<tr align="left">
						<td>Project Code</td>
						<td style="padding-left: 15px;" colspan="3">
						<html:text name="projectForm" property="pBean.projectCode" styleClass="form-control" style="width : 200px" styleId="readAbleCode"></html:text>
						</td>
					</tr>
					<tr align="left">
						<td>Project Name</td>
						<td style="padding-left: 15px;" colspan="3"><html:text name="projectForm"
								property="pBean.projectName" styleClass="form-control" style="width : 300px" styleId="readAbleName"></html:text>
						</td>
					</tr>
					<tr align="left">
						<td>Project Description</td>
						<td style="padding-left: 15px;" colspan="3"><html:textarea name="projectForm"
								property="pBean.projectDesc" styleClass="form-control"  style="width : 400px" styleId="readAbleDesc"></html:textarea>
						</td>
					</tr>
					<tr align="left">
						<td>Project Manager</td>
						<td style="padding-left: 15px;" colspan="3">
								<table>
									<tr>
										<td>
											<html:hidden property="pBean.employeeId" name="projectForm" styleId="txtEmployeeId"/>
										</td>
										<td>
											<html:text styleClass="form-control"
											styleId="empIdDisplay" readonly="true" name="projectForm"
											property="pBean.employeeName" style="width :200px"></html:text>
										</td>
										<td>
											<a href="#" class="text-info"
											data-toggle="modal" data-target="#searchEmployeeId"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" id="popView" /></a>
										</td>
									</tr>
								</table>
						</td>
					</tr>
					<tr align="left">
						<td>Department Name</td>
								<td style="padding-left: 15px;" colspan="3">
									<table>
									<tr>
										<td>
											<html:hidden property="pBean.dept_id" name="projectForm" styleId="txtDeptId"/>
										</td>
										<td>
											<html:text styleClass="form-control"
											styleId="deptIdDisplay" readonly="true" name="projectForm"
											property="pBean.deptName" style="width :300px"></html:text>
										</td>
									</tr>
									</table>
								</td>
					</tr>
					<tr align="left">
						<td>Estimate Date</td>
						<td style="padding-left: 15px;" colspan="1">
								<html:text name="projectForm"
								property="pBean.estStartDateInString" styleClass="form-control datepicker"  style="width : 120px" styleId="readAbleEstStart"></html:text> 
								</td>
						<td style="padding-left: 15px;" colspan="1"> To 
						</td>
						<td style="padding-left: 15px;" colspan="1"><html:text name="projectForm"
								property="pBean.estEndDateInString" styleClass="form-control datepicker"  style="width : 120px" styleId="readAbleEstEnd"></html:text>
						</td>
					</tr>
					<logic:equal value="true" name="show">
					<tr align="left">
						<td>Progress</td>
						<td style="padding-left: 15px;" colspan="3"><bean:write name="projectForm" property="pBean.projectStatus"/> : 
														<bean:write name="projectForm" property="pBean.projectProgress"/>%
						</td>
					</tr>
					<tr align="left">
						<td>Remarks</td>
						<td style="padding-left: 15px;" colspan="3"><html:textarea name="projectForm"
								property="pBean.remarks" styleClass="form-control" value=""></html:textarea>
						</td>
					</tr>
					</logic:equal>
				</table>
			</div>

		</div>
		
		
		<div class="modal fade" id="searchEmployeeId" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Employee</h4>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldEmpId"
										style="width: 150px">
											<option value="employeeCode">Employee Code</option>
											<option value="employeeName">Employee Name</option>
									</select></td>
									<td style="padding-left: 15px">
									<input type="text" id="txtSearchValueEmpId" class="form-control" /></td>
									<td style="padding-left: 15px">
									<button type="button" onclick="search();" id="btnSearchEmp"
											class="btn btn-sm btn-info btn-icon" title="BackEmp" value="btnEmp">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button></td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearchEmp"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>Employee Code</th>
									<th>Employee Name</th>
									<th>Department Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="lstEmployeeId">
								<logic:iterate id="emp" name="lstEmployeeId">
									<tr data-dismiss="modal" class="rowSearchEmployee">
										<td style="display: none">
										<bean:write name="emp" property="employeeId" /></td>
										<td width="150px">
										<bean:write name="emp" property="employeeCode" /></td>
										<td width="150px">
										<bean:write name="emp" property="employeeName" /></td>
										<td width="150px">
										<bean:write name="emp" property="deptName" /></td>
										<td style="display: none">
										<bean:write name="emp" property="deptId" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstEmployeeId">
								<tr>
									<td colspan="3" align="center">No Data Found</td>
								</tr>
							</logic:empty>
						</table>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>