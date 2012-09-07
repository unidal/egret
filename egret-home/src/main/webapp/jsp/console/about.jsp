<%@ page contentType="text/html; charset=utf-8"%>
<jsp:useBean id="ctx"
	type="com.dianping.egret.console.page.about.Context" scope="request" />
<jsp:useBean id="payload"
	type="com.dianping.egret.console.page.about.Payload" scope="request" />
<jsp:useBean id="model"
	type="com.dianping.egret.console.page.about.Model" scope="request" />

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
						<li><a href="home">Home</a></li>
						<li class="active"><a href="about">About</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="hero-unit">
				<h1>Egret</h1>
				<p>统一替换架构某个产品的JAR包，在此过程中确保一些列的安全，包括正确性验证，失败回滚等</p>
				<p>Build by
					<span class="label label-success">老吴</span><span
						class="label label-warning">老马</span><span
						class="label label-important">尤勇</span><span class="label label-info">一鸣</span>
				</p>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<h2>快速迭代、敢于尝试</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details &raquo;</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>拥抱变化、及时响应</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details &raquo;</a>
					</p>
				</div>
				<!--/span-->
				<div class="span4">
					<h2>敏捷开发、敏捷发布</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce
						dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
						ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.</p>
					<p>
						<a class="btn" href="#">View details &raquo;</a>
					</p>
				</div>
				<!--/span-->
			</div>
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
