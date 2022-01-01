package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContributorServiceImpl extends BaseServiceImpl<Contributor> implements ContributorService {
    private final ContributorRepository contributorRepository;

    @Override
    public JpaRepository<Contributor, Long> getRepository() {
        return contributorRepository;
    }

    @Override
    public Contributor findContributorByFullNameAndAndOriginAndGender(String fullName, String origin, String gender) {
        return contributorRepository.findContributorByFullNameAndAndOriginAndGender(fullName, origin, gender);
    }

    @Override
    public Contributor findContributorByFullName(String fullName) {
        return contributorRepository.findContributorByFullName(fullName);
    }

    @Override
    public Boolean existsByName(Contributor contributor) {
        return contributorRepository.existsContributorByFullName(contributor.getFullName());
    }
}
