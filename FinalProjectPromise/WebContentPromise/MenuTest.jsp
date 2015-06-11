<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.11.3.js"></script>
<script src="js/jquery-ui.js"></script>

<style>
body {
	font-size: 62.5%;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>

<script>
	$(function() {
		var dialog, form,

		// From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
		coba = $("#coba"), allFields = $([]).add(coba);

		function addUser(coba) {
			document.home.hasilCoba.value = coba;
			dialog.dialog("close");

		}

		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 300,
			width : 350,
			modal : true,
			buttons : {
				"Get Input" : addUser,
				Cancel : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				allFields.removeClass("ui-state-error");
			}
		});

		form = dialog.find("form").on("submit", function(event) {
			event.preventDefault();
			addUser(coba);
		});

		$("#create-user").button().on("click", function() {
			dialog.dialog("open");
		});
	});
	function getValue() {
		document.home.coba.value = document.popup.hasilCoba.value;
	
	}
</script>
</head>
<body>

	<div id="dialog-form" title="Create new user">


		<form name="home">
			<label for="name">Coba : </label> <input type="text" name="coba"
				id="coba" class="text ui-widget-content ui-corner-all">
			<!-- Allow form submission with keyboard without duplicating the dialog button -->
			<input type="submit" tabindex="-1"
				style="position: absolute; top: -1000px">
		</form>
	</div>


	<div id="users-contain" class="ui-widget">
		<form name="popup">
			<h1>Existing Users:</h1>

			<input type="text" name="hasilCoba" id="hasilCoba">
			<button id="" onclick="javascript:getValue()">Create new
				user</button>

		</form>
	</div>

</body>
</body>
</html>