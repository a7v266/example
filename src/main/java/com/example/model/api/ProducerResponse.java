package com.example.model.api;

import com.example.model.InputStatistics;
import com.example.model.Producer;
import com.example.model.json.InstantSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Instant;

public class ProducerResponse implements InputStatistics {

    @JsonProperty
    private final Long id;

    @JsonProperty
    @JsonSerialize(using = InstantSerializer.class)
    private final Instant creationDate;

    @JsonProperty
    @JsonSerialize(using = InstantSerializer.class)
    private final Instant modificationDate;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String code;

    @JsonProperty
    private final String spName;

    @JsonProperty
    private final String description;

    @JsonProperty
    private final Boolean external;

    @JsonProperty
    private final Boolean active;

    @JsonProperty
    private Long inputCount;

    @JsonProperty
    private Long errorCount;

    @JsonProperty
    @JsonSerialize(using = InstantSerializer.class)
    private Instant lastErrorDate;

    public ProducerResponse(Producer producer) {
        id = producer.getId();
        creationDate = producer.getCreationDate();
        modificationDate = producer.getModificationDate();
        name = producer.getName();
        code = producer.getCode();
        spName = producer.getSpName();
        description = producer.getDescription();
        external = producer.getExternal();
        active = producer.getActive();
    }

    @Override
    public Long getProducerId() {
        return id;
    }

    @Override
    public void setInputCount(Long inputCount) {
        this.inputCount = inputCount;
    }

    @Override
    public void setErrorCount(Long errorCount) {
        this.errorCount = errorCount;
    }

    @Override
    public void setLastErrorDate(Instant lastErrorDate) {
        this.lastErrorDate = lastErrorDate;
    }
}
