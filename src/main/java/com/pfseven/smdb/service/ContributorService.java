package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Role;

import java.util.List;

public interface ContributorService extends BaseService<Contributor, Long> {
    Contributor findContributorByFullNameAndAndOriginAndGender(String fullName, String origin, String gender);

    Contributor findContributorByFullName(String fullName);

    Boolean existsByName(Contributor contributor);

    Contributor findLazy(Long id);

    List<Contributor> findAllLazy();

    List<Contributor> findAllActors();

    List<Movie> findMoviesOfContributor(Long id);

    List<Movie> findMoviesOfContributorByRole (Long id, Role role);
}
