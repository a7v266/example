package com.example.model.search.core;

import com.example.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class Sort implements Serializable {

    @JsonProperty
    private String property;

    @JsonProperty
    private Boolean desc;

    private Sort(String property) {
        this.property = property;
    }

    private Sort(String property, Boolean desc) {
        this.property = property;
        this.desc = desc;
    }

    public static Sort create(@NotEmpty String property, Boolean desc) {
        return new Sort(property, desc);
    }

    public static Sort asc(@NotEmpty String property) {
        return new Sort(property);
    }

    public static Sort desc(@NotEmpty String property) {
        return new Sort(property, Boolean.TRUE);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isDesc() {
        return desc != null ? desc : false;
    }

    @Override
    public String toString() {
        return JsonUtils.serialize(this);
    }
}
