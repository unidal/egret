<%@ page contentType="text/html; charset=utf-8"%>
<jsp:useBean id="ctx"
	type="com.dianping.egret.console.page.project.Context" scope="request" />
<jsp:useBean id="payload"
	type="com.dianping.egret.console.page.project.Payload" scope="request" />
<jsp:useBean id="model"
	type="com.dianping.egret.console.page.project.Model" scope="request" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Egret, from Dianping</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="../css/bootstrap.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href="../css/bootstrap-responsive.css" rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Project name</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="home">Home</a></li>
						<li><a href="about">About</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header"></li>
						<li class="active"><a href="projects">List Projects</a></li>
						<li><a href="deploy">Deploy Jars</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<div class="row-fluid">
					<table class="table table-striped">
						<caption>Project Name</caption>
						<thead>
							<tr>
								<th>Property</th>
								<th>Value</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>IP</td>
								<td>127.0.0.1</td>
							</tr>
							<tr>
								<td>Path</td>
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
			</div>
			<!--/span-->
		</div>
		<!--/row-->

		<hr>

		<footer>
			<p>&copy; 远征军@Dianping 2012</p>
		</footer>

	</div>
	<!--/.fluid-container-->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../js/jquery-1.8.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>

</body>
</html>