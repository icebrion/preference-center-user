package com.vw.domain.events;

import com.vw.domain.entity.Consent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatedUserDomainEvent {
    private String id;
    private Consent consent;
}
