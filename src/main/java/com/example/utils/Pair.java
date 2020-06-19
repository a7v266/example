package com.example.utils;

import java.util.function.Consumer;

public class Pair<FIRST, SECOND> {

    public static final Pair<?, ?> EMPTY = new Pair<>(null, null);

    private final FIRST first;
    private final SECOND second;

    private Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public static <A, B> Pair<A, B> of(A a) {
        return new Pair<>(a, null);
    }

    public FIRST getFirst() {
        return first;
    }

    public SECOND getSecond() {
        return second;
    }

    public Pair<FIRST, SECOND> handleFirst(Consumer<FIRST> consumer) {
        consumer.accept(first);
        return this;
    }

    public Pair<FIRST, SECOND> handleSecond(Consumer<SECOND> consumer) {
        consumer.accept(second);
        return this;
    }

    public Pair<SECOND, FIRST> swap() {
        return new Pair<>(second, first);
    }

    @Override
    public String toString() {
        return StringJoiner
                .on(Format.COMMA_SPACE, Format.OPENING_SQUARE_BRACKET, Format.CLOSING_SQUARE_BRACKET)
                .join(first, second);
    }
}