<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Entry</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnBackClick() {
		location.href = "department.do";
	}

	function onBtnSaveClick() {
		if(validateForm()){
			document.forms[0].task.value = "save";
			document.forms[0].submit();
		}
	}

	$(document).ready(function() {
		registerSearchHeadDeptEvent();
	});

	function search() {
		showLoading();
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
				hideLoading();
			},
			error : function(e) {
				alert("Error: " + e);
				hideLoading(); 
			}
		});
	}

	function registerSearchHeadDeptEvent() {
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
	
	function validateForm(){
		
		var deptCode 		= document.getElementById("txtDeptCode").value;
		var deptName 		= document.getElementById("txtDeptName").value;
		var str = "";
		var isValid = true;
		
		if(deptCode.trim() == '') {
			str+= "<li>Department Code can not be empty!</li>";
			isValid = false;
		}
		else if(deptCode.length > 11) {
			str+= "<li>Department Code can not be more than 10 characters!</li>";
			isValid = false;
		}
		
		if(deptName.trim() == '') {
			str+= "<li>Department Name can not be empty!</li>";
			isValid = false;
		}
		else if(deptName.length > 101) {
			str+= "<li>Department Name can not be more than 100 characters!</li>";
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
	<html:form action="/department" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="departmentForm" property="task" />
		<html:hidden name="departmentForm" property="isAdd" />
		<html:hidden styleId="hdnDeptId" name="departmentForm"
			property="selectedDept.deptId" />

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
						<td class="tdLabel" align="right"><label>Department
								Code</label></td>
						<td><html:text styleClass="form-control"
								styleId="txtDeptCode" name="departmentForm"
								property="selectedDept.deptCode" style="width:150px"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Department
								Name</label></td>
						<td><html:text styleClass="form-control"
								styleId="txtDeptName" name="departmentForm"
								property="selectedDept.deptName"></html:text></td>
					</tr>
					<logic:equal value="false" property="isAdd" name="departmentForm">
						<tr>
							<td class="tdLabel" align="right"><label>Department
									Head</label></td>
							<td><html:hidden styleId="hdnDeptHeadId"
									name="departmentForm" property="selectedDept.deptHeadId" />
								<table width="100%">
									<tr>
										<td><html:text styleClass="form-control"
												styleId="txtDeptHead" readonly="true" name="departmentForm"
												property="selectedDept.deptHeadName"></html:text></td>
										<td align="center"><a href="#" class="text-info"
											data-toggle="modal" data-target="#searchDeptHead"> <span
												class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>

									</tr>
								</table></td>
						</tr>
					</logic:equal>
				</table>
			</div>

		</div>

		<div class="modal fade" id="searchDeptHead" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<html:form action="searchDeptHead" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Depatment Head Candidate</h4>
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