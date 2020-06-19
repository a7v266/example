package com.example.model.search;

import com.example.model.Producer;
import com.example.model.search.core.Filter;
import com.example.utils.CollectionUtils;
import com.example.utils.StringUtils;

import java.util.Map;
import java.util.Set;

public class ProducerSearch extends RequestSearch {

    private static final String NAME_LIKE = "name_like";
    private static final String CODE_LIKE = "code_like";
    private static final String SP_NAME_LIKE = "spName_like";

    private static final Set<String> VALID_SORTS = CollectionUtils.createHashSet(
            Producer.ID,
            Producer.CREATION_DATE,
            Producer.MODIFICATION_DATE,
            Producer.NAME,
            Producer.CODE,
            Producer.SP_NAME,
            Producer.DESCRIPTION);

    public ProducerSearch(Map<String, String> parameters) {
        super(parameters);
        setNameLike(parameters.get(NAME_LIKE));
        setCodeLike(parameters.get(CODE_LIKE));
        setSpNameLike(parameters.get(SP_NAME_LIKE));
    }

    @Override
    protected Set<String> getValidSorts() {
        return VALID_SORTS;
    }

    private void setNameLike(String name) {
        if (StringUtils.isNotEmpty(name)) {
            addFilter(Filter.likeAnywhere(Producer.NAME, name));
        }
    }

    private void setCodeLike(String code) {
        if (StringUtils.isNotEmpty(code)) {
            addFilter(Filter.likeAnywhere(Producer.CODE, code));
        }
    }

    private void setSpNameLike(String spName) {
        if (StringUtils.isNotEmpty(spName)) {
            addFilter(Filter.likeAnywhere(Producer.SP_NAME, spName));
        }
    }
}
