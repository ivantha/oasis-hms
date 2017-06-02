package com.oasis.ui.component;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SearchFXC<T> {
    public SearchFXC(TextField textField, TableView<T> tableView, ObservableList<T> observableList,
                     String... getterArgs) {
        FilteredList<T> filteredList = new FilteredList<>(observableList, p -> true);

        textField.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(t -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            for (int i = 0; i < getterArgs.length; i++) {
                try {
                    Method method = t.getClass().getDeclaredMethod(getterArgs[i]);
                    Object returnObject = method.invoke(t);

                    if (null != returnObject && returnObject.toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            return false;
        }));

        SortedList<T> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedList);
    }
}
