<%@ page import="com.crystaldecisions.report.web.viewer.CrystalExportHandler" %>
<%
   CrystalExportHandler exportHandler = new CrystalExportHandler();
   exportHandler.handleDocument( request, response, getServletConfig().getServletContext() );
%>