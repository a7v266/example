package com.example.service;

import com.example.model.api.InputRequest;
import com.example.model.api.ProducerRequest;
import com.example.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final String PRODUCER_RESOURCE = "producer.json";
    private static final String INPUT_RESOURCE = "input.json";

    private ProducerService producerService;
    private InputService inputService;

    @Autowired
    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Autowired
    public void setInputService(InputService inputService) {
        this.inputService = inputService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() throws IOException {
        loadProducer();
        loadInput();
    }

    private void loadProducer() throws IOException {
        ProducerRequest[] requests = load(ProducerRequest[].class, PRODUCER_RESOURCE);
        for (ProducerRequest request : requests) {
            producerService.saveProducer(request);
        }
    }

    private void loadInput() throws IOException {
        InputRequest[] requests = load(InputRequest[].class, INPUT_RESOURCE);
        for (InputRequest request : requests) {
            inputService.saveInput(request);
        }
    }

    private <T> T load(Class<T> clazz, String resource) throws IOException {
        try (Reader reader = new InputStreamReader(new ClassPathResource(resource).getInputStream(), StandardCharsets.UTF_8)) {
            return JsonUtils.readValue(reader, clazz);
        }
    }
}
