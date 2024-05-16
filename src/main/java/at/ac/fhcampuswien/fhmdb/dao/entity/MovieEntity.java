package at.ac.fhcampuswien.fhmdb.dao.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

@DatabaseTable(tableName = "movies")
public class MovieEntity {

    @DatabaseField(id = true, dataType = DataType.UUID)
    private UUID apiId;

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

    public MovieEntity(UUID apiId, String title, int year, String genres, String description) {
        this.apiId = apiId;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.description = description;
    }

    public UUID getApiId() {
        return apiId;
    }

    public void setApiId(UUID apiId) {
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
