package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long> {
    Contributor findContributorByFullNameAndAndOriginAndGender(String fullName, String origin, String gender);
    Contributor findContributorByFullName(String fullName);
    Boolean existsContributorByFullName(String fullName);
    Boolean existsByFullName(Contributor contributor);
}
