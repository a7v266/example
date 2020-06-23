package com.example.model.api;

import com.example.model.Input;
import com.example.model.InputCreator;
import com.example.model.InputStatus;
import com.example.model.InputUpdater;
import com.example.model.SubscriptionType;
import com.example.utils.Format;
import com.example.utils.Messages;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InputRequest implements InputCreator, InputUpdater {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(message = Messages.ERROR_PRODUCER_ID_EMPTY)
    private Long producerId;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_OPER_UID_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_OPER_UID_MAX_LENGTH)
    private String operUID;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_HOST_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_HOST_MAX_LENGTH)
    private String host;

    @JsonProperty
    @NotNull(message = Messages.ERROR_STATUS_EMPTY)
    private InputStatus status;

    @JsonProperty
    @NotNull(message = Messages.ERROR_TYPE_EMPTY)
    private SubscriptionType type;

    @JsonProperty
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_ERROR_MAX_LENGTH)
    private String error;

    @JsonProperty
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_STATUS_MESSAGE_MAX_LENGTH)
    private String statusMessage;

    @Override
    public Input createInput() {
        return updateInput(new Input(id));
    }

    @Override
    public Input updateInput(Input input) {
        input.setOperUID(operUID);
        input.setHost(host);
        input.setStatus(status);
        input.setType(type);
        return input;
    }

    public Long getProducerId() {
        return producerId;
    }
}
