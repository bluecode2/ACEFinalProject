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
<script src="js/moment.min.js"></script>
<script src="js/knockout-3.2.0.js"></script>
<script src="js/material.datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/material.datepicker.css">

<script type="text/javascript">

function generateChart(chart,fill,percents){
	var $ppc = $(chart),
    percent = parseInt($ppc.data('percent')) || 0,
    deg = 360*percent/100;
  if (percent > 50) {
    $ppc.addClass('gt-50');
  }
    $(fill).css('transform','rotate('+ deg +'deg)');
    $(percents).html(percent+'%');
  };

function generateColor(className1,className2){
	  var arr = []; i = 0;
		$(className1).each(function() {
			arr[i] = this.innerHTML;
			var deadline = new Date(arr[i]);
			var diffDays = Math.round((deadline.getTime()-today.getTime())/(oneDay));
			if (diffDays<=1) {
				$(className2)[i].style.backgroundColor = "rgba(233,30,99,0.1)";
			} else if (diffDays<=3) {
				$(className2)[i].style.backgroundColor = "rgba(255,235,59,0.15)";
			} 
			i++;
		});
  }
  
  var date = new Date();
  var dd = date.getDate();
  var mm = date.getMonth()+1;
 	if(dd<10) {dd = "0"+dd}
 	if(mm<10) {mm = "0"+mm}
  var yyyy = date.getFullYear();
  
	var dateString = yyyy+"-"+mm+"-"+dd;
	var today = new Date(dateString);
	var oneDay = 24*60*60*1000; //hours*minutes*seconds*milliseconds

</script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/include/navbar.jsp"></jsp:include>

<html:form action="/home" method="post">

<div class="container">
	<div class="row">
		<div class="col-md-9" style="margin-top: 5px;">
			<ul class="nav nav-tabs" style="margin-bottom: 15px;">
			    <li class="active"><a href="#project" data-toggle="tab">Project</a></li>
			    <li><a href="#independentTask" data-toggle="tab">Independent Task</a></li>
			</ul>

			<div id="myTabContent" class="tab-content">			    
			    <div class="tab-pane fade active in" id="project">
			        <div class="row">
			        	<div class="col-md-7">
							<div class="home-proj-header text-center"><h4>My Project</h4></div>
							<logic:notEmpty name="homeForm" property="listProjBean">
								<logic:iterate id="reg" name="homeForm" property="listProjBean">
								<div class="home-proj-body">
									<span style="font-size: 16px;"><bean:write name="reg" property="projectName" /></span>
									<br />
									Deadline: <span class="projDate">
									<logic:notEmpty name="reg" property="estEndDateInString" >
										<bean:write name="reg" property="estEndDateDisplay" /></span>
									</logic:notEmpty>
									<span style="float: right;"><bean:write name="reg" property="projectProgress" />%</span>
								</div>
								</logic:iterate>
							</logic:notEmpty>
						</div>
						
			        	<div class="col-md-5 text-center">
			        	<div id="divProj">
			        	<div class="home-proj-header"><h4><bean:write name="homeForm" property="username"/></h4></div>
			                <div style="margin: 20px auto;" id="projChart" class="progress-pie-chart" data-percent="<bean:write name="homeForm" property="avgProjProg" />">
			                    <div class="ppc-progress">
			                        <div class="ppc-progress-fill" id="projChartFill"></div>
			                    </div>
			                    <div class="ppc-percents" id="projChartPercents">
			                    <div class="pcc-percents-wrapper">
			                        <span>%</span>
			                    </div>
			                    </div>
			                </div>
			            <div class="home-task-header">Average Project Progress</div>
						</div>
						</div>
			        	
			        </div>
				</div>
			    
			    <div class="tab-pane fade" id="independentTask">
			        <div class="row">
			        	<div class="col-md-7">
							<div class="home-task-header text-center"><h4>My Current Task</h4></div>
							<logic:notEmpty name="homeForm" property="listTaskBean">
								<logic:iterate id="reg" name="homeForm" property="listTaskBean">
								<div class="home-task-body">
									<span style="font-size: 16px;"><bean:write name="reg" property="taskName" /></span>
									<br />
									Deadline: <span class="taskDate"><bean:write name="reg" property="estEndDateInString" /></span>
									<span style="float: right;"><bean:write name="reg" property="taskProgress" />%</span>
								</div>
								</logic:iterate>
							</logic:notEmpty>
						</div>
						
			        	<div class="col-md-5 text-center">
			        	<div id="divTask">
			        	<div class="home-task-header"><h4><bean:write name="homeForm" property="username"/></h4></div>
			                <div style="margin: 20px auto;" id="taskChart" class="progress-pie-chart" data-percent="<bean:write name="homeForm" property="avgTaskProg" />">
			                    <div class="ppc-progress">
			                        <div class="ppc-progress-fill" id="taskChartFill"></div>
			                    </div>
			                    <div class="ppc-percents" id="taskChartPercents">
			                    <div class="pcc-percents-wrapper">
			                        <span>%</span>
			                    </div>
			                    </div>
			                </div>
			            <div class="home-task-header">Average Task Progress</div>
						</div>
						</div>

			        </div>
				</div>
			</div>	
		</div>
		<div class="col-md-3" style="margin-top: 14px;">
			<p style="font-size: 15px;margin-bottom: -15px;text-align: center;" class="text-info"><b>Calendar</b></p>
       		<input id="field" style="visibility:hidden;">
			<script>
			$(function(){
				var options = {};
					$('#field').datepicker(options); 				
			});
			$(document).ready(function() {
				var x = document.getElementsByClassName("home-proj-body").length;
				if (x<1) document.getElementById("project").style.visibility = "hidden";
				var y = document.getElementsByClassName("home-task-body").length;
				if (y<1) document.getElementById("independentTask").style.visibility = "hidden";
				generateChart('#projChart','#projChartFill','#projChartPercents span');
				generateChart('#taskChart','#taskChartFill','#taskChartPercents span');
				generateColor('.projDate', '.home-proj-body');
				generateColor('.taskDate', '.home-task-body');
			});
			setInterval(function(){ 
				var time = new moment().format("HH:mm");
				document.getElementById("clock").innerHTML = time;
			}, 20000);
			</script>
       	</div>
	</div>
</div>
		
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>

 </html:form>
</body>
</html>
