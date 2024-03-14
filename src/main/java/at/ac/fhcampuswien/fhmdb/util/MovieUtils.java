package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortOrder;

import java.util.Comparator;
import java.util.List;

public class MovieUtils {

    public static List<Movie> initializeMovies() {
        return List.of(
                new Movie(
                        "Wall-E",
                        "A trash compactor robot goes to space because of a plant. " +
                                "Gives space humans hope for a feature on earth and makes them resettle on a " +
                                "desolate wasteland of a world, where they inevitably " +
                                "suffer and die.",
                        List.of(Genre.ADVENTURE, Genre.ANIMATION, Genre.SCIENCE_FICTION, Genre.FAMILY)
                ),
                new Movie(
                        "Ironman",
                        "A billionaire who has to fully clad himself in iron to protect himself from the harsh reality he lives in.",
                        List.of(Genre.ACTION, Genre.SCIENCE_FICTION)
                ),
                new Movie(
                        "Spiderman",
                        "About a guy who is probably not disgusted by spiders.",
                        List.of(Genre.ACTION, Genre.SCIENCE_FICTION)
                ),
                new Movie(
                        "Schindlers-List",
                        "A historical movie showing an adventure capitalist failing to make money.",
                        List.of(Genre.DRAMA, Genre.DOCUMENTARY, Genre.HISTORY)
                ),
                new Movie(
                        "Avengers",
                        "Dudes with powers and stuff",
                        List.of(Genre.ACTION, Genre.FAMILY)
                ),
                new Movie(
                        "The Name of the Rose",
                        "Doubt is the enemy of faith.",
                        List.of(Genre.CRIME, Genre.HISTORY, Genre.MYSTERY)
                )
        );
    }

    // Filters movies to a new list when the title or description contains a case-insensitive search text.
    public static List<Movie> search(List<Movie> movies, String searchText) {
        return movies.stream()
                .filter(movie -> {
                    String lowerCaseMovieTitle = movie.getTitle().toLowerCase();
                    String lowerCaseMovieDescription = movie.getDescription().toLowerCase();
                    String lowerCaseSearchText = searchText.toLowerCase();

                    // Filter lets movie through if true
                    return lowerCaseMovieTitle.contains(lowerCaseSearchText) ||
                            lowerCaseMovieDescription.contains(lowerCaseSearchText);
                })
                .toList();
    }

    public static List<Movie> filter(List<Movie> movies, Genre filterGenre) {
        return movies.stream()
                .filter(movie -> movie.getGenres().contains(filterGenre))
                .toList();
    }

    public static List<Movie> sort(List<Movie> movies, SortOrder sortOrder) {
        if (SortOrder.ASCENDING.equals(sortOrder)) {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getTitle))
                    .toList();
        } else {
            return movies.stream()
                    .sorted(Comparator.comparing(Movie::getTitle).reversed())
                    .toList();
        }
    }
}
