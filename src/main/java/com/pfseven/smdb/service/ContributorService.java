package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;

public interface ContributorService extends BaseService<Contributor, Long> {
    Contributor findContributorByFullNameAndAndOriginAndGender(String fullName, String origin, String gender);
    Contributor findContributorByFullName(String fullName);
}
