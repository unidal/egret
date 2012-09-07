<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="ctx" type="com.dianping.egret.console.page.home.Context" scope="request" />
<jsp:useBean id="payload" type="com.dianping.egret.console.page.home.Payload" scope="request" />
<jsp:useBean id="model" type="com.dianping.egret.console.page.home.Model" scope="request" />

<a:body>

	<div class="row-fluid">
		<table class="table table-striped table-bordered">
			<caption>${model.project.name}</caption>
			<tbody>
				<tr>
					<td>Owner</td>
					<td>${model.project.owner}</td>
				</tr>
				<tr>
					<td>Dependency</td>
					<td>
						<ul>
							<c:forEach var="jar" items="${model.project.dependencyJars}">
								<li>${jar}</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<form class="form-horizontal" method="get">
		<input type="hidden" name="op" value="deploy"/>
		<div class="row-fluid">
			<table class="table table-striped table-bordered table-condensed">
				<caption>Host List</caption>
				<thead>
					<tr>
						<th></th>
						<th>IP</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ip" items="${model.project.hosts}">
						<tr>
							<td><input type="checkbox" name="hosts" value="${ip}"></td>
							<td>${ip}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="row-fluid">
			<select name="plan">
				<c:forEach var="plan" items="${model.deployPlans}">
					<option>${plan}</option>
				</c:forEach>
			</select>
			<button type="submit" class="btn btn-primary">Deploy</button>
			<button type="reset" class="btn">Cancel</button>
		</div>
	</form>
</a:body>