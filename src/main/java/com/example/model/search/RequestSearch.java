package com.example.model.search;

import com.example.model.search.core.Search;
import com.example.utils.NumberUtils;

import java.util.Map;

public class RequestSearch extends Search {

    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String SORT = "sort";

    private static final String SORT_DELIMITER = ",";
    private static final String DESC_INDICATOR = "-";
    private static final String ASC_INDICATOR = "+";

    public RequestSearch(Map<String, String> parameters) {
        setPagination(parameters);
        setSort(parameters);
    }

    private void setPagination(Map<String, String> parameters) {
        Integer pageNumber = NumberUtils.getInteger(parameters.get(PAGE_NUMBER));
        Integer pageSize = NumberUtils.getInteger(parameters.get(PAGE_SIZE));
        setPagination(pageNumber, pageSize);
    }

    private void setSort(Map<String, String> parameters) {
        String sort = parameters.get(SORT);
        if (sort == null) {
            return;
        }
        for (String item : sort.split(SORT_DELIMITER)) {
            if (item.startsWith(DESC_INDICATOR)) {
                addSort(item.substring(1), Boolean.TRUE);
            } else if (item.startsWith(ASC_INDICATOR)) {
                addSort(item.substring(1));
            } else {
                addSort(item);
            }
        }
    }
}
