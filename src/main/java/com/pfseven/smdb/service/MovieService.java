package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.transfer.KeyValue;

import java.util.List;

public interface MovieService extends BaseService<Movie, Long> {
    Movie findByTitle(String title);
    List<Movie> findTopXRatedMovies(Integer moviesNum);
//    List<Movie> findByGenre(String genre);
    List<Movie> findMoviesByGenres(Genre genre);

    List<KeyValue<Genre,Integer>>findMoviesPerGenre();

    Movie findLazy(Long id);

    List<Movie> findAllLazy();
}
