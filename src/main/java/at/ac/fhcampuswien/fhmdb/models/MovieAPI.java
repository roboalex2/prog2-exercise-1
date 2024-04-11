package at.ac.fhcampuswien.fhmdb.models;

import okhttp3.HttpUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MovieAPI {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";

    public List<Movie> getMovies(String query, String genre) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        if (query != null && !query.isEmpty()) {
            urlBuilder.addQueryParameter("query", query);
        }
        if (genre != null && !genre.isEmpty()) {
            urlBuilder.addQueryParameter("genre", genre);
        }
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
