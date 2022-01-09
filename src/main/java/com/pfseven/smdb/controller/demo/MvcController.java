package com.pfseven.smdb.controller.demo;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.service.ContributorService;
import com.pfseven.smdb.service.MovieService;
import com.pfseven.smdb.service.SitcomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller

@AllArgsConstructor
public class MvcController {

    private MovieService movieService;
    private SitcomService sitcomService;
    private ContributorService contributorService;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/allmovies")
    public String showMovies(Model model) {
        final List<Movie> allMovies = movieService.findAll();
        model.addAttribute("movies", allMovies);
        return "movies";
    }

    @GetMapping(path = "/moviedetails" ,params = {"id"})
    public String showMovieDetails(Model model , @RequestParam("id") Long id) {
        final Movie movie = movieService.find(id);
        model.addAttribute("movie", movie);
        return "movieDetails";
    }

    @GetMapping("/allsitcoms")
    public String showSitcoms(Model model) {
        final List<Sitcom> allSitcoms = sitcomService.findAll();
        model.addAttribute("sitcoms", allSitcoms);
        return "sitcoms";
    }

    @GetMapping(path = "/sitcomdetails" ,params = {"id"})
    public String showSitcomDetails(Model model , @RequestParam("id") Long id) {
        final Sitcom sitcom = sitcomService.find(id);
        model.addAttribute("sitcom", sitcom);
        return "sitcomDetails";
    }


    @PostMapping("/search")
    public String findMovie(Model model ,@Valid @RequestParam("keyword") String title) {
        Movie movie = movieService.findByTitle(title);
        model.addAttribute("movie",movie );
        return "movieDetails";
    }

    @GetMapping("/allactors")
    public String showContributors(Model model) {
        final List<Contributor> allContributors = contributorService.findAllActors();
        model.addAttribute("contributors", allContributors);
        return "contributors";
    }

    @GetMapping(path = "/contributordetails" ,params = {"id"})
    public String showContributorDetails(Model model , @RequestParam("id") Long id) {
        final Contributor contributor = contributorService.find(id);
        model.addAttribute("contributor", contributor);
        return "contributorDetails";
    }

}
