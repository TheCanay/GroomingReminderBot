package com.liubomur.bot.service.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommandProcessorFactory {

    private final Map<String, CommandProcessor> serviceMap;
    private static final Logger log = LoggerFactory.getLogger(CommandProcessorFactory.class);

    public CommandProcessorFactory(Map<String, CommandProcessor> serviceMap) {
        this.serviceMap = serviceMap;
        printMap();
    }

    void printMap() {
        log.info("Service beans list:");
        for (Map.Entry<String, CommandProcessor> entry : serviceMap.entrySet()) {
            log.info("{}", entry.getKey());
        }
        log.info("-------------------");
    }

    public CommandProcessor getCommandProcessor(String command) {
       CommandProcessor commandProcessor = serviceMap.get(command);
       if (commandProcessor == null) {
           throw new IllegalArgumentException("Command %s not found".formatted(command));
       }
       return commandProcessor;
    }

}
