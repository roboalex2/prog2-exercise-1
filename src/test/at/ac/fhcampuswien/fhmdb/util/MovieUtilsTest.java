package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieUtilsTest {

    private static final UUID MOVIE1_ID = UUID.randomUUID();
    private static final UUID MOVIE2_ID = UUID.randomUUID();
    private static final UUID MOVIE3_ID = UUID.randomUUID();
    private static final UUID MOVIE4_ID = UUID.randomUUID();

    @Test
    void testTitleSearchWithSameCase() {
        // Given
        String searchText = "man";
        Movie movie1 = new Movie(MOVIE1_ID, "Ironman", "", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "", List.of());

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie1,
                movie3
        );

        // When
        List<Movie> actualMovies = MovieUtils.search(givenMovies, searchText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testTitleSearchWithMixedCase() {
        // Given
        String searchText = "maN";
        Movie movie1 = new Movie(MOVIE1_ID, "IronMan", "", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "", List.of());

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie1,
                movie3
        );

        // When
        List<Movie> actualMovies = MovieUtils.search(givenMovies, searchText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testDescriptionSearchWithSameCase() {
        // Given
        String searchText = "is";
        Movie movie1 = new Movie(MOVIE1_ID, "Ironman", "That is a movie.", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "That is a duck.", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "That was a stone.", List.of());

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
        List<Movie> actualMovies = MovieUtils.search(givenMovies, searchText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testDescriptionSearchWithMixedCase() {
        // Given
        String searchText = "is";
        Movie movie1 = new Movie(MOVIE1_ID, "Ironman", "That Is a movie.", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "That IS a duck.", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "That was a stone.", List.of());

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
        List<Movie> actualMovies = MovieUtils.search(givenMovies, searchText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testDescriptionAndTitleSearch() {
        // Given
        String searchText = "man";
        Movie movie1 = new Movie(MOVIE1_ID, "IronMan", "This is a movie.", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "This is a MAN.", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Stranger Things", "eleven", List.of());
        Movie movie4 = new Movie(MOVIE4_ID, "Spiederman", "This was a stone.", List.of());
        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3,
                movie4
        );
        List<Movie> expectedMovies = List.of(
                movie1,
                movie2,
                movie4
        );

        // When
        List<Movie> actualMovies = MovieUtils.search(givenMovies, searchText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testDescriptionAndTitelSearchNonFound() {
        // Given
        String searchText = "OMEGA";
        Movie movie1 = new Movie(MOVIE1_ID, "IronMan", "This is a movie.", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "This is a MAN.", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Stranger Things", "eleven", List.of());
        Movie movie4 = new Movie(MOVIE4_ID, "Spiederman", "This was a stone.", List.of());
        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3,
                movie4
        );
        List<Movie> expectedMovies = List.of();

        // When
        List<Movie> actualMovies = MovieUtils.search(givenMovies, searchText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testTitleSortAscending() {
        // Given
        Movie movie1 = new Movie(MOVIE1_ID, "Ironman", "A", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "B", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "C", List.of());

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie2,
                movie1,
                movie3
        );

        // When
        List<Movie> actualMovies = MovieUtils.sort(givenMovies, SortOrder.ASCENDING);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testTitleSortDescending() {
        // Given
        Movie movie1 = new Movie(MOVIE1_ID, "Ironman", "A", List.of());
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "B", List.of());
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "C", List.of());

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3
        );
        List<Movie> expectedMovies = List.of(
                movie3,
                movie1,
                movie2
        );

        // When
        List<Movie> actualMovies = MovieUtils.sort(givenMovies, SortOrder.DESCENDING);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    void testFilterForGenre() {
        // Given
        Genre filterGenre = Genre.CRIME;
        Movie movie1 = new Movie(MOVIE1_ID, "Ironman", "", List.of(Genre.ROMANCE, Genre.DOCUMENTARY));
        Movie movie2 = new Movie(MOVIE2_ID, "Avangers", "", List.of(Genre.CRIME));
        Movie movie3 = new Movie(MOVIE3_ID, "Spiederman", "", List.of(Genre.HISTORY, Genre.CRIME, Genre.SCIENCE_FICTION));
        Movie movie4 = new Movie(MOVIE4_ID, "Waterman", "", List.of());

        List<Movie> givenMovies = List.of(
                movie1,
                movie2,
                movie3,
                movie4
        );
        List<Movie> expectedMovies = List.of(
                movie2,
                movie3
        );

        // When
        List<Movie> actualMovies = MovieUtils.filter(givenMovies, filterGenre);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }
}