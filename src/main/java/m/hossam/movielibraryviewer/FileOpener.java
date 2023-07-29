package m.hossam.movielibraryviewer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileOpener {
    public void openFile(String pathToFile) {

        try {
            File file = new File(pathToFile);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.out.println("problem opening file: "+ pathToFile);
        }
    }
}
