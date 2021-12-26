package com.pfseven.smdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContributionKey implements Serializable {

    @Column(name = "CONTRIBUTOR_ID")
    private Long contributorId;
    @Column(name = "PRODUCTION_ID")
    private Long productionId;
}
