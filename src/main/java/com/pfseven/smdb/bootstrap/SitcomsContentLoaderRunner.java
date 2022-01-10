package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.service.ContributorService;
import com.pfseven.smdb.service.SitcomService;
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
public class SitcomsContentLoaderRunner extends AbstractLogComponent implements CommandLineRunner {

    private final SitcomService sitcomService;
    private final ContributorService contributorService;
    private Map<String, Sitcom> sitcoms=  new HashMap<String, Sitcom>();
    private Map<String, Contributor> contributors=  new HashMap<String, Contributor>();

    @Override
    public void run(String... args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject fileObject = (JSONObject) parser.parse(new FileReader(ResourceUtils.getFile("classpath:sitcoms.json")));
        JSONArray sitcomsArray = (JSONArray) fileObject.get("sitcoms");
        Iterator<JSONObject> sitcomsIterator = sitcomsArray.iterator();
        while (sitcomsIterator.hasNext()) {
            JSONObject object = sitcomsIterator.next();

            Sitcom sitcom = new Sitcom();
            sitcom.setTitle((String) object.get("title"));
            sitcom.setReleaseYear(Integer.parseInt((String) object.get("releaseYear")));
            sitcom.setRating(new BigDecimal((String) object.get("rating")));
            sitcom.setLanguage((String) object.get("language"));
            sitcom.setGenres(loadGenres(object));
            sitcom.setResume((String) object.get("resume"));
            sitcom.setSeasons(Integer.parseInt((String)object.get("seasons")));
            sitcom.setEpisodes(Integer.parseInt((String)object.get("episodes")));
            sitcoms.put(sitcom.getTitle(), sitcom);

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
                contributorProduction.setProduction(sitcom);
                contributorProduction.setContributor(contributor);
                contributorProduction.setRole(Role.roleCompare((String)contributorObject.get("role")));


 //               contributors.get(contributor.getFullName()).addContribution(contributorProduction);
                sitcoms.get(sitcom.getTitle()).addContribution(contributorProduction);
            }
        }
        sitcomService.createAll(new ArrayList<Sitcom>(sitcoms.values()));
        contributorService.createAll(new ArrayList<Contributor>(contributors.values()));

        /*List<Sitcom> s = sitcomService.findSitcomsByGenre(Genre.valueOf("COMEDY"));
        List<KeyValue<Genre,Integer>> s1 = sitcomService.findSitcomsPerGenre();*/
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
