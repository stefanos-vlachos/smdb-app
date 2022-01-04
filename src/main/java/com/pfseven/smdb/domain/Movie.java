package com.pfseven.smdb.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pfseven.smdb.util.MovieSerializer;
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
@Table(name = "MOVIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "MOVIES_SEQ", initialValue = 1, allocationSize = 1)
@JsonSerialize(using = MovieSerializer.class)
public class Movie extends Production {

    @NotNull(message = "{duration.null}")
    @Column(nullable = false)
    @Min(value = 1)
    private Integer duration;

}
