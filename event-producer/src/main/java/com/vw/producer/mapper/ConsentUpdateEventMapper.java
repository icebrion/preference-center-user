package com.vw.producer.mapper;

import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import com.vw.preference.center.users.consent.v1.ConsentUpdateEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ConsentUpdateEventMapper {

    @Mapping(target = "consentId", source = "consent.id")
    @Mapping(target = "enabled", source = "consent.enabled")
    ConsentUpdateEvent asConsentUpdateEvent(String userId, Consent consent);
}
