package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Sitcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitcomRepository extends JpaRepository<Sitcom, Long> {

}
