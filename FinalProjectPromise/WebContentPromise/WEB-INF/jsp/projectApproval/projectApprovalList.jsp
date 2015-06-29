<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('.linkDesc').on('click',function(){
		var proDesc = $(this).closest('tr').find('.hdProjectDesc').val();
		var proName = $(this).closest('tr').find('.hdProjectName').val();
		var proCode = $(this).closest('tr').find('.hdProjectCode').val();
		$('#txtProDesc').val(proDesc);
		$('#txtProName').val(proName);
		$('#txtProCode').val(proCode);
		$('#showDesc').modal();
		
	});
	
	
	$('.linkMember').on('click',function(){
		var selecId = $(this).closest('tr').find('.hdProjectId').val();

		$.ajax({
			type : "POST",
			url : "projectApproval.do",
			data : "task=listMembers&selectedId="
					+ selecId,
			success : function(response) {
				$("#tblShow").find("tr:gt(0)").remove();
				$("#tblShow").append(response);
				$('#showMember').modal();
			},
			error : function(e) {
				alert("Error: " + e);
			}

		});
	});
	
});
function search() {
	document.forms[0].currSearchField.value = document.forms[0].searchField.value;
	document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;

	changePage(1);
}

function goToEvaluate(id) {
	document.forms[0].task.value = "evaluate";
	document.forms[0].selectedId.value = id;
	document.forms[0].submit();
}
</script>


<title>Project Approval List</title>
</head>
<body>
	<html:form action="/projectApproval" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>
		
		<html:hidden property="task" name="projectApprovalForm"/>
		<html:hidden property="currPage" name="projectApprovalForm"/>
		<html:hidden property="currSearchValue" name="projectApprovalForm"/>
		<html:hidden property="currSearchField" name="projectApprovalForm"/>
		<html:hidden property="selectedId" name="projectApprovalForm" styleClass="hdSelectedId"/>
		<html:hidden property="selectedProjectId" name="projectApprovalForm" styleClass="hdSelectedProjectId"/>
			<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="projectApprovalForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<option value="projectCode">Project Code</option>
								<option value="projectName">Project Name</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="projectApprovalForm" property="searchValue"
								styleClass="form-control" /></td>
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
							<td class="align-center">Project Code</td>
							<td class="align-center">Project Name</td>
							<td class="align-center">Estimate Date</td>
							<td class="align-center">Actual Date</td>
							<td class="align-center">Project Member</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectApprovalForm" property="arrList">
							<logic:iterate id="reg" name="projectApprovalForm" property="arrList">
								<tr valign="middle">
									<td><html:hidden property="projectId" name="reg" styleClass="hdProjectId"/>
										<html:hidden property="projectDesc" name="reg" styleClass="hdProjectDesc"/>
										<html:hidden property="projectCode" name="reg" styleClass="hdProjectCode"/>
										<html:hidden property="projectName" name="reg" styleClass="hdProjectName"/>
										<bean:write name="reg" property="projectCode" />
									</td>
									<td>
										<a href="#" class="text-info linkDesc">
											<bean:write name="reg" property="projectName" />
										</a>
									</td>
									<td align="center">
							          <logic:notEmpty name="reg" property="estStartDateInString">
							           <bean:write name="reg" property="estStartDateDisplay" /> to 
							            <logic:notEmpty name="reg" property="estEndDateInString">
							             <bean:write name="reg" property="estEndDateDisplay" />
							            </logic:notEmpty>
							            <logic:empty  name="reg" property="estEndDateInString">
							             -
							            </logic:empty>
							            <br/>(<bean:write name="reg" property="estMainDays" /> main days)
							          </logic:notEmpty>
							          <logic:empty name="reg" property="estStartDateInString">
							           -
							          </logic:empty>
							         </td>
									<td align="center">
										<logic:notEmpty name="reg" property="actStartDateInString">
											<bean:write name="reg" property="actStartDateDisplay" /> to 
												<logic:notEmpty name="reg" property="actEndDateInString">
													<bean:write name="reg" property="actEndDateDisplay" />
												</logic:notEmpty>
												<logic:empty  name="reg" property="actEndDateInString">
													-
												</logic:empty>
												<br/>(
									             <logic:notEmpty name="reg" property="actMainDays">
									              <bean:write name="reg" property="actMainDays" />
									             </logic:notEmpty>
									             <logic:empty name="reg" property="actMainDays">
									              -
									             </logic:empty>
									             main days)
										</logic:notEmpty>
										<logic:empty name="reg" property="actStartDateInString">
											-
										</logic:empty>
									<td>
										<a href="#" class="text-info linkMember">
											Project Member
										</a>
									</td>
									<td align="center">
										<button type="button" property="" onclick="goToEvaluate('<bean:write name="reg" property="projectId" />');"  style="margin: 0px" class="btn btn-xs btn-info goEvaluate">Evaluate</button>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="projectApprovalForm" property="arrList">
							<tr>
								<td colspan="6" align="center" style="padding: 10px">No
									Data Found
								</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>
		</div>
		
		
		<!-- pop up to show Desc -->	
			<div class="modal fade" id="showDesc" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
							<div class="form-group">
								<table width="100%">
									<tr>
										<td style="padding-left: 15px">Project Code </td>
										<td >
											<input type="text" id="txtProCode" class="form-control" disabled="disabled" />
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px">Project Name</td>
										<td >
											<input type="text" id="txtProName" class="form-control" disabled="disabled" />
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px">
											Task Desc 
										</td>
										<td >
											<textarea rows="3" cols="3" class="form-control"  id="txtProDesc" disabled="disabled"></textarea>
										</button></td>
									</tr>
								</table>
							</div>

						</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		
			<!-- pop up to show member -->	
			<div class="modal fade" id="showMember" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Project Member</h4>
							<br/>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<table class="table table-bordered" cellspacing="0" id="tblShow" style="margin-top: 10px;" width="100%" class="tableContent">
									<tr>
										<th style="padding-left: 15px">Project Member Name</th>
										<th style="padding-left: 15px">Project Member Role</th>
										
									</tr>
										<logic:notEmpty name="projectApprovalForm" property="arrMember">
											<logic:iterate id="reg" name="projectApprovalForm" property="arrMember">
											<tr>
												<td style="padding-left: 15px">	<bean:write name="reg" property="empName" /></td>
												<td style="padding-left: 15px">	<bean:write name="reg" property="projRoleName" /></td>
											</tr>
											</logic:iterate>
										</logic:notEmpty>

								
								</table>
							</div>

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