package com.oasis.controller._A;

import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.model.Test;
import com.oasis.services.TestServices;
import com.oasis.ui.UIName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewTestController implements Controller {
    @FXML
    public Button okButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField basePriceTextField;
    private Test tempTest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void refreshView() {
        tempTest = new Test();
        nameTextField.textProperty().bindBidirectional(tempTest.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(tempTest.descriptionProperty());
        basePriceTextField.textProperty().bindBidirectional(tempTest.basePriceProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.valueOf(object);
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.valueOf(string);
                } catch (Exception e) {
                    return 0.0;
                }
            }
        });
    }

    public void okButtonOnAction(ActionEvent actionEvent) {
        ArrayList<Test> testArrayList = new ArrayList<>();
        testArrayList.add(tempTest);

        TestServices.newTest(testArrayList);

        UIFactory.launchUI(UIName.TEST_MANAGEMENT, true);
    }
}
