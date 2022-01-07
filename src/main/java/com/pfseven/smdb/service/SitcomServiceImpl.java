package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.repository.SitcomRepository;
import com.pfseven.smdb.transfer.KeyValue;
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
        return sitcomRepository.findByTitle(title);
    }

    @Override
    public List<Sitcom> findSitcomsByGenres(Genre genre){
        return  sitcomRepository.findSitcomsByGenres(genre);
    }

    @Override
    public List<KeyValue<Genre,Integer>> findSitcomsPerGenre(){
        return sitcomRepository.findSitcomsPerGenre();
    }


    @Override
    public Sitcom find(Long id) {
        return findLazy(id);
    }

    @Override
    public Sitcom findLazy(Long id) {
        return sitcomRepository.findLazy(id);
    }

    @Override
    public List<Sitcom> findAll() {
        return findAllLazy();
    }

    @Override
    public List<Sitcom> findAllLazy() {
        return sitcomRepository.findAllLazy();
    }

    @Override
    public void exportSitcomsToCsv(Writer writer){
        List<Sitcom> sitcoms = findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("Movie ID", "Title", "Genres", "Language", "Rating", "Release Year",  "Seasons", "Episodes", "Resume");
            for(Sitcom sitcom : sitcoms) {
                csvPrinter.printRecord(sitcom.getId(), sitcom.getTitle(),
                        sitcom.getGenres(), sitcom.getLanguage(), sitcom.getRating(),
                        sitcom.getReleaseYear(), sitcom.getSeasons(), sitcom.getEpisodes(), sitcom.getResume());
            }
        }catch (IOException e){
            logger.error("Error while writing CSV", e);
        }
    }
}
