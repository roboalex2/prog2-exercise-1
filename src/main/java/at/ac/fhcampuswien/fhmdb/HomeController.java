package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
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
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    public List<Movie> search(String searchText){
        return allMovies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                                movie.getDescription().toLowerCase().contains(searchText.toLowerCase())
                )
                .toList();
    }

    public List<Movie> filter(String filterGenre){
        // TODO
        return List.of();
    }

    public List<Movie> sort(String sortOrder){
        List<Movie> sortedMovies = new ArrayList<>(observableMovies);
        if (sortOrder.equals("Sort (asc)")) {
            sortedMovies.sort(Comparator.comparing(Movie::getTitle));
        } else {
            sortedMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
        }
        return sortedMovies;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here




        // Sort button example:
// Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                observableMovies.sort(Comparator.comparing(Movie::getTitle));
                sortBtn.setText("Sort (desc)");
            } else {
                observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
                sortBtn.setText("Sort (asc)");
            }
        });



    }

    public void onSearchButtonClick(ActionEvent actionEvent) {
        String movieToSearch = searchField.getText();
        List<Movie> searchResult = search(movieToSearch);

        movieListView.setCellFactory(movieListView -> new MovieCell());
        observableMovies.clear();
        movieListView.setCellFactory(movieListView -> new MovieCell());
        observableMovies.addAll(searchResult);
        movieListView.setCellFactory(movieListView -> new MovieCell());
    }
}