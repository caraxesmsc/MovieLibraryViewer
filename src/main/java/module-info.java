module m.hossam.movielibraryviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.jfr;


    opens m.hossam.movielibraryviewer to javafx.fxml;
    exports m.hossam.movielibraryviewer;
}