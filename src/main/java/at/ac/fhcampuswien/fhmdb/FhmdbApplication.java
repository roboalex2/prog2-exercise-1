package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.controller.ControllerFactory;
import at.ac.fhcampuswien.fhmdb.controller.IHomeController;
import at.ac.fhcampuswien.fhmdb.controller.IWatchlistController;
import at.ac.fhcampuswien.fhmdb.controller.WatchlistController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FhmdbApplication extends Application {

    public void start(Stage Stage) {
        try {
            URL homeFxmlUrl = FhmdbApplication.class.getResource("home-view.fxml");
            URL watchlistFxmlUrl = FhmdbApplication.class.getResource("watchlist-view.fxml");

            if (homeFxmlUrl == null || watchlistFxmlUrl == null) {
                throw new IllegalStateException("FXML file not found");
            }

            ControllerFactory.createController(
                    IHomeController.class, Stage, homeFxmlUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}