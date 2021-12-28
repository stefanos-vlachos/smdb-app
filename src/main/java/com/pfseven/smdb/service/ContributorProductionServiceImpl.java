package com.pfseven.smdb.service;

import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.repository.ContributorProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContributorProductionServiceImpl extends BaseServiceImpl<ContributorProduction> implements ContributorProductionService{
    private final ContributorProductionRepository contributorProductionRepository;

    public JpaRepository<ContributorProduction, Long> getRepository() {
        return contributorProductionRepository;
    }

}