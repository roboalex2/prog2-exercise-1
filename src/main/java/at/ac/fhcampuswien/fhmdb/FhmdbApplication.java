package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.controller.ControllerFactory;
import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;

public class FhmdbApplication extends Application {

    public void start(Stage Stage) {
        try {
            URL homeFxmlUrl = FhmdbApplication.class.getResource("home-view.fxml");
            URL watchlistFxmlUrl = FhmdbApplication.class.getResource("watchlist-view.fxml");

            if (homeFxmlUrl == null || watchlistFxmlUrl == null) {
                throw new IllegalStateException("FXML file not found");
            }

            ControllerFactory.createController(
                    HomeController.class, Stage, homeFxmlUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}