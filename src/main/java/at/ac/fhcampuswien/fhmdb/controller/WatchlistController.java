package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.util.Observer;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import at.ac.fhcampuswien.fhmdb.dao.WatchlistRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WatchlistController implements Observer, Initializable {


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @Override
    public void update(String message) {
        System.out.println("Update received: " + message);
        observableWatchlistMovies.clear();
        observableWatchlistMovies.setAll(MovieStateManager.getInstance().fetchWatchlistMovies());
        showAlert(message); // This line calls the showAlert method
    }



    private static WatchlistController instance;

    private WatchlistController() {
        observableWatchlistMovies.clear();
        observableWatchlistMovies.setAll(MovieStateManager.getInstance().fetchWatchlistMovies());
    }

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
        //observableWatchlistMovies.clear();
        //observableWatchlistMovies.addAll(MovieStateManager.getInstance().fetchWatchlistMovies());
        watchlistListView.setItems(observableWatchlistMovies);
        watchlistListView.setCellFactory(watchlistListView -> new MovieCell("Remove", onRemoveFromWatchlistClicked));

        try {
            WatchlistRepository repository = WatchlistRepository.getInstance();
            if (!repository.getObservers().contains(this)) { // Ensure single registration
                repository.addObserver(this);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
