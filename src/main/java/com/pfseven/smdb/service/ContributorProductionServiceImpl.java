package com.pfseven.smdb.service;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.Contributor;
import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.repository.ContributorProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContributorProductionServiceImpl extends AbstractLogComponent implements ContributorProductionService{
    private final ContributorProductionRepository contributorProductionRepository;

    public JpaRepository<ContributorProduction, Long> getRepository() {
        return contributorProductionRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ContributorProduction create(ContributorProduction entity) {
        logger.info("Creating {}", entity);
        return getRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<ContributorProduction> createAll(List<ContributorProduction> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<ContributorProduction> createAll(ContributorProduction... entities) {
        return createAll(Arrays.asList(entities));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(ContributorProduction entity) {
        logger.trace("Updating {}.", entity);
        getRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(ContributorProduction entity) {
        logger.trace("Deleting {}.", entity);
        getRepository().delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final ContributorProduction entityFound = getRepository().getById(id);
        logger.trace("Deleting {}.", entityFound);
        getRepository().deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean exists(ContributorProduction entity) {
        /*logger.trace("Checking whether {} exists.", entity);
        return getRepository().existsById(entity.getId());*/
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public ContributorProduction find(Long id) {
        logger.trace("Retrieve data by id={}", id);
        return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<ContributorProduction> findAll() {
        logger.trace("Retrieve all data");
        return getRepository().findAll();
    }
}