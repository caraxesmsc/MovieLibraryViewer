package m.hossam.movielibraryviewer;

import java.util.Comparator;
import java.util.Date;

public class Movie  {
    private String name;
    private String genre;
    private String rating;
    private String imgsrc;
    private Date dateAdded;
    private Boolean watched;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

   // Custom comparator to sort movies based on dateAdded
    public static final Comparator<Movie> DATE_ADDED_COMPARATOR = Comparator.comparing(Movie::getDateAdded);
    public static final Comparator<Movie> NAME_COMPARATOR = Comparator.comparing(Movie::getName);
}
