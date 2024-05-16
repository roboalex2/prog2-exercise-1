package at.ac.fhcampuswien.fhmdb.mapper;

import at.ac.fhcampuswien.fhmdb.dao.entity.MovieEntity;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.h2.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieEntityMapper {

    public static List<MovieEntity> fromMovies(List<Movie> movies) {
        return movies.stream()
            .map(movie -> new MovieEntity(
                movie.getId().toString(),
                movie.getTitle(),
                movie.getReleaseYear(),
                genresToString(movie.getGenres()),
                movie.getDescription())
            )
            .toList();
    }

    public static List<Movie> toMovies(List<MovieEntity> movies) {
        return movies.stream()
            .map(movie -> new Movie(
                UUID.fromString(movie.getApiId()),
                movie.getTitle(),
                movie.getDescription(),
                movie.getYear(),
                stringToGenres(movie.getGenres()),
                null,
                List.of(),
                List.of(),
                List.of(),
                -1,
                -1d)
            )
            .toList();
    }

    private static String genresToString(List<Genre> genres) {
        if (genres == null) {
            return null;
        }

        return genres.stream()
            .map(Objects::toString)
            .collect(Collectors.joining(","));
    }

    private static List<Genre> stringToGenres(String genreString) {
        if (StringUtils.isNullOrEmpty(genreString)) {
            return null;
        }

        return Stream.of(genreString.split(","))
            .map(Genre::valueOf)
            .toList();
    }
}
