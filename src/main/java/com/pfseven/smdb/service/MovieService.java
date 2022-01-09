package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.transfer.KeyValue;

import java.io.Writer;
import java.util.List;

public interface MovieService extends BaseService<Movie, Long> {
    Movie findByTitle(String title);

    List<Movie> findTopXRatedMovies(Integer moviesNum);

    List<Movie> findMoviesByGenre(Genre genre);

    List<KeyValue<Genre,Integer>>findMoviesNumberPerGenre();

    Movie findLazy(Long id);

    List<Movie> findAllLazy();

    void exportMoviesToCsv(Writer moviesWriter, Writer contributionsWriter);
}
