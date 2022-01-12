package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.repository.SitcomRepository;
import com.pfseven.smdb.transfer.KeyValue;
import com.pfseven.smdb.transfer.NumberOfProductionsPerYearAndGenreDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SitcomServiceImpl extends BaseServiceImpl<Sitcom> implements SitcomService {
    private final SitcomRepository sitcomRepository;

    @Override
    public JpaRepository<Sitcom, Long> getRepository() {
        return sitcomRepository;
    }

    @Override
    public Sitcom findByTitle(String title) {
        Sitcom s = sitcomRepository.findByTitle(title);
        if(s!=null)
            logger.info("Found sitcom with title: {}.", s.getTitle());
        return s;
    }

    @Override
    public List<Sitcom> findTopXRatedSitcoms(Integer sitcomsNum) {
        List<Sitcom> s = sitcomRepository.orderSitcomsByRating().subList(0,sitcomsNum);
        if(!s.isEmpty())
            logger.info("Found {} top rated sitcoms.", sitcomsNum);
        return s;
    }

    @Override
    public List<Sitcom> findSitcomsByGenre(Genre genre){
        List<Sitcom> s = sitcomRepository.findSitcomsByGenre(genre);
        if(!s.isEmpty())
            logger.info("Found all sitcoms related to genre: {}.", genre.toString());
        return s;
    }

    @Override
    public List<KeyValue<Genre,Integer>> findSitcomsNumberPerGenre(){
        List<KeyValue<Genre,Integer>> s = sitcomRepository.findSitcomsNumberPerGenre();
        if(!s.isEmpty()){
            logger.info("Found number of sitcoms per genre.");
        }
        return s;
    }

    @Override
    public List<NumberOfProductionsPerYearAndGenreDto> findSitcomsNumberPerGenreAndYear() {
        List<NumberOfProductionsPerYearAndGenreDto> m = sitcomRepository.findSitcomsNumberPerGenreAndYear();
        if(!m.isEmpty())
            logger.info("Found number of sitcoms per genre and release year.");
        return m;
    }

    @Override
    public Sitcom find(Long id) {
        return findLazy(id);
    }

    @Override
    public Sitcom findLazy(Long id) {
        Sitcom s = sitcomRepository.findLazy(id);
        if(s!=null)
            logger.info("Found sitcom {} with id: {}.", s.getTitle(), s.getId());
        return s;
    }

    @Override
    public List<Sitcom> findAll() {
        return findAllLazy();
    }

    @Override
    public List<Sitcom> findAllLazy() {
        List<Sitcom> s = sitcomRepository.findAllLazy();
        if(!s.isEmpty())
            logger.info("Found and returned all sitcoms saved in the database.");
        return s;
    }

    @Override
    public void exportSitcomsToCsv(Writer sitcomsWriter, Writer contributionsWriter){
        List<Sitcom> sitcoms = findAll();

        try (CSVPrinter sitcomsCsvPrinter = new CSVPrinter(sitcomsWriter, CSVFormat.DEFAULT);
             CSVPrinter contributionsCsvPrinter = new CSVPrinter(contributionsWriter, CSVFormat.DEFAULT)){

            sitcomsCsvPrinter.printRecord("Sitcom ID", "Title", "Genres", "Language", "Rating", "Release Year",  "Seasons", "Episodes", "Resume");
            contributionsCsvPrinter.printRecord("Sitcom ID", "Title", "Contributor ID", "Contributor Name", "Role");

            for(Sitcom sitcom : sitcoms) {
                sitcomsCsvPrinter.printRecord(sitcom.getId(), sitcom.getTitle(),
                        sitcom.getGenres(), sitcom.getLanguage(), sitcom.getRating(),
                        sitcom.getReleaseYear(), sitcom.getSeasons(), sitcom.getEpisodes(), sitcom.getResume());
                for(ContributorProduction cp : sitcom.getContributorProductions()) {
                    contributionsCsvPrinter.printRecord(sitcom.getId(), sitcom.getTitle(), cp.getContributor().getId(), cp.getContributor().getFullName(), cp.getRole());
                }
            }
            logger.info("Exported all sitcoms data to CSV file.");
        }catch (IOException e){
            logger.error("Error while writing sitcoms data to CSV file.", e);
        }
    }
}
