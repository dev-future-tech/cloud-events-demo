package com.example.cloud.addressprocessor;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import io.cloudevents.core.format.EventFormat;
import io.cloudevents.core.provider.EventFormatProvider;
import io.cloudevents.jackson.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import tools.jackson.databind.ObjectMapper;

@Component
public class AddressProcessor {

    private final Logger log = LoggerFactory.getLogger(AddressProcessor.class);
    private static final EventFormat CE_JSON =
            EventFormatProvider.getInstance().resolveFormat(JsonFormat.CONTENT_TYPE);

    @Bean
    public Function<Message<byte[]>, AddressDto> processAddress() {
        return (value) -> {
            byte[] payload = value.getPayload();
            String raw = new String(payload, StandardCharsets.UTF_8);
            log.info("Raw payload: {}", raw);

            if (CE_JSON == null) {
                throw new IllegalStateException("CloudEvents JSON format not available. " +
                        "Add dependency io.cloudevents:cloudevents-json-jackson.");
            }
            CloudEvent event = CE_JSON.deserialize(payload);
            log.info("CloudEvent id={} type={} source={} time={}",
                    event.getId(), event.getType(), event.getSource(), event.getTime());

            if (event.getData() == null) {
                return null;
            }
            String dataJson = new String(event.getData().toBytes(), StandardCharsets.UTF_8);
            AddressDto addressDto = new ObjectMapper().readValue(dataJson, AddressDto.class);
            log.info("CloudEvent data: {}", dataJson);
            return addressDto;
        };
    }
}
