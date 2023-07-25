module m.hossam.movielibraryviewer {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens m.hossam.movielibraryviewer to javafx.fxml;
    exports m.hossam.movielibraryviewer;
}