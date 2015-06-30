<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<link rel="stylesheet" href="css/jsTree/style.min.css" />
<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>
	<html:form action="/report" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>

		<html:hidden name="reportForm" property="task"/>
		<html:hidden styleId="hdnSelectedId" name="reportForm" property="selectedId"/>
		<html:hidden styleId="hdnFilterValue" name="reportForm" property="filterValue"/>


		<div class="container bg-info"
			style="border-radius: 3px; background-color: #E8F4FA">
			<button id="btnPrint" type="button" onclick="onBtnPrintClick();"
				class="btn btn-raised btn-info btn-icon" title="Print">
				<span class="glyphicon glyphicon-print" aria-hidden="true"></span>
			</button>
		</div>

		<div class="container" style="margin-top: 10px">
			<table width="100%">
				<tr>
					<td width="30%">
						<div class="panel"
							style="height: 500px; overflow: auto; padding: 20px">
							<div id="reportTree">
								<ul>
									<logic:notEmpty name="parentReport">
										<logic:iterate id="parent" name="parentReport">
											<li class="jstree-open" data-jstree='{"disabled":true}'><bean:write name="parent"
													property="reportCaption" />
												<ul>
													<logic:iterate id="child" name="childReport">
														<logic:equal name="child" property="parentId" value="${parent.reportId}">
															<li value="<bean:write name="child" property="reportId"/>"><a href="#" class="reportNode"><bean:write
																	name="child" property="reportCaption" /></a></li>
														</logic:equal>
													</logic:iterate>
												</ul>
											</li>
										</logic:iterate>
									</logic:notEmpty>
								</ul>
							</div>
						</div>
					</td>
					<td>
						<div class="panel form-group has-info"
							style="height: 500px; padding: 20px">
							<h4 id="reportTitle"></h4>
							<hr>
							<br>
							<div id="datePeriod" style="display: none;">
								<table>
									<tr>
										<td width="150px">Date period</td>
										<td width="200px"><input type="text" id="txtStartDate" class="datepicker form-control" /></td>
										<td width="50px" align="center">to</td>
										<td width="200px"><input type="text" id="txtEndDate" class="datepicker form-control" /></td>
									</tr>
								</table>
							</div>
							<div id="currentEmployee" style="display: none;">
								<table>
									<tr>
										<td width="150px">Employee</td>
										<td width="200px"><input type="hidden" id="hdnCurrentEmpId" value="${currentEmpId}" />
										<input type="text" readonly="readonly" id="txtCurrentEmployeeName" class="form-control" value="${currentEmpName}" /></td>
										
									</tr>
								</table>
							</div>			
							<div id="department" style="display: none;">
								<table>
									<tr>
										<td width="150px">Department Name</td>
										<td width="200px">
										<html:select property="deptId" name="reportForm" styleClass="form-control" styleId="txtDeptId"> 
											<html:option value="">---Choose One---</html:option>
											<logic:iterate id="listDept" name="reportForm" property="listOfDept" >
												<html:option value="${listDept.deptId}">
													<bean:write name="listDept" property="deptName"/>
												</html:option>
											</logic:iterate>
										</html:select>
										</td>
									</tr>
								</table>
							</div>			
							<div id="projectStatus" style="display: none;">
								<table>
									<tr>
										<td width="150px">Project Status</td>
										<td width="200px">
										<html:select property="genCodeId" name="reportForm" styleClass="form-control" styleId="txtGenCodeId">
											<html:option value="">---Choose One---</html:option>
											<logic:iterate id="listProjStat" name="reportForm" property="listOfGenCode" >
												<html:option value="${listProjStat.genCodeId}">
													<bean:write name="listProjStat" property="genCodeCaption"/>
												</html:option>
											</logic:iterate>
										</html:select>
										</td>
									</tr>
								</table>
							</div>			
							<div id="employee" style="display: none;">
								<table>
									<tr>
										<td width="150px">Employee Name</td>
										<td width="200px">
										<html:hidden property="empId" name="reportForm"
										styleId="txtEmployeeId" /> 
										<html:text property="empName" name="reportForm"
										styleId="empIdDisplay" styleClass="form-control" readonly="true"/> 
										</td>
										<td width="50px" align="center">
										<a href="#" class="text-info"
										data-toggle="modal" data-target="#searchEmployeeId"> <span
										class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
										</td>
									</tr>
								</table>
							</div>			
							<div id="project" style="display: none;">
								<table>
									<tr>
										<td width="150px">Project name</td>
										<td width="200px">
										<html:hidden property="projId" name="reportForm"
										styleId="txtProjId" /> 
										<html:text property="projName" name="reportForm"
										styleId="projIdDisplay" styleClass="form-control" readonly="true"/> 
										</td>
										<td width="50px" align="center">
										<a href="#" class="text-info"
										data-toggle="modal" data-target="#searchProject"> <span
										class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
										</td>
									</tr>
								</table>
							</div>			
							
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		
		<!-- untuk pop up employee -->
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
						<hr>
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
									<td style="padding-left: 15px"><input type="text"
										id="txtSearchValueEmpId" class="form-control" /></td>
									<td style="padding-left: 15px">
										<button type="button" onclick="searchEmp();" id="btnSearchEmp"
											class="btn btn-sm btn-info btn-icon" title="Search"
											value="btnEmp">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button>
									</td>
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
										<td style="display: none"><bean:write name="emp"
												property="employeeId" /></td>
										<td width="150px"><bean:write name="emp"
												property="employeeCode" /></td>
										<td width="150px"><bean:write name="emp"
												property="employeeName" /></td>
										<td width="150px"><bean:write name="emp"
												property="deptName" /></td>
										<td style="display: none"><bean:write name="emp"
												property="deptId" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstEmployeeId">
								<tr>
									<td colspan="4" align="center">No Data Found</td>
								</tr>
							</logic:empty>
						</table>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>


		<!-- untuk pop up Project -->
		<div class="modal fade" id="searchProject" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Project</h4>
						<hr>
					</div>
					<div class="modal-body">
						<div class="container form-group">
							<table>
								<tr>
									<td>Search</td>
									<td style="padding-left: 15px"><select
										class="form-control" id="selSearchFieldProj"
										style="width: 150px">
											<option value="projectCode">Project Code</option>
											<option value="projectName">Project Name</option>
									</select></td>
									<td style="padding-left: 15px"><input type="text"
										id="txtSearchValueProj" class="form-control" /></td>
									<td style="padding-left: 15px">
										<button type="button" onclick="searchProj();" id="btnSearchProj"
											class="btn btn-sm btn-info btn-icon" title="Search"
											value="btnProj">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button>
									</td>
								</tr>
							</table>
						</div>

						<table width="100%" id="tblSearchProj"
							class="table table-striped table-hover table-bordered table-clickable">
							<thead>
								<tr>
									<th>Project Code</th>
									<th>Project Name</th>
									<th>Manager Name</th>
								</tr>
							</thead>
							<logic:notEmpty name="lstProject">
								<logic:iterate id="proj" name="lstProject">
									<tr data-dismiss="modal" class="rowSearchProj">
										<td style="display: none"><bean:write name="proj"
												property="projectId" /></td>
										<td width="150px"><bean:write name="proj"
												property="projectCode" /></td>
										<td width="150px"><bean:write name="proj"
												property="projectName" /></td>
										<td width="150px"><bean:write name="proj"
												property="employeeName" /></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="lstProject">
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

	<script src="js/jsTree/jstree.js"></script>
	<script src="js/datepicker/bootstrap-datepicker.js"></script>
	<script src="js/datepicker/bootstrap-datepicker.min.js"></script>
	
	<script type="text/javascript">
	
	function onBtnPrintClick(){
		document.forms[0].task.value = "printReport";
		document.forms[0].filterValue.value = generateFilter();
		document.forms[0].submit();
	}
	
	function generateFilter(){
		var filterValue = "";
		if ($('#datePeriod').is(':visible')){
			filterValue+=$('#txtStartDate').val()+"#"+$('#txtEndDate').val();
		}
		if ($('#currentEmployee').is(':visible')){
			if(filterValue != "") filterValue += "#";
			filterValue+=$('#hdnCurrentEmpId').val();
		}
		if ($('#department').is(':visible')){
			if(filterValue != "") filterValue += "#";
			filterValue+=$('#txtDeptId').val();
		}
		if ($('#projectStatus').is(':visible')){
			if(filterValue != "") filterValue += "#";
			filterValue+=$('#txtGenCodeId').val();
		}
		if ($('#employee').is(':visible')){
			if(filterValue != "") filterValue += "#";
			filterValue+=$('#txtEmployeeId').val();
		}
		if ($('#project').is(':visible')){
			if(filterValue != "") filterValue += "#";
			filterValue+=$('#txtProjId').val();
		}
		
		return filterValue;
	}
	
	function registerSearchProj() {
		$('.rowSearchProj').on('click', function() {
			var valueEmp = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#txtProjId').val(valueEmp);
			$('#projIdDisplay').val(text);
		});
	}
	
	function registerSearchEmployee() {
		$('.rowSearchEmployee').on('click', function() {
			var valueEmp = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#txtEmployeeId').val(valueEmp);
			$('#empIdDisplay').val(text);
		});
	}
	
	function searchEmp() {
		showLoading();
		var searchField = $('#selSearchFieldEmpId').val();
		var searchValue = $('#txtSearchValueEmpId').val();

		$.ajax({
			type : "POST",
			url : "searchEmp.do",
			data : "&searchField=" + searchField + "&searchValue="
					+ searchValue,
			success : function(response) {
				$("#tblSearchEmp").find("tr:gt(0)").remove();
				$("#tblSearchEmp").append(response);
				registerSearchEmployee();
				hideLoading();
			},
			error : function(e) {
				alert("Error: " + e);
				hideLoading();
			}
		});
	}
	
	function searchProj() {
		showLoading();
		var searchField = $('#selSearchFieldProj').val();
		var searchValue = $('#txtSearchValueProj').val();

		$.ajax({
			type : "POST",
			url : "searchProj.do",
			data : "&searchField=" + searchField + "&searchValue="
					+ searchValue,
			success : function(response) {
				$("#tblSearchProj").find("tr:gt(0)").remove();
				$("#tblSearchProj").append(response);
				registerSearchProj();
				hideLoading();
			},
			error : function(e) {
				alert("Error: " + e);
				hideLoading();
			}
		});
	}
	
		$(document).ready(function() {
			registerSearchEmployee();
			registerSearchProj();
			showLoading();
			$.jstree.defaults.core.animation = 100;
			$.jstree.defaults.core.themes.icons = false;

			$('#reportTree').jstree();
			
			$('.reportNode').on('click',function(){
				showLoading();
				hideAllFilter();
				
				var reportId = $(this).closest('li').val();
				$('#reportTitle').html($(this).html());
				$('#hdnSelectedId').val(reportId);
				
				$.ajax({
					type : "POST",
					url : "report.do",
					data : "task=selectReport&selectedId="
							+ reportId,
					success : function(response) {
						showFilterPanel(response);
						hideLoading();
					},
					error : function(e) {
						alert("Error: " + e);
						hideLoading();
					}

				});
				
			});
			
			$(".datepicker").attr("data-provide", "datepicker");
			
			hideLoading();
		});
		
		function showFilterPanel(filter){
			var arrFilter = filter.split("#");

			for(var i = 0;i < arrFilter.length;i++){
				arrFilter[i]  = arrFilter[i].trim();
			}
			
			//Filter Date Period
			if($.inArray("datePeriod",arrFilter) > -1){
				$("#datePeriod").show();
			}

			//Filter Current Employee
			if($.inArray("currentEmployee",arrFilter) > -1){
				$("#currentEmployee").show();
			}
			
			//Filter Department
			if($.inArray("department",arrFilter) > -1){
				$("#department").show();
			}
			
			//Filter Project Status
			if($.inArray("projectStatus",arrFilter) > -1){
				$("#projectStatus").show();
			}
			
			//Filter Employee
			if($.inArray("employee",arrFilter) > -1){
				$("#employee").show();
			}
			
			//Filter Project
			if($.inArray("project",arrFilter) > -1){
				$("#project").show();
			}
		}
		
		function hideAllFilter(){
			$("#datePeriod").hide();
			$("#currentEmployee").hide();
			$("#department").hide();
			$("#projectStatus").hide();
			$("#employee").hide();
			$("#project").hide();
		}
	</script>
</body>

</html>