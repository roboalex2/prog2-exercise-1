package at.ac.fhcampuswien.fhmdb.dao;

import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BasicDatabaseTest {

    private static DatabaseManager databaseInstance;

    @BeforeAll
    public static void setUp() throws SQLException {
        databaseInstance = DatabaseManager.getDatabaseInstance();
        databaseInstance.createConnectionSource("jdbc:h2:~/movie_test_db", "moviedb", "movie123");
    }


    @Test
    public void quickAndDirtyDbTest() throws SQLException {
        databaseInstance.createTables();
        MovieRepository movieRepository = new MovieRepository();

        UUID movieId = UUID.randomUUID();

        movieRepository.addMovie(new MovieEntity(movieId, "Test Movie", 2010, "ACTION,HISTORY", "Test movie cool", "", 0, 0));

        List<MovieEntity> allMovies = movieRepository.getAllMovies();
        assert !allMovies.isEmpty();

        WatchlistRepository watchlistRepository = new WatchlistRepository();
        watchlistRepository.addMoviesToWatchlist(new WatchlistMovieEntity(allMovies.get(0)));

        assert !watchlistRepository.getAllWatchlistMovies().isEmpty();

        // h2 db does not know foreign key constraints
        movieRepository.deleteMovie(movieId);

        allMovies = movieRepository.getAllMovies();
        assert allMovies.isEmpty();

        watchlistRepository.deleteMovieFromWatchlist(movieId);

        assert watchlistRepository.getAllWatchlistMovies().isEmpty();
    }
}
