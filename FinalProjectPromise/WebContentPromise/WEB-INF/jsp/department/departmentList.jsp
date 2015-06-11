<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<html:form action="/department" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<div class="container">
			<div class="divSearch" style="float: right;">
				<table>
					<tr align="right">
						<td>Search by</td>
						<td><input type="text" /></td>
						<td><input type="text" /></td>
						<td><a href="#"><span class="glyphicon glyphicon-search"
								aria-hidden="true"></span></a></td>
					</tr>
				</table>
			</div>

			<div class="divContent">
				<table class="table" border="1" cellspacing="0" style="margin-top: 10px;"
					width="100%" class="tableContent">
					<thead class="panel panel-info">
						<tr>
							<td>Department Code</td>
							<td>Department Name</td>
							<td>Department Head</td>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="departmentForm" property="arrList">
							<logic:iterate id="reg" name="departmentForm"
								property="arrList">
								<tr>
									<td></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="departmentForm" property="arrList">
							<tr>
								<td colspan="11" align="center" style="padding: 10px">No Data Found</td>
							</tr>
						</logic:empty>
					</tbody>
				</table>
			</div>


			<div class="divPaging">

				<table cellspacing="5" width="100%">
					<tr valign="middle">
						<logic:iterate id="pageNav" name="pageNavigator">
							<td align="center" class="solidButton"><logic:notEqual
									name="pageNav" property="value" value="${currPage}">
									<a href="#" class=""
										onclick="changePage(<bean:write name="pageNav" property="value" />);"
										title="<bean:write name="pageNav" property="title" />"><bean:write
											name="pageNav" property="label" /></a>
								</logic:notEqual> <logic:equal name="pageNav" property="value"
									value="${currPage}">
									<bean:write name="pageNav" property="value" />
								</logic:equal></td>
						</logic:iterate>
						<td>Go to Page &nbsp; <input type="text" id="txtGoToPage" />
							<input type="button" class="myButton"
							onclick="goToPage('<bean:write name="pageCount"
											 />');"
							value="Go">
						</td>
						<td align="right">Total <bean:write name="rowCount" />
							Record(s), Page <bean:write name="currPage" /> of <bean:write
								name="pageCount" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

	</html:form>
</body>
</html>