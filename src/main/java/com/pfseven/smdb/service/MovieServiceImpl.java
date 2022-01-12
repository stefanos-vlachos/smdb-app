package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.repository.MovieRepository;
import com.pfseven.smdb.transfer.KeyValue;
import com.pfseven.smdb.transfer.NumberOfProductionsPerYearAndGenreDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.Writer;
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
        Movie m = movieRepository.findByTitle(title);
        if(m!=null)
            logger.info("Found movie with title:{}.", m.getTitle());
        return m;
    }

    @Override
    public List<Movie> findTopXRatedMovies(Integer moviesNum){
        List<Movie> m = movieRepository.orderMoviesByRating().subList(0,moviesNum);
        if(!m.isEmpty())
            logger.info("Found {} top rated movies.", moviesNum);
        return m;
    }

    @Override
    public List<Movie> findMoviesByGenre(Genre genre){
        List<Movie> m = movieRepository.findMoviesByGenre(genre);
        if(!m.isEmpty())
            logger.info("Found all movies related to genre: {}.",genre.toString());
        return  m;
    }

    @Override
    public  List<KeyValue<Genre,Integer>> findMoviesNumberPerGenre(){
        List<KeyValue<Genre,Integer>> m = movieRepository.findMoviesNumberPerGenre();
        if(!m.isEmpty())
            logger.info("Found number of movies per movie genre.");
        return m;
    }

    @Override
    public List<NumberOfProductionsPerYearAndGenreDto> findMoviesNumberPerGenreAndYear() {
        List<NumberOfProductionsPerYearAndGenreDto> m = movieRepository.findMoviesNumberPerGenreAndYear();
        if(!m.isEmpty())
            logger.info("Found number of movies per genre and release year.");
        return m;
    }

    @Override
    public Movie find(Long id) {
        return findLazy(id);
    }

    @Override
    public Movie findLazy(Long id) {
        Movie m = movieRepository.findLazy(id);
        if(m!=null)
            logger.info("Found movie {} with id: {}.", m.getTitle(), m.getId());
        return m;
    }

    @Override
    public List<Movie> findAll() {
        return findAllLazy();
    }

    @Override
    public List<Movie> findAllLazy() {
        List<Movie> m = movieRepository.findAllLazy();
        if(!m.isEmpty())
            logger.info("Found and returned all movies data saved in the database.");
        return m;
    }

    @Override
    public void exportMoviesToCsv(Writer moviesWriter, Writer contributionsWriter){
        List<Movie> movies = findAll();

        try (CSVPrinter moviesCsvPrinter = new CSVPrinter(moviesWriter, CSVFormat.DEFAULT);
            CSVPrinter contributionsCsvPrinter = new CSVPrinter(contributionsWriter, CSVFormat.DEFAULT);){

            moviesCsvPrinter.printRecord("Movie ID", "Title", "Duration", "Genres", "Language", "Rating", "Release Year", "Resume");
            contributionsCsvPrinter.printRecord("Movie ID", "Title", "Contributor ID", "Contributor Name", "Role");

            for(Movie movie : movies) {
                moviesCsvPrinter.printRecord(movie.getId(), movie.getTitle(),
                        movie.getDuration(), movie.getGenres(), movie.getLanguage(), movie.getRating(),
                        movie.getReleaseYear(), movie.getResume());
                for(ContributorProduction cp : movie.getContributorProductions()) {
                    contributionsCsvPrinter.printRecord(movie.getId(), movie.getTitle(), cp.getContributor().getId(), cp.getContributor().getFullName(), cp.getRole());
                }
            }
            logger.info("Exported all movies data to CSV file.");
        }catch (IOException e){
            logger.error("Error while writing movies data to CSV file.", e);
        }
    }
}
