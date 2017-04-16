package com.oasis.main;

import com.oasis.factory.UIFactory;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import com.oasis.utils.SystemFunction;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SystemFunction.start();

        UI ui = UIFactory.getUI(UIName.DASHBOARD);
        Parent parent = ui.getParent();
        Scene scene = new Scene(parent, 1300, 700);
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                SystemFunction.showLauncher();
            } else if (event.getCode() == KeyCode.Q) {
                SystemFunction.exit();
            }
        });

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
