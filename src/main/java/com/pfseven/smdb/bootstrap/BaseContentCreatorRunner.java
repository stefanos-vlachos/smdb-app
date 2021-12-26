package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.*;
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

    @Override
    public void run(String... args) throws Exception {

        List<Movie> movies = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:movies.json")));
        JSONArray moviesObjects = (JSONArray) obj.get("movies");
        Iterator<JSONObject> moviesIterator = moviesObjects.iterator();

        while (moviesIterator.hasNext()) {
            Set<Genre> genres = new HashSet<>();
            JSONObject element = moviesIterator.next();

            JSONArray genresArray = (JSONArray) element.get("genres");
            for (int i = 0; i < genresArray.size(); i++) {
                genres.add(Genre.stringCompare((String) genresArray.get(i)));
            }

            Movie movie = Movie.builder().title((String) element.get("title"))
                    .releaseYear(new SimpleDateFormat("yyyy").parse((String)element.get("releaseYear")))
                    .rating(new BigDecimal((String) element.get("rating")))
                    .language((String) element.get("language"))
                    .duration(Integer.parseInt((String) element.get("duration")))
                    .genres(genres)
                    .resume((String) element.get("resume"))
                    .build();

            /*JSONArray contributorsArray = (JSONArray) element.get("productionCrew");
            Iterator<JSONObject> contributorsIterator = contributorsArray.iterator();

            Set<ContributorProduction> contributions = new HashSet<>();
            while (contributorsIterator.hasNext()) {
                JSONObject contributorObject = contributorsIterator.next();
                Contributor contributor = Contributor.builder()
                        .fullName((String) contributorObject.get("fullName"))
                        .gender((String) contributorObject.get("gender"))
                        .origin((String) contributorObject.get("origin"))
                        .build();
                ContributorProduction contribution = ContributorProduction.builder()
                        .production(movie)
                        .role(Role.roleCompare((String) contributorObject.get("origin")))
                        .contributor(contributor)
                        .build();
                contributions.add(contribution);
            }*/

            movies.add(movie);

        }

        movieService.createAll(movies);
    }
}
