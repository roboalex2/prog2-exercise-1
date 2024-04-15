package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.*;
import at.ac.fhcampuswien.fhmdb.util.TestUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieApiTest {

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private OkHttpClient client;

    @InjectMocks
    private MovieApi movieApi;

    @Test
    void fetchMovies_withParams_shouldUseParamsOnCall() throws IOException {
        // Given
        Request expectedRequest = buildRequest(Map.of("query", "lord"));
        MovieRequestParameter movieRequestParameter = new MovieRequestParameter("lord", null, null, null);

        // When
        Call mockCall = mock(Call.class);
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        when(mockCall.execute()).thenReturn(buildResponse(200,"[]", expectedRequest));
        when(client.newCall(requestCaptor.capture())).thenReturn(mockCall);

        movieApi.fetchMovies(movieRequestParameter);

        // Then
        Request actualRequest = requestCaptor.getValue();
        assertThat(actualRequest.url()).usingRecursiveComparison().isEqualTo(expectedRequest.url());
    }

    @Test
    void fetchMovies_withParams_shouldReturnExpectedMovies() throws IOException {
        // Given
        String responseText = TestUtils.fromFile("example/fetchMovieResponse-valid.json");
        Response mockResponse = buildResponse(200, responseText, buildRequest(Map.of("query", "lord")));

        MovieRequestParameter movieRequestParameter = new MovieRequestParameter("lord", null, null, null);
        List<Movie> expectedMovies = List.of(
            new Movie(
                UUID.fromString("a47afd8a-b768-4a34-8ed6-bf5d90c0feeb"),
                "The Lord of the Rings: The Return of the King",
                "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
                List.of(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY)
            ),
            new Movie(
                UUID.fromString("e109ec5f-c3af-472d-93e5-9c4c0d04d640"),
                "The Lord of the Rings: The Two Towers",
                "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron\"s new ally, Saruman, and his hordes of Isengard.",
                List.of(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY)
            )
        );

        // When
        Call mockCall = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        List<Movie> actualMovies = movieApi.fetchMovies(movieRequestParameter);

        // Then
        assertAll(
            () -> assertThat(actualMovies).hasSize(expectedMovies.size()),
            () -> assertThat(actualMovies).containsAll(expectedMovies)
        );
    }


    private Request buildRequest(Map<String, String> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(MovieApi.BASE_URL).newBuilder();
        params.forEach(urlBuilder::addQueryParameter);

        return new Request.Builder()
            .url(urlBuilder.build())
            .addHeader("User-Agent", "Java FHMDb Client")
            .build();
    }

    private Response buildResponse(int code, String bodyText, Request request) {
        return new Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_2)
            .code(code)
            .message(bodyText)
            .body(ResponseBody.create(bodyText.getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json")))
            .build();
    }
}