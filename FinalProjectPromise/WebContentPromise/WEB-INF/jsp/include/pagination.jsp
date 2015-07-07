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
	<div class="row">
		<div class="col-md-9">
			<ul class="pagination pagination-sm" style="float: left;">
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
			<div class="form-group has-info" style="display: inline-block;margin: 15px;">
				<label for="txtGoToPage" class="col-md-6" style="margin-top: 12px;text-align: right;color: #777;width: 150px !important;">
					Go to Page</label>
				<div class="col-md-4" style="margin-top: 2px;padding-left: 0;">
					<input type="text" class="form-control" id="txtGoToPage"/>
	            </div>
				<div class="col-md-1" style="margin-top: -3px;padding-left: 5px;">
	                <button type="button" class="btn btn-raised btn-info btn-sm"
					onclick="goToPage('<bean:write name="pageCount" />');">Go</button>
	            </div>
			</div>
		</div>
		<div class="col-md-3" style="text-align: right;margin-top: 30px;">
		Total <bean:write name="rowCount" />
			Record(s), Page <bean:write name="currPage" /> of <bean:write
				name="pageCount" />
		</div>
	</div>
</div>