package com.pfseven.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pfseven.smdb.util.ContributorSerializer;
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
@Table(name = "CONTRIBUTORS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTRIBUTORS_SEQ", initialValue = 1, allocationSize = 1)
@JsonSerialize(using = ContributorSerializer.class)
public class Contributor extends BaseModel {

    @NotNull(message = "{fullName.null}")
    @Column(length = 50, nullable = false)
    private String fullName;

    @NotNull(message = "{gender.null}")
    @Column(length = 6, nullable = false)
    private String gender;

    @NotNull(message = "{origin.null}")
    @Column(length = 20, nullable = false)
    private String origin;

    @OneToMany(mappedBy = "contributor", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ContributorProduction> contributorProductions = new HashSet<>();

    public void addContribution(ContributorProduction contribution){
        getContributorProductions().add(contribution);
    }
}
