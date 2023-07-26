package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class movieCardController {
    @FXML
    private ImageView moviePoster2;

    public void setData(Movie movie) {
        try {
            Image image = new Image(getClass().getResourceAsStream(movie.getImgsrc()));
            moviePoster2.setImage(image);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
