package com.oasis.ui.component;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
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

import java.time.Duration;
import java.time.Instant;


public class Notification extends Pane {
    private static final int DEFAULT_HEIGHT = 35;
    private static final int DEFAULT_WIDTH = 175;

    private int nId = 0;
    private StringProperty heading = new SimpleStringProperty();
    private StringProperty content = new SimpleStringProperty();
    private NotificationType type = NotificationType.DEFAULT;
    private LongProperty timeout = new SimpleLongProperty(-1);
    private EventHandler<ActionEvent> eventHandler = null;
    private Instant timestamp = Instant.now();

    private Label headingLabel;
    private Label contentLabel;
    private int animationCount = 0;
    private boolean isContract = true;
    private EventHandler<ActionEvent> closeButtonEventHandler;

    public Notification(String heading, String content, NotificationType type) {
        this.heading.setValue(heading);
        this.content.setValue(content);
        this.type = type;

        this.headingLabel = new Label();
        this.contentLabel = new Label();

        headingLabel.textProperty().bind(headingProperty());
        contentLabel.textProperty().bind(contentProperty());

        this.getStyleClass().add("notification");

        contract();

        this.setOnMouseClicked(event -> {
            if (isContract) {
                expand();
            } else {
                contract();
            }
        });
    }

    public Notification(String heading, String content, NotificationType type, long timeout) {
        this(heading, content, type);
        this.timeout.setValue(timeout);
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

    public void setHeading(String heading) {
        this.heading.set(heading);
    }

    public StringProperty headingProperty() {
        return heading;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public StringProperty contentProperty() {
        return content;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public long getTimeout() {
        return timeout.get();
    }

    public void setTimeout(int timeout) {
        this.timeout.set(timeout);
    }

    public void setTimeout(long timeout) {
        this.timeout.set(timeout);
    }

    public LongProperty timeoutProperty() {
        return timeout;
    }

    public EventHandler<ActionEvent> getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler<ActionEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getAnimationCount() {
        return animationCount;
    }

    public void setAnimationCount(int animationCount) {
        this.animationCount = animationCount;
    }

    public boolean isContract() {
        return isContract;
    }

    public void setContract(boolean contract) {
        isContract = contract;
    }

    public EventHandler<ActionEvent> getCloseButtonEventHandler() {
        return closeButtonEventHandler;
    }

    public void setCloseButtonEventHandler(EventHandler<ActionEvent> closeButtonEventHandler) {
        this.closeButtonEventHandler = closeButtonEventHandler;
    }

    public void expand() {
        isContract = false;

        this.setStyle(null);
        this.getStyleClass().remove(getType().name());
        this.setStyle("-fx-border-color: #FFFFFF;");

        this.setPrefHeight(USE_COMPUTED_SIZE);
        setWidth(DEFAULT_WIDTH);

        contentLabel.setWrapText(true);
        contentLabel.setMaxWidth(170);
        contentLabel.setStyle("-fx-text-fill: #FFFFFF");

        Button closeButton = new Button();
        closeButton.getStyleClass().add("notification-close-button");
        closeButton.setOnAction(closeButtonEventHandler);

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

    public void contract() {
        isContract = true;

        this.setStyle(null);
        this.getStyleClass().add(getType().name());

        this.setPrefHeight(DEFAULT_HEIGHT);
        setWidth(DEFAULT_WIDTH);

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

    private void setWidth(int width) {
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setPrefWidth(width);
    }

    public boolean isExpired() {
        Duration duration = Duration.between(getTimestamp(), Instant.now());
        long milliDuration = duration.toMillis();

        if (getTimeout() == -1) {
            return false;
        } else {
            return milliDuration > getTimeout();
        }
    }

    public enum NotificationType {
        DEFAULT,
        SUCCESSFUL,
        INFORMATION,
        WARNING,
        ERROR,

        ADD,
        EDIT,
        DELETE
    }
}
