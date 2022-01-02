package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.repository.SitcomRepository;
import com.pfseven.smdb.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
}
