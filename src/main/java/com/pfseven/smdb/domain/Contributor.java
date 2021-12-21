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

@Entity
@Table(name="CONTRIBUTORS")
@SequenceGenerator(name="idGenerator", sequenceName = "CONTRIBUTORS_SEQ", initialValue = 1, allocationSize = 1)
public class Contributor extends BaseModel{

    @NotNull(message = "{fullName.null}")
    @Column(length=50, nullable = false)
    private String fullName;

    @NotNull(message = "{gender.null}")
    @Column(length=1, nullable = false)
    private char gender;

    @NotNull(message = "{origin.null}")
    @Column(length=20, nullable = false)
    private String origin;
}
