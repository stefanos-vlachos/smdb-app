package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.service.ContributorProductionService;
import com.pfseven.smdb.service.ContributorService;
import com.pfseven.smdb.service.MovieService;
import com.pfseven.smdb.service.SitcomService;
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
    private final SitcomService sitcomService;
    private final ContributorService contributorService;
    private final ContributorProductionService contributorProductionService;

    @Override
    public void run(String... args) throws Exception {

        JSONParser parser = new JSONParser();
        JSONObject fileObject = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:movies.json")));
        JSONArray objectsArray = (JSONArray) fileObject.get("movies");
        Iterator<JSONObject> objectsIterator = objectsArray.iterator();

        while (objectsIterator.hasNext()) {
            JSONObject object = objectsIterator.next();

            Movie movie = Movie.builder().title((String) object.get("title"))
                    .releaseYear(new SimpleDateFormat("yyyy").parse((String)object.get("releaseYear")))
                    .rating(new BigDecimal((String) object.get("rating")))
                    .language((String) object.get("language"))
                    .duration(Integer.parseInt((String) object.get("duration")))
                    .genres(loadGenres(object))
                    .resume((String) object.get("resume"))
                    .build();
            movieService.create(movie);

            loadContributors(object, movie);
        }

        fileObject = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:sitcoms.json")));
        objectsArray = (JSONArray) fileObject.get("sitcoms");
        objectsIterator = objectsArray.iterator();

        while (objectsIterator.hasNext()) {
            JSONObject object = objectsIterator.next();

            Sitcom sitcom = Sitcom.builder()
                    .title((String) object.get("title"))
                    .releaseYear(new SimpleDateFormat("yyyy").parse((String)object.get("releaseYear")))
                    .rating(new BigDecimal((String) object.get("rating")))
                    .language((String) object.get("language"))
                    .genres(loadGenres(object))
                    .resume((String) object.get("resume"))
                    .seasons(Integer.parseInt((String) object.get("seasons")))
                    .episodes(Integer.parseInt((String) object.get("episodes")))
                    .build();
            sitcomService.create(sitcom);

            //loadContributors(object, );
        }
    }

    private Set<Genre> loadGenres(JSONObject object){
        Set<Genre> genres = new HashSet<>();
        JSONArray genresArray = (JSONArray) object.get("genres");
        for (int i = 0; i < genresArray.size(); i++) {
            genres.add(Genre.stringCompare((String) genresArray.get(i)));
        }
        return genres;
    }
    
    private void loadContributors(JSONObject object, Movie movie){
        JSONArray contributorsArray = (JSONArray) object.get("productionCrew");
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

            ContributorProduction contributorProduction = ContributorProduction.builder()
                    //.production(movie)
                    //.contributor(contributor)
                    .role(Role.roleCompare((String) contributorObject.get("role")))
                    .build();

            contributorProductionService.create(contributorProduction);

            /*movie.getContributorProductions().add(contributorProduction);
            contributor.getContributorProductions().add(contributorProduction);
            movieService.update(movie);
            contributorService.update(contributor);*/

        }
    }
}
