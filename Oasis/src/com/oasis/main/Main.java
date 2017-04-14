package com.oasis.main;

import com.oasis.common.Session;
import com.oasis.configuration.ConfigurationFile;
import com.oasis.configuration.ConfigurationHandler;
import com.oasis.factory.UIFactory;
import com.oasis.ui.UI;
import com.oasis.ui.UIName;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        UIFactory.initializeAllUIs();
        this.initializeSession();

        UI ui = UIFactory.getUI(UIName.DASHBOARD);
        Parent parent = ui.getParent();
        Scene scene = new Scene(parent, 1300, 680);

        primaryStage.setTitle(Session.APP_CONFIG.getStageTitle());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    private void initializeSession(){
        Session.APP_CONFIG = new ConfigurationFile(new ConfigurationHandler());
    }
}
