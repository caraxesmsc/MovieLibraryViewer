package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.*;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static m.hossam.movielibraryviewer.PythonScriptRunner.pythonScriptRunner;

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
        int row = 1;
        try {
            for (Movie value : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardbox = fxmlLoader.load();
                cardController cardController = fxmlLoader.getController();
                cardController.setData(value);
                cardLayoutRecent.getChildren().add(cardbox);
            }
            for (Movie movie : allMovies) {
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
                if (coloumn == 6) {
                    coloumn = 0;
                    ++row;
                }

            }

        } catch (IOException e) {
            System.out.println("A7a at Controller/n" + e);
        }

    }

    private List<Movie> recentlyAdded;
    private List<Movie> allMovies;

    private List<Movie> recentlyAdded() {
        List<Movie> ls = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("Avengers Endgame");
        movie.setDateAdded(new Date(2023, Calendar.JULY, 1));
        movie.setGenre("Superhero");
        movie.setImgsrc("/img/endgme.jpeg");
        movie.setRating("4");
        movie.setWatched(true);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY, 5));
        movie.setGenre("Historic");
        movie.setImgsrc("/img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        ls.add(movie);
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY, 23));
        movie.setGenre("Historic");
        movie.setImgsrc("/img/oppenhiemer.jpg");
        movie.setRating("4.5");
        movie.setWatched(true);
        ls.add(movie);
        movie = new Movie();
        movie.setName("Oppenheimer");
        movie.setDateAdded(new Date(2023, Calendar.JULY, 5));
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

        // Replace "path/to/your/folder" with the actual path of the folder containing text files
        String folderPath = "C:\\My_Data\\Movies\\Movie_Metadata";
        pythonScriptRunner();

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Invalid folder path.");
            return am;
        }

        // Get a list of files in the folder
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.err.println("The folder is empty.");
            return am;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    // Open the file for reading
                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Assuming each line contains data for one movie and is formatted accordingly
                        // Example: name;dateAdded;genre;imgsrc;rating;watched
                        String[] movieData = line.split(";");

                        Movie movie = new Movie();
                        movie.setName(movieData[0]);
                        movie.setDateAdded(new Date(Long.parseLong(movieData[1])));
                        movie.setGenre(movieData[2]);
                        movie.setImgsrc(movieData[3]);
                        movie.setRating(String.valueOf(Double.parseDouble(movieData[4])));
                        movie.setWatched(Boolean.parseBoolean(movieData[5]));
                        am.add(movie);
                    }
                } catch (Exception e) {
                    System.out.println("Error reading properties from file: " + file.getName());
                    e.printStackTrace();
                }

            }
        }
                // Sort the movies based on their name using the custom comparator
                Collections.sort(am, Movie.NAME_COMPARATOR);

                return am;
    }
}

