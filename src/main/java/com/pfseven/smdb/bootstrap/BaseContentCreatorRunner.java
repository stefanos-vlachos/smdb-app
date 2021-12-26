package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.service.MovieService;
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
@Profile("base-content-creator")
public class BaseContentCreatorRunner extends AbstractLogComponent implements CommandLineRunner {

    private final MovieService movieService;
    private final Set<Genre> genres;
    private final Set<ContributorProduction> contributorProductions;

    @Override
    public void run(String... args) throws Exception {

        List<Movie> movies = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:movies.json")));
        JSONArray moviesObjects = (JSONArray) obj.get("movies");
        Iterator<JSONObject> iterator = moviesObjects.iterator();

        while (iterator.hasNext()) {
            JSONObject element = iterator.next();

            Movie movie = Movie.builder().title((String) element.get("title"))
                    .releaseYear(new SimpleDateFormat("yyyy").parse((String)element.get("releaseYear")))
                    .rating(new BigDecimal((String) element.get("rating")))
                    .language((String) element.get("language"))
                    .duration(Integer.parseInt((String) element.get("duration")))
                    .genres(null)
                    .resume((String) element.get("resume"))
                    .contributorProductions(null)
                    .build();

            movies.add(movie);
        }

        movieService.createAll(movies);
    }
}
