<!DOCTYPE html>
<html>
<head>
    <title>MOVIES</title>
</head>
<body>

<nav> <a href="/">Home </a></nav>

<div class="container">

    <h1>${movie.title}</h1>
    <p>release year: ${movie.releaseYear} , rating : ${movie.rating} </p>
    <p>Language: ${movie.language}</p>
    <p> Genres :
        <#list movie.genres as genre>
            ${genre}
        </#list>
    </p>
    <h3>Resume</h3>
    <p> ${movie.resume}</p>

    <h3> Contributors </h3>
    <p>
        <#list movie.contributorProductions as contribution>
          <p> <a href = "./contributordetails?id=${contribution.contributor.id}"> ${contribution.contributor.fullName} </a> as ${contribution.role}</p>
        </#list>
    </p>



</div>
</div>
</div>
</body>
</html>
