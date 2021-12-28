package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@Entity()
@Table(name = "CONTRIBUTORS_PRODUCTIONS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTRIBUTORS_PRODUCTIONS_SEQ", initialValue = 1, allocationSize = 1)
public class ContributorProduction extends BaseModel {

    /*@EmbeddedId
    private ContributionKey contributionKey;*/

    @ManyToOne(cascade = CascadeType.ALL)
    //@MapsId("contributorId")
    @JoinColumn(name = "CONTRIBUTOR_ID")
    private Contributor contributor;

    @ManyToOne(cascade = CascadeType.ALL)
    //@MapsId("productionId")
    @JoinColumn(name = "PRODUCTION_ID")
    private Production production;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Role role;
}
