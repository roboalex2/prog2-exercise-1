package at.ac.fhcampuswien.fhmdb.dao;

import at.ac.fhcampuswien.fhmdb.util.Observable;
import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.exception.DatabaseException;
import at.ac.fhcampuswien.fhmdb.manager.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.util.Observer;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class WatchlistRepository implements Observable {

    private static WatchlistRepository instance;
    //fetch all Movie Entities
    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    private List<Observer> observers = new ArrayList<>(); // new line
    public static synchronized WatchlistRepository getInstance() throws SQLException{
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    private WatchlistRepository() throws SQLException {
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
            if (fetchMovieEntity(watchlistMovie.getMovieEntity().getApiId()) == null) {
                watchlistDao.createOrUpdate(watchlistMovie);
                notifyObservers("Movie " + watchlistMovie.getMovieEntity().getTitle() + " Added to Watchlist");
            } else {
                notifyObservers("Movie "  + watchlistMovie.getMovieEntity().getTitle() + " Already in Watchlist");
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public WatchlistMovieEntity fetchMovieEntity(UUID apiId) {
        try {
            return watchlistDao.queryBuilder()
                    .where()
                    .eq("apiId", apiId)
                    .queryForFirst();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }

    public boolean deleteMovieFromWatchlist(UUID apiId) {
        try {
            WatchlistMovieEntity watchlistMovieEntity = watchlistDao.queryBuilder()
                    .where()
                    .eq("apiId", apiId)
                    .queryForFirst();

            if (watchlistMovieEntity != null) {
                // Delete the WatchlistMovieEntity
                watchlistDao.delete(watchlistMovieEntity);
                notifyObservers("Movie "  + watchlistMovieEntity.getMovieEntity().getTitle() + "  removed from watchlist");
                return true;
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
        notifyObservers("Movie not found in watchlist"); // new line
        return false;
    }

    public void deleteAllWatchlistMovies() {
        try {
            watchlistDao.deleteBuilder().delete();
        } catch (SQLException exception) {
            throw new DatabaseException(exception);
        }
    }
    @Override
    public void addObserver(Observer observer) { // new method
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) { // new method
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) { // new method
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
