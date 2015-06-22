<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LIST ASSIGN TASK</title>
<script src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.firstBtn').each(function() {
			var taskCode = $(this).closest('tr').find('.hdnTaskStatus').val();
			if (taskCode == 'TA_STAT_02') {
				$(this).addClass('glyphicon glyphicon-pencil');
				$(this).attr("title","Edit Task");
			} 		
			else if (taskCode == 'TA_STAT_04') {
				$(this).addClass('glyphicon glyphicon-ok');
				$(this).attr("title","Approve Task");
			}
			else if (taskCode == 'TA_STAT_06') {
				$(this).addClass('glyphicon glyphicon-play');
				$(this).attr("title","Resume Task");
			}
			else
				$(this).hide();
		});
		$('.secondBtn').each(function() {
			var taskCode = $(this).closest('tr').find('.hdnTaskStatus').val();
			if (taskCode == 'TA_STAT_02') {
				$(this).addClass('glyphicon glyphicon-remove');
				$(this).attr("title","Cancel Task");
			} else if (taskCode == 'TA_STAT_03') {
				$(this).addClass('glyphicon glyphicon-pause');
				$(this).attr("title","Pause Task");
			}
			else if (taskCode == 'TA_STAT_04') {
				$(this).addClass('glyphicon glyphicon-remove');
				$(this).attr("title","Decline Task");
			}
			else if (taskCode == 'TA_STAT_06') {
				$(this).addClass('glyphicon glyphicon-stop');
				$(this).attr("title","Stop Task");
			}
			else
				$(this).hide();
		});
		
	});
	function changeStatus() {
	
				
	}
</script>
</head>
<body>
	<html:form action="/assignTask" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden property="task" name="assignTaskForm" />
		<html:hidden property="currSearchField" name="assignTaskForm" />
		<html:hidden property="currSearchValue" name="assignTaskForm" />
		<html:hidden property="currPage" name="assignTaskForm" />

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left: 15px;"><html:select
								name="assignTaskForm" property="searchField"
								styleId="selSearchField" styleClass="form-control">
								<html:option value="">-- All --</html:option>
								<option value="taskName">task Name</option>
								<option value="taskDesc">task DESC</option>
							</html:select></td>
						<td style="padding-left: 15px"><html:text
								name="assignTaskForm" property="searchValue"
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
							<td>Task Name</td>
							<td>Assign By</td>
							<td>Assign To</td>
							<td>Task Progress</td>
							<td>Task Status</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="assignTaskForm" property="arrList">
							<logic:iterate id="reg" name="assignTaskForm" property="arrList">
								<tr>
									<td><bean:write name="reg" property="taskName" /></td>
									<td><bean:write name="reg" property="assignedByName" /></td>
									<td><bean:write name="reg" property="assignedToName" /></td>
									<td><bean:write name="reg" property="taskProgress" /></td>
									<td><html:hidden name="reg" property="taskStatus" styleClass="hdnTaskStatus" /> 
										<bean:write name="reg" property="taskStatusName"/>
									</td>
									<td align="center">
										<a class="text-success" href="#" id="tes"
										onclick="changeStatus()">
											<span class="firstBtn" aria-hidden="true" id="first"></span>
										</a>
										&nbsp; 
										<a class="text-success" href="#"
										onclick="">
											<span class="secondBtn" aria-hidden="true"></span>
										</a>
									

									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="assignTaskForm" property="arrList">
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

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>