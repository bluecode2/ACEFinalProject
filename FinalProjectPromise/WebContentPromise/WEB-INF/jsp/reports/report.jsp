<%@ page import="java.util.Map"%>
<%@ page import="reports.ReportBean"%>
<%@ page import="reports.ReportManager"%>


<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<title>Report Class</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
</head>
<%@include file="../include/CrystalReportHelper.jsp"%>
<body>
	<%
		try {

			ReportBean rptBean = (ReportBean)session.getAttribute("reportBean");
			String filter = (String)session.getAttribute("filterValue");
			System.out.println(filter);
			
			ReportClientDocument clientDoc = null;//getClientDocument(rptBean.getReportFile());
			System.out.println(rptBean.getReportFile());
			
			//Map map = (Map) session.getAttribute("param");

			/*int i = 0;
			String batchName = "'"+map.get("batchNamePrint")+"'";
			String topicName = "'"+map.get("topicNamePrint")+"'";*/
			try {
				/*setDocParameter(0, batchName, clientDoc);
				setDocParameter(1, topicName, clientDoc);*/
				String[] filterValue = filter.split("#"); 
				int paramCount = filterValue.length;
				System.out.println(paramCount);
				
				for(int i = 0;i < paramCount; i++){
					//setDocParameter(i, filterValue[i], clientDoc);
					System.out.println(filterValue[i]);
				}
				
				
				//viewReport(clientDoc, request, response, session);
			} finally {
				clientDoc.close();
				clientDoc.dispose();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	%>
</body>
</html>

