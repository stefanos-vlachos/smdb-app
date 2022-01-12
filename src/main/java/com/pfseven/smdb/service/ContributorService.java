package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.*;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public interface ContributorService extends BaseService<Contributor, Long> {
    Contributor findContributorByFullNameAndOriginAndGender(String fullName, String origin, String gender);

    Contributor findContributorByFullName(String fullName);

    Boolean existsByName(Contributor contributor);

    Contributor findLazy(Long id);

    List<Contributor> findAllLazy();

    List<Contributor> findAllActors();

    List<Movie> findMoviesOfContributor(Long id);

    List<Movie> findMoviesOfContributorByRole (Long id, Role role);

    List<Sitcom> findSitcomsOfContributor(Long id);

    List<Sitcom> findSitcomsOfContributorByRole (Long id, Role role);

    List<Production> findContentOfContributor (Long id);

    List<Production> findContentOfContributorByRole (Long id, Role role);

    void exportContributorsToCsv(Writer contributorsWriter, Writer contributionsWriter);

    Map<Genre, List<Production>> findContentOfContributorByGenre(Long id);
}
