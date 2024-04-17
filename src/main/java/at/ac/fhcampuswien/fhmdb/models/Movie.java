package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;
import java.util.UUID;

public class Movie {

    private UUID id;
    private String title;
    private String description;
    private int releaseYear;
    private List<Genre> genres;
    private String imgUrl;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;
    private int lengthInMinutes;
    private double rating;

    public Movie() {
    }

    public Movie(UUID id, String title, String description, int releaseYear, List<Genre> genres, String imgUrl, List<String> directors, List<String> writers, List<String> mainCast, int lengthInMinutes, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.imgUrl = imgUrl;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public Movie(UUID id, String title, String description, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;

        if (!getId().equals(movie.getId())) return false;
        if (getTitle() != null ? !getTitle().equals(movie.getTitle()) : movie.getTitle() != null) return false;
        if (getDescription() != null ? !getDescription().equals(movie.getDescription()) : movie.getDescription() != null)
            return false;
        return getGenres().equals(movie.getGenres());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getGenres().hashCode();
        return result;
    }
}
