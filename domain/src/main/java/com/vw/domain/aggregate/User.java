package com.vw.domain.aggregate;

import com.vw.domain.entity.Consent;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
public class User {
    private String id;
    private String email;
    private List<Consent> consents;

    public static User createUser(String email, List<Consent> consents) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setConsents(consents);

        return user;
    }

    public User addConsents(List<Consent> consents) {
        consents.addAll(consents);

        return this;
    }
}
