package com.pfseven.smdb.transfer;

import com.pfseven.smdb.domain.Genre;

public interface NumberOfProductionsPerYearAndGenreDto {
    Integer getReleaseYear();

    Genre getGenre();

    Integer getNumberOfProductions();
}
