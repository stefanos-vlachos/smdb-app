package com.pfseven.smdb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity()
@Table(name = "CONTRIBUTORS_PRODUCTIONS")
public class ContributorProduction implements Serializable {

    @EmbeddedId
    private ContributionKey contributionKey;

    @ManyToOne
    @MapsId("contributorId")
    @JoinColumn(name = "CONTRIBUTOR_ID")
    private Contributor contributor;

    @ManyToOne
    @MapsId("productionId")
    @JoinColumn(name = "PRODUCTION_ID")
    private Production production;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Role role;
}
