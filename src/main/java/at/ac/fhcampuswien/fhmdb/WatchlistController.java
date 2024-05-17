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
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WatchlistController implements Initializable {
    @FXML
    private JFXListView<Movie> watchlistListView; // Ensure this matches the fx:id in the FXML

    private final ObservableList<Movie> observableWatchlistMovies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableWatchlistMovies.addAll(MovieStateManager.getInstance().fetchWatchlistMovies()); // new code
        watchlistListView.setItems(observableWatchlistMovies); // new code
        watchlistListView.setCellFactory(watchlistListView -> new MovieCell(onRemoveFromWatchlistClicked)); // new code
    }

    private final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedItem) -> { // new code
        MovieStateManager.getInstance().removeMovieFromWatchlist(clickedItem);
        observableWatchlistMovies.setAll(MovieStateManager.getInstance().fetchWatchlistMovies());

    }; // new code

    public void showHome(ActionEvent actionEvent) { // new code
        try { // new code
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home-view.fxml")); // new code
            Scene scene = new Scene(fxmlLoader.load()); // new code
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow(); // new code
            stage.setScene(scene); // new code
        } catch (IOException e) { // new code
            e.printStackTrace(); // new code
        } // new code
    } // new code

    private void showAlert(String title, String message) { // new code
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // new code
        alert.setTitle(title); // new code
        alert.setContentText(message); // new code
        alert.show(); // new code
    } // new code
}
