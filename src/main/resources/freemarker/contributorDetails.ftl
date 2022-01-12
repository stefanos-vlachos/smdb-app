<#import "base.ftl" as base>

<!DOCTYPE html>
<html>
<head>
    <@base.head>
    </@base.head>
    <title>${contributor.fullName}</title>
</head>
<body>

<@base.navbar>
</@base.navbar>

<div class="container">

    <h1>${contributor.fullName}</h1>
    <p>Gender: ${contributor.gender}</p>
    <p>Origin: ${contributor.origin}</p>

    <h3> Contributions </h3>
    <p>
        <#list contributor.contributorProductions as contribution>
        <#if contribution.production.class.simpleName == "Movie">
    <p> <a href = "/moviedetails?id=${contribution.production.id}"> ${contribution.production.title} </a> as ${contribution.role}</p>
    </#if>
    <#if contribution.production.class.simpleName == "Sitcom">
    <p> <a href = "/sitcomdetails?id=${contribution.production.id}"> ${contribution.production.title} </a> as ${contribution.role}</p>
    </#if>

    </#list>
    </p>



</div>
</div>
</div>
</body>
</html>
