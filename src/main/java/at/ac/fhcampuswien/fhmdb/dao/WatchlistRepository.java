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

    public WatchlistRepository() throws DatabaseException {
        this.watchlistDao = DatabaseManager.getDatabaseInstance().getWatchlistMovieDao();
    }

    public List<WatchlistMovieEntity> getAllWatchlistMovies() throws SQLException {
        return watchlistDao.queryForAll();
    }

    public void addMoviesToWatchlist(WatchlistMovieEntity watchlistMovie) throws SQLException {
        watchlistDao.createOrUpdate(watchlistMovie);
    }

    public void deleteMovieFromWatchlist(UUID apiId) throws SQLException {
        WatchlistMovieEntity watchlistMovieEntity = watchlistDao.queryBuilder()
            .where()
            .eq("apiId", apiId)
            .queryForFirst();

        if (watchlistMovieEntity != null) {
            // Delete the WatchlistMovieEntity
            watchlistDao.delete(watchlistMovieEntity);
        }
    }

    public void deleteAllWatchlistMovies() throws SQLException {
        watchlistDao.deleteBuilder().delete();
    }
}
