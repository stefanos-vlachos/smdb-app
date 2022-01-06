<!DOCTYPE html>
<html>
<head>
	<title>MOVIES</title>
</head>
<body>

<nav> <a href="/">Home </a></nav>

<div class="container">
	<div class="row">
		<div class="col">
			<table class="table table-hover">
				<thead>
				<tr>
					<th></th>
					<th>Title</th>
					<th>Release Year</th>
					<th>Rating</th>
					<th>Language</th>
					<th>Genre</th>

				</tr>
				</thead>
				<tbody>
				<#list movies as movie>
					<tr>
						<td>${movie.id}</td>
						<td><a href = "./moviedetails?id=${movie.id}">${movie.title}</a></td>
						<td>${movie.releaseYear}</td>
						<td>${movie.rating}</td>
						<td>${movie.language}</td>
						<td> <#list movie.genres as genre>
								 ${genre}
						</#list>
					</td>

					</tr>
				</#list>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
