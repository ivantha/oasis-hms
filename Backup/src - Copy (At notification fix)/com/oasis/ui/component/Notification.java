package com.oasis.ui.component;

import com.oasis.services.SystemServices;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Date;


public class Notification extends Pane{
    private static final int DEFAULT_HEIGHT = 35;
    private static final int DEFAULT_WIDTH = 175;

    private boolean isContract = true;

    private int nId = 0;
    private StringProperty heading = new SimpleStringProperty();
    private StringProperty content = new SimpleStringProperty();
    private NotificationType type = NotificationType.DEFAULT;
    private IntegerProperty timeout = new SimpleIntegerProperty(-1);
    private EventHandler<ActionEvent> eventHandler = null;
    private Date timestamp;

    public int animationCount = 0;

    public Notification(int nId, String heading, String content, NotificationType type) {
        this.nId = nId;
        this.heading.setValue(heading);
        this.content.setValue(content);
        this.type = type;
        this.timestamp = new Date();

        this.getStyleClass().add("notification");

        contract();

        this.setOnMouseClicked(event -> {
            if(isContract){
                expand();
            }else {
                contract();
            }
        });
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getHeading() {
        return heading.get();
    }

    public StringProperty headingProperty() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading.set(heading);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public int getTimeout() {
        return timeout.get();
    }

    public IntegerProperty timeoutProperty() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout.set(timeout);
    }

    public EventHandler<ActionEvent> getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler<ActionEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void expand(){
        isContract = false;

        this.setStyle(null);
        this.getStyleClass().remove(getType().name());
        this.setStyle("-fx-border-color: #FFFFFF;");

        this.setPrefHeight(USE_COMPUTED_SIZE);
        setWidth(DEFAULT_WIDTH);

        Label contentLabel = new Label(getContent());
        contentLabel.setWrapText(true);
        contentLabel.setMaxWidth(170);
        contentLabel.setStyle("-fx-text-fill: #FFFFFF");

        Button closeButton = new Button();
        closeButton.getStyleClass().add("notification-close-button");
        closeButton.setOnAction(event -> SystemServices.removeNotification(Notification.this));

        HBox closeHBox = new HBox();
        closeHBox.setAlignment(Pos.BOTTOM_RIGHT);
        closeHBox.setSpacing(5.0);
        closeHBox.getChildren().add(closeButton);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPrefWidth(DEFAULT_WIDTH);
        vBox.setPrefHeight(USE_COMPUTED_SIZE);
        vBox.setPadding(new Insets(5.0, 0.0, 0.0, 5.0));


        vBox.getChildren().add(contentLabel);
        vBox.getChildren().add(closeHBox);

        this.getChildren().clear();
        this.getChildren().add(vBox);
    }

    public void contract(){
        isContract = true;

        this.setStyle(null);
        this.getStyleClass().add(getType().name());

        this.setPrefHeight(DEFAULT_HEIGHT);
        setWidth(DEFAULT_WIDTH);

        Label headingLabel = new Label(getHeading());
        headingLabel.setWrapText(true);
        headingLabel.setMaxWidth(170);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPrefHeight(DEFAULT_HEIGHT);
        vBox.setPadding(new Insets(0.0, 0.0, 0.0, 25));
        vBox.getChildren().add(headingLabel);

        this.getChildren().clear();
        this.getChildren().add(vBox);
    }

    private void setWidth(int width){
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setPrefWidth(width);
    }
}
