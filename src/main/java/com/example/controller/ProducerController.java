package com.example.controller;

import com.example.model.api.ProducerResponseList;
import com.example.model.search.ProducerSearch;
import com.example.service.InputService;
import com.example.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProducerController {

    public static final String PATH_PRODUCER = "/producer";

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

    @GetMapping(PATH_PRODUCER)
    public ProducerResponseList getProducerList(@RequestParam(required = false) Map<String, String> parameters) {
        ProducerResponseList response = new ProducerResponseList();
        ProducerSearch search = new ProducerSearch(parameters);
        response.setSearch(search);
        response.setItems(producerService.getProducerList(search));
        response.setTotalCount(producerService.getProducerCount(search));
        inputService.collectInputStatistics(response.getItems());
        return response;
    }
}
