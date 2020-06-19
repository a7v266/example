package com.example.model.exception;

import com.example.model.Producer;
import com.example.utils.Messages;

public class ProducerNotFoundException extends NotFoundException {

    public ProducerNotFoundException(Long id) {
        super(Messages.ERROR_PRODUCER_NOT_FOUND, Producer.ID, id);
    }
}
