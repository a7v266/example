package com.example.model;

import com.example.utils.Format;
import com.example.utils.JsonUtils;
import com.example.utils.Messages;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "producer")
public class Producer extends BaseEntity {

    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String SP_NAME = "spName";
    public static final String DESCRIPTION = "description";

    @JsonProperty
    @Column(name = "name")
    @NotEmpty(message = Messages.ERROR_NAME_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_NAME_MAX_LENGTH)
    private String name;

    @JsonProperty
    @Column(name = "code")
    @NotEmpty(message = Messages.ERROR_CODE_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_CODE_MAX_LENGTH)
    private String code;

    @JsonProperty
    @Column(name = "sp_name")
    @NotEmpty(message = Messages.ERROR_SP_NAME_EMPTY)
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_SP_NAME_MAX_LENGTH)
    private String spName;

    @JsonProperty
    @Column(name = "description")
    @Length(max = Format.LENGTH_DEFAULT, message = Messages.ERROR_DESCRIPTION_MAX_LENGTH)
    private String description;

    @JsonProperty
    @Column(name = "external")
    @NotNull(message = Messages.ERROR_EXTERNAL_EMPTY)
    private Boolean external;

    @JsonProperty
    @Column(name = "active")
    @NotNull(message = Messages.ERROR_ACTIVE_EMPTY)
    private Boolean active;

    public Producer() {
    }

    public Producer(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return JsonUtils.serialize(this);
    }
}
