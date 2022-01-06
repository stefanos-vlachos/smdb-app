<#import "/spring.ftl" as spring />


<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>

<br>

<nav> <a href="/">Home </a></nav>

<div class="container">
	<div class="row" align="center">
		<div class="col">
			<h1>TEAM 3</h1>
			<h1>Welcome to our Movie Database !</h1>
			<h2> <a href="/allmovies">MOVIES </a></h2>
			<h2> <a href="/allactors">ACTORS </a></h2>

			<form method="post"  action="/search/movie" >
				<h1> Title  </h1>
				<input type = "text" name = "title"/>
				<input type="submit" value="Submit">
			</form>

			<hr>
		</div>
	</div>
</div>
</body>
</html>
