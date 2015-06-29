<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%><%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%><%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	function changePage(page){
		document.forms[0].currPage.value = page;
		document.forms[0].submit();
	}
	
	function goToPage(maxPage){
		var page = document.getElementById("txtGoToPage").value;
		if(isNaN(page) || parseInt(page) > parseInt(maxPage) || parseInt(page) < 1 || page == ''){
			alert('Invalid Page!');
			return;
		}
		else{
			changePage(page);
		}
	}
</script>
<div class="divPaging">
	<table cellspacing="5" width="100%">
		<tr valign="middle">
			<td>
				<ul class="pagination pagination-sm">
					<logic:iterate id="pageNav" name="pageNavigator">
						<logic:notEqual name="pageNav" property="value"
							value="${currPage}">
							<li>
						</logic:notEqual>
						<logic:equal name="pageNav" property="value" value="${currPage}">
							<li class="active">
						</logic:equal>
						<a href="#" class=""
							onclick="changePage(<bean:write name="pageNav" property="value" />);"
							title="<bean:write name="pageNav" property="title" />"><bean:write
								name="pageNav" property="label" /></a>
						</li>
					</logic:iterate>
				</ul>
			</td>
			<td>Go to Page &nbsp; <input type="text" id="txtGoToPage" />
				<button type="button" class="btn btn-raised btn-info btn-sm"
					onclick="goToPage('<bean:write name="pageCount"
										 />');">Go</button>
			</td>
			<td align="right">Total <bean:write name="rowCount" />
				Record(s), Page <bean:write name="currPage" /> of <bean:write
					name="pageCount" />
			</td>
		</tr>
	</table>
</div>