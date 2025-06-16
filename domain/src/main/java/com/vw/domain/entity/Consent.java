package com.vw.domain.entity;

import com.vw.domain.enums.ConsentId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consent {
    @EqualsAndHashCode.Include
    private ConsentId id;
    private boolean enabled;
}
