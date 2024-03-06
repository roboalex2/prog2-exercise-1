package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void testSearch() {
        // Given
        String searchText = "man";
        List<String> expectedMovieTitles = List.of(
                "Ironman",
                "Spiderman"
        );

        // When
        List<Movie> actualMovies = new HomeController().search(searchText);

        // Then
        assertEquals(expectedMovieTitles.size(), actualMovies.size());
        for (int i = 0; i < actualMovies.size(); i++) {
            assertEquals(expectedMovieTitles.get(i), actualMovies.get(i).getTitle());
        }
    }

    @Test
    void testFilter() {
        // Given
        String filterText = "Action";
        List<Movie> expectedMovies = List.of();

        // When
        List<Movie> actualMovies = new HomeController().filter(filterText);

        // Then
        assertEquals(expectedMovies, actualMovies);
    }


    @Test
    void testSort() {
        // Given
        String sortButtonInput = "Sort (asc)";
        HomeController homeController = new HomeController();

        // When
        List<Movie> actualMovies = homeController.sort(sortButtonInput);

        // Then
        List<Movie> expectedMoviesAsc = new ArrayList<>(homeController.allMovies);
        expectedMoviesAsc.sort(Comparator.comparing(Movie::getTitle));
        assertEquals(expectedMoviesAsc, actualMovies);

        // Given
        sortButtonInput = "Sort (desc)";

        // When
        actualMovies = homeController.sort(sortButtonInput);

        // Then
        List<Movie> expectedMoviesDesc = new ArrayList<>(homeController.allMovies);
        expectedMoviesDesc.sort(Comparator.comparing(Movie::getTitle).reversed());
        assertEquals(expectedMoviesDesc, actualMovies);
    }


}