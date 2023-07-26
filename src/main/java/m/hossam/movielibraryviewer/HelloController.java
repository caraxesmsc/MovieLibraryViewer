package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import model.Movie;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    @FXML
    private HBox cardLayoutRecent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    recentlyAdded = new ArrayList<>(recentlyAdded());
    try{
        for(int i=0;i<recentlyAdded.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("card.fxml"));
            HBox cardbox = fxmlLoader.load();
            cardController cardController = fxmlLoader.getController();
            cardController.setData(recentlyAdded.get(i));
            cardLayoutRecent.getChildren().add(cardbox);
        }
    }catch(IOException e){
        e.printStackTrace();
    }

    }
    private List<Movie> recentlyAdded;

    private List<Movie> recentlyAdded() {
        List<Movie> ls = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("Avengers Endgame");
        movie.setDateAdded(new Date(2023,Calendar.JULY,1));
        movie.setGenre("Superhero");
        movie.setImgsrc("img/endgme.jpeg");
        movie.setRating("4");
        movie.setWatched(true);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,5));
        movie.setGenre("Historic");
        movie.setImgsrc("img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        ls.add(movie);

// Sort the movies based on their dateAdded using the custom comparator
        Collections.sort(ls, Movie.DATE_ADDED_COMPARATOR.reversed());

        // Now the movies list is sorted based on their dateAdded
        return ls;
    }
}