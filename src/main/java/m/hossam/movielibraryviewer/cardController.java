package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    try {
        String imgSrc = movie.getImgsrc();
        if (imgSrc != null) {
            Image image = new Image(getClass().getResourceAsStream(imgSrc));
            moviePoster.setImage(image);
        }else {
            // Set a default image or handle the case when imgSrc is null.
            Image defaultImage = new Image(getClass().getResourceAsStream("img/untit.png"));
             moviePoster.setImage(defaultImage);
        }

    } catch (Exception e) {
        // Handle the exception, for example, log the error or set a default image.
        System.out.print(e);
    }
        movieName.setText(movie.getName());
        movieGenre.setText(movie.getGenre());
        movieRating.setText(movie.getRating());
        box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";"
                + "-fx-background-radius: 10;" + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1),10,0,0,10);");



    }
}
