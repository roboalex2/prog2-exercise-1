package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public interface IWatchlistController {
    static WatchlistController instance = null;
    final ObservableList<Movie> observableWatchlistMovies = null;
    void initialize(URL url, ResourceBundle resourceBundle);
}
