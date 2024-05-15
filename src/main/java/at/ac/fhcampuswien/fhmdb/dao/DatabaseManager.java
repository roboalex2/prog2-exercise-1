package at.ac.fhcampuswien.fhmdb.dao;


import com.j256.ormlite.support.ConnectionSource;

public class DatabaseManager {
    private static DatabaseManager instance;
    private ConnectionSource connectionSource;
}