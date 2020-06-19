package com.example.service;

import com.example.model.Input;
import com.example.model.InputStatistics;
import com.example.model.Producer;
import com.example.model.api.InputRequest;
import com.example.model.exception.ProducerNotFoundException;
import com.example.service.persistence.InputPersistence;
import com.example.service.persistence.ProducerPersistence;
import com.example.utils.DateUtils;
import com.example.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class InputServiceImpl implements InputService {

    private InputPersistence inputPersistence;
    private ProducerPersistence producerPersistence;

    @Autowired
    public void setInputPersistence(InputPersistence inputPersistence) {
        this.inputPersistence = inputPersistence;
    }

    @Autowired
    public void setProducerPersistence(ProducerPersistence producerPersistence) {
        this.producerPersistence = producerPersistence;
    }

    @Override
    public void collectInputStatistics(List<? extends InputStatistics> statistics) {
        Map<Long, InputStatistics> statisticsMap = statistics.stream().collect(Collectors.toMap(InputStatistics::getProducerId, Function.identity()));
        List<Object[]> queryResult = inputPersistence.getStatistics(statisticsMap.keySet());
        for (Object[] objects : queryResult) {
            InputStatistics inputStatistics = statisticsMap.get(NumberUtils.getLong(objects[0]));
            inputStatistics.setInputCount(NumberUtils.getLong(objects[1]));
            inputStatistics.setErrorCount(NumberUtils.getLong(objects[2]));
            inputStatistics.setLastErrorDate(DateUtils.getInstant(objects[3]));
        }
    }

    @Override
    public Input saveInput(InputRequest request) {
        Input input = request.createInput();
        Producer producer = producerPersistence.get(request.getProducerId());
        if (producer == null) {
            throw new ProducerNotFoundException(request.getProducerId());
        }
        input.setProducer(producer);
        return inputPersistence.merge(input);
    }
}
