package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.transfer.KeyValue;
import java.util.List;

public interface SitcomService extends BaseService<Sitcom, Long> {
    Sitcom findByTitle(String title);
    List<KeyValue<Genre,Integer>> findSitcomsPerGenre();
    List<Sitcom> findSitcomsByGenres(Genre genre);


    Sitcom findLazy(Long id);

    List<Sitcom> findAllLazy();
}
