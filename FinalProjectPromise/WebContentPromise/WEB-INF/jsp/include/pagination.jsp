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
						</logic:notEqual> <logic:equal name="pageNav" property="value" value="${currPage}">
							<bean:write name="pageNav" property="value" />
						</logic:equal></td>
				</logic:iterate>
				<td>Go to Page &nbsp; <input type="text" id="txtGoToPage" /> <input
					type="button" class="myButton"
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
</body>
</html>