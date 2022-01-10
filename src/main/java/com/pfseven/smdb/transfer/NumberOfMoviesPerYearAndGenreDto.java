package com.pfseven.smdb.transfer;

import com.pfseven.smdb.domain.Genre;

public interface NumberOfMoviesPerYearAndGenreDto {
    Integer getReleaseYear();

    Genre getGenre();

    Integer getNumberOfMovies();
}
