package at.ac.fhcampuswien.fhmdb.dao;

import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.exception.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class WatchlistRepository {
    //fetch all Movie Entities
    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    public WatchlistRepository() throws SQLException {
        this.watchlistDao = DatabaseManager.getDatabaseInstance().getWatchlistMovieDao();
    }

    public List<WatchlistMovieEntity> getAllWatchlistMovies() {
        try {
            return watchlistDao.queryForAll();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public void addMoviesToWatchlist(WatchlistMovieEntity watchlistMovie) {
        try {
            watchlistDao.createOrUpdate(watchlistMovie);
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public void deleteMovieFromWatchlist(UUID apiId) {
        try {
            WatchlistMovieEntity watchlistMovieEntity = watchlistDao.queryBuilder()
                    .where()
                    .eq("apiId", apiId)
                    .queryForFirst();

            if (watchlistMovieEntity != null) {
                // Delete the WatchlistMovieEntity
                watchlistDao.delete(watchlistMovieEntity);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public void deleteAllWatchlistMovies() {
        try {
            watchlistDao.deleteBuilder().delete();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }
}
