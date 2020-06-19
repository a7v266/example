package com.example.model.api;

import com.example.model.Producer;

public class ProducerResponseList extends ResponseList<ProducerResponse, Producer> {

    @Override
    ProducerResponse createItem(Producer producer) {
        return new ProducerResponse(producer);
    }
}
