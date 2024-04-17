package at.ac.fhcampuswien.fhmdb.models;

import java.util.List;
import java.util.Objects;

public class Movie {

    private String id;
    private String title;
    private List<String> genres;
    private int releaseYear;
    private String description;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List<String> writers;
    private List<String> mainCast;
    private double rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;
        return releaseYear == movie.releaseYear && lengthInMinutes == movie.lengthInMinutes && Double.compare(rating, movie.rating) == 0 && Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(genres, movie.genres) && Objects.equals(description, movie.description) && Objects.equals(imgUrl, movie.imgUrl) && Objects.equals(directors, movie.directors) && Objects.equals(writers, movie.writers) && Objects.equals(mainCast, movie.mainCast);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genres, releaseYear, description, imgUrl, lengthInMinutes, directors, writers, mainCast, rating);
    }

    public Movie(String id, String title, List<String> genres, int releaseYear, String description, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.description = description;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public Movie(String title, String description, List<String> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public String getId() {
        return id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public double getRating() {
        return rating;
    }
}
