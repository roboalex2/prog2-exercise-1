package at.ac.fhcampuswien.fhmdb.api;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class MovieApi {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";

    /**
     * Retrieves a list of movies from the API based on provided search parameters.
     * @param parameters A map containing query parameters where the key is the parameter name
     *                   and the value is the parameter value.
     * @return A list of Movie objects.
     * @throws IOException if there is a problem executing the HTTP request.
     */
    public List<Movie> getMovies(Map<String, String> parameters) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        parameters.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                urlBuilder.addQueryParameter(key, value);
            }
        });

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Java FHMDb Client")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Type listOfMoviesType = new TypeToken<List<Movie>>(){}.getType();
            return gson.fromJson(response.body().string(), listOfMoviesType);
        }
    }
}
