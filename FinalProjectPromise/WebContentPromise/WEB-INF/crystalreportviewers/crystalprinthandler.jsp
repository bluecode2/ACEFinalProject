<%@ page import="com.crystaldecisions.report.web.viewer.ReportPrintControl" %>
<%
	ReportPrintControl printControl = new ReportPrintControl();
	printControl.processHttpRequest(request, response, getServletContext(), null);
%>