package com.example.service.persistence;

import com.example.model.Input;

import java.util.List;
import java.util.Set;

public interface InputPersistence extends BasePersistence<Input, Long> {

    List<Object[]> getStatistics(Set<Long> producerIds);

}
