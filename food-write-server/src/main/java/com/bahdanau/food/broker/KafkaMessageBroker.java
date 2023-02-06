package com.bahdanau.food.broker;

import com.bahdanau.food.dto.broker.ActionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageBroker {
    private final StreamBridge streamBridge;
    private final String kafkaTopic = "food-write-operation"; //todo this is .yml value

    public void sendMessage(ActionMessage message) {
        streamBridge.send(kafkaTopic, message);
    }
}
