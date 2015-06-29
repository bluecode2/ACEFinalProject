<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<jsp:include page="/WEB-INF/jsp/include/head.jsp"></jsp:include>

<link rel="stylesheet" href="css/calendar.min.css">
<!--
<script type="text/javascript">
$(function(){
    var $ppc = $('.progress-pie-chart'),
      percent = parseInt($ppc.data('percent')),
      deg = 360*percent/100;
    if (percent > 50) {
      $ppc.addClass('gt-50');
    }
    $('.ppc-progress-fill').css('transform','rotate('+ deg +'deg)');
    $('.ppc-percents span').html(percent+'%');
  });
 
</script> -->

</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navbar.jsp"></jsp:include>

	<html:form action="/home" method="post">
	
<div class="container">
	<div class="row">
		<div class="col-md-4">
			<ul class="nav nav-tabs" style="margin-bottom: 15px;">
			    <li class="active"><a href="#home" data-toggle="tab">Home</a></li>
			    <li><a href="#profile" data-toggle="tab">Profile</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
			    <div class="tab-pane fade active in" id="home">
			        <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.</p>
			    </div>
			    <div class="tab-pane fade" id="profile">
			        <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit.</p>
			    </div>
			</div>	
		</div>
	</div>
</div>

		<div id="calendar"></div>
    
<!-- 		<div class="container-fluid" style="background-color: white; height: 80px; 
		  margin-top: -20px !important; padding-top: 20px !important">
			<div class="container">
			<h1>Dashboard</h1>
			<hr style="margin-top: 10px" />
			</div>
		</div>
		<div class="container">
		<h4 style="margin-bottom: 20px">My Current Task</h4>
			<div class="row">
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">hello</div>
						<div class="panel-body">
						
							<div class="statChartHolder">
				                <div class="progress-pie-chart" data-percent="67">
				                    <div class="ppc-progress">
				                        <div class="ppc-progress-fill"></div>
				                    </div>
				                    <div class="ppc-percents">
				                    <div class="pcc-percents-wrapper">
				                        <span>%</span>
				                    </div>
				                    </div>
				                </div>
				            </div>
				            
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">hello</div>
						<div class="panel-body">hello<br>hello<br>hello</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">hello</div>
						<div class="panel-body">hello<br>hello<br>hello</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="panel panel-info">
						<div class="panel-heading">hello</div>
						<div class="panel-body">hello<br>hello<br>hello</div>
					</div>
				</div>
			</div>
				<hr><br>
			
			<div class="row">
				<div class="col-sm-4">
					<div style="background-color: #f4f4f4">
						<ul class="nav nav-pills nav-stacked">
							<li class="active" style="visibility: inherit;"><a href="#a" data-toggle="tab">General</a></li>
		                    <li ><a href="#b" data-toggle="tab">Department</a></li>
		                    <li><a href="#c" data-toggle="tab">Project</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-8">
					<div style="background-color: #f4f4f4; min-height: 200px">
						<div class="tab-content">
		                    <div class="tab-pane active" id="a">Contains all departments</div>
		                    <div class="tab-pane" id="b">Contains all projects in department</div>
		                    <div class="tab-pane" id="c">Detail about a project</div>
		                </div>
					</div>
				</div>
			</div>
		</div> -->
		
<!-- 		<div class="container" style="background-color: white; min-height: 100px; margin-top: 20px">

		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
		    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
		    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
		    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
		  </ul>
		
		  Tab panes
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="home">...</div>
		    <div role="tabpanel" class="tab-pane" id="profile">...</div>
		    <div role="tabpanel" class="tab-pane" id="messages">...</div>
		    <div role="tabpanel" class="tab-pane" id="settings">...</div>
		  </div>
		</div> -->
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
		
<!-- 		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/underscore-min.js"></script>
	    <script type="text/javascript" src="js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="js/calendar.min.js"></script>
	    <script type="text/javascript">
	    $(document).ready(function () {
	    	var calendar = $("#calendar").calendar(
		            {
		                tmpl_path: "/tmpls/",
		                events_source: function () { return []; }
		            });  
	    	});
	               
	    </script>  -->
 </html:form>
</body>
</html>