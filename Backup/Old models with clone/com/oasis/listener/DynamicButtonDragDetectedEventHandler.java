package com.oasis.listener;

import com.oasis.common.Temp;
import com.oasis.ui.UIName;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.transform.Transform;


public class DynamicButtonDragDetectedEventHandler implements EventHandler<MouseEvent>{
    private UIName uiName;

    public DynamicButtonDragDetectedEventHandler(UIName uiName) {
        this.uiName = uiName;
    }

    @Override
    public void handle(MouseEvent event) {
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setTransform(Transform.scale(0.6, 0.8));

        Button dragButton = (Button) event.getSource();
        Dragboard dragboard = dragButton.startDragAndDrop(TransferMode.MOVE);
        dragboard.setDragView(dragButton.snapshot(snapshotParameters, null));

        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.put(Temp.BUTTON_NAME_DATA_FORMAT, uiName.name());
        dragboard.setContent(clipboardContent);

        event.consume();
    }
}
