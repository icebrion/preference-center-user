package com.vw.common;

import com.vw.domain.events.UpdatedUserDomainEvent;
import com.vw.notifications.user.UpdatedUserDomainHandler;
import com.vw.user.create.CreateUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainEventDispatcher {

    private final UpdatedUserDomainHandler updatedUserDomainHandler;

    public void dispatch(List<Object> events) {
        for (Object event : events) {
            if (event instanceof UpdatedUserDomainHandler) {
                updatedUserDomainHandler.handle((UpdatedUserDomainEvent) event);
            }
        }
    }
}
