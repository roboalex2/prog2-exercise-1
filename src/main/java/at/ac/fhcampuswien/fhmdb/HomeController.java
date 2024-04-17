package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieApi;
import at.ac.fhcampuswien.fhmdb.models.*;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.MovieUtils;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    private static final String NO_GENRE_TEXT = "No Genre Filter";

    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<String> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    private List<Movie> allMovies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            allMovies = new MovieApi().fetchMovies(new MovieRequestParameter());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.setValue(NO_GENRE_TEXT);
        ObservableList<String> genres = FXCollections.observableArrayList(NO_GENRE_TEXT);
        genres.addAll(
            Arrays.stream(Genre.values())
                .map(Objects::toString)
                .toList()
        );
        genreComboBox.setItems(genres);

        searchBtn.setOnAction(this::onSearchButtonClick);
        sortBtn.setOnAction(this::onSortButtonClick);
    }

    private void onSortButtonClick(ActionEvent actionEvent) {
        if ("Sort (desc)".equals(sortBtn.getText())) {
            List<Movie> sortedMovies = MovieUtils.sort(observableMovies, SortOrder.DESCENDING);
            observableMovies.setAll(sortedMovies);
            sortBtn.setText("Sort (asc)");
        } else {
            List<Movie> sortedMovies = MovieUtils.sort(observableMovies, SortOrder.ASCENDING);
            observableMovies.setAll(sortedMovies);
            sortBtn.setText("Sort (desc)");
        }
    }

    public void onSearchButtonClick(ActionEvent actionEvent) {
        List<Movie> searchResult = MovieUtils.search(allMovies, searchField.getText());
        if (!NO_GENRE_TEXT.equals(genreComboBox.getValue())) {
            searchResult = MovieUtils.filter(searchResult, Genre.valueOf(Genre.class, genreComboBox.getValue()));
        }

        observableMovies.setAll(searchResult);
    }
}