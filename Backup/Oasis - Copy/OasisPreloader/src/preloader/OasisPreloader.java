package preloader;

import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OasisPreloader extends Preloader {
    JFXProgressBar bar;
    Stage stage;

    double progress = 0.0;

    private Scene createPreloaderScene() {
        bar = new JFXProgressBar();
        bar.setPrefSize(500, 10);

        Image logoImage = new Image(OasisPreloader.class.getResourceAsStream("oasis_logo.png"));
        ImageView imageView = new ImageView(logoImage);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(bar);
        borderPane.setCenter(imageView);
        return new Scene(borderPane, 500, 300);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;

        stage.initStyle(StageStyle.UNDECORATED);
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
//            System.out.println("Progress " + bar.getProgress());
        }
    }
}
