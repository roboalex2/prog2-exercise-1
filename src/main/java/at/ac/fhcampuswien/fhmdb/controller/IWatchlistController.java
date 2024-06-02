package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXListView;

import java.net.URL;
import java.util.ResourceBundle;

public interface IWatchlistController {
    JFXListView<Movie> watchlistListView = null;
    void initialize(URL url, ResourceBundle resourceBundle);
}
