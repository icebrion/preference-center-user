package com.vw.notifications.user;

import com.vw.domain.events.UpdatedUserDomainEvent;
import com.vw.domain.repository.UserRepository;
import com.vw.producer.ProducerSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdatedUserDomainHandler {

    private final ProducerSender producerSender;

    public void handle(UpdatedUserDomainEvent event) {
        producerSender.send(event);
    }
}

