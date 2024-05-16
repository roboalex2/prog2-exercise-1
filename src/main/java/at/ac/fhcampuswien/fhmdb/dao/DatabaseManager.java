package at.ac.fhcampuswien.fhmdb.dao;


import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import at.ac.fhcampuswien.fhmdb.dao.entity.WatchlistMovieEntity;
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

    public void createConnectionSource(String username, String password) {
        try {
            connectionSource = new JdbcConnectionSource(DATA_BASE_URL, username, password);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing database connection!", e);
        }
    }

    public void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connectionSource = null;
        }
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public Dao<MovieEntity, UUID> getMovieRepositoryDao() throws SQLException {
        if (connectionSource == null) {
            throw new UnsupportedOperationException("ConnectionSource has not been created yet. Create with createConnectionSource(..)");
        }

        return DaoManager.createDao(connectionSource, MovieEntity.class);
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistMovieEntity() throws SQLException {
        if (connectionSource == null) {
            throw new UnsupportedOperationException("ConnectionSource has not been created yet. Create with createConnectionSource(..)");
        }

        return DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
    }

    public void createTables() throws SQLException {
        if (connectionSource == null) {
            throw new UnsupportedOperationException("ConnectionSource has not been created yet. Create with createConnectionSource(..)");
        }

        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }
}