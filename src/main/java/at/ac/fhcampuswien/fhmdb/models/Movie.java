package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

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

    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
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
}
