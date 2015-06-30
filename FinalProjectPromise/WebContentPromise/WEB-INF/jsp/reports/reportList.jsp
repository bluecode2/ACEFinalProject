<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<link rel="stylesheet" href="css/jsTree/style.min.css" />
<link href="css/datepicker/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>
	<html:form action="/report" method="post">
		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>

		<div class="container bg-info"
			style="border-radius: 3px; background-color: #E8F4FA">
			<button id="btnPrint" type="button" onclick="onBtnPrintClick();"
				class="btn btn-raised btn-info btn-icon" title="Print">
				<span class="glyphicon glyphicon-print" aria-hidden="true"></span>
			</button>
		</div>

		<div class="container" style="margin-top: 10px">
			<table width="100%">
				<tr>
					<td width="30%">
						<div class="panel"
							style="height: 500px; overflow: auto; padding: 20px">
							<div id="reportTree">
								<ul>
									<logic:notEmpty name="parentReport">
										<logic:iterate id="parent" name="parentReport">
											<li class="jstree-open" data-jstree='{"disabled":true}'><bean:write name="parent"
													property="reportCaption" />
												<ul>
													<logic:iterate id="child" name="childReport">
														<logic:equal name="child" property="parentId" value="${parent.reportId}">
															<li value="<bean:write name="child" property="reportId"/>"><a href="#" class="reportNode"><bean:write
																	name="child" property="reportCaption" /></a></li>
														</logic:equal>
													</logic:iterate>
												</ul>
											</li>
										</logic:iterate>
									</logic:notEmpty>
								</ul>
							</div>
						</div>
					</td>
					<td>
						<div class="panel form-group has-info"
							style="height: 500px; padding: 20px">
							<h4 id="reportTitle"></h4>
							<hr>
							<br>
							<div id="datePeriod" style="display: none;">
								<table>
									<tr>
										<td width="150px">Date period</td>
										<td width="200px"><input type="text" id="txtStartDate" class="datepicker form-control" /></td>
										<td width="50px" align="center">to</td>
										<td width="200px"><input type="text" id="txtEndDate" class="datepicker form-control" /></td>
									</tr>
								</table>
							</div>
							<div id="currentEmployee" style="display: none;">
								<table>
									<tr>
										<td width="150px">Employee</td>
										<td width="200px"><input type="hidden" id="hdnCurrentEmpId" value="${currentEmpId}" /><input type="text" readonly="readonly" id="txtCurrentEmployeeName" class="form-control" value="${currentEmpName}" /></td>
										
									</tr>
								</table>
							</div>			
							
						</div>
					</td>
				</tr>
			</table>
		</div>

		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>

	<script src="js/jsTree/jstree.js"></script>
	<script src="js/datepicker/bootstrap-datepicker.js"></script>
	<script src="js/datepicker/bootstrap-datepicker.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			showLoading();
			$.jstree.defaults.core.animation = 100;
			$.jstree.defaults.core.themes.icons = false;

			$('#reportTree').jstree();
			
			$('.reportNode').on('click',function(){
				showLoading();
				hideAllFilter();
				
				var reportId = $(this).closest('li').val();
				$('#reportTitle').html($(this).html());
				
				$.ajax({
					type : "POST",
					url : "report.do",
					data : "task=selectReport&selectedId="
							+ reportId,
					success : function(response) {
						showFilterPanel(response);
						hideLoading();
					},
					error : function(e) {
						alert("Error: " + e);
						hideLoading();
					}

				});
				
			});
			
			$(".datepicker").attr("data-provide", "datepicker");
			
			hideLoading();
		});
		
		function showFilterPanel(filter){
			var arrFilter = filter.split("#");

			for(var i = 0;i < arrFilter.length;i++){
				arrFilter[i]  = arrFilter[i].trim();
			}
			
			//Filter Date Period
			if($.inArray("datePeriod",arrFilter) > -1){
				$("#datePeriod").show();
			}

			//Filter Current Employee
			if($.inArray("currentEmployee",arrFilter) > -1){
				$("#currentEmployee").show();
			}
			
		}
		
		function hideAllFilter(){
			$("#datePeriod").hide();
			$("#currentEmployee").hide();
		}
	</script>
</body>

</html>