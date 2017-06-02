package com.oasis.main;

import com.oasis.common.Session;
import com.oasis.services.SystemServices;
import com.oasis.services.UserServices;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OasisApplication extends Application {
    BooleanProperty ready = new SimpleBooleanProperty(false);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();

//        loadPreloader();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SystemServices.start();

        Session.currentUser = UserServices.getUser(null, "ivantha");

        primaryStage.initStyle(StageStyle.UNDECORATED);
        SystemServices.loadDashboard(primaryStage);
    }

    private void loadPreloader() {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 100;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(10);
                    notifyPreloader(new Preloader.ProgressNotification(Double.valueOf(i) / max));
                }
                ready.setValue(Boolean.TRUE);

                notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

                return null;
            }
        };
        new Thread(task).start();
    }
}
