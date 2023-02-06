package com.bahdanau.food.broker;

import com.bahdanau.food.dto.broker.ActionMessage;
import com.bahdanau.food.service.command.BrokerActionCommand;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class KafkaConsumer {

    private final Map<String, BrokerActionCommand> commands = new HashMap<>();

    public void registerCommand(String type, BrokerActionCommand command) {
        commands.put(type, command);
    }

    @Bean
    public Consumer<String> consumer() {
        return message -> {
            Gson gson = new Gson();
            ActionMessage action = gson.fromJson(message, ActionMessage.class);
            BrokerActionCommand command = commands.get(action.getActionType().name());
            command.process(action.getFoodDto(), action.getUserEmail());
        };
    }
}
