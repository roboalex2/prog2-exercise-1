package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.exception.HttpStatusException;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import at.ac.fhcampuswien.fhmdb.models.MovieRequestParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieApi {

    public static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public MovieApi() {
        this(new OkHttpClient(), new ObjectMapper());
    }

    public MovieApi(OkHttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Movie> fetchMovies(MovieRequestParameter movieRequestParameter) throws IOException {
        movieRequestParameter = Optional.ofNullable(movieRequestParameter)
            .orElse(new MovieRequestParameter());

        Request request = new Request.Builder()
            .url(buildMovieUrl(movieRequestParameter))
            .addHeader("User-Agent", "Java FHMDb Client")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new HttpStatusException(response.code(), "Failed to fetchMovies, got: " + response);
            }

            return parseToMovies(response.body().string());
        }
    }

    private List<Movie> parseToMovies(String jsonArray) throws JsonProcessingException {
        return objectMapper.readValue(jsonArray, new TypeReference<List<Movie>>(){});
    }

    private HttpUrl buildMovieUrl(MovieRequestParameter params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();

        if (!isNullOrEmpty(params.getQuery())) {
            urlBuilder.addQueryParameter("query", params.getQuery());
        }
        if (!isNullOrEmpty(params.getGenre())) {
            urlBuilder.addQueryParameter("genre", params.getGenre().toString());
        }
        if (!isNullOrEmpty(params.getRatingFrom())) {
            urlBuilder.addQueryParameter("ratingFrom", params.getRatingFrom().toString());
        }
        if (!isNullOrEmpty(params.getReleaseYear())) {
            urlBuilder.addQueryParameter("releaseYear", params.getReleaseYear().toString());
        }

        return urlBuilder.build();
    }

    private <T> boolean isNullOrEmpty(T element) {
        if (element == null) {
            return true;
        }

        return element.toString().isBlank();
    }
}
