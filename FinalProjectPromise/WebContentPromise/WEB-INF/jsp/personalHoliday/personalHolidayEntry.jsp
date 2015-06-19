<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Holiday</title>

<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
	function onBtnSaveClick(){
		document.forms[0].task.value = 'save';
		document.forms[0].submit();
	}
	
	function onBtnBackClick(){
		location.href = "personalHoliday.do";
	}
	
	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
		registerSearchEmpEvent();
	});
	
	function registerSearchEmpEvent(){
		$('.rowSearch').on(
				'click',
				function() {
					var value = $(this).find('td').eq(0).html();
					var text = $(this).find('td').eq(1).html() + ' - '
							+ $(this).find('td').eq(2).html();
					$('#hiddenEmpId').val(value);
					$('#txtEmp').val(text);
				});
	}
	
	function search() {
		var empId = $('#hiddenEmpId').val();
		var searchField = $('#selSearchFieldEmp').val();
		var searchValue = $('#txtSearchValueEmp').val();

		$.ajax({
			type : "POST",
			url : "searchEmp.do",
			data : "empId=" + empId + "&searchField=" + searchField
					+ "&searchValue=" + searchValue,
			success : function(response) {
				$("#tblSearch").find("tr:gt(0)").remove();
				$("#tblSearch").append(response);
				registerSearchEmpEvent();
			},
			error : function(e) {
				alert("Error: " + e);
			}
		});
	}
</script>
</head>
<body>
	<html:form action="/personalHoliday" method="post">
		<html:hidden name="personalHolidayForm" property="task" />
		<html:hidden name="personalHolidayForm" property="isAdd" />
		<html:hidden name="personalHolidayForm" property="persHolidayBean.holidayId" />
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divContent">
				<table>
					<tr>
						<td>Personal Holiday Name</td>
						<td>
							<html:text styleClass="form-control" styleId="txtPersHolCode" name="personalHolidayForm" property="persHolidayBean.holidayDesc"></html:text>
						</td>
					</tr>
					<tr>
						<td>Personal Holiday Date</td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtPersHolDate"
								name="personalHolidayForm" property="persHolidayBean.holidayDateInString"></html:text>
						</td>
					</tr>
					<tr>
						<td>Employee ID</td>
						<td>
							<html:text styleClass="form-control" styleId="txtPersHolCode" name="personalHolidayForm" property="persHolidayBean.holidayDesc"></html:text>
						</td>
						<td>
							<html:hidden styleId="hiddenEmpId"
								name="personalHolidayForm" property="persHolidayBean.employeeId" />
							<table width="100%">
								<tr>
									<td>
										<html:text styleClass="form-control"
											styleId="txtEmp" readonly="true" name="personalHolidayForm"
											property="persHolidayBean.empDisplay">
										</html:text>
									</td>
									<td align="center">
										<a href="#" class="text-info" data-toggle="modal" data-target="#searchEmp">
										<span class="glyphicon glyphicon-edit" aria-hidden="true" /></a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="modal fade" id="searchEmp" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<html:form action="searchEmp" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Employee Name</h4>
						</div>
						<div class="modal-body">
							<div class="container form-group">
								<table>
									<tr>
										<td>Search</td>
										<td style="padding-left: 15px"><select
											class="form-control" id="selSearchFieldEmp"
											style="width: 150px">
												<option value="employeeCode">Employee Code</option>
												<option value="employeeName">Employee Name</option>
												<option value="email">Email</option>
										</select></td>
										<td style="padding-left: 15px"><input type="text"
											id="txtSearchValueEmp" class="form-control" /></td>
										<td style="padding-left: 15px"><button type="button"
												onclick="search();" id="btnSearch"
												class="btn btn-sm btn-info btn-icon" title="Back">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
											</button></td>
									</tr>
								</table>
							</div>

							<table width="100%" id="tblSearch"
								class="table table-striped table-hover table-bordered table-clickable">
								<thead>
									<tr>
										<th>Employee Code</th>
										<th>Employee Name</th>
										<th>Email</th>
									</tr>
								</thead>
								<tbody>
									<logic:notEmpty name="listEmployeeSearch">
										<logic:iterate id="emp" name="listEmployeeSearch">
											<tr data-dismiss="modal" class="rowSearch">
												<td style="display: none"><bean:write name="emp"
														property="employeeId" /></td>
												<td width="150px"><bean:write name="emp"
														property="employeeCode" /></td>
												<td><bean:write name="emp" property="employeeName" /></td>
												<td width="150px"><bean:write name="emp"
														property="email" /></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="listEmployeeSearch">
										<tr>
											<td colspan="3" align="center">No Data Found</td>
										</tr>
									</logic:empty>
								</tbody>
							</table>
						</div>
					</html:form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>