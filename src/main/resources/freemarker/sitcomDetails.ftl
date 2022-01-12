<#import "base.ftl" as base>

<!DOCTYPE html>
<html>
<head>
    <@base.head>
    </@base.head>
    <title>${sitcom.title}</title>
</head>
<body>

<@base.navbar>
</@base.navbar>

<div class="container">

    <h1>${sitcom.title}</h1>
    <p>release year: ${sitcom.releaseYear} , rating : ${sitcom.rating} </p>
    <p>Language: ${sitcom.language}</p>
    <p>Seasons: ${sitcom.seasons} , episodes: ${sitcom.episodes}</p>
    <p> Genres :
        <#list sitcom.genres as genre>
            ${genre}
        </#list>
    </p>
    <h3>Resume</h3>
    <p> ${sitcom.resume}</p>

    <h3> Contributors </h3>
    <p>
        <#list sitcom.contributorProductions as contribution>
    <p> <a href = "/contributordetails?id=${contribution.contributor.id}"> ${contribution.contributor.fullName} </a> as ${contribution.role}</p>
    </#list>
    </p>



</div>
</div>
</div>
</body>
</html>
