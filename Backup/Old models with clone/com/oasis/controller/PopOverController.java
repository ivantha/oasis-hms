package com.oasis.controller;

import java.net.URL;
import java.util.ResourceBundle;

public interface PopOverController<T> extends Controller{
    @Override
    void initialize(URL location, ResourceBundle resources);

    @Override
    void refreshView();

    void refreshView(T t);
}
