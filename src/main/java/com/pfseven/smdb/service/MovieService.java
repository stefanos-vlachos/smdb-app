package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Movie;

import java.util.List;

public interface MovieService extends BaseService<Movie, Long>{
    Movie findByTitle(String title);
//    List<Movie> findByGenre(String genre);
}
