package com.oasis.listener;

import com.oasis.common.Temp;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;

public class DynamicPaneDragExitedEventHandler implements EventHandler<DragEvent>{
    @Override
    public void handle(DragEvent event) {
        Object eventSource = event.getSource();
        if(eventSource instanceof AnchorPane){
            AnchorPane tabButtonAnchorPane = (AnchorPane) eventSource;
            if (event.getGestureSource() != tabButtonAnchorPane &&  event.getDragboard().hasContent(Temp.BUTTON_NAME_DATA_FORMAT)) {
                tabButtonAnchorPane.setStyle("-fx-background-color: transparent;");
            }
        }
        event.consume();
    }
}
