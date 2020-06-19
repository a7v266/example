package com.example.model.search.core;

import com.example.utils.ArrayUtils;
import com.example.utils.CollectionUtils;
import com.example.utils.JsonUtils;
import com.example.utils.ObjectUtils;
import com.example.utils.Pair;
import com.example.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Filter implements Serializable {

    @JsonProperty
    private final Operator operator;

    @JsonProperty
    private String property;

    @JsonProperty
    private Object value;

    @JsonProperty
    private Filter filter;

    @JsonProperty
    private List<Filter> filters;

    private Filter(Operator operator, String property) {
        this.operator = operator;
        this.property = property;
    }

    private Filter(Operator operator, String property, Object value) {
        this.operator = operator;
        this.property = property;
        this.value = value;
    }

    private Filter(Operator operator, List<Filter> filters) {
        this.operator = operator;
        this.filters = filters;
    }

    private Filter(Operator operator, Filter filter) {
        this.operator = operator;
        this.filter = filter;
    }

    public static Filter eq(String property, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.EQUALS, property, value);
    }

    public static Filter ne(String property, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.NOT_EQUALS, property, value);
    }

    public static Filter gt(String property, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.GREATER, property, value);
    }

    public static Filter ge(String property, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.GREATER_EQUAL, property, value);
    }

    public static Filter lt(String property, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.LESS, property, value);
    }

    public static Filter le(String property, Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.LESS_EQUAL, property, value);
    }

    public static Filter isNull(String property) {
        return new Filter(Operator.IS_NULL, property);
    }

    public static Filter isNotNull(String property) {
        return new Filter(Operator.IS_NOT_NULL, property);
    }

    public static Filter in(String property, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return new Filter(Operator.IN, property, values);
    }

    public static Filter in(String property, Object... values) {
        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        return new Filter(Operator.IN, property, CollectionUtils.createArrayList(values));
    }

    public static Filter between(String property, Object lowBound, Object highBound) {
        if (ObjectUtils.isEmpty(lowBound) || ObjectUtils.isEmpty(highBound)) {
            return null;
        }
        return new Filter(Operator.BETWEEN, property, Pair.of(lowBound, highBound));
    }

    public static Filter likeAnywhere(String property, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.LIKE_ANYWHERE, property, value);
    }

    public static Filter likeStart(String property, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.LIKE_START, property, value);
    }

    public static Filter likeExact(String property, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return new Filter(Operator.LIKE_EXACT, property, value);
    }

    public static Filter or(List<Filter> filters) {
        if (CollectionUtils.isEmpty(filters)) {
            return null;
        }
        return new Filter(Operator.OR, filters);
    }

    public static Filter or(Filter... filters) {
        if (ArrayUtils.isEmpty(filters)) {
            return null;
        }
        return new Filter(Operator.OR, CollectionUtils.createArrayList(filters));
    }

    public static Filter and(List<Filter> filters) {
        if (CollectionUtils.isEmpty(filters)) {
            return null;
        }
        return new Filter(Operator.AND, filters);
    }

    public static Filter and(Filter... filters) {
        if (ArrayUtils.isEmpty(filters)) {
            return null;
        }
        return new Filter(Operator.AND, CollectionUtils.createArrayList(filters));
    }

    public static Filter not(Filter filter) {
        if (filter == null) {
            return null;
        }
        return new Filter(Operator.NOT, filter);
    }

    public Operator getOperator() {
        return operator;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public Collection<?> getValues() {
        return value instanceof Collection ? (Collection<?>) value : Collections.emptyList();
    }

    public Object getLowBound() {
        return value instanceof Pair<?, ?> ? ((Pair<?, ?>) value).getFirst() : null;
    }

    public Object getHighBound() {
        return value instanceof Pair<?, ?> ? ((Pair<?, ?>) value).getSecond() : null;
    }

    public Collection<Filter> getFilters() {
        return filters;
    }

    public Filter getFilter() {
        return filter;
    }

    @Override
    public String toString() {
        return JsonUtils.serialize(this);
    }
}
