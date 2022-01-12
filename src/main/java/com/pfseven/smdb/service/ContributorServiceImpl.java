package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContributorServiceImpl extends BaseServiceImpl<Contributor> implements ContributorService {
    private final ContributorRepository contributorRepository;

    @Override
    public JpaRepository<Contributor, Long> getRepository() {
        return contributorRepository;
    }

    @Override
    public Contributor findContributorByFullNameAndOriginAndGender(String fullName, String origin, String gender) {
        Contributor c = contributorRepository.findContributorByFullNameAndOriginAndGender(fullName, origin, gender);
        if(c!=null)
            logger.info("Found {} in database, with origin:{} and gender:{}.", c.getFullName(), c.getOrigin(), c.getGender());
        return c;
    }

    @Override
    public Contributor findContributorByFullName(String fullName) {
        Contributor c = contributorRepository.findContributorByFullName(fullName);
        if(c!=null)
            logger.info("Found {} in database.", c.getFullName());
        return c;
    }

    @Override
    public Contributor find(Long id) {
        return findLazy(id);
    }

    @Override
    public Contributor findLazy(Long id) {
        Contributor c = contributorRepository.findLazy(id);
        if(c!=null)
            logger.info("Found contributor {} with id:{}.", c.getFullName(), c.getId());
        return c;
    }

    @Override
    public List<Contributor> findAll() {
        return findAllLazy();
    }

    @Override
    public List<Contributor> findAllLazy() {
        List<Contributor> c = contributorRepository.findAllLazy();
        if(!c.isEmpty())
            logger.info("Found and returned all contributors saved in the database.");
        return c;
    }

    @Override
    public List<Contributor> findAllActors() {
        List<Contributor> c = contributorRepository.findAllActors();
        if(!c.isEmpty())
            logger.info("Found and returned all actors saved in the database.");
        return c;
    }

    @Override
    public List<Movie> findMoviesOfContributor(Long id){
        List<Movie> m = contributorRepository.findMoviesOfContributor(id);
        if(!m.isEmpty())
            logger.info("Found all movies that contributor with id:{} has participated in.", id);
        return m;
    }

    @Override
    public List<Movie> findMoviesOfContributorByRole(Long id, Role role) {
        List<Movie> m = contributorRepository.findMoviesOfContributorByRole(id, role);
        if(!m.isEmpty())
            logger.info("Found all movies in which contributor with id:{} has been a {}.", id, role.toString());
        return m;
    }

    @Override
    public List<Sitcom> findSitcomsOfContributor(Long id) {
        List<Sitcom> s = contributorRepository.findSitcomsOfContributor(id);
        if(!s.isEmpty())
            logger.info("Found all sitcoms that contributor with id:{} has participated in.", id);
        return s;
    }

    @Override
    public List<Sitcom> findSitcomsOfContributorByRole(Long id, Role role) {
        List<Sitcom> s = contributorRepository.findSitcomsOfContributorByRole(id, role);
        if(!s.isEmpty())
            logger.info("Found all sitcoms in which contributor with id:{} has been {}.", id, role.toString());
        return s;
    }

    @Override
    public List<Production> findContentOfContributor(Long id) {
        List<Production> p = contributorRepository.findContentOfConributor(id);
        if(!p.isEmpty())
            logger.info("Found all productions that contributor with id:{} has participated in.", id);
        return p;
    }

    @Override
    public List<Production> findContentOfContributorByRole(Long id, Role role) {
        List<Production> p = contributorRepository.findContentOfConributorByRole(id, role);
        if(!p.isEmpty())
            logger.info("Found all productions in which contributor with id:{} has been {}.", id, role.toString());
        return p;
    }

    @Override
    public void exportContributorsToCsv(Writer contributorsWriter, Writer contributionsWriter){
        List<Contributor> contributors = findAll();

        try (CSVPrinter contributorsCsvPrinter = new CSVPrinter(contributorsWriter, CSVFormat.DEFAULT);
             CSVPrinter contributionsCsvPrinter2 = new CSVPrinter(contributionsWriter, CSVFormat.DEFAULT)){

            contributorsCsvPrinter.printRecord("Contributor ID", "Full Name", "Origin", "Gender");
            contributionsCsvPrinter2.printRecord("Contributor ID", "Contributor Name", "Production ID", "Production Title", "Role");

            for(Contributor contributor : contributors) {
                contributorsCsvPrinter.printRecord(contributor.getId(), contributor.getFullName(),
                        contributor.getOrigin(), contributor.getGender());
                for (ContributorProduction cp : contributor.getContributorProductions()){
                    contributionsCsvPrinter2.printRecord(contributor.getId(), contributor.getFullName(), cp.getProduction().getId(), cp.getProduction().getTitle(), cp.getRole());
                }
            }
            logger.info("Exported all Contributors data to csv file.");
        }catch (IOException e){
            logger.error("Error while writing Contributors data to CSV file.", e);
        }
    }

    @Override
    public Map<Genre, List<Production>> findContentOfContributorByGenre(Long id) {
        Map<Genre, List<Production>> output = new HashMap<>();
        List<Production> prods = contributorRepository.findContentOfConributor(id);
        for(Production p: prods){
            for(Genre g: p.getGenres()){
                if(output.get(g)==null){
                    output.put(g,new ArrayList<Production>(Arrays.asList(p)));
                }
                else{
                    output.get(g).add(p);
                }
            }
        }
        if(!output.isEmpty())
            logger.info("Found all productions that contributor with id:{} has participated in, grouped by the genre of the production.", id);
        return output;
    }

}
