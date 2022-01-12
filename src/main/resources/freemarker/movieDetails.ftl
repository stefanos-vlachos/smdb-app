<#import "base.ftl" as base>

<!DOCTYPE html>
<html>
<head>
    <@base.head>
    </@base.head>
    <title>${movie.title}</title>
</head>
<body>

<@base.navbar>
</@base.navbar>

<div class="container">

    <h1>${movie.title}</h1>
    <p>release year: ${movie.releaseYear} , rating : ${movie.rating} </p>
    <p>Language: ${movie.language}</p>
    <p>Duration: ${movie.duration}</p>
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
          <p> <a href = "/contributordetails?id=${contribution.contributor.id}"> ${contribution.contributor.fullName} </a> as ${contribution.role}</p>
        </#list>
    </p>



</div>
</div>
</div>
</body>
</html>
