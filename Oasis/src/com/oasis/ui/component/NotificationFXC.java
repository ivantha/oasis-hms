package com.oasis.ui.component;


import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationFXC extends ListView<Notification> {
    private static final int ANIMATE_DURATION = 400;

    private ObservableList<Notification> notificationObservableList;
    private int lastNId = 0;

    public NotificationFXC(double width, double height) {
        this.setId("notificationListView");
        this.setPrefWidth(width);
        this.setPrefHeight(height);

        notificationObservableList = FXCollections.observableList(new ArrayList<>());
        this.setItems(notificationObservableList);

        this.setCellFactory(new Callback<ListView<Notification>, ListCell<Notification>>() {
            @Override
            public ListCell<Notification> call(ListView<Notification> param) {
                ListCell<Notification> cell = new ListCell<Notification>() {
                    @Override
                    protected void updateItem(Notification item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null && !empty) {
                            setGraphic(item);
                            if (getIndex() == this.getListView().getItems().size() - 1) {
                                if (item.getAnimationCount() == 0) {
                                    this.setOpacity(0);
                                }

                                if (item.getAnimationCount() == 0) {
                                    PauseTransition pauseTransition = new PauseTransition(Duration.millis(1));
                                    pauseTransition.setOnFinished(e -> {
                                        if (NotificationFXC.this.isNotificationScrollVisible()) {
                                            // Fix :
                                            // Scroll to the notification when scroll bar is visible
                                        }

                                        FadeTransition fadeTransition = new FadeTransition();
                                        fadeTransition.setDuration(Duration.millis(ANIMATE_DURATION));
                                        fadeTransition.setFromValue(0.0);
                                        fadeTransition.setToValue(1.0);
                                        fadeTransition.setNode(this);

                                        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(ANIMATE_DURATION));
                                        translateTransition.setFromX(175);
                                        translateTransition.setToX(0);
                                        translateTransition.setNode(this);

                                        ParallelTransition parallelTransition = new ParallelTransition();
                                        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
                                        parallelTransition.setOnFinished(event -> {
                                            item.setAnimationCount(item.getAnimationCount() + 1);
                                            this.setOpacity(1.0);
                                            this.setTranslateX(0.0);
                                        });
                                        parallelTransition.play();
                                    });
                                    pauseTransition.play();
                                }
                            }
                        } else {
                            setGraphic(null);
                        }
                    }
                };

                return cell;
            }
        });

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable notificationWorker = () -> {
            ArrayList<Notification> expiredNotificationArrayList = new ArrayList<>();
            for(Notification notification: notificationObservableList){
                if(notification.isExpired()){
                    expiredNotificationArrayList.add(notification);
                }
            }

            for(Notification notification: expiredNotificationArrayList){
                if(notification.isContract()) {
                    NotificationFXC.this.removeNotification(notification);
                }
            }
        };
        scheduledExecutorService.scheduleWithFixedDelay(notificationWorker, 1, 1, TimeUnit.SECONDS);
    }

    public void addNotification(Notification notification){
        int nId = ++lastNId;
        notification.setnId(nId);

        notificationObservableList.add(notification);
    }

    public void removeNotification(Notification notification){
        IndexedCell<Notification> indexedCell = NotificationFXC.this.getNotificationCell(
                notificationObservableList.indexOf(notification));

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(ANIMATE_DURATION));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setNode(indexedCell);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(ANIMATE_DURATION));
        translateTransition.setFromX(0);
        translateTransition.setToX(175);
        translateTransition.setNode(indexedCell);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
        parallelTransition.setOnFinished(event -> {
            notificationObservableList.remove(notification);
            indexedCell.setOpacity(1.0);
            indexedCell.setTranslateX(0.0);
        });
        parallelTransition.play();

    }

    public boolean isNotificationScrollVisible(){
        return this.lookup(".scroll-bar").isVisible();
    }

    public IndexedCell<Notification> getNotificationCell(int cellIndex) {
        VirtualFlow virtualFlow = (VirtualFlow) this.lookup(".virtual-flow");
        return virtualFlow.getCell(cellIndex);
    }
}
