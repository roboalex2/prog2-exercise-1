package at.ac.fhcampuswien.fhmdb.dao;


import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.exception.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {

    private static DatabaseManager databaseInstance;
    private static final String DATA_BASE_URL = "jdbc:h2:~/movie_db";

    private ConnectionSource connectionSource;

    private DatabaseManager() {
        // Singleton
    }

    public static synchronized DatabaseManager getDatabaseInstance() {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseManager();
        }

        return databaseInstance;
    }

    public void createConnectionSource(String username, String password) throws DatabaseException {
        createConnectionSource(DATA_BASE_URL, username, password);
    }

    public void createConnectionSource(String dbUrl, String username, String password) throws DatabaseException {
        if (connectionSource != null) {
            throw new UnsupportedOperationException("ConnectionSource has already been created. Close before with closeConnectionSource()");
        }

        try {
            connectionSource = new JdbcConnectionSource(dbUrl, username, password);
        } catch (SQLException exception) {
            throw new DatabaseException("Failed to create database connection.", exception);
        }
    }

    public void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (Exception e) {
                // Ignore close failure
            }
            connectionSource = null;
        }
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public Dao<MovieEntity, UUID> getMovieRepositoryDao() throws DatabaseException {
        if (connectionSource == null) {
            throw new UnsupportedOperationException("ConnectionSource has not been created yet. Create with createConnectionSource(..)");
        }

        try {
            return DaoManager.createDao(connectionSource, MovieEntity.class);
        } catch (SQLException exception) {
            throw new DatabaseException("Failed to access MOVIES table.", exception);
        }
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistMovieEntity() throws DatabaseException {
        if (connectionSource == null) {
            throw new UnsupportedOperationException("ConnectionSource has not been created yet. Create with createConnectionSource(..)");
        }

        try {
            return DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException exception) {
            throw new DatabaseException("Failed to access WATCHLIST table.", exception);
        }
    }

    public void createTables() throws DatabaseException {
        if (connectionSource == null) {
            throw new UnsupportedOperationException("ConnectionSource has not been created yet. Create with createConnectionSource(..)");
        }

        try {
            TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException exception) {
            throw new DatabaseException("Failed to create tables.", exception);
        }
    }
}