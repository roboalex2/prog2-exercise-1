package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilsBetweenYearsTest {

    private static final UUID MOVIE1_ID = UUID.randomUUID();
    private static final UUID MOVIE2_ID = UUID.randomUUID();
    private static final UUID MOVIE3_ID = UUID.randomUUID();

    @Test
    void testFilterForMoviesBetweenYears_shouldFilterOutMoviesBeforeStartYear() {
        // Given
        Movie movie1 = buildMovie(MOVIE1_ID, "The Movie", 2000);
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", 2015);
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", 1970);

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie1,
                movie2
        );

        // When
        List<Movie> actualMovies = MovieUtils.getMoviesBetweenYears(givenMovies, 2000, 2100);

        // Then
        assertThat(actualMovies).containsExactlyElementsOf(expectedMovies);
    }

    @Test
    void testFilterForMoviesBetweenYears_shouldFilterOutMoviesAfterEndYear() {
        // Given
        Movie movie1 = buildMovie(MOVIE1_ID, "The Movie", 2000);
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", 2015);
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", 1970);

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie3
        );

        // When
        List<Movie> actualMovies = MovieUtils.getMoviesBetweenYears(givenMovies, 1960, 1999);

        // Then
        assertThat(actualMovies).containsExactlyElementsOf(expectedMovies);
    }

    @Test
    void testFilterForMoviesBetweenYears_shouldOnlyKeepMovieWithinGivenRange() {
        // Given
        Movie movie1 = buildMovie(MOVIE1_ID, "The Movie", 2000);
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", 2015);
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", 1970);

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie1
        );

        // When
        List<Movie> actualMovies = MovieUtils.getMoviesBetweenYears(givenMovies, 1990, 2010);

        // Then
        assertThat(actualMovies).containsExactlyElementsOf(expectedMovies);
    }

    @Test
    void testFilterForMoviesBetweenYears_withOverlappingInvalidRange_shouldNotFindAnyMovies() {
        // Given
        Movie movie1 = buildMovie(MOVIE1_ID, "The Movie", 2000);
        Movie movie2 = buildMovie(MOVIE2_ID, "Avangers", 2015);
        Movie movie3 = buildMovie(MOVIE3_ID, "The Name of the Rose", 1970);

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );

        List<Movie> expectedMovies = List.of();

        // When
        List<Movie> actualMovies = MovieUtils.getMoviesBetweenYears(givenMovies, 2100, 1900);

        // Then
        assertThat(actualMovies).containsExactlyElementsOf(expectedMovies);
    }

    @Test
    void testFilterForMoviesBetweenYears_withNoMoviesGiven_shouldNotFindAnyMovies() {
        // Given
        List<Movie> givenMovies = List.of();
        List<Movie> expectedMovies = List.of();

        // When
        List<Movie> actualMovies = MovieUtils.getMoviesBetweenYears(givenMovies, 2100, 1900);

        // Then
        assertThat(actualMovies).containsExactlyElementsOf(expectedMovies);
    }

    private Movie buildMovie(UUID id, String title, int releaseYear) {
        return new Movie(
                id,
                title,
                "",
                releaseYear,
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
