package com.example.model.api;

import com.example.model.Producer;
import com.example.utils.Format;
import com.example.utils.Messages;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProducerRequest {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_NAME_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_NAME_MAX_LENGTH)
    private String name;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_CODE_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_CODE_MAX_LENGTH)
    private String code;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_SP_NAME_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_SP_NAME_MAX_LENGTH)
    private String spName;

    @JsonProperty
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_DESCRIPTION_MAX_LENGTH)
    private String description;

    @JsonProperty
    @NotNull(message = Messages.ERROR_EXTERNAL_EMPTY)
    private Boolean external;

    @JsonProperty
    @NotNull(message = Messages.ERROR_ACTIVE_EMPTY)
    private Boolean active;

    public Producer createProducer() {
        return updateProducer(new Producer(id));
    }

    public Producer updateProducer(Producer producer) {
        producer.setName(name);
        producer.setCode(code);
        producer.setSpName(spName);
        producer.setDescription(description);
        producer.setExternal(external);
        producer.setActive(active);
        return producer;
    }
}
