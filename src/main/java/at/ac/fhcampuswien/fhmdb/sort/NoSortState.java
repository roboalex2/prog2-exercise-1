package at.ac.fhcampuswien.fhmdb.sort;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.List;

public class NoSortState implements SortState {
    @Override
    public String getButtonText() {
        return "Sort";
    }

    @Override
    public void next(HomeController controller) {
        controller.setSortState(new AscSortState());
    }

    @Override
    public List<Movie> sort(List<Movie> movies) {
        return movies.stream().toList();
    }
}
