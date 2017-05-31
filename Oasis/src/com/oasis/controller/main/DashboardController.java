package com.oasis.controller.main;

import com.oasis.common.Session;
import com.oasis.controller.Controller;
import com.oasis.factory.UIFactory;
import com.oasis.listener.DynamicPaneDragDroppedEventHandler;
import com.oasis.listener.DynamicPaneDragEnteredEventHandler;
import com.oasis.listener.DynamicPaneDragExitedEventHandler;
import com.oasis.listener.DynamicPaneDragOverEventHandler;
import com.oasis.main.Main;
import com.oasis.services.NotificationServices;
import com.oasis.services.SystemServices;
import com.oasis.ui.UIName;
import com.oasis.ui.component.Notification;
import com.oasis.ui.component.NotificationFXC;
import com.oasis.ui.utils.ImageScaler;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

public class DashboardController implements Controller {
    private static final Duration LAUNCHER_AIMATE_TIME = Duration.millis(300);
    private static final Duration WORKSPACE_ANIMATE_TIME = Duration.millis(400);

    @FXML
    private Circle profilePictureCircle;
    @FXML
    private Label nameLabel;
    @FXML
    private Label roleLabel;

    @FXML
    private AnchorPane mainSideButton1AnchorPane;
    @FXML
    private AnchorPane mainSideButton2AnchorPane;
    @FXML
    private AnchorPane mainSideButton3AnchorPane;
    @FXML
    private AnchorPane mainSideButton4AnchorPane;
    @FXML
    private AnchorPane mainSideButton5AnchorPane;
    @FXML
    private AnchorPane mainSideButton6AnchorPane;
    @FXML
    private AnchorPane mainSideButton7AnchorPane;

    @FXML
    private AnchorPane notificationAnchorPane;
    @FXML
    private VBox notificationVBox;

    @FXML
    private Button signOutButton;

    @FXML
    private AnchorPane sideBarAnchorPane;
    @FXML
    private AnchorPane workspaceAnchorPane;

    private NotificationFXC notificationFXC;

    private boolean isLauncherVisible = false;
    private Parent launcherParent;

    private ObjectProperty<Button> lastPressedMainSideButton = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAsDynamic(mainSideButton1AnchorPane);
        setAsDynamic(mainSideButton2AnchorPane);
        setAsDynamic(mainSideButton3AnchorPane);
        setAsDynamic(mainSideButton4AnchorPane);
        setAsDynamic(mainSideButton5AnchorPane);
        setAsDynamic(mainSideButton6AnchorPane);
        setAsDynamic(mainSideButton7AnchorPane);

        SystemServices.loadDynamicButton(mainSideButton1AnchorPane, Session.APP_CONFIG.getTabButton1(), lastPressedMainSideButton);
        SystemServices.loadDynamicButton(mainSideButton2AnchorPane, Session.APP_CONFIG.getTabButton2(), lastPressedMainSideButton);
        SystemServices.loadDynamicButton(mainSideButton3AnchorPane, Session.APP_CONFIG.getTabButton3(), lastPressedMainSideButton);
        SystemServices.loadDynamicButton(mainSideButton4AnchorPane, Session.APP_CONFIG.getTabButton4(), lastPressedMainSideButton);
        SystemServices.loadDynamicButton(mainSideButton5AnchorPane, Session.APP_CONFIG.getTabButton5(), lastPressedMainSideButton);
        SystemServices.loadDynamicButton(mainSideButton6AnchorPane, Session.APP_CONFIG.getTabButton6(), lastPressedMainSideButton);
        SystemServices.loadDynamicButton(mainSideButton7AnchorPane, Session.APP_CONFIG.getTabButton7(), lastPressedMainSideButton);

        lastPressedMainSideButton.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.setStyle(null);
            }

            newValue.setStyle("-fx-font-family: 'Roboto';\n" +
                    "-fx-font-size: 13;\n" +
                    "-fx-background-radius: 0px;\n" +
                    "-fx-background-color: #03A9F4;");
        });

        notificationFXC = new NotificationFXC(200, ListView.USE_COMPUTED_SIZE);
        notificationVBox.getChildren().add(notificationFXC);

        Rectangle clipRectangle = new Rectangle(1100, 700);
        workspaceAnchorPane.setClip(clipRectangle);
    }

    @Override
    public void refreshView() {
        setLoginDetails();
    }

    public NotificationFXC getNotificationFXC() {
        return notificationFXC;
    }

    public void closeButtonOnAction(ActionEvent actionEvent) {
        SystemServices.exit();
    }

    public void minimizeButtonOnAction(ActionEvent actionEvent) {
//        ((Stage) ((Button) (actionEvent.getSource())).getScene().getWindow()).setIconified(true);

        Random random = new Random();
        long randLong = (long) (random.nextDouble() * 1000 * 10);
        NotificationServices.addNotification("My custom notification",
                "I just want to test out how things are working. That is all", Notification.NotificationType.INFORMATION, randLong);
//        SystemServices.addNotification("One",
//                "This is the first notification", NotificationType.DEFAULT);
//        SystemServices.addNotification("Successful insertion",
//                "Employee successfully added to the database", NotificationType.SUCCESSFUL);
//        SystemServices.addNotification("Patient deleted",
//                "Patient record 53984 successfully deleted", NotificationType.SUCCESSFUL);
//        SystemServices.addNotification("Incorrect input",
//                "Please enter a correct phone number in the form os xxxxxxxxxx", NotificationType.WARNING);
//        SystemServices.addNotification("Error",
//                "Cannot connect with the server", NotificationType.ERROR);
//        SystemServices.addNotification("Incorrect input",
//                "Please select a gender", NotificationType.ERROR);
//        SystemServices.addNotification("Assistive options",
//                "Hover over the options to see tooltips", NotificationType.INFORMATION);
//        SystemServices.addNotification("One",
//                "This is the first notification", NotificationType.DEFAULT);
//        SystemServices.addNotification("Successful insertion",
//                "Employee successfully added to the database", NotificationType.SUCCESSFUL);
//        SystemServices.addNotification("Patient deleted",
//                "Patient record 53984 successfully deleted", NotificationType.SUCCESSFUL);
//        SystemServices.addNotification("Error",
//                "Cannot connect with the server", NotificationType.ERROR);
//        SystemServices.addNotification("Incorrect input",
//                "Please enter a correct phone number in the form os xxxxxxxxxx", NotificationType.WARNING);
//        SystemServices.addNotification("Incorrect input",
//                "Please select a gender", NotificationType.ERROR);
//        SystemServices.addNotification("Assistive options",
//                "Hover over the options to see tooltips", NotificationType.INFORMATION);

        slideOutChildren();
    }

    public boolean isLauncherVisible() {
        return isLauncherVisible;
    }

    public void setLauncherVisible(boolean launcherVisible) {
        isLauncherVisible = launcherVisible;
    }

    public void showLauncher() {
        if (!isLauncherVisible()) {
            slideInLauncher();
        } else {
            slideOutLauncher(event -> workspaceAnchorPane.getChildren().remove(launcherParent));
        }
    }

    private void setAsDynamic(AnchorPane anchorPane) {
        anchorPane.setOnDragOver(new DynamicPaneDragOverEventHandler());
        anchorPane.setOnDragEntered(new DynamicPaneDragEnteredEventHandler());
        anchorPane.setOnDragExited(new DynamicPaneDragExitedEventHandler());
        anchorPane.setOnDragDropped(new DynamicPaneDragDroppedEventHandler(lastPressedMainSideButton));
    }

    private void slideInLauncher() {
        launcherParent = UIFactory.getUI(UIName.LAUNCHER).getParent();
        this.workspaceAnchorPane.getChildren().add(launcherParent);

        TranslateTransition translateTransition = new TranslateTransition(LAUNCHER_AIMATE_TIME);
        translateTransition.setFromX(0 - 450);
        translateTransition.setFromY(700 - 300);
        translateTransition.setToX(100);
        translateTransition.setToY(50);

        ScaleTransition scaleTransition = new ScaleTransition(LAUNCHER_AIMATE_TIME);
        scaleTransition.setFromX(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        ParallelTransition pt = new ParallelTransition(launcherParent, translateTransition, scaleTransition);
        pt.play();

        setLauncherVisible(true);
    }

    private void slideOutLauncher(EventHandler<ActionEvent> eventEventHandler) {
        TranslateTransition translateTransition = new TranslateTransition(LAUNCHER_AIMATE_TIME);
        translateTransition.setFromX(100);
        translateTransition.setFromY(50);
        translateTransition.setToX(0 - 450);
        translateTransition.setToY(700 - 300);

        ScaleTransition scaleTransition = new ScaleTransition(LAUNCHER_AIMATE_TIME);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);

        ParallelTransition pt = new ParallelTransition(launcherParent, translateTransition, scaleTransition);
        pt.setOnFinished(eventEventHandler);
        pt.play();

        setLauncherVisible(false);
    }

    private void slideInParent(Parent parent) {
        TranslateTransition parentInTranslation = new TranslateTransition(WORKSPACE_ANIMATE_TIME);
        parentInTranslation.setFromX(1100);
        parentInTranslation.setToX(0);
        parentInTranslation.setNode(parent);

        if (workspaceAnchorPane.getChildren().size() == 0) {
            workspaceAnchorPane.getChildren().add(parent);
            parentInTranslation.play();
        } else {
            Node childNode = workspaceAnchorPane.getChildren().get(0);
            workspaceAnchorPane.getChildren().add(parent);

            TranslateTransition childOutTranslation = new TranslateTransition(WORKSPACE_ANIMATE_TIME);
            childOutTranslation.setFromX(0);
            childOutTranslation.setToX(-1100);
            childOutTranslation.setNode(childNode);

            ParallelTransition parallelTransition = new ParallelTransition(parentInTranslation, childOutTranslation);
            parallelTransition.setOnFinished(event -> {
                Iterator<Node> nodeIterator = workspaceAnchorPane.getChildren().iterator();
                while (nodeIterator.hasNext()) {
                    nodeIterator.next();
                    if (nodeIterator.hasNext()) {
                        nodeIterator.remove();
                    }
                }
            });
            parallelTransition.play();
        }
    }

    private void slideOutChildren() {
        Iterator<Node> nodeIterator = this.workspaceAnchorPane.getChildren().iterator();
        while (nodeIterator.hasNext()) {
            TranslateTransition translateTransition = new TranslateTransition(WORKSPACE_ANIMATE_TIME);
            translateTransition.setFromX(0);
            translateTransition.setToX(1100);
            translateTransition.setNode(nodeIterator.next());
            translateTransition.setOnFinished(event -> nodeIterator.remove());
            translateTransition.play();
        }
    }

    public void setWorkspace(Parent parent) {
        if (isLauncherVisible()) {
            EventHandler<ActionEvent> eventEventHandler = event -> {
                workspaceAnchorPane.getChildren().remove(launcherParent);
                slideInParent(parent);
            };
            slideOutLauncher(eventEventHandler);
        } else {
            slideInParent(parent);
        }
    }

    private void setLoginDetails() {
        nameLabel.setText(Session.currentUser.getFirstName() + " " + Session.currentUser.getLastName());
        roleLabel.setText(Session.currentUser.getEmployeeRole().getRole());

        Image profilePictureImage = new Image(Main.class.getResourceAsStream("/com/oasis/resources/images/default_profile_picture.png"));
        File savedImage = new File(System.getProperty("user.dir") + "/profile_pictures/", "pp_" + Session.currentUser.getId() + ".jpg");
        if (savedImage.exists()) {
            profilePictureImage = new Image(savedImage.toURI().toString());
        }
        Image resizedImage = ImageScaler.resizeImage(profilePictureImage, 30);

        profilePictureCircle.setFill(new ImagePattern(resizedImage));
    }

    public void signOutButtonOnAction(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) signOutButton.getScene().getWindow();
        SystemServices.loadLogin(primaryStage, new SimpleBooleanProperty(Boolean.TRUE));
    }
}