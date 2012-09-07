<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>
<jsp:useBean id="ctx" type="com.dianping.egret.console.page.home.Context" scope="request" />
<jsp:useBean id="payload" type="com.dianping.egret.console.page.home.Payload" scope="request" />
<jsp:useBean id="model" type="com.dianping.egret.console.page.home.Model" scope="request" />

<a:body>

	<div class="row-fluid">
		<table class="table table-striped table-bordered">
			<caption>Project Name</caption>
			<thead>
				<tr>
					<th>Property</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Version</td>
					<td></td>
				</tr>
				<tr>
					<td>Description</td>
					<td></td>
				</tr>
				<tr>
					<td>Dependency</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="row-fluid">
		<table class="table table-striped table-bordered table-condensed">
			<caption>Server List</caption>
			<thead>
				<tr>
					<th></th>
					<th>IP</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox"></td>
					<td>127.0.0.1</td>
				</tr>
				<tr>
					<td><input type="checkbox"></td>
					<td>127.0.0.2</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="row-fluid">
		<h4>Select Plan</h4>
		<select>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select>
		<button type="submit" class="btn btn-primary">Submit Plan</button>
		<button type="button" class="btn">Cancel</button>
	</div>

</a:body>