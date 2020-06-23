package com.example.model;

import com.example.utils.Format;
import com.example.utils.JsonUtils;
import com.example.utils.Messages;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NamedQuery(
        name = "getInputStatistics",
        query = "SELECT i.producer.id, " +
                "count(i), " +
                "sum(case when i.status = :errorStatus then 1 else 0 end), " +
                "max(i.creationDate) " +
                "FROM Input i " +
                "WHERE i.producer.id in :producerIds " +
                "GROUP BY i.producer"

)
@Entity
@Table(name = "input")
public class Input extends BaseEntity {

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "producer_id", updatable = false)
    @NotNull(message = Messages.ERROR_PRODUCER_ID_EMPTY)
    private Producer producer;

    @JsonProperty
    @Column(name = "oper_uid")
    @NotEmpty(message = Messages.ERROR_OPER_UID_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_OPER_UID_MAX_LENGTH)
    private String operUID;

    @JsonProperty
    @Column(name = "host")
    @NotEmpty(message = Messages.ERROR_HOST_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_HOST_MAX_LENGTH)
    private String host;

    @JsonProperty
    @Column(name = "status")
    @NotNull(message = Messages.ERROR_STATUS_EMPTY)
    private InputStatus status;

    @JsonProperty
    @Column(name = "type")
    @NotNull(message = Messages.ERROR_TYPE_EMPTY)
    private SubscriptionType type;

    @JsonProperty
    @Column(name = "error")
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_STATUS_MESSAGE_MAX_LENGTH)
    private String error;

    @JsonProperty
    @Column(name = "status_message")
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_STATUS_MESSAGE_MAX_LENGTH)
    private String statusMessage;

    public Input() {
    }

    public Input(Long id) {
        super(id);
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getOperUID() {
        return operUID;
    }

    public void setOperUID(String operUID) {
        this.operUID = operUID;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public InputStatus getStatus() {
        return status;
    }

    public void setStatus(InputStatus status) {
        this.status = status;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return JsonUtils.serialize(this);
    }
}
