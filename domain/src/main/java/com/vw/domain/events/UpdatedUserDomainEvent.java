package com.vw.domain.events;

import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdatedUserDomainEvent {
    private String id;
    private String email;
    private List<Consent> consents;

    public UpdatedUserDomainEvent(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.consents = user.getConsents();
    }
}
