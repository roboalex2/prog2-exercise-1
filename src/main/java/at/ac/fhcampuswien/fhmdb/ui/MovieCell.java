package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.util.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {

    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final VBox movieInfo = new VBox(title, detail, genre);
    private final Button addToWatchlist = new Button("Watchlist");
    private final HBox layout = new HBox(movieInfo, addToWatchlist);

    public MovieCell(ClickEventHandler<Movie> addToWatchlistClicked) {
        super();
        addToWatchlist.setOnMouseClicked(mouseEvent -> {
            addToWatchlistClicked.onClick(getItem()); // Invoke the handler with the current item
        });
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            String genreText = movie.getGenres().stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(", "));
            genre.setText(genreText);

            // Color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // Layout settings
            title.fontProperty().set(title.getFont().font(20));
            movieInfo.setMinWidth(this.getScene().getWidth() - 140);
            movieInfo.setMaxWidth(movieInfo.getMinWidth());
            detail.setWrapText(true);
            movieInfo.setPadding(new Insets(10));
            movieInfo.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);


            HBox.setHgrow(movieInfo, Priority.ALWAYS);
            layout.setSpacing(10);
            layout.setPadding(new Insets(10));
            layout.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            addToWatchlist.setStyle("-fx-alignment: center-right;");
            setGraphic(layout);
        }
    }
}
