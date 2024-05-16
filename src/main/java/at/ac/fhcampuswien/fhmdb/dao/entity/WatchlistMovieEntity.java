package at.ac.fhcampuswien.fhmdb.dao.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "watchlist")
public class WatchlistMovieEntity {

    @DatabaseField(id = true)
    private long id;

    @DatabaseField(foreign = true, columnName = "apiId", foreignAutoRefresh = true)
    private MovieEntity movieEntity;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(long id, MovieEntity movieEntity) {
        this.id = id;
        this.movieEntity = movieEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }
}
