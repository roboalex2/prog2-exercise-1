package at.ac.fhcampuswien.fhmdb.sort;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;
import java.util.List;

public class AscSortState implements SortState {
    @Override
    public String getButtonText() {
        return "Sort (asc)";
    }

    @Override
    public void next(HomeController controller) {
        controller.setSortState(new DescSortState());
    }

    @Override
    public List<Movie> sort(List<Movie> movies) {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle))
                .toList();
    }
}
