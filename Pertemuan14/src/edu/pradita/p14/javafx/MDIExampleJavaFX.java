package edu.pradita.p14.javafx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MDIExampleJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the main container
        AnchorPane mainPane = new AnchorPane();
        mainPane.setPrefSize(800, 600);

        // Create a few child windows
        for (int i = 1; i <= 3; i++) {
            Pane childWindow = createChildWindow("Document " + i);
            childWindow.setLayoutX(50 * i);
            childWindow.setLayoutY(50 * i);
            mainPane.getChildren().add(childWindow);
        }

        // Set up the primary stage
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MDI Example with JavaFX");
        primaryStage.show();
    }

    private Pane createChildWindow(String title) {
        Pane childPane = new Pane();
        childPane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: lightgray;");
        childPane.setPrefSize(300, 200);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> ((Pane) childPane.getParent()).getChildren().remove(childPane));
        childPane.getChildren().add(closeButton);

        // Make the childPane draggable
        makeDraggable(childPane);

        return childPane;
    }

    private void makeDraggable(Pane pane) {
        pane.setOnMousePressed(event -> {
            pane.setUserData(new double[]{event.getSceneX() - pane.getLayoutX(), event.getSceneY() - pane.getLayoutY()});
        });
        pane.setOnMouseDragged(event -> {
            double[] offset = (double[]) pane.getUserData();
            pane.setLayoutX(event.getSceneX() - offset[0]);
            pane.setLayoutY(event.getSceneY() - offset[1]);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
