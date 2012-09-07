<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>
<jsp:useBean id="ctx"
	type="com.dianping.egret.console.page.deploy.Context" scope="request" />
<jsp:useBean id="payload"
	type="com.dianping.egret.console.page.deploy.Payload" scope="request" />
<jsp:useBean id="model"
	type="com.dianping.egret.console.page.deploy.Model" scope="request" />

<a:body>
	<div class="row-fluid">
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-condensed">
				<caption>${model.plan}</caption>
				<thead>
					<tr>
						<th>Remote Deploy Log</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ip" items="${model.hosts}">
						<tr>
							<td>${ip}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="progress progress-info progress-striped">
			<div id="progressbar" class="bar" style="width: 0%;"></div>
		</div>

		<div class="row-fluid">
			<div id="status" data-spy="scroll" data-offset="0"
				class="scrollspy-example" style="height:300px;line-height:20px;overflow:auto;">
				<span class="label label-default"> ${model.log} </span>
			</div>

			<div id="alert" class="alert alert-info" style="display: none">Deploy
				Completed</div>
		</div>
	</div>
	<input type="hidden" id="offset" name="offset" value="${model.offset}">
	<script type="text/javascript">
		
		function updateDeployStatus() {
			$(document).ready(
					function() {
						$.ajax({
							type : "GET",
							url : "?op=log",
							dataType : "json",
							data : {
								offset : $("#offset").val(),
								plan : "${payload.plan}"
							},
							error:function(xhRequest, ErrorText, thrownError){
								console.log(xhRequest);
								console.log(ErrorText);
								console.log(thrownError);
							},
							success : function(data) {
								$("#progressbar").css("width", data.progress+"%");

								if (data.content) {
									$("#status").prepend(
											"<p><span class=\"label label-info\">"+data.content+"</span></p>");

									$("#offset").val(data.offset);
									
									if(data.progress>=100){
										$("#alert").show();
										stopTimer();
									}
								}
							}
						});
					}
			);
		}
		var	 interval = setInterval('updateDeployStatus()', 1000);	
		function stopTimer(){
			clearInterval(interval);
		}
	</script>
</a:body>