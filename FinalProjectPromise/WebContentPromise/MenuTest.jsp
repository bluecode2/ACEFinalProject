<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Index</title>

<!-- CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/material.ripples.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<!-- JavaScript -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/material.ripples.min.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
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
									url : "userRoleReport.do",
									data : "task=openReportAccess&selectedId="+ userRoleId,
									success : function(response) {
										var strReportId = response.split("$")[0];
										var listReportId = strReportId.split("#");
											$('.chkSelectedMenu').each(
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

						$('.chkSelectedMenu:checked').each(
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

	});


</script>

</head>
<body>

	<!-- userRoleReportForm -->
		<table class="table table-bordered" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td class="align-center">User Role Code</td>
							<td class="align-center">User Role Name</td>
							<td align="center">Menu Access</td>
				

						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="display: none">1</td>
							<td>Code</td>
							<td>Name</td>
							<td align="center"><a href="#" class="text-info lnkReportAccess">Reports Access</a></td>
						</tr>
					</tbody>
				</table>
		
			
	
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
								<table width="70%" style="margin-top: 20px; margin-bottom: 20px">
									<tr>
										<td width="20%">User Role</td>
										<td><input type="hidden" id="hdUserRoleIdReport" /><input
											type="text" disabled="disabled" id="lblUserRoleNameReport"
											style="width: 300px"></input></td>
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
																property="menuId" /></td>
														<td align="center"><input type="checkbox"
															class="chkSelectedMenu" /></td>
														<td><bean:write name="menu" property="menuCode" /></td>
														<td><bean:write name="menu" property="menuCaption" /></td>
														
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

</body>
</html>