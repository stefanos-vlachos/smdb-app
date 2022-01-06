package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;

import java.util.List;

public interface ContributorService extends BaseService<Contributor, Long> {
    Contributor findContributorByFullNameAndAndOriginAndGender(String fullName, String origin, String gender);

    Contributor findContributorByFullName(String fullName);

    Boolean existsByName(Contributor contributor);

    Contributor findLazy(Long id);

    List<Contributor> findAllLazy();

    List<Contributor> findAllActors();
}
