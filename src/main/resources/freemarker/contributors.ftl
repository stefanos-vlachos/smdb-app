<#import "base.ftl" as base>

<!DOCTYPE html>
<html>
<head>
    <@base.head>
    </@base.head>
    <title>Actors</title>
</head>
<body>

<@base.navbar>
</@base.navbar>

<div class="container">
    <div class="row">
        <div class="col">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th>FullName</th>
                </tr>
                </thead>
                <tbody>
                <#list contributors as contributor>
                    <tr>
                        <td>${contributor?counter}</td>
                        <td><a href = "./contributordetails?id=${contributor.id}">${contributor.fullName}</td>

                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
