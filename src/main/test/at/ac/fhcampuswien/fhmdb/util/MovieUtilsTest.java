package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieUtilsTest {

    @Test
    void testTitleSearchWithSameCase() {
        // Given
        String searchText = "man";
        Movie movie1 = new Movie("Ironman", "", List.of());
        Movie movie2 = new Movie("Avangers", "", List.of());
        Movie movie3 = new Movie("Spiederman", "", List.of());

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
        Movie movie1 = new Movie("IronMan", "", List.of());
        Movie movie2 = new Movie("Avangers", "", List.of());
        Movie movie3 = new Movie("Spiederman", "", List.of());

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
        Movie movie1 = new Movie("Ironman", "That is a movie.", List.of());
        Movie movie2 = new Movie("Avangers", "That is a duck.", List.of());
        Movie movie3 = new Movie("Spiederman", "That was a stone.", List.of());

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
        Movie movie1 = new Movie("Ironman", "That Is a movie.", List.of());
        Movie movie2 = new Movie("Avangers", "That IS a duck.", List.of());
        Movie movie3 = new Movie("Spiederman", "That was a stone.", List.of());

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
        Movie movie1 = new Movie("IronMan", "This is a movie.", List.of());
        Movie movie2 = new Movie("Avangers", "This is a MAN.", List.of());
        Movie movie3 = new Movie("Stranger Things", "eleven", List.of());
        Movie movie4 = new Movie("Spiederman", "This was a stone.", List.of());
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
        Movie movie1 = new Movie("IronMan", "This is a movie.", List.of());
        Movie movie2 = new Movie("Avangers", "This is a MAN.", List.of());
        Movie movie3 = new Movie("Stranger Things", "eleven", List.of());
        Movie movie4 = new Movie("Spiederman", "This was a stone.", List.of());
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
        Movie movie1 = new Movie("Ironman", "A", List.of());
        Movie movie2 = new Movie("Avangers", "B", List.of());
        Movie movie3 = new Movie("Spiederman", "C", List.of());

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
        Movie movie1 = new Movie("Ironman", "A", List.of());
        Movie movie2 = new Movie("Avangers", "B", List.of());
        Movie movie3 = new Movie("Spiederman", "C", List.of());

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
        Movie movie1 = new Movie("Ironman", "", List.of(Genre.ROMANCE, Genre.DOCUMENTARY));
        Movie movie2 = new Movie("Avangers", "", List.of(Genre.CRIME));
        Movie movie3 = new Movie("Spiederman", "", List.of(Genre.HISTORY, Genre.CRIME, Genre.SCIENCE_FICTION));
        Movie movie4 = new Movie("Waterman", "", List.of());

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