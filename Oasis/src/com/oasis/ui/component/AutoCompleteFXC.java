package com.oasis.ui.component;

import com.oasis.adapter.SearchAdapter;
import com.oasis.controller.PopOverController;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;

public class AutoCompleteFXC<T> extends Pane {
    private TextField textField;
    private ListView<T> listView;
    private VBox vBox;
    private PopOver popOver;

    private PopOverController popOverController;
    private ListProperty<T> listItemListProperty;

    private ObjectProperty objectProperty = new SimpleObjectProperty();

    public AutoCompleteFXC(SearchAdapter<T> searchAdapter, UIName popOverName) {
        this(250, 25, 150, searchAdapter, popOverName);
    }

    public AutoCompleteFXC(double parentWidth, double parentHeight, double listHeight, SearchAdapter<T> searchAdapter, UIName popOverName) {
        textField = new TextField();
        textField.setPrefWidth(parentWidth);

        listView = new ListView<>();
        listView.setPrefWidth(parentWidth);
        listView.setMaxHeight(listHeight);

        vBox = new VBox();
        vBox.getChildren().add(textField);

        popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
        UI ui = UIFactory.getUI(popOverName);
        popOver.setContentNode(ui.getParent());

        popOverController = (PopOverController) ui.getController();

        listItemListProperty = new SimpleListProperty<>();

        listView.itemsProperty().bindBidirectional(listItemListProperty);

        this.getChildren().add(vBox);

        this.setMaxHeight(parentHeight);
        this.getStylesheets().add(this.getClass().getResource("/styles/default/auto_complete_fxc.css").toExternalForm());

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                vBox.getChildren().add(listView);
                listView.setVisible(true);
            } else {
                vBox.getChildren().remove(listView);
                listView.setVisible(false);
            }
        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if ("".equals(newValue)) {
                listItemListProperty.clear();
                listView.setVisible(false);
            } else {
                Platform.runLater(() -> {
                    ArrayList<T> tArrayList = searchAdapter.getLike(newValue);
                    listItemListProperty.set(FXCollections.observableArrayList(tArrayList));
                    listView.setPrefHeight(tArrayList.size() * 25);
                    listView.setVisible(true);
                });
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                textField.setText(newValue.toString());
                objectProperty.setValue(newValue);
                popOver.hide();
            }
        });

        listView.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                popOver.hide();
            }
        });

        listView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> param) {
                ListCell<T> cell = new ListCell<T>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.toString());
                        }
                    }
                };

                cell.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue && !cell.isEmpty()) {
                        popOverController.refreshView(cell.itemProperty().get());
                        popOver.show(cell, -5);
                    } else if (newValue) {
                        popOver.hide();
                    }
                });

                return cell;
            }
        });
    }

    public TextField getTextField() {
        return textField;
    }

    public ListView<T> getListView() {
        return listView;
    }

    public VBox getvBox() {
        return vBox;
    }

    public PopOver getPopOver() {
        return popOver;
    }

    public PopOverController getPopOverController() {
        return popOverController;
    }

    public ObservableList getListItemListProperty() {
        return listItemListProperty.get();
    }

    public ListProperty<T> listItemListPropertyProperty() {
        return listItemListProperty;
    }

    public Object getObjectProperty() {
        return objectProperty.get();
    }

    public ObjectProperty objectPropertyProperty() {
        return objectProperty;
    }

    public void bindList(ObjectProperty objectProperty) {
        this.objectProperty = objectProperty;
    }

    public void unBindList() {
        this.objectProperty = new SimpleObjectProperty();
    }

    public void updateText() {
        if (null != objectPropertyProperty().getValue()) {
            textField.setText(objectProperty.getValue().toString());
        } else {
            textField.setText(null);
        }
    }
}
