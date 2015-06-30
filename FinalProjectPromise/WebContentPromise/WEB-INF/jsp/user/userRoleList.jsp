<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Role</title>

<script src="js/jquery.js"></script>

<script type="text/javascript">
	function onBtnAddClick() {
		document.forms[0].task.value = 'add';
		document.forms[0].submit();
	}

	function search() {
		document.forms[0].currSearchField.value = document.forms[0].searchField.value;
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

		changePage(1);
	}

	function actionForm(task, id, nama) {
		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;

		if (task == "delete") {
			if (confirm("Are u sure want to delete General Holiday " + nama
					+ " ?")) {
				document.forms[0].submit();
			}

		} else {
			document.forms[0].submit();
		}
	}

	$(document)
			.ready(
					function() {
						$('.lnkMenuAccess')
								.on(
										'click',
										function() {
											showLoading();
											var userRoleId = $(this).closest(
													'tr').find('td').eq(0)
													.html();
											var userRoleCode = $(this).closest(
													'tr').find('td').eq(1)
													.html();
											var userRoleName = $(this).closest(
													'tr').find('td').eq(2)
													.html();

											$('#hdnUserRoleCodeId').val(
													userRoleId);
											$('#lblUserRoleName').val(
													userRoleName);

											$
													.ajax({
														type : "POST",
														url : "userRole.do",
														data : "task=openMenuAccess&selectedId="
																+ userRoleId,
														success : function(
																response) {
															response = response.trim();
															var strMenuId = response
																	.split("$")[0];
															var strAllowAdd = response
																	.split("$")[1];
															var strAllowBack = response
																	.split("$")[2];
															var strAllowSave = response
																	.split("$")[3];
															var strAllowApprove = response
																	.split("$")[4];
															var strAllowDecline = response
																	.split("$")[5];

															var listMenuId = strMenuId
																	.split("#");
															var listAllowAdd = strAllowAdd
																	.split("#");
															var listAllowBack = strAllowBack
																	.split("#");
															var listAllowSave = strAllowSave
																	.split("#");
															var listAllowApprove = strAllowApprove
																	.split("#");
															var listAllowDecline = strAllowDecline
																	.split("#");

															$(
																	'.chkSelectedMenu')
																	.each(
																			function() {

																				var menuId = $(
																						this)
																						.closest(
																								'tr')
																						.find(
																								'td')
																						.eq(
																								0)
																						.html();
																				var chkAllowAdd = $(
																						this)
																						.closest(
																								'tr')
																						.find(
																								'td')
																						.eq(
																								4)
																						.find(
																								'.chkIsAllowAdd');
																				var chkAllowBack = $(
																						this)
																						.closest(
																								'tr')
																						.find(
																								'td')
																						.eq(
																								5)
																						.find(
																								'.chkIsAllowBack');
																				var chkAllowSave = $(
																						this)
																						.closest(
																								'tr')
																						.find(
																								'td')
																						.eq(
																								6)
																						.find(
																								'.chkIsAllowSave');
																				var chkAllowApprove = $(
																						this)
																						.closest(
																								'tr')
																						.find(
																								'td')
																						.eq(
																								7)
																						.find(
																								'.chkIsAllowApprove');
																				var chkAllowDecline = $(
																						this)
																						.closest(
																								'tr')
																						.find(
																								'td')
																						.eq(
																								8)
																						.find(
																								'.chkIsAllowDecline');

																				var index = $
																						.inArray(
																								menuId,
																								listMenuId);

																				if (index > -1) {
																					$(
																							this)
																							.prop(
																									'checked',
																									true);
																					chkAllowAdd
																							.prop(
																									'checked',
																									listAllowAdd[index] == "true");
																					chkAllowBack
																							.prop(
																									'checked',
																									listAllowBack[index] == "true");
																					chkAllowSave
																							.prop(
																									'checked',
																									listAllowSave[index] == "true");
																					chkAllowApprove
																							.prop(
																									'checked',
																									listAllowApprove[index] == "true");
																					chkAllowDecline
																							.prop(
																									'checked',
																									listAllowDecline[index] == "true");
																				} else {
																					$(
																							this)
																							.prop(
																									'checked',
																									false);
																					chkAllowAdd
																							.prop(
																									'checked',
																									false);
																					chkAllowBack
																							.prop(
																									'checked',
																									false);
																					chkAllowSave
																							.prop(
																									'checked',
																									false);
																					chkAllowApprove
																							.prop(
																									'checked',
																									false);
																					chkAllowDecline
																							.prop(
																									'checked',
																									false);
																				}

																			});

															$('#searchDeptHead')
																	.modal();
															hideLoading();
														},
														error : function(e) {
															alert("Error: " + e);
															hideLoading();
														}

													});

											hideLoading();
										});

						$("#btnSaveUserRoleMenu")
								.on(
										'click',
										function() {
											showLoading();
											var listMenuId = "";
											var listAllowAdd = "";
											var listAllowBack = "";
											var listAllowSave = "";
											var listAllowApprove = "";
											var listAllowDecline = "";

											$('.chkSelectedMenu:checked')
													.each(
															function() {
																var menuId = $(
																		this)
																		.closest(
																				'tr')
																		.find(
																				'td')
																		.eq(0)
																		.html();
																var allowAdd = $(
																		this)
																		.closest(
																				'tr')
																		.find(
																				'td')
																		.eq(4)
																		.find(
																				'.chkIsAllowAdd')
																		.is(
																				':checked');
																var allowBack = $(
																		this)
																		.closest(
																				'tr')
																		.find(
																				'td')
																		.eq(5)
																		.find(
																				'.chkIsAllowBack')
																		.is(
																				':checked');
																var allowSave = $(
																		this)
																		.closest(
																				'tr')
																		.find(
																				'td')
																		.eq(6)
																		.find(
																				'.chkIsAllowSave')
																		.is(
																				':checked');
																var allowApprove = $(
																		this)
																		.closest(
																				'tr')
																		.find(
																				'td')
																		.eq(7)
																		.find(
																				'.chkIsAllowApprove')
																		.is(
																				':checked');
																var allowDecline = $(
																		this)
																		.closest(
																				'tr')
																		.find(
																				'td')
																		.eq(8)
																		.find(
																				'.chkIsAllowDecline')
																		.is(
																				':checked');

																if (listMenuId != '') {
																	listMenuId += '#';
																	listAllowAdd += '#';
																	listAllowBack += '#';
																	listAllowSave += '#';
																	listAllowApprove += '#';
																	listAllowDecline += '#';
																}
																listMenuId += menuId;
																listAllowAdd += allowAdd;
																listAllowBack += allowBack;
																listAllowSave += allowSave;
																listAllowApprove += allowApprove;
																listAllowDecline += allowDecline;
															});

											$
													.ajax({
														type : "POST",
														url : "userRole.do",
														data : "task=saveMenuAccess&selectedId="
																+ $(
																		"#hdnUserRoleCodeId")
																		.val()
																+ "&listMenuId="
																+ listMenuId
																+ "&listAllowAdd="
																+ listAllowAdd
																+ "&listAllowBack="
																+ listAllowBack
																+ "&listAllowSave="
																+ listAllowSave
																+ "&listAllowApprove="
																+ listAllowApprove
																+ "&listAllowDecline="
																+ listAllowDecline,
														success : function(
																response) {
															$('#searchDeptHead')
																	.modal(
																			'hide');
															hideLoading();
														},
														error : function(e) {
															alert("Error: " + e);
															hideLoading();
														}
													});
											
										});
						
						$('.lnkReportAccess').on('click',
							function() {
								showLoading(); 
								var userRoleId = $(this).closest(
									'tr').find('td').eq(0).html();
								var userRoleName = $(this).closest(
									'tr').find('td').eq(2).html();

								$('#hdUserRoleIdReport').val(
										userRoleId);
								$('#lblUserRoleNameReport').val(
										userRoleName);

								$.ajax({
									type : "POST",
									url : "userRole.do",
									data : "task=openReportAccess&selectedId="+ userRoleId,
									success : function(response) {
										
										var listReportId = response.trim().split("#");
											$('.chkSelectedReport').each(
												function() {
													var reportId = $(this).closest('tr').find(
																	'td').eq(0).html();
													var index = $.inArray(reportId,listReportId);
														if (index > -1) {
															$(this).prop('checked',true);
														} 
														else {
															$(this).prop('checked',false);
															}

																});

												$('#searchUserRoleReport')
														.modal();
										 		hideLoading(); 
											},
											error : function(e) {
												alert("Error: " + e);
										 		hideLoading(); 
											}

										});

					 			hideLoading(); 
								});
							
							$("#btnSaveUserRoleReport").on('click',
									function() {
				 						showLoading(); 
										var listReportId = "";

										$('.chkSelectedReport:checked').each(
											function() {
											var reportId = $(this).closest('tr')
													.find('td').eq(0).html();
											

															if (listReportId != '') {
																listReportId += '#';
																
															}
															listReportId += reportId;
															
														});

										$.ajax({
													type : "POST",
													url : "userRole.do",
													data : "task=saveReportAccess&selectedId="
															+ $("#hdUserRoleIdReport")
																	.val()
															+ "&listReportId="
															+ listReportId,
													success : function(
															response) {
														$('#searchUserRoleReport')
																.modal(
																		'hide');
											 			hideLoading(); 
													},
													error : function(e) {
														alert("Error: " + e);
													 	hideLoading(); 
													}
												});
										
									});
							
							$('.lnkDepartmentAccess').on('click',
									function() {
										showLoading(); 
										var userRoleId = $(this).closest(
											'tr').find('td').eq(0).html();
										var userRoleName = $(this).closest(
											'tr').find('td').eq(2).html();

										$('#hdUserRoleIdDepartment').val(
												userRoleId);
										$('#lblUserRoleNameDepartment').val(
												userRoleName);

										$.ajax({
											type : "POST",
											url : "userRole.do",
											data : "task=openDepartmentAccess&selectedId="+ userRoleId,
											success : function(response) {
												
												var listDepartmentId = response.trim().split("#");
													$('.chkSelectedDepartment').each(
														function() {
															var departmentId = $(this).closest('tr').find(
																			'td').eq(0).html();
															var index = $.inArray(departmentId,listDepartmentId);
																if (index > -1) {
																	$(this).prop('checked',true);
																} 
																else {
																	$(this).prop('checked',false);
																	}

																		});

														$('#searchUserRoleDepartment')
																.modal();
												 		hideLoading(); 
													},
													error : function(e) {
														alert("Error: " + e);
												 		hideLoading(); 
													}

												});

							 			hideLoading(); 
										});
									
									$("#btnSaveUserRoleDepartment").on('click',
											function() {
						 						showLoading(); 
												var listDepartmentId = "";

												$('.chkSelectedDepartment:checked').each(
													function() {
													var departmentId = $(this).closest('tr')
															.find('td').eq(0).html();
													

																	if (listDepartmentId != '') {
																		listDepartmentId += '#';
																		
																	}
																	listDepartmentId += departmentId;
																	
																});

												$.ajax({
															type : "POST",
															url : "userRole.do",
															data : "task=saveDepartmentAccess&selectedId="
																	+ $("#hdUserRoleIdDepartment")
																			.val()
																	+ "&listDeptId="
																	+ listDepartmentId,
															success : function(
																	response) {
																$('#searchUserRoleDepartment')
																		.modal(
																				'hide');
													 			hideLoading(); 
															},
															error : function(e) {
																alert("Error: " + e);
															 	hideLoading(); 
															}
														});
												
											});
					});
</script>
</head>
<body>
	<html:form action="/userRole" method="post">
		<html:hidden name="userRoleForm" property="task" />
		<html:hidden name="userRoleForm" property="selectedId" />


		<html:hidden property="currSearchValue" name="userRoleForm" />
		<html:hidden property="currSearchField" name="userRoleForm" />
		<html:hidden property="currPage" name="userRoleForm" />


		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="userRoleForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="userRoleCode">User Role Code</option>
								<option value="userRoleName">User Role Name</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text name="userRoleForm"
								property="searchValue" styleClass="form-control">
							</html:text></td>

						<td style="padding-left: 15px">
							<button type="button" onclick="search();" id="btnSearch"
								class="btn btn-info btn-icon" title="Search">

								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</td>
					</tr>
				</table>
			</div>


			<div class="divContent">
				<table class="table table-bordered" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td class="align-center">User Role Code</td>
							<td class="align-center">User Role Name</td>
							<td align="center">Menu Access</td>
							<td align="center">Report Access</td>
							<td align="center">Department Access</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="userRoleForm" property="arrList">
							<logic:iterate id="reg" name="userRoleForm" property="arrList">
								<tr>
									<td style="display: none"><bean:write name="reg"
											property="userRoleId" /></td>
									<td><bean:write name="reg" property="userRoleCode" /></td>
									<td><bean:write name="reg" property="userRoleName" /></td>

									<td align="center"><a href="#"
										class="text-info lnkMenuAccess">Menu Access</a></td>
									
									<td align="center"><a href="#" class="text-info lnkReportAccess">Reports Access</a></td>									
									
									<td align="center"><a href="#" class="text-info lnkDepartmentAccess">Department Access</a></td>
										
									<td align="center"><a class="text-success" href="#"
										onclick="actionForm('edit','<bean:write name="reg" property="userRoleId" />');"
										title="Edit"><span class="glyphicon glyphicon-pencil"
											aria-hidden="true"></span></a> &nbsp; <a href="#"
										class="text-danger"
										onclick="actionForm('delete','<bean:write name="reg" property="userRoleId" />','<bean:write name="reg" property="userRoleName" />');"
										title="Delete"><span class="glyphicon glyphicon-trash"
											aria-hidden="true"></span></a></td>

								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="userRoleForm" property="arrList">
							<tr>
								<td colspan="4" align="center" style="padding: 10px">No
									Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

			<!-- pop up to give menu access -->
			<div class="modal fade" id="searchDeptHead" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<html:form action="searchDeptHead" method="post">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">User Role Menu Access</h4>
							</div>
							<div class="modal-body">
								<table width="70%" style="margin-top: 20px; margin-bottom: 20px" class="form-group has-info">
									<tr>
										<td width="20%">User Role</td>
										<td><input type="hidden" id="hdnUserRoleCodeId" /><input
											type="text" disabled="disabled" id="lblUserRoleName"
											style="width: 300px" class="form-control"></input></td>
									</tr>
								</table>
								<div style="overflow: auto; height: 350px">
									<table width="100%" id="tblSearch"
										class="table table-striped table-hover table-bordered table-clickable">
										<thead>
											<tr>
												<th width="40px"></th>
												<th width="80px">Menu Code</th>
												<th>Menu Caption</th>
												<th width="40px">Allow Add</th>
												<th width="40px">Allow Back</th>
												<th width="40px">Allow Save</th>
												<th width="40px">Allow Approve</th>
												<th width="40px">Allow Decline</th>
											</tr>
										</thead>

										<tbody>
											<logic:notEmpty name="lstMenu">
												<logic:iterate id="menu" name="lstMenu">
													<tr class="rowSearch">
														<td style="display: none"><bean:write name="menu"
																property="menuId" /></td>
														<td align="center"><input type="checkbox"
															class="chkSelectedMenu" /></td>
														<td><bean:write name="menu" property="menuCode" /></td>
														<td><bean:write name="menu" property="menuCaption" /></td>
														<td align="center"><logic:equal name="menu"
																property="isAllowAdd" value="true">
																<input type="checkbox" class="chkIsAllowAdd" />
															</logic:equal></td>
														<td align="center"><logic:equal name="menu"
																property="isAllowBack" value="true">
																<input type="checkbox" class="chkIsAllowBack" />
															</logic:equal></td>
														<td align="center"><logic:equal name="menu"
																property="isAllowSave" value="true">
																<input type="checkbox" class="chkIsAllowSave" />
															</logic:equal></td>
														<td align="center"><logic:equal name="menu"
																property="isAllowApprove" value="true">
																<input type="checkbox" class="chkIsAllowApprove" />
															</logic:equal></td>
														<td align="center"><logic:equal name="menu"
																property="isAllowDecline" value="true">
																<input type="checkbox" class="chkIsAllowDecline" />
															</logic:equal></td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
											<logic:empty name="lstMenu">
												<tr>
													<td colspan="3" align="center">No Data Found</td>
												</tr>
											</logic:empty>
										</tbody>

									</table>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-info getValue"
									id="btnSaveUserRoleMenu">Save changes</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>

							</div>
						</html:form>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->

			<!-- pop up to give report access -->
				<div class="modal fade" id="searchUserRoleReport" tabindex="-1" 
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">User Role Report Access</h4>
							</div>
							<div class="modal-body">
								<table width="70%" style="margin-top: 20px; margin-bottom: 20px" class="form-group has-info">
									<tr>
										<td width="20%">User Role</td>
										<td><input type="hidden" id="hdUserRoleIdReport" /><input
											type="text" disabled="disabled" id="lblUserRoleNameReport"
											style="width: 300px" class="form-control"></input></td>
									</tr>
								</table>
								<div style="overflow: auto; height: 350px">
									<table width="100%" id="tblSearch"
										class="table table-striped table-hover table-bordered table-clickable">
										<thead>
											<tr>
												<th width="40px"></th>
												<th width="200px">Report Code</th>
												<th>Report Caption</th>

											</tr>
										</thead>

										<tbody>
											<logic:notEmpty name="lstReport">
												<logic:iterate id="menu" name="lstReport">
													<tr class="rowSearch">
														<td style="display: none"><bean:write name="menu"
																property="reportId" /></td>
														<td align="center"><input type="checkbox"
															class="chkSelectedReport" /></td>
														<td><bean:write name="menu" property="reportCode" /></td>
														<td><bean:write name="menu" property="reportCaption" /></td>
														
													</tr>
												</logic:iterate>
											</logic:notEmpty>
											<logic:empty name="lstReport">
												<tr>
													<td colspan="3" align="center">No Data Found</td>
												</tr>
											</logic:empty>
										</tbody>

									</table>
								</div>
							</div>
							
							<div class="modal-footer">
								<button type="button" class="btn btn-info getValue"
									id="btnSaveUserRoleReport">Save changes</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>

							</div>
					</div>
				
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			
			<!-- pop up to give department access -->
				<div class="modal fade" id="searchUserRoleDepartment" tabindex="-1" 
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">User Role Department Access</h4>
							</div>
							<div class="modal-body">
								<table width="70%" style="margin-top: 20px; margin-bottom: 20px" class="form-group has-info">
									<tr>
										<td width="20%">User Role</td>
										<td><input type="hidden" id="hdUserRoleIdDepartment" /><input
											type="text" disabled="disabled" id="lblUserRoleNameDepartment"
											style="width: 300px" class="form-control"></input></td>
									</tr>
								</table>
								<div style="overflow: auto; height: 350px">
									<table width="100%" id="tblSearch"
										class="table table-striped table-hover table-bordered table-clickable">
										<thead>
											<tr>
												<th width="40px"></th>
												<th width="200px">Department Code</th>
												<th>Department Name</th>

											</tr>
										</thead>

										<tbody>
											<logic:notEmpty name="lstDepartment">
												<logic:iterate id="dept" name="lstDepartment">
													<tr>
														<td style="display: none"><bean:write name="dept"
																property="deptId" /></td>
														<td align="center"><input type="checkbox"
															class="chkSelectedDepartment" /></td>
														<td><bean:write name="dept" property="deptCode" /></td>
														<td><bean:write name="dept" property="deptName" /></td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
											<logic:empty name="lstDepartment">
												<tr>
													<td colspan="3" align="center">No Data Found</td>
												</tr>
											</logic:empty>
										</tbody>

									</table>
								</div>
							</div>
							
							<div class="modal-footer">
								<button type="button" class="btn btn-info getValue"
									id="btnSaveUserRoleDepartment">Save changes</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>

							</div>
					</div>
				
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->

		</div>
		<html:hidden name="userRoleForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>