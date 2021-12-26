package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.ContributorProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorProductionRepository extends JpaRepository<ContributorProduction, Long> {

}
