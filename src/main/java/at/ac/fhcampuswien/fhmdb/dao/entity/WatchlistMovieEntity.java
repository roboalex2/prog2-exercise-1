package at.ac.fhcampuswien.fhmdb.dao.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "watchlist")
public class WatchlistMovieEntity {

    @DatabaseField(foreign = true, columnName = "apiId", foreignAutoRefresh = true, id = true)
    private MovieEntity movieEntity;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }
}
