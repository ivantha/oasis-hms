package com.oasis.adapter;

import java.util.ArrayList;

public interface SearchAdapter<T> {
    ArrayList<T> getLike(String searchParam);
}
