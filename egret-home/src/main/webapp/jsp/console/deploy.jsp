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
			<div class="bar" style="width: 60%;"></div>
		</div>

		<div class="row-fluid">
			<pre>
				deploy information updated;
			</pre>
		</div>
	</div>

</a:body>