package com.pfseven.smdb.controller;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.MovieService;
import com.pfseven.smdb.transfer.ApiResponse;
import com.pfseven.smdb.transfer.KeyValue;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController extends AbstractController<Movie> {

    private final MovieService movieService;

    @Override
    protected BaseService<Movie, Long> getBaseService() {
        return movieService;
    }

    @GetMapping(params = {"top"})
    public ResponseEntity<ApiResponse<List<Movie>>> findTopXRatedMovies(@RequestParam("top") Integer moviesNum) {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder().data(movieService.findTopXRatedMovies(moviesNum)).build());
    }

    @GetMapping(params = {"genre"})
    public ResponseEntity<ApiResponse<List<Movie>>> findMoviesByGenre(@RequestParam("genre") Genre genre) {
        return ResponseEntity.ok(ApiResponse.<List<Movie>>builder().data(movieService.findMoviesByGenre(genre)).build());
    }

    @GetMapping(headers = "action=findMoviesPerGenre")
    public ResponseEntity<ApiResponse<List<KeyValue<Genre, Integer>>>> findMoviesPerGenre() {
        return ResponseEntity.ok(ApiResponse.<List<KeyValue<Genre, Integer>>>builder().data(movieService.findMoviesPerGenre()).build());
    }

    @GetMapping(headers="action=export")
    public void exportToCSV() throws IOException {
        movieService.exportMoviesToCsv(new FileWriter("src/main/resources/csvExports/movies.csv"),
                new FileWriter("src/main/resources/csvExports/movieContributions.csv"));
    }
}
