<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>General Holiday</title>

<script type="text/javascript">
	function onBtnAddClick(){
		//alert('add');
		document.forms[0].task.value = 'add';
		document.forms[0].submit();
	}
	
	function onBtnBackClick(){
		alert('back');
	}

</script>
</head>
<body>
	<html:form action="/generalHoliday" method="post">
		<html:hidden property="task" name="generalHolidayForm" />
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by</td>
						<td style="padding-left:15px;">
							<select id="selSearchField" class="form-control">	
								<option value="genHolidayName">General Holiday Name</option>
								<option value="genHolidayDate">General Holiday Date</option>
							</select>
						</td>
						<td style="padding-left:15px"><input type="text" class="form-control" /></td>
						<td style="padding-left:15px"><button id="btnSearch" class="btn btn-info btn-icon"
								title="Back">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button></td>
					</tr>
				</table>
			</div>
			
			<div class="divContent">
				<table class="table table-bordered" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td>General Holiday ID</td>
							<td>General Holiday Name</td>
							<td>General Holiday Date</td>
							<td>Is Generated</td>
							<td class="align-center"></td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="generalHolidayForm" property="arrList">
							<logic:iterate id="reg" name="generalHolidayForm" property="arrList">
								<tr>
									<td></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="generalHolidayForm" property="arrList">
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