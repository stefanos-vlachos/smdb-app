package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.transfer.KeyValue;
import com.pfseven.smdb.transfer.NumberOfProductionsPerYearAndGenreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m join fetch m.contributorProductions where upper(m.title) = upper(?1)")
    Movie findByTitle(String title);

    @Query("select distinct m from Movie m join fetch m.contributorProductions order by m.rating desc")
    List<Movie> orderMoviesByRating();

    @Query("select distinct m from Movie m join fetch m.contributorProductions where ?1 MEMBER OF m.genres")
    List<Movie> findMoviesByGenre(Genre genre);

    @Query("select new com.pfseven.smdb.transfer.KeyValue(genre, count(p.id) )" +
            "from Production p inner join p.genres genre where type(p)=Movie group by genre")
    List<KeyValue<Genre,Integer>> findMoviesNumberPerGenre();

    @Query("select m.releaseYear as releaseYear, genres as genre, count(m.id) as numberOfProductions " +
            "from Movie m inner join m.genres genres group by genre, releaseYear")
    List<NumberOfProductionsPerYearAndGenreDto> findMoviesNumberPerGenreAndYear();

    @Query("select m from Movie m join fetch m.contributorProductions where m.id = ?1")
    Movie findLazy(Long id);

    @Query("select distinct m from Movie m join fetch m.contributorProductions")
    List<Movie> findAllLazy();

}
