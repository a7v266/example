package com.example.service.persistence;

import com.example.model.Producer;
import org.springframework.stereotype.Repository;

@Repository
public class ProducerPersistenceImpl extends BasePersistenceImpl<Producer, Long> implements ProducerPersistence {

    protected ProducerPersistenceImpl() {
        super(Producer.class);
    }
}
