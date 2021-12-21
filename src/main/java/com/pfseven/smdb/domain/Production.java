package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    private float rating;
    private String language;
    private List<Genre> genres;
    private String resume;
    private ProductionCrew productionCrew;
    private Cast cast;
}
