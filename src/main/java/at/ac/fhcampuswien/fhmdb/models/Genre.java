package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    SPORT("Sport"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private static final List<String> allGenres;

    private final String value;

    static {
        allGenres = new ArrayList<>();
        for(Genre genres : Genre.values()) {
            allGenres.add(genres.value);
        }
    }

    Genre(String value) {
        this.value = value;
    }

    public String getGenre() {
        return value;
    }

    public static List<String> getValues() {
        return Collections.unmodifiableList(allGenres);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
