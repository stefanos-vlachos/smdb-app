package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.repository.MovieRepository;
import com.pfseven.smdb.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public JpaRepository<Movie, Long> getRepository() {
        return movieRepository;
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findTopXRatedMovies(Integer moviesNum){
        return movieRepository.orderMoviesByRating().subList(0,moviesNum);
    }

    @Override
    public List<Movie> findMoviesByGenres(Genre genre){
        return  movieRepository.findMoviesByGenres(genre);
    }

    @Override
    public  List<KeyValue<Genre,Integer>> findMoviesPerGenre(){
        return movieRepository.findMoviesPerGenre();
    }


    @Override
    public Movie find(Long id) {
        return findLazy(id);
    }

    @Override
    public Movie findLazy(Long id) {
        return movieRepository.findLazy(id);
    }

    @Override
    public List<Movie> findAll() {
        return findAllLazy();
    }

    @Override
    public List<Movie> findAllLazy() {
        return movieRepository.findAllLazy();
    }



}
