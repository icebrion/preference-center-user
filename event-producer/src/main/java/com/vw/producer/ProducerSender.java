package com.vw.producer;

import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import com.vw.preference.center.users.consent.v1.ConsentUpdateEvent;
import com.vw.producer.mapper.ConsentUpdateEventMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProducerSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ConsentUpdateEventMapper  consentUpdateEventMapper;

    public void sendConsentUpdateEvent(String userId, Consent consent) {
        ConsentUpdateEvent event = this.consentUpdateEventMapper.asConsentUpdateEvent(userId, consent);
        log.info("Sending ConsentUpdateEvent: {}", event);
        kafkaTemplate.send(KafkaTopics.CONSENT_UPDATED, event);
    }
}
