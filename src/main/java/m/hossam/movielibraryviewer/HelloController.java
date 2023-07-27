package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
            GridPane.setMargin(cardcontainer, new Insets(10));
            GridPane.setColumnIndex(cardcontainer, coloumn);
            GridPane.setRowIndex(cardcontainer, row);
            coloumn++;
            if (coloumn==6){
                coloumn=0;
                ++row;
            }

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
        movie.setImgsrc("/img/endgme.jpeg");
        movie.setRating("4");
        movie.setWatched(true);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,5));
        movie.setGenre("Historic");
        movie.setImgsrc("/img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        ls.add(movie);
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,23));
        movie.setGenre("Historic");
        movie.setImgsrc("/img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        ls.add(movie);
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY,5));
        movie.setGenre("Historic");
        movie.setImgsrc("/img/untit.jpg");
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

        try {
            String userHome = System.getProperty("user.home");
            String propertiesFilePath = userHome + File.separator + "your-properties-file.properties";

            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream(propertiesFilePath);
            properties.load(inputStream);
            inputStream.close();

            // Read properties and create movie objects
            int movieCount = Integer.parseInt(properties.getProperty("movie.count"));
            for (int i = 1; i <= movieCount; i++) {
                Movie movie = new Movie();
                try {
                    BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);

                    // Get last modified time
                    FileTime lastModifiedTime = attrs.lastModifiedTime();
                    System.out.println("Last Modified Time: " + lastModifiedTime);


                } catch (IOException e) {
                    e.printStackTrace();
                }



                movie.setName(properties.getProperty("movie." + i + ".name"));
                movie.setDateAdded(new Date(Long.parseLong(properties.getProperty("movie." + i + ".dateModified"))));
                movie.setGenre(properties.getProperty("movie." + i + ".genre"));
                movie.setImgsrc(properties.getProperty("movie." + i + ".imgsrc"));
                movie.setRating(properties.getProperty("movie." + i + ".rating"));
                movie.setWatched(Boolean.parseBoolean(properties.getProperty("movie." + i + ".watched")));

                am.add(movie);
            }
        } catch (Exception e) {
            System.out.println("Error reading properties from file: " + file.getName());
            e.printStackTrace();
            }



        // Sort the movies based on their name using the custom comparator
        Collections.sort(am, Movie.NAME_COMPARATOR);

        return am;
    }
}