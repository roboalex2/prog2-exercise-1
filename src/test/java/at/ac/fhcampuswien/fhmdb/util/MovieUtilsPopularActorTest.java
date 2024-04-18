package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilsPopularActorTest {

    @Test
    void testGetMostPopularActor_withFewMoviesGiven_shouldFindMostPopularActor() {
        // Given
        String expectedMostPopularActor = "Actor2";

        List<Movie> givenMovies = List.of(
                buildMovie("2001: A space odyssey", List.of("Actor1", "Actor2", "Actor3")),
                buildMovie("Avangers", List.of("Actor2")),
                buildMovie("The Name of the Rose", List.of("Actor2", "Actor3")),
                buildMovie("Movie", List.of("Actor3", "Actor1"))
        );

        // When
        String actualMostPopularActor = MovieUtils.getMostPopularActor(givenMovies);

        // Then
        assertThat(actualMostPopularActor).isEqualTo(expectedMostPopularActor);
    }

    @Test
    void testGetMostPopularActor_withMovieHasDuplicateActor_shouldFindMostPopularActorCountingDuplicatesOnlyOnce() {
        // Given
        String expectedMostPopularActor = "Actor2";

        List<Movie> givenMovies = List.of(
                buildMovie("2001: A space odyssey", List.of("Actor1", "Actor2", "Actor3")),
                buildMovie("Avangers", List.of("Actor2")),
                buildMovie("The Name of the Rose", List.of("Actor2", "Actor3")),
                buildMovie("Movie", List.of("Actor3", "Actor1", "Actor1", "Actor1", "Actor1"))
        );

        // When
        String actualMostPopularActor = MovieUtils.getMostPopularActor(givenMovies);

        // Then
        assertThat(actualMostPopularActor).isEqualTo(expectedMostPopularActor);
    }

    @Test
    void testGetMostPopularActor_withNoMoviesGiven_shouldFindNull() {
        // Given
        String expectedMostPopularActor = null;

        List<Movie> givenMovies = List.of();

        // When
        String actualMostPopularActor = MovieUtils.getMostPopularActor(givenMovies);

        // Then
        assertThat(actualMostPopularActor).isEqualTo(expectedMostPopularActor);
    }

    @Test
    void testGetMostPopularActor_withActorSameAmountOfAppearance_shouldFindDeterministicRandomActor() {
        // Given
        String expectedMostPopularActor = "Actor1";

        List<Movie> givenMovies = List.of(
                buildMovie("2001: A space odyssey", List.of("Actor1", "Actor2")),
                buildMovie("The Name of the Rose", List.of("Actor2", "Actor3")),
                buildMovie("Movie", List.of("Actor3", "Actor1"))
        );

        // When
        String actualMostPopularActor = MovieUtils.getMostPopularActor(givenMovies);

        // Then
        assertThat(actualMostPopularActor).isEqualTo(expectedMostPopularActor);
    }

    private Movie buildMovie(String title, List<String> mainCast) {
        return new Movie(
                UUID.randomUUID(),
                title,
                "",
                2000,
                List.of(),
                "",
                List.of(),
                List.of(),
                mainCast,
                120,
                9
        );
    }
}
