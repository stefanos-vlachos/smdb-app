<html>

<#macro head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</#macro>

<body>

<#macro navbar>

    <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark" >
        <a class="nav-link" href="/"> HOME</a>
        <a class="nav-link" href="/allmovies">MOVIES </a>
        <a class="nav-link" href="/allsitcoms">SITCOMS </a>
        <a class="nav-link" href="/allactors">ACTORS </a>

        <form class="form-inline ml-auto " method="post" action="/search" >
            <select class = "custom-select" name="searchFor" onClick="searchFor = this.options[this.selectedIndex].value;" required>
                <option value="" selected disabled hidden>Search for </option>
                <option value="movie">Movie</option>
                <option value="sitcom">Sitcom</option>
                <option value="contributor">Actor</option>
            </select>
            <input class = "form-control input-lg" type="text" name="keyword" placeholder="Type to search"/>
            <input class="btn btn-light" type="submit" value="Search">
        </form>

    </nav>
</#macro>

</body>
</html>
