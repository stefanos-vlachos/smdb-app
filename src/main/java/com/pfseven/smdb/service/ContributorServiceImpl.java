package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.*;
import com.pfseven.smdb.repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
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

    @Override
    public Contributor find(Long id) {
        return findLazy(id);
    }

    @Override
    public Contributor findLazy(Long id) {
        return contributorRepository.findLazy(id);
    }

    @Override
    public List<Contributor> findAll() {
        return findAllLazy();
    }

    @Override
    public List<Contributor> findAllLazy() {
        return contributorRepository.findAllLazy();
    }

    @Override
    public List<Contributor> findAllActors() {
        return contributorRepository.findAllActors();
    }

    @Override
    public List<Movie> findMoviesOfContributor(Long id){
        return contributorRepository.findMoviesOfContributor(id);
    }

    @Override
    public List<Movie> findMoviesOfContributorByRole(Long id, Role role) {
        return contributorRepository.findMoviesOfContributorByRole(id, role);
    }

    @Override
    public List<Sitcom> findSitcomsOfContributor(Long id) {
        return contributorRepository.findSitcomsOfContributor(id);
    }

    @Override
    public List<Sitcom> findSitcomsOfContributorByRole(Long id, Role role) {
        return contributorRepository.findSitcomsOfContributorByRole(id, role);
    }

    @Override
    public List<Production> findContentOfContributor(Long id) {
        return contributorRepository.findContentOfConributor(id);
    }

    @Override
    public List<Production> findContentOfContributorByRole(Long id, Role role) {
        return contributorRepository.findContentOfConributorByRole(id, role);
    }

    @Override
    public void exportContributorsToCsv(Writer writer){
        List<Contributor> contributors = findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            for(Contributor contributor : contributors) {
                csvPrinter.printRecord("Contributor ID", "Full Name", "Origin", "Gender");
                csvPrinter.printRecord(contributor.getId(), contributor.getFullName(),
                        contributor.getOrigin(), contributor.getGender());
            }
        }catch (IOException e){
            logger.error("Error while writing CSV", e);
        }
    }

}
