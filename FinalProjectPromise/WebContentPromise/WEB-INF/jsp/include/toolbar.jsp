<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="container bg-info">
		<button id="btnAdd" onclick="onBtnAddClick();" class="btn btn-info btn-icon" title="Add">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
		<button id="btnBack" onclick="onBtnBackClick();" class="btn btn-info btn-icon" title="Back">
			<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
		</button>
		<button id="btnSave"  onclick="onBtnSaveClick();" class="btn btn-info btn-icon" title="Save">
			<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
		</button>
		<button id="btnApprove" onclick="onBtnApproveClick();" class="btn btn-info btn-icon" title="Approve">
			<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
		</button>
		<button id="btnDecline"  onclick="onBtnDeclineClick();" class="btn btn-info btn-icon" title="Decline">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		</button>
	</div>
	
	
</body>
</html>