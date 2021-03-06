<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Notifications</title>
<script src="js/jquery.js"></script>
</head>
<script>
	$(document).ready(function() {
		$.material.init();

		$('.notificationNodeList').on('click', function() {
			var notificationId = $(this).find('.hdnNotifId').val();
			document.forms[0].task.value = "select";
			document.forms[0].selectedId.value = notificationId;
			document.forms[0].submit();
		});

		$('.btnPrevNotif').on('click', function() {
			var currPage = parseInt($('#hdnCurrPage').val());
			changePageNotif(currPage - 1);
		});

		$('.btnNextNotif').on('click', function() {
			var currPage = parseInt($('#hdnCurrPage').val());
			changePageNotif(currPage + 1);
		});
	});

	function changePageNotif(page) {
		document.forms[0].task.value = "";
		document.forms[0].currPage.value = page;
		document.forms[0].submit();
	}
</script>

<body>
	<html:form action="/notification" method="post">

		<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/title.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/toolbar.jsp"></jsp:include>

		<html:hidden name="notificationForm" property="task" />
		<html:hidden name="notificationForm" property="selectedId" />

		<div class="container divContent divNotif">
			<div>
				<div class="col-md-8">
					<nav>
					<ul class="pager">
						<logic:notEqual name="currPage" value="1">
							<li class="previous"><a href='#'
								class="text-info btnPrevNotif" style="padding-right: 20px"><span aria-hidden="true">&larr;</span> Newer</a></li>
						</logic:notEqual>
						<logic:notEqual name="currPage" value="${pageCount}">
							<li class="next"><a href="#" class="text-info btnNextNotif">Older <span aria-hidden="true">&rarr;</span></a></li>
						</logic:notEqual>
					</ul>
					</nav>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<hr>
					<logic:iterate id="notif" name="notificationForm"
						property="lstBean">
						<logic:equal name="notif" property="isRead" value="0">
							<div class="notificationNodeList alert alert-info notif">
								<html:hidden styleClass="hdnNotifId" name="notif"
									property="notificationId" />
								<bean:write name="notif" property="notificationDesc" />
								<br /> <span style="font-size: xx-small;"><bean:write
										name="notif" property="notificationDateDisplay" /></span>
							</div>
						</logic:equal>
						<logic:equal name="notif" property="isRead" value="1">
							<div class="notificationNodeList alert alert-info notif checked">
								<html:hidden styleClass="hdnNotifId" name="notif"
									property="notificationId" />
								<bean:write name="notif" property="notificationDesc" />
								<br /> <span style="font-size: xx-small;"><bean:write
										name="notif" property="notificationDateDisplay" /></span> <span
									style="font-size: xx-small; padding-left: 30px">Read : <bean:write
										name="notif" property="readDateDisplay" /></span>
							</div>
						</logic:equal>
					</logic:iterate>
					<hr>
				</div>
			</div>
			<div>
				<div class="col-md-8">
					<nav>
					<ul class="pager">
						<logic:notEqual name="currPage" value="1">
							<li class="previous"><a href='#'
								class="text-info btnPrevNotif" style="padding-right: 20px"><span aria-hidden="true">&larr;</span> Newer</a></li>
						</logic:notEqual>
						<logic:notEqual name="currPage" value="${pageCount}">
							<li class="next"><a href="#" class="text-info btnNextNotif">Older <span aria-hidden="true">&rarr;</span></a></li>
						</logic:notEqual>
					</ul>
					</nav>
				</div>
			</div>
		</div>

		<html:hidden styleId="hdnCurrPage" name="notificationForm"
			property="currPage" />
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	</html:form>
</body>
</html>