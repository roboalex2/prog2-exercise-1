package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.exception.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieRequestParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

        String url = new MovieAPIRequestBuilder(BASE_URL)
                .query(movieRequestParameter.getQuery())
                .genre(movieRequestParameter.getGenre() != null ? movieRequestParameter.getGenre().toString() : null)
                .releaseYear(movieRequestParameter.getReleaseYear() != null ? movieRequestParameter.getReleaseYear().toString() : null)
                .ratingFrom(movieRequestParameter.getRatingFrom() != null ? movieRequestParameter.getRatingFrom().toString() : null)
                .build();

        Request request = new Request.Builder()
            .url(url)
            .addHeader("User-Agent", "Java FHMDb Client")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new MovieApiException(response.code(), "Failed to fetchMovies, got: " + response);
            }

            return parseToMovies(response.body().string());
        }
    }

    private List<Movie> parseToMovies(String jsonArray) throws JsonProcessingException {
        return objectMapper.readValue(jsonArray, new TypeReference<List<Movie>>(){});
    }
}
