package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name="CONTRIBUTORS")
@SequenceGenerator(name="idGenerator", sequenceName = "CONTRIBUTORS_SEQ",initialValue = 1, allocationSize = 1)
public class Contributor extends BaseModel{

    @NotNull(message = "{fullName.null}")
    @Column(length = 50, nullable = false)
    private String fullName;

    @NotNull(message = "{gender.null}")
    @Column(length = 6, nullable = false)
    private String gender;

    @NotNull(message = "{origin.null}")
    @Column(length = 20, nullable = false)
    private String origin;

    @OneToMany(mappedBy = "contributor")
    private Set<ContributorProduction> contributorProductions = new HashSet<>();

}
