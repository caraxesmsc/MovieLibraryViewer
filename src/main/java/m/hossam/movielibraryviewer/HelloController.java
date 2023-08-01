package m.hossam.movielibraryviewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static m.hossam.movielibraryviewer.PythonScriptRunner.pythonScriptRunner;

public class HelloController implements Initializable {

    @FXML
    private HBox cardLayoutRecent;
    @FXML
    private GridPane movieContainerGrid;
    @FXML
    private Button Btn12;

    @FXML
    private Button Btn2;

    @FXML
    private Button Btn6;
    @FXML
    private Label recencyBOX;
    public int recencyDigit=2;
    public List<Movie> recentlyAdded;
    public List<Movie> allMovies;
    @FXML
    private VBox rootVBox; // Add this field

    @FXML
    private ScrollPane scrollPane; // Add this field
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootVBox.prefWidthProperty().bind(scrollPane.widthProperty());
        rootVBox.prefHeightProperty().bind(scrollPane.heightProperty());
        recentlyAdded = new ArrayList<>(recentlyAdded());
        allMovies = new ArrayList<>(allMovies());
        int coloumn = 1;
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
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("movieCard.fxml"));
                    AnchorPane cardcontainer = fxmlLoader.load();
                    movieCardController cardContainerController = fxmlLoader.getController();
                    cardContainerController.setData(movie);
                    System.out.println(movie.getName()+" "+movie.getWatched());
                    movieContainerGrid.getChildren().add(cardcontainer);
                    GridPane.setMargin(cardcontainer, new Insets(10));
                    GridPane.setColumnIndex(cardcontainer, coloumn);
                    GridPane.setRowIndex(cardcontainer, row);
                    coloumn++;
                    if (coloumn == 6) {
                        coloumn = 0;
                        ++row;
                    }
                } catch (Exception e) {
                    System.out.println("\n\nMoshkelaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n");
                }
            }



        } catch (IOException e) {
            System.out.println("A7a at Controller/n" + e);
        }

    }

    private List<Movie> recentlyAdded() {
        List<Movie> ls = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
       try {
            for (int i = 0; i < allMovies.size(); i++) {
                if (allMovies.get(i).getDateAdded().isAfter(currentDateTime.minusMonths(getRecency()))) {
                    ls.add(allMovies.get(i));
                }
            }
        }catch (Exception e){
           System.out.println("list is null");
           System.out.println(e);
       }
        //Sort the movies based on their dateAdded using the custom comparator
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
                ArrayList<String> linesX = null;
                try {
                    // Open the file for reading
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    // ArrayList to store the lines from the file
                    linesX = new ArrayList<>();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Store each line in the array
                        linesX.add(line);
                    }
                    // Close the file
                    reader.close();

                } catch (Exception e) {
                    System.out.println("Error reading properties from file: " + file.getName());
                }

                Movie movie = new Movie();
                movie.setName(linesX.get(0));
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ssXXX");
                    movie.setDateAdded(LocalDateTime.parse(linesX.get(1), formatter));
                }catch (Exception a0){
                    System.out.println("Error with Date for: "+linesX.get(0));
                }
                if (linesX.size()>1) {
                    try {
                        movie.setGenre(linesX.get(2));
                    }catch (Exception a1){
                        System.out.println("Error with Genre for: "+linesX.get(0));
                    }
                    try {
                    movie.setImgsrc(linesX.get(4));
                    }catch (Exception a2){
                        System.out.println("Error with IMGSRC for: "+linesX.get(0));
                    }
                    try {
                    movie.setRating(String.valueOf(Double.parseDouble(linesX.get(3))));
                    }catch (Exception a3){
                        System.out.println("Error with Rating for: "+linesX.get(0));
                    }
                    try {
                    movie.setWatched(Boolean.parseBoolean(linesX.get(5)));
                    }catch (Exception a4){
                        System.out.println("Error with Watched for: "+linesX.get(0));
                        movie.setWatched(false);
                    }
                }
                am.add(movie);

            }
        }
                // Sort the movies based on their name using the custom comparator
                // Collections.sort(am, Movie.NAME_COMPARATOR);

                return am;
    }

    private int getRecency() {
        recencyBOX.setText(recencyDigit+" month");
        return recencyDigit;
    }
    @FXML
    public void setRecency2() {
        recencyDigit=2;
        recencyBOX.setText(recencyDigit+" month");
        recentlyAdded();
    }
    @FXML
    public void setRecency6() {
        recencyDigit=6;
        recencyBOX.setText(recencyDigit+" month");
        recentlyAdded();
    }
    @FXML
    public void setRecency12() {
        recencyDigit=12;
        recencyBOX.setText(recencyDigit+" month");
        recentlyAdded();
    }
    @FXML
    public void openOnClick(ActiveEvent event) throws IOException {
        openFile(allMovies.get(allMovies.indexOf(this)).getImgsrc());
    }

    private void openFile(String imgsrc) {
        try {
            File file = new File(imgsrc);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.out.println("problem opening file: "+ imgsrc);
        }
    }
}

