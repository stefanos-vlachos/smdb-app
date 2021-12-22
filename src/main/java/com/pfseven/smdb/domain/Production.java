package com.pfseven.smdb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity()
@Table(name="PRODUCTIONS")
@SequenceGenerator(name="idGenerator", sequenceName = "PRODUCTIONS_SEQ",initialValue = 1, allocationSize = 1)

public class Production extends BaseModel{

    @NotNull(message = "{title.null}")
    @Column(length = 30, nullable = false)
    private String title;

    @NotNull(message = "{releaseYear.null}")
    @Column(nullable = false)
    private Integer releaseYear;

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

    @JsonManagedReference("prodContributions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Contribution> prodContributions;
}

