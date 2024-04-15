package at.ac.fhcampuswien.fhmdb.models;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieAnalytics {

    public static String getMostPopularActor(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getGenres().stream())  // Assuming each movie has a list of Genre
                .map(Enum::name)  // This line replaces getName with name
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .map(Movie::getTitle)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    public static long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDescription().contains(director)) // Assuming Director info is in description
                .count();
    }

    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> {
                    int year = extractYear(movie.getDescription()); // Assuming year is part of the description
                    return year >= startYear && year <= endYear;
                })
                .collect(Collectors.toList());
    }

    private static int extractYear(String description) {
        // Dummy implementation
        return 2000;
    }
}
