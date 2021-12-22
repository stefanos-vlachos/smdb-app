package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity()
@Table(name="SITCOMS")
@SequenceGenerator(name="sitcomIdGenerator", sequenceName = "SITCOMS_SEQ", initialValue = 1, allocationSize = 1)
public class Sitcom extends Production{

    @NotNull(message = "{seasons.null}")
    @Column(length = 2, nullable = false)
    private Integer seasons;

    @NotNull(message = "{episodes.null}")
    @Column(length = 3, nullable = false)
    private Integer episodes;

}
