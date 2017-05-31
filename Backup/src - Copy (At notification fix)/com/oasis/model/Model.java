package com.oasis.model;

public interface Model<T> {
    T clone();

    boolean isEmpty();
}
