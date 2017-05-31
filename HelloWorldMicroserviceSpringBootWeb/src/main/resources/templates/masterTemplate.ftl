<#macro masterTemplate title="Welcome">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/assets/css/main.css" />
	<noscript><link rel="stylesheet" href="/assets/css/noscript.css" /></noscript>
	<title>User Microservice Example | ${title}</title>
</head>
<body class="is-loading">
	<!-- Scripts -->
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/jquery.scrollex.min.js"></script>
	<script src="/assets/js/jquery.scrolly.min.js"></script>
	<script src="/assets/js/skel.min.js"></script>
	<script src="/assets/js/util.js"></script>
	<script src="/assets/js/main.js"></script>
	<div id="wrapper">
		<header id="header">
			<a href="/" class="logo">User Management &dash; A Microservice Example Application</a>
		</header>
		<nav id="nav">
			<ul class="links">
				<li<#if page??><#if page == "home"> class="active"</#if></#if>><a href="/">Home</a></li>
				<li<#if page??><#if page == "about"> class="active"</#if></#if>><a href="/about">About</a></li>
				<li<#if page??><#if page == "users"> class="active"</#if></#if>><a href="/users">Users</a></li>
			</ul>
		</nav>
		<main>
			<div id="main">
				<section class="post">
					<h1>${title}</h1>
<#nested />
				</section>
			</div>
		</main>
		<footer id="footer">Some footer content...</footer>
		<div id="copyright">
			<ul>
				<li><a href="/">User Management</a> <a href="https://en.wikipedia.org/wiki/Microservices">Microservice</a> Example</li>
				<li>A <a href="http://spring.io/">Spring Boot</a> application</li>
				<li><a href="https://html5up.net/massively">Massively</a> HTML template</li>
				<li>Developed by <a href="https://github.com/mattjlewis">Matt Lewis</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
</#macro>
