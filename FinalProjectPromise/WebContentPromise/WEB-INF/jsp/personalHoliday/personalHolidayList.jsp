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
	function onBtnAddClick(){
		//alert('add');
		document.forms[0].task.value = 'add';
		document.forms[0].submit();
	}
	
	function search() {
		document.forms[0].task.value = 'search';
		document.forms[0].currSearchField.value = 'byDate';
		document.forms[0].currSearchValue.value = document.forms[0].searchValue.value;
		document.forms[0].currSearchValue2.value = document.forms[0].searchValue2.value;
		changePage(1);
	}
	
	function actionForm(task, id, nama) {
		document.forms[0].task.value = task;
		document.forms[0].selectedId.value = id;

		if (task == "delete") {
			if (confirm("Are u sure want to delete General Holiday " + nama + " ?")) {
			    document.forms[0].submit();
			}	
		} 
		else {
			document.forms[0].submit();
		}
	}
	
	$(document).ready(function() {
		$(".datepicker").attr("data-provide", "datepicker");
	});
</script>
</head>
<body>
	<html:form action="/personalHoliday" method="post">
		<html:hidden name="personalHolidayForm" property="task" />
		<html:hidden name="personalHolidayForm" property="selectedId"/>
		
		<html:hidden property="currSearchValue" name="personalHolidayForm"/>
		<html:hidden property="currSearchValue2" name="personalHolidayForm"/>
		<html:hidden property="currSearchField" name="personalHolidayForm"/>
		
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch form-group has-info" style="float: right;">
				<table>
					<tr>
						<td>Search by Date</td>
<!-- 						<td style="padding-left: 15px;"> -->
<%-- 							<html:select name="personalHolidayForm" property="searchField" styleId="selSearchField" styleClass="form-control"> --%>
<!-- 								<option value="holidayDesc">Personal Holiday Name</option> -->
<!-- 								<option value="holidayDateInString">Personal Holiday Date</option> -->
<%-- 							</html:select> --%>
<!-- 						</td> -->
						<td style="padding-left: 15px"><html:text
								styleClass="form-control datepicker" styleId="txtGenHolDate"
								name="personalHolidayForm" property="searchValue"></html:text></td>
						<td style="padding-left: 15px">-</td>
						<td style="padding-left: 15px"><html:text
								styleClass="form-control datepicker" styleId="txtGenHolDate"
								name="personalHolidayForm" property="searchValue2"></html:text></td>
								
						<td style="padding-left: 15px">
							<button type="button" onclick="search();" id="btnSearch"
								class="btn btn-raised btn-info btn-icon" title="Search">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</td>
					</tr>
				</table>
			</div>
			<logic:notEmpty name="validationMessage">
						<br/>
							<div class="col-md-6 alert alert-dismissable alert-<bean:write name="validationType" />" role="alert">
								<button type="button" class="close" data-dismiss="alert">�</button>
								<strong><bean:write name="validationMessage" /></strong>
							</div>
			</logic:notEmpty>
			
			<div class="divContent">
				<table class="table table-striped table-bordered table-hover" cellspacing="0"
					style="margin-top: 10px;" width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td class="align-center">Personal Holiday Date</td>
							<td class="align-center">Employee Name</td>
							<td class="align-center">Personal Holiday Name</td>
							<td class="align-center">Holiday Type</td>
							<td class="align-center">Action</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="personalHolidayForm" property="arrList">
							<logic:iterate id="reg" name="personalHolidayForm" property="arrList">
								<tr>
									<td><bean:write name="reg" property="holidayDateDisplay" /></td>
									<td><bean:write name="reg" property="employeeName" /></td>
									<td><bean:write name="reg" property="holidayDesc" /></td>
									<td><bean:write name="reg" property="holidayTypeName" /></td>
									<td align="center">
										<a class="text-success" href="#"
										onclick="actionForm('edit','<bean:write name="reg" property="holidayId" />');"
										title="Edit"><span class="glyphicon glyphicon-pencil"
											aria-hidden="true"></span></a>
										&nbsp; 
										<a href="#" class="text-danger" 
										onclick="actionForm('delete','<bean:write name="reg" property="holidayId" />','<bean:write name="reg" property="holidayDesc" />');"
										title="Delete"><span class="glyphicon glyphicon-trash"
											aria-hidden="true"></span></a>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="personalHolidayForm" property="arrList">
							<tr>
								<td colspan="5" align="center" style="padding: 10px">No Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/jsp/include/pagination.jsp"></jsp:include>
			</div>

		</div>
		<html:hidden name="personalHolidayForm" property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>