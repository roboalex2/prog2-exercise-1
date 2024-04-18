package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilsDirectorCountTest {

    private static final UUID MOVIE1_ID = UUID.randomUUID();
    private static final UUID MOVIE2_ID = UUID.randomUUID();
    private static final UUID MOVIE3_ID = UUID.randomUUID();

    @Test
    void testCountMoviesByDirector_withDirectorAtRandomPositionInList_shouldFindTwoMovies() {
        // Given
        String searchDirector = "Mark Yosh";

        Movie movie1 = buildMovie(MOVIE1_ID, "2001: A space odyssey", List.of("Stanley Kubrick", "Mark Yosh", "Karl"));
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", List.of("Mark Yosh"));
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", List.of("Jean-Jacques Annaud"));

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        long expectedMovieCount = 2;

        // When
        long actualMovieCount = MovieUtils.countMoviesFromDirector(givenMovies, searchDirector);

        // Then
        assertThat(actualMovieCount).isEqualTo(expectedMovieCount);
    }

    @Test
    void testCountMoviesByDirector_withDirectorAtBeginning_shouldFindOneMovie() {
        // Given
        String searchDirector = "Stanley Kubrick";

        Movie movie1 = buildMovie(MOVIE1_ID, "2001: A space odyssey", List.of("Stanley Kubrick", "Mark Yosh", "Karl"));
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", List.of("Mark Yosh"));
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", List.of("Jean-Jacques Annaud"));

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        long expectedMovieCount = 1;

        // When
        long actualMovieCount = MovieUtils.countMoviesFromDirector(givenMovies, searchDirector);

        // Then
        assertThat(actualMovieCount).isEqualTo(expectedMovieCount);
    }

    @Test
    void testCountMoviesByDirector_withDirectorAtEnd_shouldFindOneMovie() {
        // Given
        String searchDirector = "Karl";

        Movie movie1 = buildMovie(MOVIE1_ID, "2001: A space odyssey", List.of("Stanley Kubrick", "Mark Yosh", "Karl"));
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", List.of("Mark Yosh"));
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", List.of("Jean-Jacques Annaud"));

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        long expectedMovieCount = 1;

        // When
        long actualMovieCount = MovieUtils.countMoviesFromDirector(givenMovies, searchDirector);

        // Then
        assertThat(actualMovieCount).isEqualTo(expectedMovieCount);
    }

    @Test
    void testCountMoviesByDirector_withOnlyOneDirector_shouldFindOneMovie() {
        // Given
        String searchDirector = "Jean-Jacques Annaud";

        Movie movie1 = buildMovie(MOVIE1_ID, "2001: A space odyssey", List.of("Stanley Kubrick", "Mark Yosh", "Karl"));
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", List.of("Mark Yosh"));
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", List.of("Jean-Jacques Annaud"));

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        long expectedMovieCount = 1;

        // When
        long actualMovieCount = MovieUtils.countMoviesFromDirector(givenMovies, searchDirector);

        // Then
        assertThat(actualMovieCount).isEqualTo(expectedMovieCount);
    }

    @Test
    void testCountMoviesByDirector_withNoMoviesGiven_shouldFindNoMovie() {
        // Given
        String searchDirector = "Jean-Jacques Annaud";
        List<Movie> givenMovies = List.of();
        long expectedMovieCount = 0;

        // When
        long actualMovieCount = MovieUtils.countMoviesFromDirector(givenMovies, searchDirector);

        // Then
        assertThat(actualMovieCount).isEqualTo(expectedMovieCount);
    }

    @Test
    void testCountMoviesByDirector_withSearchingForUnknownDirector_shouldFindNoMovie() {
        // Given
        String searchDirector = "";

        Movie movie1 = buildMovie(MOVIE1_ID, "2001: A space odyssey", List.of("Stanley Kubrick", "Mark Yosh", "Karl"));
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", List.of("Mark Yosh"));
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", List.of("Jean-Jacques Annaud"));

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        long expectedMovieCount = 0;

        // When
        long actualMovieCount = MovieUtils.countMoviesFromDirector(givenMovies, searchDirector);

        // Then
        assertThat(actualMovieCount).isEqualTo(expectedMovieCount);
    }

    private Movie buildMovie(UUID id, String title, List<String> directors) {
        return new Movie(
                id,
                title,
                "",
                2000,
                List.of(),
                "",
                directors,
                List.of(),
                List.of(),
                120,
                9
        );
    }
}
