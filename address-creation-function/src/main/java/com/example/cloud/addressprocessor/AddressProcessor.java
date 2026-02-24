package com.example.cloud.addressprocessor;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;

@Component
public class AddressProcessor {

    private final Logger log = LoggerFactory.getLogger(AddressProcessor.class);

    @Bean
    public Function<Message, String> processAddress() {
        return (value) -> {
            log.info("Consumed value is {}", value.getMessageProperties().toString());
            String body = new String(value.getBody());

            // CloudEvent event = CloudEventBuilder.from(value).build();
            // var dataType = event.getType();
            // var data = event.getData().toString();
            // log.info("Consumed body is {} of type {}", data, dataType);
            return body;
        };
    }
}
