package com.bahdanau.food.service.command;

import com.bahdanau.food.broker.KafkaConsumer;
import com.bahdanau.food.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

public interface BrokerActionCommand {
    void process(FoodDto foodDto, String email);

    @Autowired
    default void registerCommand(KafkaConsumer consumerConfig) {
        consumerConfig.registerCommand(getActionName(), this);
    }

    String getActionName();
}
