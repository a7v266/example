package com.example.model.search.core;

import java.io.Serializable;
import java.util.Iterator;

public interface Scroll<T> extends Iterable<T>, Iterator<T>, AutoCloseable, Serializable {
}
