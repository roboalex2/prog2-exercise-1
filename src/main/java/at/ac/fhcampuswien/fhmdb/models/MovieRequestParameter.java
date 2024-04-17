package at.ac.fhcampuswien.fhmdb.models;

public class MovieRequestParameter {
    private String query;
    private Genre genre;
    private Integer releaseYear;
    private Double ratingFrom;

    public MovieRequestParameter(String query, Genre genre, Integer releaseYear, Double ratingFrom) {
        this.query = query;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.ratingFrom = ratingFrom;
    }

    public MovieRequestParameter() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Double getRatingFrom() {
        return ratingFrom;
    }

    public void setRatingFrom(Double ratingFrom) {
        this.ratingFrom = ratingFrom;
    }
}
