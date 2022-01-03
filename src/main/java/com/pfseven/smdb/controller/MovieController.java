package com.pfseven.smdb.controller;

import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.service.BaseService;
import com.pfseven.smdb.service.MovieService;
import com.pfseven.smdb.transfer.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Movie> findTopXRatedMovies(@RequestParam("top") Integer moviesNum) {
        return movieService.findTopXRatedMovies(moviesNum);
    }
}
