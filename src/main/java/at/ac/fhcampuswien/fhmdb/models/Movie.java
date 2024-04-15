package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

public class Movie {
    private UUID id;
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(UUID id, String title, String description, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public Movie(String title, String description, List<Genre> genres) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public Movie() {
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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!Objects.equals(title, movie.title)) return false;
        if (!Objects.equals(description, movie.description)) return false;
        return Objects.equals(genres, movie.genres);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                '}';
    }
}
