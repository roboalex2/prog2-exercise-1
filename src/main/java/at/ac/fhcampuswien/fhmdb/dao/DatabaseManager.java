package at.ac.fhcampuswien.fhmdb.dao;


import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DatabaseManager {

    //startpoint is setDbInstance(String url)

    private static DatabaseManager databaseInstance;
    private final String DATA_BASE_URL;
    private String username;
    private String password;

    private ConnectionSource connectionSource;

    private DatabaseManager(String DATA_BASE_URL, String username, String password) {
        //String databaseUrl = "jdbc:h2:mem:myDb";
        this.DATA_BASE_URL = DATA_BASE_URL;
        this.username = username;
        this.password = password;

        createConnectionSource();
    }

    public static synchronized void setDbInstance(String DATA_BASE_URL, String username, String password) {
        if (databaseInstance == null)
            databaseInstance = new DatabaseManager(DATA_BASE_URL, username, password);
    }

    public static DatabaseManager getDatabaseInstance() {
        if(databaseInstance == null)
            throw new UnsupportedOperationException("The Instance has not been created yet.");
        return databaseInstance;
    }



    private void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(this.DATA_BASE_URL, this.username, this.password);
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
        }
    }
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public Dao<MovieEntity, Integer> getMovieRepositoryDao() throws SQLException {
        return DaoManager.createDao(connectionSource, MovieEntity.class);
    }

    public Dao<WatchlistRepository, Integer> getWatchlistRepositoryDao() throws SQLException {
        return DaoManager.createDao(connectionSource, WatchlistRepository.class);
    }
}