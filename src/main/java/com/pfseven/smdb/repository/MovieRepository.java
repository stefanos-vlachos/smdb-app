package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);
}
