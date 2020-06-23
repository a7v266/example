package com.example.service;

import com.example.model.Producer;
import com.example.model.ProducerCreator;
import com.example.model.search.ProducerSearch;
import com.example.service.persistence.ProducerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private ProducerPersistence producerPersistence;

    @Autowired
    public void setProducerPersistence(ProducerPersistence producerPersistence) {
        this.producerPersistence = producerPersistence;
    }

    @Override
    public List<Producer> getProducerList(ProducerSearch search) {
        return producerPersistence.list(search);
    }

    @Override
    public Long getProducerCount(ProducerSearch search) {
        return producerPersistence.count(search);
    }

    @Override
    public Producer saveProducer(ProducerCreator creator) {
        return producerPersistence.merge(creator.createProducer());
    }
}
