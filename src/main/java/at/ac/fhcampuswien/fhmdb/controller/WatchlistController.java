package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import at.ac.fhcampuswien.fhmdb.manager.MovieStateManager;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.ClickEventHandler;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {

    private static WatchlistController instance;

    private WatchlistController() {}

    @FXML
    private JFXListView<Movie> watchlistListView;
    private final ObservableList<Movie> observableWatchlistMovies = FXCollections.observableArrayList();

    public static synchronized WatchlistController getInstance() {
        if (instance == null) {
            instance = new WatchlistController();
        }
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableWatchlistMovies.clear();
        observableWatchlistMovies.addAll(MovieStateManager.getInstance().fetchWatchlistMovies());
        watchlistListView.setItems(observableWatchlistMovies);
        watchlistListView.setCellFactory(watchlistListView -> new MovieCell("Remove", onRemoveFromWatchlistClicked));
    }

    private final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedMovie) -> {
        MovieStateManager.getInstance().removeMovieFromWatchlist(clickedMovie);
        observableWatchlistMovies.setAll(MovieStateManager.getInstance().fetchWatchlistMovies());
    };
    @FXML
    private void showHome(ActionEvent actionEvent) {
        try {
            URL homeFxmlUrl = FhmdbApplication.class.getResource("home-view.fxml");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            ControllerFactory.createController(HomeController.class, stage, homeFxmlUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
