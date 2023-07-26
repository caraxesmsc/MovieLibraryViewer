package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Movie;

public class cardController{
    @FXML
    private HBox box;

    @FXML
    private Label movieGenre;

    @FXML
    private Label movieName;

    @FXML
    private ImageView moviePoster;

    @FXML
    private Label movieRating;
    private String[] colors = {"6A1B9A","8E24AA","8E24AA"};
    public void setData(Movie movie){
        Image image = new Image(getClass().getResourceAsStream(movie.getImgsrc()));
        moviePoster.setImage(image);
        movieName.setText(movie.getName());
        movieGenre.setText(movie.getGenre());
        movieRating.setText(movie.getRating());
        box.setStyle("-fx-background-color: "+ Color.web(colors[(int)Math.random()*colors.length]));
    }
}
