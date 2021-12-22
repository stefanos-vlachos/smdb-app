package com.pfseven.smdb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

@Entity()
@Table(name="CONTRIBUTIONS")
@SequenceGenerator(name="idGenerator", sequenceName = "CONTRIBUTIONS_SEQ",initialValue = 1, allocationSize = 1)
public class Contribution extends BaseModel{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference("contributions")
    private Contributor contributor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Role role;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference("prodContributions")
    private Production production;

}
