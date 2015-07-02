<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Entry</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	function onBtnBackClick() {
		location.href = "employee.do";
	}

	function onBtnSaveClick() {
		if(validateForm()){
			document.forms["employeeForm"].task.value = "save";
			document.forms["employeeForm"].submit();
		}
	}

	$(document).ready(function() {
		registerSearchRankEvent();
		registerSearchSpvEvent();
		registerSearchDeptEvent();
	});

	/* SPV di sini */
	function registerSearchSpvEvent() {
		$('.rowSearchSpv').on('click', function() {
			var value = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#hdSupervisorId').val(value);
			$('#txtSupervisor').val(text);
		});
	}

	function searchSupervisor() {
		showLoading();
		var searchField = $('#selSearchFieldSpv').val();
		var searchValue = $('#txtSearchValueSpv').val();
		var deptId		= $('#hdDeptId').val();
		var rankId		= $('#hdnSelectedRankId').val();
		$.ajax({
					type : "POST",
					url : "searchSpv.do",
					data : "searchField=" + searchField + "&searchValue="
							+ searchValue+"&deptId="+deptId+ "&rankId="+rankId,
					success : function(response) {
						$("#tblSearchSpv").find("tr:gt(0)").remove();
						$("#tblSearchSpv").append(response);
						registerSearchSpvEvent();
						hideLoading();
					},
					error : function(e) {
						alert("Error: " + e);
						hideLoading();
					}
				});
	}

	/* departement di sini  */
	
	function registerSearchDeptEvent() {
		$('.rowSearchDept').on('click', function() {
			var value = $(this).find('td').eq(0).html().trim();
			var text = $(this).find('td').eq(2).html().trim();
			$('#hdDeptId').val(value);
			$('#txtDept').val(text);
			$('#hdSupervisorId').val('');
			$('#txtSupervisor').val('');
			searchSupervisor();
		});
	}

	function searchDepartment() {
		showLoading();
		var searchField = $('#selSearchFieldDept').val();
		var searchValue = $('#txtSearchValueDept').val();

		$.ajax({
					type : "POST",
					url : "searchDept.do",
					data : "searchField=" + searchField + "&searchValue="
							+ searchValue,
					success : function(response) {
						$("#tblSearchDept").find("tr:gt(0)").remove();
						$("#tblSearchDept").append(response);
						registerSearchDeptEvent();
						hideLoading();
					},
					error : function(e) {
						alert("Error: " + e);
						hideLoading();
					}
				});
	}

	/* rank di sini */
	function registerSearchRankEvent() {
		$('.rowSearchRank').on(
				'click',
				function() {
					var value = $(this).find('td').eq(0).html();
					var text = $(this).find('td').eq(2).html();
					$('#hdRankId').val(value);
					$('#hdnSelectedRankId').val(value);
					
					$('#txtRankName').val(text);
					$('#hdSupervisorId').val('');
					$('#txtSupervisor').val('');
					searchSupervisor();
				});
	}
	
	function searchRank() {
		showLoading();
		var searchField = $('#selSearchFieldRank').val();
		var searchValue = $('#txtSearchValueRank').val();
		$
				.ajax({
					type : "POST",
					url : "searchRank.do",
					data : "searchField=" + searchField + "&searchValue="
							+ searchValue,
					success : function(response) {
						$("#tblSearchRank").find("tr:gt(0)").remove();
						$("#tblSearchRank").append(response);
						registerSearchRankEvent();
						hideLoading();
					},
					error : function(e) {
						alert("Error: " + e);
						hideLoading();
					}
				});
	}
	
function validateForm(){
		var empCode 	= document.getElementById("txtEmpCode").value;
		var empName 	= document.getElementById("txtEmpName").value;
		var email 		= document.getElementById("txtEmail").value;
		var address 	= document.getElementById("txtAddress").value;
		var phone 		= document.getElementById("txtPhone").value;
		var dept 		= document.getElementById("txtDept").value;
		var rank 		= document.getElementById("txtRankName").value;
			
		var str = "";
		var isValid = true;
		
		if(empCode.trim() == '') {
			str+= "<li>Employee Code can not be empty!</li>";
			isValid = false;
		}
		else if(empCode.length > 21) {
			str+= "<li>Employee Code can not be more than 20 characters!</li>";
			isValid = false;
		}
		
		if(empName.trim() == '') {
			str+= "<li>Employee Name can not be empty!</li>";
			isValid = false;
		}
		else if(empName.length > 51) {
			str+= "<li>Employee Name can not be more than 50 characters!</li>";
			isValid = false;
		}
		
		if(email.trim() == '') {
			str+= "<li>Email can not be empty!</li>";
			isValid = false;
		}
		else if(email.length > 51) {
			str+= "<li>Email can not be more than 50 characters!</li>";
			isValid = false;
		}
		
		if(address.trim() == '') {
			str+= "<li>Address can not be empty!</li>";
			isValid = false;
		}
		else if(address.length > 101) {
			str+= "<li>Address can not be more than 100 characters!</li>";
			isValid = false;
		}
		
		if(phone.trim() == '') {
			str+= "<li>Phone number can not be empty!</li>";
			isValid = false;
		}
		else if(phone.length > 26) {
			str+= "<li>Phone number can not be more than 25 characters!</li>";
			isValid = false;
		}
		
		if(dept.trim() == '') {
			str+= "<li>Department can not be empty!</li>";
			isValid = false;
		}
		
		if(rank.trim() == '') {
			str+= "<li>Rank can not be empty!</li>";
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
	<html:form action="/employee" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="employeeForm" property="task" />
		<html:hidden name="employeeForm" property="isAdd" />
		<html:hidden name="employeeForm" property="selectedEmp.employeeId" />
		<html:hidden property="selectedEmp.rankId" name="employeeForm"/>
		
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
						<td class="tdLabel" align="right"><label>Employee
								Code</label></td>
						<td><html:text styleClass="form-control" name="employeeForm" styleId="txtEmpCode"
								property="selectedEmp.employeeCode" style="width:150px"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Employee
								Name</label></td>
						<td><html:text styleClass="form-control" name="employeeForm" styleId="txtEmpName"
								property="selectedEmp.employeeName"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Gender</label></td>
						<td>

							<table width="50%">
								<tr align="left">
									<td>
									<div class="radio radio-info">
									    <label>
									      <html:radio name="employeeForm"
											property="selectedEmp.gender" value="M">Male</html:radio>
									    </label>
									  </div>
									</td>
									<td>
									<div class="radio radio-info">
									    <label>
									      <html:radio name="employeeForm"
											property="selectedEmp.gender" value="F">Female</html:radio>
									    </label>
									  </div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Email </label></td>
						<td><html:text styleClass="form-control" name="employeeForm" styleId="txtEmail"
								property="selectedEmp.email"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Address</label></td>
						<td><html:textarea styleClass="form-control" rows="3" styleId="txtAddress"
								name="employeeForm" property="selectedEmp.address"></html:textarea></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Phone Number</label></td>
						<td><html:text styleClass="form-control" name="employeeForm" styleId="txtPhone"
								property="selectedEmp.phoneNumber"></html:text></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Department</label></td>
						<td><html:hidden styleId="hdDeptId" name="employeeForm" 
								property="selectedEmp.deptId" />
							<table width="100%">
								<tr>
									<td>
										<html:text styleClass="form-control"
											styleId="txtDept" readonly="true" name="employeeForm"
											property="selectedEmp.deptName">
										</html:text>
									</td>
									<td align="center"><a href="#" class="text-info"
										data-toggle="modal" data-target="#searchDept"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>

								</tr>
							</table></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Rank</label></td>
						
						<td>
							<html:hidden name="employeeForm" property="selectedRankId" styleId="hdnSelectedRankId"/>
							<html:hidden styleId="hdRankId" name="employeeForm" property="selectedEmp.rankId" />
							<table width="100%">
								<tr>
									<td><html:text styleClass="form-control"
											styleId="txtRankName" readonly="true" name="employeeForm"
											property="selectedEmp.rankName"></html:text></td>
									<td align="center"><a href="#" class="text-info"
										data-toggle="modal" data-target="#searchRank"> <span
											class="glyphicon glyphicon-edit" aria-hidden="true" /></a></td>

								</tr>
							</table></td>
					</tr>
					<tr>
						<td class="tdLabel" align="right"><label>Supervisor</label></td>
						<td>
							<html:hidden styleId="hdSupervisorId" name="employeeForm" property="selectedEmp.supervisorId" />
							<table width="100%">
								<tr>
									<td>
										<html:text styleClass="form-control"
											styleId="txtSupervisor" readonly="true" name="employeeForm"
											property="selectedEmp.supervisorName">
										</html:text>
									</td>
									<td align="center">
										<a href="#" class="text-info" data-toggle="modal" data-target="#searchSpv"> 
										<span class="glyphicon glyphicon-edit" aria-hidden="true" />
										</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

		</div>

		<!-- Fix untuk Dept-->
		<div class="modal fade" id="searchDept" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Rank Option</h4>
						</div>
						<div class="modal-body">
							<div class="container form-group has-info">
								<table>
									<tr>
										<td>Search</td>
										<td style="padding-left: 15px"><select
											class="form-control" id="selSearchFieldDept"
											style="width: 150px">
												<option value="deptCode">Dept. Code</option>
												<option value="deptName">Dept. Name</option>
												<option value="deptHeadName">Dept. Head Name</option>
										</select></td>
										<td style="padding-left: 15px">
											<input type="text" id="txtSearchValueDept" class="form-control" />
										</td>
										<td style="padding-left: 15px">
										<button type="button"
												onclick="searchDepartment();" id="btnSearch"
												class="btn btn-raised btn-info btn-icon" title="Back">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
											</button></td>
									</tr>
								</table>
							</div>

							<table width="100%" id="tblSearchDept"
								class="table table-striped table-hover table-bordered table-clickable">
								<thead>
									<tr>
										<th>Dept. Code</th>
										<th>Dept. Name</th>
										<th>Dept. Head Name</th>
									</tr>
								</thead>
								<tbody>
									<logic:notEmpty name="listOfDepartment">
										<logic:iterate id="emp" name="listOfDepartment">
											<tr data-dismiss="modal" class="rowSearchDept">
												<td style="display: none">
													<bean:write name="emp" property="deptId" />
												</td>
												<td width="150px">
													<bean:write name="emp" property="deptCode" />
												</td>
												<td>
													<bean:write name="emp" property="deptName" />
												</td>
												<td width="150px">
													<bean:write name="emp" property="deptHeadName" />
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="listOfDepartment">
										<tr>
											<td colspan="3" align="center">No Data Found</td>
										</tr>
									</logic:empty>
								</tbody>
							</table>
						</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<!-- FIX UNTUK DEPT-->
		

		<!-- FIX UNTUK RANK -->
		<div class="modal fade" id="searchRank" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Rank Option</h4>
						</div>
						<div class="modal-body">
							<div class="container form-group has-info">
								<table>
									<tr>
										<td>Search</td>
										<td style="padding-left: 15px"><select
											class="form-control" id="selSearchFieldRank"
											style="width: 150px">
												<option value="rankCode">Rank Code</option>
												<option value="rankName">Rank Name</option>
										</select></td>
										<td style="padding-left: 15px"><input type="text"
											id="txtSearchValueRank" class="form-control" /></td>
										<td style="padding-left: 15px"><button type="button"
												onclick="searchRank();" id="btnSearch"
												class="btn btn-raised btn-info btn-icon" title="Back">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
											</button></td>
									</tr>
								</table>
							</div>

							<table width="100%" id="tblSearchRank"
								class="table table-striped table-hover table-bordered table-clickable">
								<thead>
									<tr>
										<th>Rank Code</th>
										<th>Rank Name</th>
										<th>Rank Level</th>
									</tr>
								</thead>
								<tbody>
									<logic:notEmpty name="listRank">
										<logic:iterate id="emp" name="listRank">
											<tr data-dismiss="modal" class="rowSearchRank">
												<td style="display: none"><bean:write name="emp"
														property="rankId" /></td>
												<td width="150px"><bean:write name="emp"
														property="rankCode" /></td>
												<td><bean:write name="emp" property="rankName" /></td>
												<td width="150px"><bean:write name="emp"
														property="rankLevel" /></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="listRank">
										<tr>
											<td colspan="3" align="center">No Data Found</td>
										</tr>
									</logic:empty>
								</tbody>
							</table>
						</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<!-- 	FIX UNTUK RANK -->

		<div class="modal fade" id="searchSpv" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Supervisor Option</h4>
						</div>
						<div class="modal-body">
							<div class="container form-group has-info">
								<table>
									<tr>
										<td>Search</td>
										<td style="padding-left: 15px">
											<select
											class="form-control" id="selSearchFieldSpv"
											style="width: 150px">
												<option value="employeeCode">Employee Code</option>
												<option value="employeeName">Employee Name</option>
											</select>
										</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtSearchValueSpv" class="form-control" />
										</td>
										<td style="padding-left: 15px">
											<button type="button"
												onclick="searchSupervisor();" id="btnSearch"
												class="btn btn-raised btn-info btn-icon" title="Back">
												<span class="glyphicon glyphicon-search" aria-hidden="true">
												</span>
											</button>
										</td>
									</tr>
								</table>
							</div>

							<table width="100%" id="tblSearchSpv"
								class="table table-striped table-hover table-bordered table-clickable">
								<thead>
									<tr>
										<th>Employee Code</th>
										<th>Employee Name</th>
										<th>Employee Email</th>
									</tr>
								</thead>
								<tbody>
									<logic:notEmpty name="listOfSupervisor">
										<logic:iterate id="emp" name="listOfSupervisor">
											<tr data-dismiss="modal" class="rowSearchSpv">
												<td style="display: none">
													<bean:write name="emp" property="employeeId" />
												</td>
												<td width="150px">
													<bean:write name="emp" property="employeeCode" />
												</td>
												<td>
													<bean:write name="emp" property="employeeName" />
												</td>
												<td width="150px">
													<bean:write name="emp" property="email" />
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="listOfSupervisor">
										<tr>
											<td colspan="3" align="center">No Data Found</td>
										</tr>
									</logic:empty>
								</tbody>
							</table>
						</div>
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
