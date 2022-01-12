<#import "base.ftl" as base>

<!DOCTYPE html>
<html>
<head>
    <@base.head>
    </@base.head>
    <title>Movies</title>
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
                    <th>Title</th>
                    <th>Release Year</th>
                    <th>Rating</th>
                    <th>Language</th>
                    <th>Genre</th>

                </tr>
                </thead>
                <tbody>
                <#list sitcoms as sitcom>
                    <tr>
                        <td>${sitcom?counter}</td>
                        <td><a href = "./sitcomdetails?id=${sitcom.id}">${sitcom.title}</a></td>
                        <td>${sitcom.releaseYear}</td>
                        <td>${sitcom.rating}</td>
                        <td>${sitcom.language}</td>
                        <td> <#list sitcom.genres as genre>
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
