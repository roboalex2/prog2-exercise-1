package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilsLongestTitleTest {

    @Test
    void testLongestTitle_withMovies_shouldFindLongest() {
        // Given
        long expectedLongestTitleLength = 21;

        Movie movie1 = buildMovie("2001: A space odyssey");
        Movie movie2 = buildMovie("Avangers");
        Movie movie3 = buildMovie("The Name of the Rose");

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );

        // When
        long actualLongestTitleLength = MovieUtils.getLongestMovieTitle(givenMovies);

        // Then
        assertThat(actualLongestTitleLength).isEqualTo(expectedLongestTitleLength);
    }

    @Test
    void testLongestTitle_withNoMovies_shouldFindZero() {
        // Given
        long expectedLongestTitleLength = 0;

        List<Movie> givenMovies = List.of();

        // When
        long actualLongestTitleLength = MovieUtils.getLongestMovieTitle(givenMovies);

        // Then
        assertThat(actualLongestTitleLength).isEqualTo(expectedLongestTitleLength);
    }

    @Test
    void testLongestTitle_withMovieWithEmptyTitle_shouldFindZero() {
        // Given
        long expectedLongestTitleLength = 0;

        Movie movie1 = buildMovie("");
        List<Movie> givenMovies = List.of(
                movie1
        );

        // When
        long actualLongestTitleLength = MovieUtils.getLongestMovieTitle(givenMovies);

        // Then
        assertThat(actualLongestTitleLength).isEqualTo(expectedLongestTitleLength);
    }

    @Test
    void testLongestTitle_withMovieWithNullTitle_shouldFindZero() {
        // Given
        long expectedLongestTitleLength = 0;

        Movie movie1 = buildMovie(null);
        List<Movie> givenMovies = List.of(
                movie1
        );

        // When
        long actualLongestTitleLength = MovieUtils.getLongestMovieTitle(givenMovies);

        // Then
        assertThat(actualLongestTitleLength).isEqualTo(expectedLongestTitleLength);
    }

    private Movie buildMovie(String title) {
        return new Movie(
                UUID.randomUUID(),
                title,
                "",
                2000,
                List.of(),
                "",
                List.of(),
                List.of(),
                List.of(),
                120,
                9
        );
    }
}
