package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Production extends BaseModel{
    private String title;
    private Integer releaseYear;
    private BigDecimal rating;
    private String language;
    private List<Genre> genres;
    private String resume;
    private List<Contribution> contributions;
}

