package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.manager.MovieStateManager;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.ClickEventHandler;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {


    @FXML
    private JFXListView<Movie> watchlistListView;
    private final ObservableList<Movie> observableWatchlistMovies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableWatchlistMovies.addAll(MovieStateManager.getInstance().fetchWatchlistMovies());
        watchlistListView.setItems(observableWatchlistMovies);
        watchlistListView.setCellFactory(watchlistListView -> new MovieCell("Remove", onRemoveFromWatchlistClicked));
    }

    private final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedMovie) -> {
        MovieStateManager.getInstance().removeMovieFromWatchlist(clickedMovie);
        observableWatchlistMovies.setAll(MovieStateManager.getInstance().fetchWatchlistMovies());
    };

    public void showHome(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
