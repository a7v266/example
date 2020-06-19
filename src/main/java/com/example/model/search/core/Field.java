package com.example.model.search.core;

import com.example.utils.StringJoiner;

import java.io.Serializable;

public final class Field implements Serializable {

    public static final String DELIMITER = ".";

    public static String path(String... names) {
        return StringJoiner.on(DELIMITER).skipEmpty().join(names);
    }
}
