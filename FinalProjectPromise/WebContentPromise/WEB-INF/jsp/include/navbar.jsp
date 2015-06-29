<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- This is navbar -->
<nav class="navbar navbar-material-blue navbar-static-top shadow-z-1">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home.do"><span class="logo"><img
						src="icon/logo2.png"></span><b>PROMISE</b></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<logic:iterate id="menuLvl1" name="arrMenuLvl1">
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle"><bean:write name="menuLvl1"
									property="menuCaption" /> <b class="caret"></b></a>
							<ul class="dropdown-menu" id="menu1">
								<logic:iterate id="menuLvl2" name="arrMenuLvl2">
									<logic:equal name="menuLvl2" property="parentId"
										value="${menuLvl1.menuId}">
										<li><a
											href="<bean:write name="menuLvl2" property="menuUrl" />"><bean:write
													name="menuLvl2" property="menuCaption" /></a>
											<ul class="dropdown-menu sub-menu"><logic:iterate 
												id="menuLvl3" name="arrMenuLvl3"><logic:equal name="menuLvl3" property="parentId"
														value="${menuLvl2.menuId}"><li><a
															href="<bean:write name="menuLvl3" property="menuUrl" />"><bean:write
																	name="menuLvl3" property="menuCaption" /></a></li>
													</logic:equal>
												</logic:iterate>
											</ul></li>
									</logic:equal>
								</logic:iterate>
							</ul></li>
					</logic:iterate>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="home.do" title="Home"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
					<logic:notEmpty name="unreadNotification">
					<li class="dropdown"><a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-expanded="false" title="Notification"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span> 
							<span class="badge" id="badge"><bean:write name="unreadCount" /></span></a>
						<ul class="dropdown-menu dropdown-menu-notif">
							<logic:iterate id="notif" name="unreadNotification">
								<li><html:hidden styleClass="hdnNotifId" name="notif" property="notificationId" /><a href="#" class="notificationNode"><bean:write name="notif" property="notificationDesc" /></a></li>
							</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="unreadNotification">
					<li class="dropdown"><a href="#" class="dropdown-toggle" 
						data-toggle="dropdown" role="button" aria-expanded="false" title="Notification"><span
							class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;
							</a>
						<ul class="dropdown-menu dropdown-menu-notif" style="min-width: 200px;">
							<li><span>No Unread Notification</span></li>
					</logic:empty>
							<li role="separator" class="divider"></li>
							<li style="text-align: center"><a href="notification.do" class="text-info">See All</a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"> <span
							class="glyphicon glyphicon-user" aria-hidden="true"
							style="margin-right: 10px"></span> <logic:notEmpty
								name="userName">
								<bean:write name="userName" /> &nbsp;
								<span class="caret">
							</logic:notEmpty> </span>
					</a>
						<ul class="dropdown-menu dropdown-menu-right" role="menu">
							<li><span><bean:write name="userName" /> (<bean:write name="userRoleName" />)</span></li>
							<li role="separator" class="divider"></li>
							<logic:equal name="isActiveDirectory" value="0">
								<li><a href="#" onclick="onChangePasswordClick();">Change Password</a></li>
							</logic:equal>
							<li><a href="logout.do">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="full" id="loading" style="visibility: hidden">
		<div class="full-inner">
			<h2>Loading...</h2>
			<div class="loader">Loading...</div>
		</div>
	</div>
<!-- navbar end -->