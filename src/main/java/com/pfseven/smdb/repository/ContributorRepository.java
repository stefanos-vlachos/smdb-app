package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long> {
    Contributor findContributorByFullNameAndAndOriginAndGender(String fullName, String origin, String gender);
    Contributor findContributorByFullName(String fullName);
}
