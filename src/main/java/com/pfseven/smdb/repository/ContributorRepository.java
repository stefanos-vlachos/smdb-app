package com.pfseven.smdb.repository;

import com.pfseven.smdb.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long> {

    @Query("select c from Contributor c join fetch c.contributorProductions where upper(c.fullName) = upper(?1) AND upper(c.origin) = upper(?2) AND upper(c.gender) = upper(?3) ")
    Contributor findContributorByFullNameAndOriginAndGender(String fullName, String origin, String gender);

    @Query("select c from Contributor c join fetch c.contributorProductions where upper(c.fullName) = upper(?1)")
    Contributor findContributorByFullName(String fullName);

    @Query("select c from Contributor c join fetch c.contributorProductions where c.id = ?1")
    Contributor findLazy(Long id);

    @Query("select distinct c from Contributor c join fetch c.contributorProductions")
    List<Contributor> findAllLazy();

    @Query("select distinct c from Contributor c join fetch c.contributorProductions cp where cp.role = 'ACTOR'")
    List<Contributor> findAllActors();

    @Query("select distinct m from Movie m join fetch m.contributorProductions cp where cp.contributor.id = ?1")
    List<Movie> findMoviesOfContributor(Long id);

    @Query("select distinct m from Movie m join fetch m.contributorProductions cp where cp.contributor.id = ?1 and cp.role= ?2")
    List<Movie> findMoviesOfContributorByRole(Long id, Role role);

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions cp where cp.contributor.id = ?1")
    List<Sitcom> findSitcomsOfContributor(Long id);

    @Query("select distinct s from Sitcom s join fetch s.contributorProductions cp where cp.contributor.id = ?1 and cp.role= ?2")
    List<Sitcom> findSitcomsOfContributorByRole(Long id, Role role);

    @Query("select distinct p from Production p join fetch p.contributorProductions cp where cp.contributor.id = ?1")
    List<Production> findContentOfContributor(Long id);

    @Query("select distinct p from Production p join fetch p.contributorProductions cp where cp.contributor.id = ?1 and cp.role = ?2")
    List<Production> findContentOfContributorByRole(Long id, Role role);

}
