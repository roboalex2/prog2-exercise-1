package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public interface IHomeController {
    static HomeController instance = null;
    final ObservableList<Movie> observableMovies = null;
    void initialize(URL url, ResourceBundle resourceBundle);

}
