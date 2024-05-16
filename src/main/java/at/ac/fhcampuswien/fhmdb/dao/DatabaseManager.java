package at.ac.fhcampuswien.fhmdb.dao;


import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DatabaseManager {

    //startpoint is setDbInstance(String url)

    private static DatabaseManager dbInstance;
    private static MovieRepository mrInstance;
    private static WatchlistRepository wrInstance;

    private ConnectionSource connectionSource;

    private DatabaseManager(String databaseUrl, String username, String password) {
        //String databaseUrl = "jdbc:h2:mem:myDb";
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl, username, password);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing database connection!", e);
        }
    }

    public static DatabaseManager getDbInstance() {
        return dbInstance;
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


    //getter / setter
    public static synchronized void setDbInstance(String databaseUrl, String username, String password) {
        if (dbInstance == null)
            dbInstance = new DatabaseManager(databaseUrl,username, password);
    }

    public static MovieRepository getMrInstance() {
        return mrInstance;
    }

    public static void setMrInstance() {
        if(mrInstance == null)
            mrInstance = new MovieRepository();
    }

    public static WatchlistRepository getWrInstance() {
        return wrInstance;
    }

    public static void setWrInstance() {
        if(wrInstance == null)
            wrInstance = new WatchlistRepository();
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}