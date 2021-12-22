package com.pfseven.smdb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="CONTRIBUTORS")
@SequenceGenerator(name="idGenerator", sequenceName = "CONTRIBUTORS_SEQ",initialValue = 1, allocationSize = 1)
public class Contributor extends BaseModel{

    @NotNull(message = "{fullName.null}")
    @Column(length = 30, nullable = false)
    private String fullName;

    @NotNull(message = "{gender.null}")
    @Column(length = 1, nullable = false)
    private char gender;

    @NotNull(message = "{origin.null}")
    @Column(length = 20, nullable = false)
    private String origin;

    @JsonManagedReference("contributions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="contributor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Contribution> contributions;

}
