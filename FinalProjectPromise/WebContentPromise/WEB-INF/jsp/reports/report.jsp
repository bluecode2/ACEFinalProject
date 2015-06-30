<%@ page import="java.util.Map"%>
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

	<%!private final String reportName = "report_exercise.rpt";%>
	<%
		try {
			ReportClientDocument clientDoc = getClientDocument(reportName);
			//Map map = (Map) session.getAttribute("param");

			String empId = (String) session.getAttribute("param");

			/*int i = 0;
			String batchName = "'"+map.get("batchNamePrint")+"'";
			String topicName = "'"+map.get("topicNamePrint")+"'";*/
			try {
				/*setDocParameter(0, batchName, clientDoc);
				setDocParameter(1, topicName, clientDoc);*/
				setDocParameter(0, empId, clientDoc);
				viewReport(clientDoc, request, response, session);
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

