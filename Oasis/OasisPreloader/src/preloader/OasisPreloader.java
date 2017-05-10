package preloader;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OasisPreloader extends Preloader {
    ProgressBar bar;
    Stage stage;

    double progress = 0.0;

    private Scene createPreloaderScene() {
        bar = new ProgressBar();
        BorderPane p = new BorderPane();
        p.setCenter(bar);
        return new Scene(p, 500, 300);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START && progress == 1.0) {
            stage.hide();
        }
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        if (preloaderNotification instanceof ProgressNotification) {
            ProgressNotification pn = (ProgressNotification) preloaderNotification;
            bar.setProgress(pn.getProgress());
            System.out.println("Progress " + bar.getProgress());
        }
    }
}
