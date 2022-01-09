<html>

<#macro head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</#macro>

<body>

<#macro navbar>

    <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark" >
        <a class="nav-link" href="/"> HOME</a>
        <a class="nav-link" href="/allmovies">MOVIES </a>
        <a class="nav-link" href="/allsitcoms">SITCOMS </a>
        <a class="nav-link" href="/allactors">ACTORS </a>

        <form method="post" action="/search" class="ml-auto">
            <input class="ml-auto" type="text" class="form-control" name="keyword" placeholder="Search for a movie"/>
            <input type="submit" value="Search">
        </form>

    </nav>
</#macro>

</body>
</html>
