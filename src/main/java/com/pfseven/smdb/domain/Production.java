package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@Entity()
@Table(name = "PRODUCTIONS")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTIONS_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)

public class Production extends BaseModel {

    @NotNull(message = "{title.null}")
    @Column(nullable = false)
    private String title;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{releaseYear.null}")
    @Column(nullable = false)
    private Date releaseYear;

    @NotNull(message = "{rating.null}")
    @Column(precision = 4, scale = 2, nullable = false)
    @Max(value = 10)
    private BigDecimal rating;

    @NotNull(message = "{language.null}")
    @Column(length = 20, nullable = false)
    private String language;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "PRODUCTION_GENRES")
    private Set<Genre> genres;

    @NotNull(message = "{resume.null}")
    @Column(length = 2048, nullable = false)
    private String resume;

    @OneToMany(mappedBy = "production", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ContributorProduction> contributorProductions = new HashSet<>();

    public void addContribution(ContributorProduction contribution){
        this.contributorProductions.add(contribution);
    }

}

