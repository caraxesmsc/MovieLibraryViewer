package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class movieCardController {
    @FXML
    private ImageView moviePoster2;

    public void setData(Movie movie) {
        try {
            Image image = new Image(toString(movie.getImgsrc()));
            moviePoster2.setImage(image);
        } catch (Exception e) {
            // Handle the exception gracefully
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
            // Optionally, you can set a default image or do other error handling here
            try {
                Image image = new Image(getClass().getResourceAsStream("untit.png"));
                moviePoster2.setImage(image);
            }catch (Exception e1) {
                System.out.println("Error loading default image");
            }

        }
    }

    private String toString(String imgsrc) {
        return String.valueOf(imgsrc);
    }
}