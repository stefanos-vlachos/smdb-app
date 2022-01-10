package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.transfer.KeyValue;
import com.pfseven.smdb.transfer.NumberOfMoviesPerYearAndGenreDto;
import java.io.Writer;
import java.util.List;

public interface MovieService extends BaseService<Movie, Long> {
    Movie findByTitle(String title);

    List<Movie> findTopXRatedMovies(Integer moviesNum);

    List<Movie> findMoviesByGenre(Genre genre);

    List<KeyValue<Genre,Integer>>findMoviesNumberPerGenre();

    List<NumberOfMoviesPerYearAndGenreDto>findMoviesNumberPerGenreAndYear();

    Movie findLazy(Long id);

    List<Movie> findAllLazy();

    void exportMoviesToCsv(Writer moviesWriter, Writer contributionsWriter);


}
