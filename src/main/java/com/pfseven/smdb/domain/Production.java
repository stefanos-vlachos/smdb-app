package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
@Table(name="PRODUCTIONS")
@SequenceGenerator(name="productionIdGenerator", sequenceName = "PRODUCTIONS_SEQ",initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)

public class Production extends BaseModel{

    @NotNull(message = "{title.null}")
    @Column(length = 30, nullable = false)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{releaseYear.null}")
    @Column(nullable = false)
    private Date releaseYear;

    @NotNull(message = "{rating.null}")
    @Column(precision = 4, nullable = false)
    private BigDecimal rating;

    @NotNull(message = "{language.null}")
    @Column(length = 20, nullable = false)
    private String language;

    //private List<Genre> genres;

    @NotNull(message = "{resume.null}")
    @Column(length = 100, nullable = false)
    private String resume;

    @OneToMany(mappedBy = "production")
    private Set<ContributorProduction> contentAuthors = new HashSet<>();
}

