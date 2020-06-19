package com.example.service;

import com.example.model.Input;
import com.example.model.InputStatistics;
import com.example.model.api.InputRequest;

import java.util.List;

public interface InputService {

    void collectInputStatistics(List<? extends InputStatistics> items);

    Input saveInput(InputRequest request);
}
