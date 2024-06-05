package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import okhttp3.HttpUrl;

public class MovieAPIRequestBuilder {
    private final HttpUrl.Builder urlBuilder;

    public MovieAPIRequestBuilder(String baseUrl) {
        this.urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
    }

    public MovieAPIRequestBuilder query(String query) {
        if (query != null && !query.isEmpty()) {
            urlBuilder.addQueryParameter("query", query);
        }
        return this;
    }

    public MovieAPIRequestBuilder genre(Genre genre) {
        if (genre != null && !genre.toString().isEmpty()) {
            urlBuilder.addQueryParameter("genre", genre.toString());
        }
        return this;
    }

    public MovieAPIRequestBuilder releaseYear(Integer releaseYear) {
        if (releaseYear != null && !releaseYear.toString().isEmpty()) {
            urlBuilder.addQueryParameter("releaseYear", releaseYear.toString());
        }
        return this;
    }

    public MovieAPIRequestBuilder ratingFrom(Double ratingFrom) {
        if (ratingFrom != null && !ratingFrom.toString().isEmpty()) {
            urlBuilder.addQueryParameter("ratingFrom", ratingFrom.toString());
        }
        return this;
    }

    public String build() {
        return urlBuilder.build().toString();
    }
}
