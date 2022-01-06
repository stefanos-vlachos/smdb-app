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
                    <th>FullName</th>
                </tr>
                </thead>
                <tbody>
                <#list contributors as contributor>
                    <tr>
                        <td>${contributor.id}</td>
                        <td>${contributor.fullName}</td>

                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
