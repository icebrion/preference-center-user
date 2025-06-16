package com.vw.domain.aggregate;

import com.vw.domain.entity.Consent;
import com.vw.domain.enums.ConsentId;
import com.vw.domain.enums.UserConsentStatus;
import com.vw.domain.events.UpdatedUserDomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class User extends Aggregate {
    private String id;
    private String email;
    private Set<Consent> consents;
    private UserConsentStatus userConsentStatus;

    public static User createUser(String email, List<Consent> consents) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);

        return user.addConsents(consents);
    }

    public User addConsents(List<Consent> consents) {
        if (this.consents == null) {
            this.consents = new HashSet<>();
        } else {
            consents.forEach(consent -> {
                this.consents.remove(consent);
            });
        }
        this.consents.addAll(consents);

        this.calculateUserConsentStatus();

        consents.forEach(consent -> this.registerEvent(new UpdatedUserDomainEvent(this.id, consent)));
        return this;
    }

    private void calculateUserConsentStatus() {
        if (this.consents == null || this.consents.isEmpty()) {
            this.userConsentStatus = UserConsentStatus.NONE;
        } else if (this.consents.size() > 1) {
            this.userConsentStatus = UserConsentStatus.SMS_EMAIL;
        } else if (ConsentId.SMS_NOTIFICATIONS.equals(this.consents.iterator().next().getId())) {
            this.userConsentStatus = UserConsentStatus.SMS;
        } else {
            this.userConsentStatus = UserConsentStatus.EMAIL;
        }
    }
}
