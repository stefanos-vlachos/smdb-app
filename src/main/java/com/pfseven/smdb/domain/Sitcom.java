package com.pfseven.smdb.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pfseven.smdb.serializer.SitcomSerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "SITCOMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "SITCOMS_SEQ", initialValue = 1, allocationSize = 1)
@JsonSerialize(using = SitcomSerializer.class)
public class Sitcom extends Production {

    @NotNull(message = "{seasons.null}")
    @Column(nullable = false)
    @Min(value = 1)
    private Integer seasons;

    @NotNull(message = "{episodes.null}")
    @Column(nullable = false)
    @Min(value = 1)
    private Integer episodes;

}
