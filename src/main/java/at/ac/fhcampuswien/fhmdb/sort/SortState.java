package at.ac.fhcampuswien.fhmdb.sort;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public interface SortState {

    String getButtonText();
    void next(HomeController controller);
    List<Movie> sort(List<Movie> movies);
}
