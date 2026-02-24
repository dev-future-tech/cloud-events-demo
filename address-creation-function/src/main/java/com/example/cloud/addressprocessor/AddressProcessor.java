package com.example.cloud.addressprocessor;

import io.cloudevents.CloudEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.cloudevents.dsl.CloudEvents;
import org.springframework.integration.cloudevents.transformer.ToCloudEventTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

@Component
public class AddressProcessor {

    private final Logger log = LoggerFactory.getLogger(AddressProcessor.class);

    @Bean
    public Function<Message<CloudEvent>, AddressDto> processAddress() {
        return (value) -> {
            CloudEvent event = value.getPayload();
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

    @Bean
    public ToCloudEventTransformer cloudEventTransformer() {
        return new ToCloudEventTransformer("trace*", "correlation-id");
    }

    @Bean
    public IntegrationFlow cloudEventTransformFlow(ToCloudEventTransformer toCloudEventTransformer) {
        return IntegrationFlow
                .from("process-address-in")
                .transform(toCloudEventTransformer)
                .channel("process-address-out")
                .get();
    }
}
