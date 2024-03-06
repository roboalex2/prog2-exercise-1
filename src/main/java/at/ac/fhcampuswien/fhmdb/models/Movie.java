package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;
    // TODO add more properties here

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        return List.of(
                new Movie(
                        "Ironman",
                        "Some texA movie about Ironman",
                        List.of(Genre.ACTION, Genre.SCIENCE_FICTION)
                ),
                new Movie(
                        "Spiderman",
                        "Some text",
                        List.of(Genre.ACTION, Genre.SCIENCE_FICTION)
                ),
                new Movie(
                        "Schindlers-List",
                        "A historical movie showing how jews suffer at WW2",
                        List.of(Genre.DRAMA, Genre.DOCUMENTARY, Genre.HISTORY)
                ),
                new Movie(
                        "Avangers",
                        "Duds with powers and stuff",
                        List.of(Genre.ACTION, Genre.FAMILY)
                ),
                new Movie(
                        "Im Namen der Rose",
                        "A historical classic",
                        List.of(Genre.CRIME, Genre.HISTORY, Genre.MYSTERY)
                )
        );
    }
}
