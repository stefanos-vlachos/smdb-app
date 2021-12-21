package com.pfseven.smdb.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Sitcom extends Production{

    private Integer Seasons;
    private Integer Episodes;

}
