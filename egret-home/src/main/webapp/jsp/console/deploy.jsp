<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>
<jsp:useBean id="ctx" type="com.dianping.egret.console.page.deploy.Context" scope="request" />
<jsp:useBean id="payload" type="com.dianping.egret.console.page.deploy.Payload" scope="request" />
<jsp:useBean id="model" type="com.dianping.egret.console.page.deploy.Model" scope="request" />

<a:body>
	<div class="row-fluid">
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-condensed">
				<caption>${model.plan}</caption>
				<thead>
					<tr>
						<th>IP</th>
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
			<div id="progressbar" class="bar" style="width: 60%;"></div>
		</div>

		<div class="row-fluid">
			<div id="status" data-spy="scroll" data-offset="0"
				class="scrollspy-example">
				<c:forEach var="msg" items="${model.msgs}">
					<span class="label label-${msg.info}">${msg.msgid} &nbsp;
						${msg.content} </span>
					<script type="text/javascript">
							setProgress(${msg.progress});
						</script>
				</c:forEach>
			</div>

			<div id="alert" class="alert alert-info" style="display: none">Deploy
				Completed</div>
		</div>
	</div>
	<input type="hidden" name="offset" value="${model.offset}">
	<script type="text/javascript">
		function setProgress(progress){
			$("#progressbar").css("width", progress);
		}
		
		function updateDeployStatus() {
			$(document).ready(
					function() {
						$.ajax({
							type : "GET",
							url : "?op=log",
							dataType : "json",
							data : {
								"offset" : offset
							},
							success : function(msg) {
								//alert(msg);
								$.each(msg, function(msgid, content, progress,
										info) {
									if (info = "") {
										info = "default";
									}

									$("#status").append(
											"<span class=\"label label-")
											.append(info).append("\">").append(
													msgid).append(" ").append(
													content).append("</span>");
									setProgress(progress);

									offset = msgid;
									
									if(progress=100){
										$("#alert").show();
									}
								});
							}
						});
					});
		}
		setInterval('updateDeployStatus()', 1000);
	</script>
</a:body>