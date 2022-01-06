<!DOCTYPE html>
<html>
<head>
    <title>MOVIES</title>
</head>
<body>

<nav> <a href="/">Home </a></nav>

<div class="container">

    <h1>${contributor.fullName}</h1>
    <p>Gender: ${contributor.gender}</p>
    <p>Origin: ${contributor.origin}</p>

    <h3> Contributions </h3>
    <p>
        <#list contributor.contributorProductions as contribution>
    <p> <a href = "./moviedetails?id=${contribution.production.id}"> ${contribution.production.title} </a> as ${contribution.role}</p>
    </#list>
    </p>



</div>
</div>
</div>
</body>
</html>
