package com.oasis.listener;

import com.oasis.common.Temp;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UIName;
import com.oasis.ui.utils.UIUtils;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.AnchorPane;

public class DynamicPaneDragDroppedEventHandler implements EventHandler<DragEvent> {
    private ObjectProperty<Button> lastPressedMainSideButton;

    public DynamicPaneDragDroppedEventHandler(ObjectProperty<Button> lastPressedMainSideButton) {
        this.lastPressedMainSideButton = lastPressedMainSideButton;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        Object eventSource = event.getSource();
        if (event.getDragboard().hasContent(Temp.BUTTON_NAME_DATA_FORMAT) && eventSource instanceof AnchorPane) {
            success = true;

            Button sideButton = new Button();
            sideButton.setPrefWidth(200);
            sideButton.setPrefHeight(40);
            sideButton.getStyleClass().add("tabButton");

            UIName uiName = UIName.valueOf(String.valueOf(dragboard.getContent(Temp.BUTTON_NAME_DATA_FORMAT)));
            sideButton.setText("                " + UIUtils.getUIName(uiName));
            sideButton.getStyleClass().add(uiName.name());

            sideButton.setOnAction(event1 -> {
                UIFactory.launchUI(uiName, true);
                lastPressedMainSideButton.setValue(sideButton);
            });

            AnchorPane tabButtonAnchorPane = (AnchorPane) eventSource;
            tabButtonAnchorPane.getChildren().clear();
            tabButtonAnchorPane.getChildren().add(sideButton);

            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.put(Temp.PANE_ID_DATA_FORMAT, tabButtonAnchorPane.getId());
            clipboardContent.put(Temp.BUTTON_NAME_DATA_FORMAT, uiName.name());
            dragboard.setContent(clipboardContent);
        }

        event.setDropCompleted(success);
        event.consume();
    }
}
