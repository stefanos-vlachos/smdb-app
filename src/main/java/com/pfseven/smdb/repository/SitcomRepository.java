package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SitcomRepository extends JpaRepository<Sitcom, Long> {

    @Query("select s from Sitcom s join fetch s.contributorProductions where s.title = ?1")
    Sitcom findByTitle(String title);

    @Query("select new com.pfseven.smdb.transfer.KeyValue(genre, count(p.id) )" +
            "from Production p  inner join p.genres genre where type(p)=Sitcom group by genre")
    List<KeyValue<Genre,Integer>> findSitcomsPerGenre();

    List<Sitcom> findSitcomsByGenres(Genre genre);

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions order by s.rating desc")
    List<Sitcom> orderSitcomsByRating();

    @Query("select s from Sitcom s join fetch s.contributorProductions where s.id = ?1")
    Sitcom findLazy(Long id);

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions")
    List<Sitcom> findAllLazy();
}
