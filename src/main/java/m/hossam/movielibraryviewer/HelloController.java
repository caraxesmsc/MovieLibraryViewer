package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    @FXML
    private HBox cardLayoutRecent;
    @FXML
    private GridPane movieContainer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    recentlyAdded = new ArrayList<>(recentlyAdded());
    allMovies = new ArrayList<>(allMovies());
    int coloumn = 0;
    int row =1;
    try{
        for (Movie value : recentlyAdded) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("card.fxml"));
            HBox cardbox = fxmlLoader.load();
            cardController cardController = fxmlLoader.getController();
            cardController.setData(value);
            cardLayoutRecent.getChildren().add(cardbox);
        }
        for (Movie movie: allMovies){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("movieCard.fxml"));
            AnchorPane cardcontainer = fxmlLoader.load();
            movieCardController cardContainerController = fxmlLoader.getController();
            cardContainerController.setData(movie);
            movieContainer.getChildren().add(cardcontainer);

            if (coloumn==6){
                coloumn=0;
                ++row;
            }
            movieContainer.add(cardcontainer,coloumn++,row);
            GridPane.setMargin(cardcontainer,new Insets(10));
        }

    }catch(IOException e){
        System.out.println("A7a at Controller/n"+e);
    }

    }
    private List<Movie> recentlyAdded;
    private List<Movie> allMovies;
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
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,23));
        movie.setGenre("Historic");
        movie.setImgsrc("img/oppenhiemer.jpg");
        movie.setRating("4.5");
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
    private List<Movie> allMovies() {
        List<Movie> am = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("Avengers Endgame");
        movie.setDateAdded(new Date(2023,Calendar.JULY,1));
        movie.setGenre("Superhero");
        movie.setImgsrc("img/endgme.jpeg");
        movie.setRating("4");
        movie.setWatched(true);
        am.add(movie);

        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,5));
        movie.setGenre("Historic");
        movie.setImgsrc("img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        am.add(movie);
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,23));
        movie.setGenre("Historic");
        movie.setImgsrc("img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        am.add(movie);
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,5));
        movie.setGenre("Historic");
        movie.setImgsrc("img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        am.add(movie);
        Collections.sort(am, Movie.NAME_COMPARATOR);

        // Now the movies list is sorted based on their dateAdded
        return am;
    }
}