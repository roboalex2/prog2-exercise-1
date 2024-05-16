package at.ac.fhcampuswien.fhmdb.dao;

import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    //fetch all Movie Entities
    private Dao<WatchlistMovieEntity, Integer> watchlistDao;

    public WatchlistRepository() {
        try {
            ConnectionSource connectionSource = DatabaseManager.getDbInstance().getConnectionSource();
            this.watchlistDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing WatchlistRepository", e);
        }
    }

    public List<WatchlistMovieEntity> getAllWatchlistMovies() throws SQLException {
        return watchlistDao.queryForAll();
    }

    public void addMoviesToWatchlist(WatchlistMovieEntity watchlistMovie) throws SQLException {
        watchlistDao.createOrUpdate(watchlistMovie);
    }

    public void deleteMovieFromWatchlist(int apiId) throws SQLException {
        watchlistDao.deleteById(apiId);
    }

    public void deleteAllWatchlistMovies() throws SQLException {
        watchlistDao.delete(watchlistDao.queryForAll());
    }
}
