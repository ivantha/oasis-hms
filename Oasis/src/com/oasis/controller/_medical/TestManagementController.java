package com.oasis.controller._medical;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Test;
import com.oasis.services.TestServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.SearchFXC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TestManagementController implements Controller {
    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Test> testTableView;
    @FXML
    private TableColumn<Test, Integer> idTableColumn;
    @FXML
    private TableColumn<Test, String> nameTableColumn;
    @FXML
    private TableColumn<Test, String> descriptionTableColumn;
    @FXML
    private TableColumn<Test, Double> basePriceTableColumn;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField basePriceTextField;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;

    private HashMap<Integer, Test> tempTestHashMap = new HashMap<>();
    private HashMap<Integer, Test> editedTestHashMap = new HashMap<>();
    private HashMap<Integer, Test> deletedTestHashMap = new HashMap<>();

    private SearchFXC<Test> testSearchFXC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (testTableView.getSelectionModel().getSelectedItem() != null) {
                idTextField.setText(String.valueOf(newValue.getId()));

                if (oldValue != null) {
                    nameTextField.textProperty().unbindBidirectional(oldValue.nameProperty());
                    descriptionTextArea.textProperty().unbindBidirectional(oldValue.descriptionProperty());
                    basePriceTextField.textProperty().unbindBidirectional(oldValue.basePriceProperty());

                    if (!oldValue.equals(tempTestHashMap.get(oldValue.getId()))) {
                        editedTestHashMap.put(oldValue.getId(), oldValue);
                    }
                }
                nameTextField.textProperty().bindBidirectional(newValue.nameProperty());
                descriptionTextArea.textProperty().bindBidirectional(newValue.descriptionProperty());
                basePriceTextField.textProperty().bindBidirectional(newValue.basePriceProperty(), new NumberStringConverter());
            }
        });
    }

    @Override
    public void refreshView() {
        tempTestHashMap.clear();
        editedTestHashMap.clear();
        deletedTestHashMap.clear();

        ArrayList<Test> testArrayList = new ArrayList<>();
        for (Test test : TestServices.getTestArrayList(UIName.TEST_MANAGEMENT)) {
            testArrayList.add(test.clone());
            tempTestHashMap.put(test.getId(), test.clone());
        }

        searchTextField.setText(null);
        ObservableList<Test> testObservableList = FXCollections.observableList(testArrayList);
        testSearchFXC = new SearchFXC<>(searchTextField, testTableView, testObservableList,
                "getName");

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(param -> param.getValue().nameProperty());
        descriptionTableColumn.setCellValueFactory(param -> param.getValue().descriptionProperty());
        basePriceTableColumn.setCellValueFactory(param -> param.getValue().basePriceProperty().asObject());
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        Test selectedTest = testTableView.getSelectionModel().getSelectedItem();
        if (selectedTest != null) {
            testTableView.getItems().remove(selectedTest);
            deletedTestHashMap.put(selectedTest.getId(), selectedTest);
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        if (testTableView.getSelectionModel().getSelectedItem() != null) {
            Test test = testTableView.getSelectionModel().getSelectedItem();

            if (!test.equals(tempTestHashMap.get(test.getId()))) {
                editedTestHashMap.put(test.getId(), test);
            }
        }

        TestServices.updateTest(UIName.TEST_MANAGEMENT, new ArrayList<>(editedTestHashMap.values()));
        editedTestHashMap.clear();

        TestServices.deleteTest(UIName.TEST_MANAGEMENT, new ArrayList<>(deletedTestHashMap.values()));
        deletedTestHashMap.clear();

        refreshView();
    }

    public void newButtonOnAction(ActionEvent actionEvent) {
        UIFactory.launchUI(UIName.NEW_TEST, true);
    }
}
