package com.oasis.listener;

import com.oasis.common.Session;
import com.oasis.common.Temp;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

public class DynamicButtonDragDoneEventHandler implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        String buttonName = String.valueOf(dragboard.getContent(Temp.BUTTON_NAME_DATA_FORMAT));
        String tabAnchorPaneId = String.valueOf(dragboard.getContent(Temp.PANE_ID_DATA_FORMAT));
        if (tabAnchorPaneId.equals("mainSideButton1AnchorPane")) {
            Session.APP_CONFIG.setTabButton1(buttonName);
        } else if (tabAnchorPaneId.equals("mainSideButton2AnchorPane")) {
            Session.APP_CONFIG.setTabButton2(buttonName);
        } else if (tabAnchorPaneId.equals("mainSideButton3AnchorPane")) {
            Session.APP_CONFIG.setTabButton3(buttonName);
        } else if (tabAnchorPaneId.equals("mainSideButton4AnchorPane")) {
            Session.APP_CONFIG.setTabButton4(buttonName);
        } else if (tabAnchorPaneId.equals("mainSideButton5AnchorPane")) {
            Session.APP_CONFIG.setTabButton5(buttonName);
        } else if (tabAnchorPaneId.equals("mainSideButton6AnchorPane")) {
            Session.APP_CONFIG.setTabButton6(buttonName);
        } else if (tabAnchorPaneId.equals("mainSideButton7AnchorPane")) {
            Session.APP_CONFIG.setTabButton7(buttonName);
        }
    }
}
