<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%><%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%><%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- 	This is title -->
	<div class="container">
		<h1><bean:write name="pageTitle" /></h1>
		<hr />
		<ol class="breadcrumb"><logic:iterate name="breadCrumb" id="menu"><logic:equal name="menu" property="menuId" value="${currMenuId}">
			<li class="active"><bean:write name="menu" property="menuCaption" /></li></logic:equal><logic:notEqual name="menu" property="menuId" value="${currMenuId}">
			<li><a href="<bean:write name="menu" property="menuUrl" />"><bean:write	name="menu" property="menuCaption" /></a></li></logic:notEqual></logic:iterate>
		</ol>
	</div>
<!-- 	title end -->