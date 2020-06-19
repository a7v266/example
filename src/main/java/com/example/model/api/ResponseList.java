package com.example.model.api;

import com.example.model.search.core.Search;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ResponseList<I, E> {

    @JsonProperty
    private List<I> items;

    @JsonProperty
    private Integer pageNumber;

    @JsonProperty
    private Integer pageSize;

    @JsonProperty
    private Long pageCount;

    abstract I createItem(E entity);

    public List<I> getItems() {
        return items;
    }

    public void setItems(List<E> entities) {
        items = entities.stream().map(this::createItem).collect(Collectors.toList());
    }

    public void setSearch(Search search) {
        pageNumber = search.getPageNumber();
        pageSize = search.getPageSize();
    }

    public void setTotalCount(Long totalCount) {
        this.pageCount = totalCount != null && pageSize != null ? calculatePageCount(totalCount, pageSize) : 0;
    }

    private long calculatePageCount(long totalCount, int pageSize) {
        if (pageSize == 0) {
            return 0;
        }
        return totalCount / pageSize + ((totalCount % pageSize) > 0 ? 1 : 0);
    }
}
