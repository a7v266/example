package com.example.model.search.core;

import com.example.model.exception.BadRequestException;
import com.example.utils.CollectionUtils;
import com.example.utils.Format;
import com.example.utils.MapUtils;
import com.example.utils.Messages;
import com.example.utils.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Search implements Serializable {

    private final Map<String, String> aliases = MapUtils.createHashMap();
    private final List<Sort> sorts = CollectionUtils.createArrayList();
    private final List<Filter> filters = CollectionUtils.createArrayList();
    private int offset = 0;
    private int limit = 10;
    private int pageNumber = 1;
    private int pageSize = 10;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPagination(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber != null ? pageNumber : 1;
        if (this.pageNumber < 1) {
            throw new BadRequestException(Messages.ERROR_PAGE_NUMBER_INVALID);
        }
        this.pageSize = pageSize != null ? pageSize : limit;
        if (this.pageSize <= 0) {
            throw new BadRequestException(Messages.ERROR_PAGE_SIZE_INVALID);
        }
        this.limit = this.pageSize;
        this.offset = (this.pageNumber - 1) * this.pageSize;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit != null) {
            this.limit = limit;
        }
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        if (offset != null) {
            this.offset = offset;
        }
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void addFilter(Filter filter) {
        if (filter != null) {
            aliasingFilter(filter);
            filters.add(filter);
        }
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void addSort(String sort) {
        addSort(sort, Boolean.FALSE);
    }

    public void addSort(String sort, Boolean desc) {
        if (isValidSort(sort)) {
            addSort(Sort.create(sort, desc));
        }
    }

    protected Set<String> getValidSorts() {
        return Collections.emptySet();
    }

    private boolean isValidSort(String sort) {
        return StringUtils.isNotEmpty(sort) && getValidSorts().contains(sort);
    }

    private void addSort(Sort sort) {
        String property = sort.getProperty();
        if (property.contains(Field.DELIMITER)) {
            sort.setProperty(aliasingProperty(property));
        }
        sorts.add(sort);
    }

    private void aliasingFilter(Filter filter) {
        if (filter.getFilters() != null && filter.getFilters().size() > 0) {
            filter.getFilters().forEach(this::aliasingFilter);
        }
        String property = filter.getProperty();
        if (property != null && property.contains(Field.DELIMITER)) {
            filter.setProperty(aliasingProperty(property));
        }
    }

    private String aliasingProperty(String property) {
        int index = property.lastIndexOf(Field.DELIMITER);
        if (index == -1) {
            return property;
        }
        return createAlias(property.substring(0, index)).concat(property.substring(index));
    }

    private String createAlias(String path) {
        int index = path.lastIndexOf(Field.DELIMITER);
        if (index == -1) {
            aliases.put(path, path);
            return path;
        }
        String aliasName = path.replaceAll(Format.PATTERN_DOT, Format.UNDERSCORE);
        String aliasPath = createAlias(path.substring(0, index)).concat(path.substring(index));
        aliases.put(aliasName, aliasPath);
        return aliasName;
    }
}
