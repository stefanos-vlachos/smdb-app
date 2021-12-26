package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.service.ContributorProductionService;
import com.pfseven.smdb.service.ContributorService;
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
    private final ContributorService contributorService;
    private final ContributorProductionService contributorProductionService;

    @Override
    public void run(String... args) throws Exception {

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:movies.json")));
        JSONArray moviesObjects = (JSONArray) obj.get("movies");
        Iterator<JSONObject> moviesIterator = moviesObjects.iterator();

        while (moviesIterator.hasNext()) {

            Set<ContributorProduction> contributions = new HashSet<>();
            Set<Contributor> contributors = new HashSet<>();

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
            movieService.create(movie);

            JSONArray contributorsArray = (JSONArray) element.get("productionCrew");
            Iterator<JSONObject> contributorsIterator = contributorsArray.iterator();

            while (contributorsIterator.hasNext()) {
                JSONObject contributorObject = contributorsIterator.next();
                Contributor contributor = Contributor.builder()
                        .fullName((String) contributorObject.get("fullName"))
                        .gender((String) contributorObject.get("gender"))
                        .origin((String) contributorObject.get("origin"))
                        .build();
                if(contributorService.findContributorByFullNameAndAndOriginAndGender(
                        contributor.getFullName(), contributor.getOrigin(), contributor.getGender())==null)
                    contributorService.create(contributor);

            }
        }
    }
}
