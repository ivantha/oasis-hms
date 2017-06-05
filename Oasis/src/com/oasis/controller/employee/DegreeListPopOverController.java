package com.oasis.controller.employee;

import com.oasis.controller.PopOverController;
import com.oasis.model.Degree;
import com.oasis.services.DegreeServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DegreeListPopOverController implements PopOverController<Degree> {
    @FXML
    private ListView<Degree> degreeListView;

    private ObservableList<Degree> addedDegreeObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        ObservableList<Degree> degreeObservableList = FXCollections.observableList(DegreeServices.getDegreeArrayList());
        degreeListView.setItems(degreeObservableList);
    }

    @Override
    public void refreshView(Degree degree) {
    }

    public ObservableList<Degree> getAddedDegreeObservableList() {
        return addedDegreeObservableList;
    }

    public void setAddedDegreeObservableList(ObservableList<Degree> addedDegreeObservableList) {
        this.addedDegreeObservableList = addedDegreeObservableList;
    }

    public void degreeListViewOnMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Degree selectedDegree = degreeListView.getSelectionModel().getSelectedItem();

            if (!addedDegreeObservableList.contains(selectedDegree)) {
                addedDegreeObservableList.add(selectedDegree);
            }
        }
    }
}
