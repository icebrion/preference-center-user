package com.vw.producer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProducerSender {

    public void send(Object event) {}
}
