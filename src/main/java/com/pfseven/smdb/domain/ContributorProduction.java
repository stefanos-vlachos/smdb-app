package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@Entity()
@Table(name = "CONTRIBUTORS_PRODUCTIONS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTRIBUTORS_PRODUCTIONS_SEQ", initialValue = 1, allocationSize = 1)
public class ContributorProduction extends BaseModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTRIBUTOR_ID")
    private Contributor contributor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCTION_ID")
    private Production production;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Role role;
}
