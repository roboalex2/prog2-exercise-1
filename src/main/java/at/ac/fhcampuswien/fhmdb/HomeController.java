package at.ac.fhcampuswien.fhmdb;

import javafx.fxml.FXMLLoader; // new code
import javafx.scene.Scene; // new code
import javafx.stage.Stage; // new code
import java.io.IOException; // new code

import at.ac.fhcampuswien.fhmdb.manager.MovieStateManager;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortOrder;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.util.MovieUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.IntStream;


public class HomeController implements Initializable {
    private static final String NO_GENRE_TEXT = "No Genre Filter";

    @FXML
    public JFXButton watchlistButton; // new code
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

    @FXML
    public JFXComboBox releaseComboBox;

    @FXML
    public TextField ratingField;

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        watchlistButton.setOnAction(this::showWatchlist); // new code

        observableMovies.addAll(MovieStateManager.getInstance().fetchAllMovies());

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked)); // use custom cell factory to display data

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


        releaseComboBox.getItems().add("No Filter");
        IntStream.iterate(OffsetDateTime.now().getYear(), year -> year - 1)
                .limit(200)
                .boxed()
                .forEach(releaseComboBox.getItems()::add);
        releaseComboBox.getSelectionModel().select("No Filter");

        String RegexInputValidationRating = "^(10\\.0|[0-9](\\.\\d?)?)$";
        ratingField.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (!newvalue.matches(RegexInputValidationRating) && !newvalue.isEmpty()) {
                ratingField.setText(oldvalue);
            }
        });
    }

    private final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        // Add code to add movie to watchlist here
        System.out.println("Added to watchlist: " + clickedItem.getTitle());
        // Example: WatchlistRepository.addMovie(clickedItem);
    };

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
        Double searchRating = getSearchRating();
        String searchQuery = getSearchQuery();
        Genre searchGenre = getSearchGenre();
        Integer searchReleaseDate = getSearchReleaseDate();

        // We no longer use the API filter and use our filter logic because we want easy database support.
        // MovieRequestParameter params = new MovieRequestParameter(searchQuery, searchGenre, searchReleaseDate, searchRating);
        List<Movie> searchResult = MovieStateManager.getInstance().fetchAllMovies();

        // Keep exercise one logic working
        if (searchQuery != null) {
            searchResult = MovieUtils.search(searchResult, searchField.getText());
        }

        if (searchGenre != null) {
            searchResult = MovieUtils.filter(searchResult, searchGenre);
        }

        if (sortBtn.getText() != null && sortBtn.getText().contains("des")) {
            searchResult = MovieUtils.sort(searchResult, SortOrder.ASCENDING);
        } else {
            searchResult = MovieUtils.sort(searchResult, SortOrder.DESCENDING);
        }

        observableMovies.setAll(searchResult);
    }

    private Double getSearchRating() {
        if (ratingField.getText() == null || ratingField.getText().isBlank()) {
            return null;
        }

        return Double.parseDouble(ratingField.getText().strip());
    }

    private String getSearchQuery() {
        if (searchField.getText() == null || searchField.getText().isBlank()) {
            return null;
        }

        return searchField.getText().strip();
    }

    private Genre getSearchGenre() {
        if (NO_GENRE_TEXT.equals(genreComboBox.getValue())) {
            return null;
        }

        return Genre.valueOf(genreComboBox.getValue());
    }

    private Integer getSearchReleaseDate() {
        if (releaseComboBox.getValue() != null) {
            try {
                return Integer.parseInt(releaseComboBox.getValue().toString());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }


    public void showWatchlist(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("watchlist-view.fxml")); // new code
            Scene scene = new Scene(fxmlLoader.load()); // new code
            Stage stage = (Stage) ((JFXButton) actionEvent.getSource()).getScene().getWindow(); // new code
            stage.setScene(scene); // new code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}