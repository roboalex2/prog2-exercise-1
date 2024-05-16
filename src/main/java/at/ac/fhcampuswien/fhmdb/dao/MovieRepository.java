package at.ac.fhcampuswien.fhmdb.dao;

import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MovieRepository {
    private Dao<MovieEntity, UUID> movieDao;

    public MovieRepository() throws SQLException {
        this.movieDao = DatabaseManager.getDatabaseInstance().getMovieRepositoryDao();
    }

    public List<MovieEntity> getAllMovies() throws SQLException {
        return movieDao.queryForAll();
    }

    public void addMovie(MovieEntity movie) throws SQLException {
        movieDao.createOrUpdate(movie);
    }

    public void deleteMovie(UUID apiId) throws SQLException {
        movieDao.deleteById(apiId);
    }

    public void deleteAllMovies() throws SQLException {
        movieDao.deleteBuilder().delete();
    }
}
