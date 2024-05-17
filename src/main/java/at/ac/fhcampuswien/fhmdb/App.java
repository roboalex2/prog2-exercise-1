package at.ac.fhcampuswien.fhmdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage; // Primary stage for the application

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        setRoot("home-view"); // Start with the home view
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
    }

    public static void main(String[] args) {
        launch();
    }
}
