package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.transfer.KeyValue;

import java.io.Writer;
import java.util.List;

public interface SitcomService extends BaseService<Sitcom, Long> {
    Sitcom findByTitle(String title);

    List<Sitcom> findTopXRatedSitcoms(Integer sitcomsNum);

    List<KeyValue<Genre,Integer>> findSitcomsNumberPerGenre();

    List<Sitcom> findSitcomsByGenre(Genre genre);

    Sitcom findLazy(Long id);

    List<Sitcom> findAllLazy();

    void exportSitcomsToCsv(Writer sitcomsWriter, Writer contributionsWriter);
}
