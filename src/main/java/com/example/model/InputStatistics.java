package com.example.model;

import java.time.Instant;

public interface InputStatistics {

    Long getProducerId();

    void setInputCount(Long inputCount);

    void setErrorCount(Long errorCount);

    void setLastErrorDate(Instant lastErrorDate);
}
