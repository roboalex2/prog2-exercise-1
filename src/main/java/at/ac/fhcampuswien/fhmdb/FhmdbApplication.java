package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.dao.*;
import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 620);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
        stage.show();

        DatabaseManager databaseInstance = DatabaseManager.getDatabaseInstance();
        databaseInstance.createConnectionSource("roboalex2", "test");

        try {
            databaseInstance.createTables();
            MovieRepository movieRepository = new MovieRepository();

            UUID movieId = UUID.randomUUID();

            movieRepository.addMovie(new MovieEntity(movieId, "Test Movie", 2010, "ACTION,HISTORY", "Test movie cool"));

            List<MovieEntity> allMovies = movieRepository.getAllMovies();
            assert !allMovies.isEmpty();

            WatchlistRepository watchlistRepository = new WatchlistRepository();
            watchlistRepository.addMoviesToWatchlist(new WatchlistMovieEntity(allMovies.get(0)));

            assert !watchlistRepository.getAllWatchlistMovies().isEmpty();

            movieRepository.deleteMovie(movieId);

            allMovies = movieRepository.getAllMovies();
            assert allMovies.isEmpty();

        } catch (UnsupportedOperationException | SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}