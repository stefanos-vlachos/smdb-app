package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Sitcom;
import com.pfseven.smdb.transfer.KeyValue;
import com.pfseven.smdb.transfer.NumberOfProductionsPerYearAndGenreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SitcomRepository extends JpaRepository<Sitcom, Long> {

    @Query("select s from Sitcom s join fetch s.contributorProductions where upper(s.title) = upper(?1)")
    Sitcom findByTitle(String title);

    @Query("select new com.pfseven.smdb.transfer.KeyValue(genre, count(p.id) )" +
            "from Production p  inner join p.genres genre where type(p)=Sitcom group by genre")
    List<KeyValue<Genre,Integer>> findSitcomsNumberPerGenre();

    @Query("select s.releaseYear as releaseYear, genres as genre, count(s.id) as numberOfProductions " +
            "from Sitcom s inner join s.genres genres group by genre, releaseYear")
    List<NumberOfProductionsPerYearAndGenreDto> findSitcomsNumberPerGenreAndYear();

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions where ?1 MEMBER OF s.genres")
    List<Sitcom> findSitcomsByGenre(Genre genre);

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions order by s.rating desc")
    List<Sitcom> orderSitcomsByRating();

    @Query("select s from Sitcom s join fetch s.contributorProductions where s.id = ?1")
    Sitcom findLazy(Long id);

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions")
    List<Sitcom> findAllLazy();
}
