package com.pfseven.smdb.service;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseModel> extends AbstractLogComponent
        implements BaseService<T, Long> {
    public abstract JpaRepository<T, Long> getRepository();

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public T create(T entity) {
        logger.info("Creating {}", entity);
        return getRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<T> createAll(T... entities) {
        return createAll(Arrays.asList(entities));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<T> createAll(List<T> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(T entity) {
        logger.trace("Updating {}.", entity);
        getRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(T entity) {
        logger.trace("Deleting {}.", entity);
        getRepository().delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final T entityFound = getRepository().getById(id);
        logger.trace("Deleting {}.", entityFound);
        getRepository().deleteById(id);
    }

    @Override
    public boolean exists(T entity) {
        logger.trace("Checking whether {} exists.", entity);
        return getRepository().existsById(entity.getId());
    }

    @Override
    public T find(Long id) {
        logger.trace("Retrieve data by id={}", id);
        return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<T> findAll() {
        logger.trace("Retrieve all data");
        return getRepository().findAll();
    }
}
