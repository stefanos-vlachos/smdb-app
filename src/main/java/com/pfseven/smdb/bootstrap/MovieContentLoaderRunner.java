package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.service.ContributorService;
import com.pfseven.smdb.service.MovieService;
import com.pfseven.smdb.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
@Profile("productions-loader")
public class MovieContentLoaderRunner extends AbstractLogComponent implements CommandLineRunner {

    private final MovieService movieService;
    private final ContributorService contributorService;
    private Map<String, Movie> movies=  new HashMap<String, Movie>();
    private Map<String, Contributor> contributors=  new HashMap<String, Contributor>();

    @Override
    public void run(String... args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject fileObject = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:movies.json")));
        JSONArray moviesArray = (JSONArray) fileObject.get("movies");
        Iterator<JSONObject> moviesIterator = moviesArray.iterator();
        while (moviesIterator.hasNext()) {
            JSONObject object = moviesIterator.next();

            Movie movie = new Movie();
            movie.setTitle((String) object.get("title"));
            movie.setReleaseYear(new SimpleDateFormat("yyyy").parse((String) object.get("releaseYear")));
            movie.setRating(new BigDecimal((String) object.get("rating")));
            movie.setLanguage((String) object.get("language"));
            movie.setDuration(Integer.parseInt((String) object.get("duration")));
            movie.setGenres(loadGenres(object));
            movie.setResume((String) object.get("resume"));
            movies.put(movie.getTitle(), movie);

            JSONArray contributorsArray = (JSONArray) object.get("productionCrew");
            Iterator<JSONObject> contributorsIterator = contributorsArray.iterator();

            while (contributorsIterator.hasNext()) {

                JSONObject contributorObject = contributorsIterator.next();

                Contributor contributor = null;

                if(!contributors.containsKey((String) contributorObject.get("fullName"))) {
                    contributor=new Contributor();
                    contributor.setFullName((String) contributorObject.get("fullName"));
                    contributor.setGender((String) contributorObject.get("gender"));
                    contributor.setOrigin((String) contributorObject.get("origin"));
                    contributors.put(contributor.getFullName(), contributor);
                }
                else
                    contributor = contributors.get((String) contributorObject.get("fullName"));

                ContributorProduction contributorProduction = new ContributorProduction();
                contributorProduction.setProduction(movie);
                contributorProduction.setContributor(contributor);
                contributorProduction.setRole(Role.roleCompare((String)contributorObject.get("role")));


                contributors.get(contributor.getFullName()).addContribution(contributorProduction);
                movies.get(movie.getTitle()).addContribution(contributorProduction);
            }
        }
        movieService.createAll(new ArrayList<Movie>(movies.values()));
        contributorService.createAll(new ArrayList<Contributor>(contributors.values()));

        List<Movie> m1 = movieService.findMoviesByGenres(Genre.valueOf("DRAMA"));
        List<KeyValue<Genre,Integer>> m2 = movieService.findMoviesPerGenre();
    }

    private Set<Genre> loadGenres(JSONObject object){
        Set<Genre> genres = new HashSet<>();
        JSONArray genresArray = (JSONArray) object.get("genres");
        for (int i = 0; i < genresArray.size(); i++) {
            genres.add(Genre.stringCompare((String) genresArray.get(i)));
        }
        return genres;
    }

}
