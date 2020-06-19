package com.example.service;

import com.example.model.Producer;
import com.example.model.api.ProducerRequest;
import com.example.model.search.ProducerSearch;

import java.util.List;

public interface ProducerService {

    List<Producer> getProducerList(ProducerSearch search);

    Long getProducerCount(ProducerSearch search);

    Producer saveProducer(ProducerRequest request);
}
