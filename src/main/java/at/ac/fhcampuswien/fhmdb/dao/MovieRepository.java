package at.ac.fhcampuswien.fhmdb.dao;

import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import at.ac.fhcampuswien.fhmdb.exception.DatabaseException;
import at.ac.fhcampuswien.fhmdb.manager.DatabaseManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MovieRepository {
    private Dao<MovieEntity, UUID> movieDao;

    public MovieRepository() throws SQLException {
        this.movieDao = DatabaseManager.getDatabaseInstance().getMovieRepositoryDao();
    }

    public List<MovieEntity> getAllMovies() {
        try {
            return movieDao.queryForAll();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public void addMovie(MovieEntity movie) {
        try {
            movieDao.createOrUpdate(movie);
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public void deleteMovie(UUID apiId) {
        try {
            movieDao.deleteById(apiId);
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public void deleteAllMovies() {
        try {
            movieDao.deleteBuilder().delete();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }
}
