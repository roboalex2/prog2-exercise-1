package at.ac.fhcampuswien.fhmdb.dao.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "movies")
public class MovieEntity {
    @DatabaseField(id = true)
    private long id;

    @DatabaseField(unique = true)
    private String apiId;

    @DatabaseField
    private String title;

    @DatabaseField
    private int year;

    @DatabaseField
    private String genres;

    @DatabaseField
    private String description;

    public MovieEntity() {
    }

    public MovieEntity(long id, String apiId, String title, int year, String genres, String description) {
        this.id = id;
        this.apiId = apiId;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
