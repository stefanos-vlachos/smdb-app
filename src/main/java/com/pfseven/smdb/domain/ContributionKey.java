package com.pfseven.smdb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ContributionKey implements Serializable {

    @Column(name = "CONTRIBUTOR_ID")
    private Long contributorId;
    @Column(name = "PRODUCTION_ID")
    private Long productionId;
}