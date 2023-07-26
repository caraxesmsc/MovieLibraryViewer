package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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

    private List<Movie> recentlyAdded(){
        List<Movie> ls = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("Avengers Endgame");
        movie.setDateAdded(new Date(2023,Calendar.JULY,1));
        movie.setGenre("Superhero");
        movie.setImgsrc("C:\\Users\\mdzhs\\OneDrive\\Documents\\Programming Projects\\MovieLibraryViewer\\MovieLibraryViewer\\src\\img\\endgme.jpeg");
        movie.setRating("4");
        movie.setWatched(true);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,5));
        movie.setGenre("Historic");
        movie.setImgsrc("C:\\Users\\mdzhs\\OneDrive\\Documents\\Programming Projects\\MovieLibraryViewer\\MovieLibraryViewer\\src\\img\\oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        ls.add(movie);


        return ls;
    }
}