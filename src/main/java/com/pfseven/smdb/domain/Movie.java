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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

@Entity()
@Table(name="MOVIES")
@SequenceGenerator(name="idGenerator", sequenceName = "MOVIES_SEQ",initialValue = 1, allocationSize = 1)
public class Movie extends Production{

    @NotNull(message = "{duration.null}")
    @Column(nullable = false)
    private Integer duration;

}
