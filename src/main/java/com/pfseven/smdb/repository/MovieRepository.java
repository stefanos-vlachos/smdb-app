package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);

    @Query(value = "SELECT TOP ?1 * " +
            "FROM (MOVIES " +
            "INNER JOIN PRODUCTIONS ON (MOVIES.id=PRODUCTIONS.ID)) " +
            "ORDER BY PRODUCTIONS.rating DESC ", nativeQuery = true)
    List<Movie> findTopXRatedMovies(Integer moviesNum);
}
