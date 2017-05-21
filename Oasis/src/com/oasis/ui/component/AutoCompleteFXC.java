package com.oasis.ui.component;

import com.oasis.controller.patient.PatientDetailsPopOverController;
import com.oasis.factory.UIFactory;
import com.oasis.model.Patient;
import com.oasis.services.PatientServices;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.Collection;

public class AutoCompleteFXC<T> extends Pane {
    private TextField textField;
    private ListView<T> listView;
    private VBox vBox;
    private PopOver popOver;

    private PatientDetailsPopOverController patientDetailsPopOverController;
    private ListProperty<T> listItemListProperty;

    public AutoCompleteFXC(double parentWidth, double parentHeight, double listHeight) {
        textField = new TextField();
        textField.setPrefWidth(parentWidth);

        listView = new ListView<>();
        listView.setPrefWidth(parentWidth);
        listView.setMaxHeight(listHeight);

        vBox = new VBox();
        vBox.getChildren().add(textField);

        popOver = new PopOver();
        popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
        UI ui = UIFactory.getUI(UIName.PATIENT_DETAILS_POPOVER);
        popOver.setContentNode(ui.getParent());

        patientDetailsPopOverController = (PatientDetailsPopOverController) ui.getController();

        listItemListProperty = new SimpleListProperty<>();

        listView.itemsProperty().bindBidirectional(listItemListProperty);

        this.getChildren().add(vBox);

        this.setMaxHeight(parentHeight);
        this.getStylesheets().add(this.getClass().getResource("/com/oasis/resources/styles/auto_complete_fxc.css").toExternalForm());

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    vBox.getChildren().add(listView);
                    listView.setVisible(true);
                } else {
                    vBox.getChildren().remove(listView);
                    listView.setVisible(false);
                }
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("".equals(newValue)) {
                    listItemListProperty.clear();
                    listView.setVisible(false);
                } else {
                    Platform.runLater(() -> {
                        ArrayList<Patient> x = PatientServices.getPatientLike(newValue);
                        listItemListProperty.set(FXCollections.observableArrayList((Collection<? extends T>) x));
                        listView.setPrefHeight(x.size() * 25);
                        listView.setVisible(true);
                    });
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
            @Override
            public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                if (null != newValue) {
                    textField.setText(newValue.toString());
                    popOver.hide();
                }
            }
        });

        listView.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    popOver.hide();
                }
            }
        });

        listView.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> param) {
                ListCell<T> cell = new ListCell<T>(){
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        }else{
                            setText(item.toString());
                        }
                    }
                };

                cell.hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue && ! cell.isEmpty()) {
                            patientDetailsPopOverController.refreshView();
                            popOver.show(cell, -5);
                        } else if (newValue){
                            popOver.hide();
                        }
                    }
                });

                return cell;
            }
        });
    }
}
