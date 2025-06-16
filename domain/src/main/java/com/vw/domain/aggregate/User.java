package com.vw.domain.aggregate;

import com.vw.domain.entity.Consent;
import com.vw.domain.events.UpdatedUserDomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class User extends Aggregate {
    private String id;
    private String email;
    private List<Consent> consents;

    public static User createUser(String email, List<Consent> consents) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);

        return user.addConsents(consents);
    }

    public User addConsents(List<Consent> consents) {
        if (this.consents == null) {
            this.consents = new ArrayList<>();
        }
        this.consents.addAll(consents);

        this.registerEvent(new UpdatedUserDomainEvent(this));
        return this;
    }
}
