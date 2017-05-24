package com.oasis.listener;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class DynamicPaneDragOverEventHandler implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }
}
