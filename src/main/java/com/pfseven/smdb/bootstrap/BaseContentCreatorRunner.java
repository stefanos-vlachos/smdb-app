package com.pfseven.smdb.bootstrap;

import com.pfseven.smdb.base.AbstractLogComponent;
import com.pfseven.smdb.domain.ContributorProduction;
import com.pfseven.smdb.domain.Genre;
import com.pfseven.smdb.domain.Movie;
import com.pfseven.smdb.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Profile("base-content-creator")
public class BaseContentCreatorRunner extends AbstractLogComponent implements CommandLineRunner {

    private final MovieService movieService;
    private final Set<Genre> genres;
    private final Set<ContributorProduction> contributorProductions;

    @Override
    public void run(String... args) throws Exception {
        genres.add(Genre.CRIME);
        genres.add(Genre.ADVENTURE);



        Movie movie = Movie.builder().title("The Power of the Dog")
                .releaseYear(new Date(2021))
                .rating(BigDecimal.valueOf(10))
                .language("eng")
                .duration(120)
                .genres(genres)
                .resume("Charismatic rancher Phil Burbank inspires fear and awe in those around him. When his brother brings home a new wife and her son, Phil torments them until he finds himself exposed to the possibility of love.")
                .contributorProductions(null)
                .build();

        Movie movie2 = Movie.builder().title("Drive my car")
                .releaseYear(new Date(2021))
                .rating(BigDecimal.valueOf(10))
                .language("eng")
                .duration(150)
                .genres(genres)
                .resume("Charismatic rancher Phil Burbank inspires fear and awe in those around him. When his brother brings home a new wife and her son, Phil torments them until he finds himself exposed to the possibility of love.")
                .contributorProductions(null)
                .build();

        movieService.create(movie);
        movieService.create(movie2);

        logger.info("Found {}",movieService.findByTitle("Drive my car").getDuration());

    }
}
