package at.ac.fhcampuswien.fhmdb.util;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortOrder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieUtils {

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

    // Filter for movies containing the specified genre
    public static List<Movie> filter(List<Movie> movies, Genre filterGenre) {
        return movies.stream()
                .filter(movie -> movie.getGenres().contains(filterGenre))
                .toList();
    }

    // Sorts given movies by the title
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

    /*
    String getMostPopularActor(List<Movie> movies): gibt jene Person zurueck, die am
    oeftesten im mainCast der übergebenen Filme vorkommt.
     */
    public static String getMostPopularActor(List<Movie> movies) {
        Map<String, Integer> actorCount = new HashMap<>();

        actorCount = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.toMap(
                        actor -> actor,
                        actor -> 1,
                        Integer::sum
                ));

        final String mostPopularActor = actorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No actors found");

        return mostPopularActor;
    }

    /*
    int getLongestMovieTitle(List<Movie> movies): filtert auf den längsten Titel der
    übergebenen Filme und gibt die Anzahl der Buchstaben des Titels zurück
     */
    public static int getLongestMovieTitle(List<Movie> movies) {
        Map<String, Integer> titleMap = movies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.toMap(title -> title, String::length));

        final int longestTitle = titleMap.values().stream()
                .max(Comparator.comparingInt(Integer::intValue))
                .orElse(0);

        return longestTitle;
    }

    /*
    long countMoviesFrom(List<Movie> movies, String director): gibt die Anzahl der
    Filme eines bestimmten Regisseurs zurück
     */
    public static long countMoviesFromDirector(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    /*
    List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int
    endYear): gibt jene Filme zurück, die zwischen zwei gegebenen Jahren veröffentlicht
    wurden.
     */
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }
}
