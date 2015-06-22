<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script src="js/datepicker/bootstrap-datepicker.js"></script>
<script src="js/datepicker/bootstrap-datepicker.min.js"></script>
<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">
<script type="text/javascript">
function onBtnSaveClick() {
	document.forms[0].task.value = "save";
	document.forms[0].submit();
}
$(document).ready(function() {
	$(".datepicker").attr("data-provide", "datepicker");
	registerSearchHeadDeptEvent();
});
function search() {
	var deptId = $('#hdnDeptId').val();
	var searchField = $('#selSearchFieldDeptHead').val();
	var searchValue = $('#txtSearchValueDeptHead').val();

	$.ajax({
		type : "POST",
		url : "searchDeptHead.do",
		data : "deptId=" + deptId + "&searchField=" + searchField
				+ "&searchValue=" + searchValue,
		success : function(response) {
			$("#tblSearch").find("tr:gt(0)").remove();
			$("#tblSearch").append(response);
			registerSearchHeadDeptEvent();
		},
		error : function(e) {
			alert("Error: " + e);
		}
	});
}

function registerSearchHeadDeptEvent(){
	$('.rowSearch').on(
			'click',
			function() {
				var value = $(this).find('td').eq(0).html();
				var text = $(this).find('td').eq(1).html() + ' - '
						+ $(this).find('td').eq(2).html();
				$('#hdnDeptHeadId').val(value);
				$('#txtDeptHead').val(text);
			});
}
</script>
</head>
<body>
	<html:form action="/assignTask" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		<html:hidden property="task" name="assignTaskForm"/>
		<html:hidden property="tkBean.taskId" name="assignTaskForm"/>
		<html:hidden property="tkBean.assignedBy" name="assignTaskForm"/>
		
		<div class="container">

			<div class="divContent form-group has-info">
				<table width="50%">
					<colgroup>
						<col width="30%" class="tdLabel" />
						<col />
					</colgroup>
					<tr>
						<td class="tdLabel" align="right"><label>Task Name</label></td>
						<td><html:text styleClass="form-control" styleId="txtTaskName" name="assignTaskForm"
								property="tkBean.taskName"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Task DESC</label></td>
						<td><html:textarea property="tkBean.taskDesc" name="assignTaskForm" styleId="txtTaskDesc" styleClass="form-control"></html:textarea>
						</td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Est. Start Date</label></td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtEstStartDate"
								name="assignTaskForm" property="tkBean.estStartDateInString"></html:text>
						</td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Est. End Date</label></td>
						<td>
							<html:text styleClass="form-control datepicker" styleId="txtEstStartDate"
								name="assignTaskForm" property="tkBean.estEndDateInString"></html:text>
						</td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Assigned To
								</label></td>
						<td><html:hidden styleId="hdEmpId"
								name="assignTaskForm" property="tkBean.assignedTo" />
							<table width="100%">
								<tr>
									<td><html:text styleClass="form-control"
											styleId="txt" readonly="true" name="assignTaskForm"
											property="tkBean.assignedToName"></html:text></td>
									<td align="center"><a href="#" class="text-info"
										data-toggle="modal" data-target="#searchAssTo"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>

								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

		</div>
		
		<!-- popup buat ambil assignedTo -->
		<div class="modal fade" id="searchAssTo" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<html:form action="searchDeptHead" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Assign To</h4>
						</div>
						<div class="modal-body">
							<div class="container form-group">
								<table>
									<tr>
										<td>Search</td>
										<td style="padding-left: 15px"><select
											class="form-control" id="selSearchFieldDeptHead"
											style="width: 150px">
												<option value="employeeCode">Employee Code</option>
												<option value="employeeName">Employee Name</option>
												<option value="email">Email</option>
										</select></td>
										<td style="padding-left: 15px"><input type="text"
											id="txtSearchValueDeptHead" class="form-control" /></td>
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
									<logic:notEmpty name="lstDeptHead">
										<logic:iterate id="emp" name="lstDeptHead">
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
									<logic:empty name="lstDeptHead">
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