package com.example.cloud.addressmanagement;

import org.springframework.stereotype.Service;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class ConsoleService {
    private final StreamBridge streamBridge;

    public ConsoleService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void createAddress(AddressDto addressDto) {
        ObjectMapper objectMapper = new ObjectMapper();

        String output = objectMapper.writeValueAsString(addressDto);

        CloudEvent event = CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withSource(URI.create("/console"))
                .withType("com.example.cloudeventsdemo.AddressDto")
                .withTime(OffsetDateTime.now())
                .withData("application/json", output.getBytes(StandardCharsets.UTF_8))
                .build();

        streamBridge.send("create-address-in", event);
    }

}
