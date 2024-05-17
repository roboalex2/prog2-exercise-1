package at.ac.fhcampuswien.fhmdb.manager;

import at.ac.fhcampuswien.fhmdb.api.MovieApi;
import at.ac.fhcampuswien.fhmdb.dao.MovieRepository;
import at.ac.fhcampuswien.fhmdb.dao.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.exception.DatabaseException;
import at.ac.fhcampuswien.fhmdb.mapper.MovieEntityMapper;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieRequestParameter;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MovieStateManager {

    private static final String USERNAME = "moviedb";
    private static final String PASSWORD = "movie123";
    private MovieApi movieApi = new MovieApi();
    private MovieRepository movieRepository;
    private WatchlistRepository watchlistRepository;

    private static MovieStateManager movieStateManager;

    private MovieStateManager() {
        // Singleton
    }

    public static synchronized MovieStateManager getInstance() {
        if (movieStateManager == null) {
            movieStateManager = new MovieStateManager();
            movieStateManager.reinitializeDatabase();
        }

        return movieStateManager;
    }

    public void reinitializeDatabase() {
        DatabaseManager databaseInstance = DatabaseManager.getDatabaseInstance();
        try {
            databaseInstance.createConnectionSource(USERNAME, PASSWORD);
            databaseInstance.createTables();

            this.movieRepository = new MovieRepository();
            this.watchlistRepository = new WatchlistRepository();
        } catch (SQLException | UnsupportedOperationException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to use local database!");
            alert.setContentText("The application failed to access the local database. Details: " + exception.getMessage());
            alert.show();
            exception.printStackTrace();
        }
    }

    public List<Movie> fetchAllMovies() {
        List<Movie> movies = fetchMoviesFromApi();

        if (movieRepository != null) {
            movies = updateMovieRepository(movies);
        }

        return movies;
    }


    public List<Movie> fetchWatchlistMovies() {
        if (watchlistRepository == null) {
            return List.of();
        }

        try {
            return MovieEntityMapper.toMovies(
                    watchlistRepository.getAllWatchlistMovies().stream()
                            .map(WatchlistMovieEntity::getMovieEntity)
                            .filter(Objects::nonNull)
                            .toList()
            );
        } catch (DatabaseException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to load watchlist movies!");
            alert.setContentText("The application failed to access the local database for watchlist movies. Details: " + exception.getMessage());
            alert.show();
            exception.printStackTrace();
        }
        return List.of();
    }

    public boolean addMovieToWatchlist(Movie movie) {
        if (watchlistRepository == null) {
            return false;
        }

        try {
            WatchlistMovieEntity watchlistMovieEntity = watchlistRepository.fetchMovieEntity(movie.getId());
            if (watchlistMovieEntity != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Movie already added.");
                alert.setContentText("The movie '" + movie.getTitle() + "' is already in the watchlist.");
                alert.show();
                return false;
            }

            watchlistRepository.addMoviesToWatchlist(
                    new WatchlistMovieEntity(
                            MovieEntityMapper.fromMovies(List.of(movie)).get(0)
                    )
            );
            return true;
        } catch (DatabaseException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to add movie to watchlist!");
            alert.setContentText("Failed to add movie to watchlist. Details: " + exception.getMessage());
            alert.show();
            exception.printStackTrace();
        }
        return false;
    }

    public boolean removeMovieFromWatchlist(Movie movie) {
        if (watchlistRepository == null) {
            return false;
        }

        try {
            return watchlistRepository.deleteMovieFromWatchlist(movie.getId());
        } catch (DatabaseException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to add movie to watchlist!");
            alert.setContentText("Failed to add movie to watchlist. Details: " + exception.getMessage());
            alert.show();
            exception.printStackTrace();
        }
        return false;
    }

    private List<Movie> fetchMoviesFromApi() {
        List<Movie> movies = List.of();
        try {
            movies = movieApi.fetchMovies(new MovieRequestParameter());
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to load movie Data!");
            alert.setContentText("The application failed to retrieve data from the server. Check your network connection. Using local database instead. Details: " + exception.getMessage());
            alert.show();
        }
        return movies;
    }

    private List<Movie> updateMovieRepository(List<Movie> movies) {
        try {
            if (!movies.isEmpty()) {
                MovieEntityMapper.fromMovies(movies)
                        .forEach(movieRepository::addMovie);
            }

            movies = MovieEntityMapper.toMovies(movieRepository.getAllMovies());
        } catch (DatabaseException | NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to access MOVIES table!");
            alert.setContentText("The application failed to retrieve movies from the movie db." +
                    (!movies.isEmpty() ? " Using API only. " : " API and DB failed no movies available. ") +
                    "Details: " + exception.getMessage());
            alert.show();
        }
        return movies;
    }
}
