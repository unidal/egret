<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="res" uri="http://www.unidal.org/webres"%>
<jsp:useBean id="ctx" type="com.dianping.egret.console.page.home.Context" scope="request" />
<jsp:useBean id="payload" type="com.dianping.egret.console.page.home.Payload" scope="request" />
<jsp:useBean id="model" type="com.dianping.egret.console.page.home.Model" scope="request" />

<a:body>

	<div class="row-fluid">
		<div class="row-fluid">
			<!-- project search form -->
			<form class="form-search">
				<div class="input-append">
					<input type="text" class="search-query input-large" placeholder="jar-version">
					<button type="submit" class="btn">Search</button>
				</div>
			</form>
		</div>
		<!-- project list -->
		<div class="row-fluid">
			<table class="table table-striped table-hover">
				<caption></caption>
				<thead>
					<tr>
						<th>Project</th>
						<th>Version</th>
						<th>Description</th>
						<th>Operation</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>SampleProject</td>
						<td>1.0.0</td>
						<td>A sample web project</td>
						<td><a href="?op=project">View More</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--/row-->
	</div>
	<!--/row-->

</a:body>
