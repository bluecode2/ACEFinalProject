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
});
</script>


<title>Insert title here</title>
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
		
			<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="projectApprovalForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<html:option value="">-- All --</html:option>
								<option value="taskName">task Name</option>
								<option value="taskDesc">task DESC</option>
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
							<td>Project Code</td>
							<td>Project Name</td>
							<td>Estimate Start Date</td>
							<td>Estimate End Date</td>	
							<td>Actual Start Date</td>
							<td>Actual Start Date</td>
							<td>Project Member</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="projectApprovalForm" property="arrList">
							<logic:iterate id="reg" name="projectApprovalForm" property="arrList">
								<tr>
									<td>
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
									<td><bean:write name="reg" property="estStartDateInString" /></td>
									<td><bean:write name="reg" property="estEndDateInString" /></td>
									<td><bean:write name="reg" property="actStartDateInString" /></td>
									<td><bean:write name="reg" property="actEndDateInString" /></td>
									<td>
										<a href="#" class="text-info linkMember">
											member
										</a>
									</td>
									<td align="center">
										<a class="text-success firstBtn" href="#" id="tes"
										onclick="changeStatusFirstBtn('<bean:write name="reg" property="projectId" />','<bean:write name="reg" property="taskStatus"/>')">
											<span aria-hidden="true"></span>
										</a>
										&nbsp; 
										<a href="#" class="text-info secondBtn">
											<span aria-hidden="true"></span>
										</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="projectApprovalForm" property="arrList">
							<tr>
								<td colspan="4" align="center" style="padding: 10px">No
									Data Found</td>
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
										<td style="padding-left: 15px">Project Code</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtProCode" class="form-control" disabled="disabled" />
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px">Project Name</td>
										<td style="padding-left: 15px">
											<input type="text" id="txtProName" class="form-control" disabled="disabled" />
										</td>
										
									</tr>
									<tr>
										<td style="padding-left: 15px">
											Task Desc 
										</td>
										<td style="padding-left: 15px">
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
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
		
	</html:form>
</body>
</html>