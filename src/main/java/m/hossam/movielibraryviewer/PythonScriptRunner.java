package m.hossam.movielibraryviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonScriptRunner {

    public static void pythonScriptRunner() {
        // Change the path to your Python script accordingly
        String pythonScriptPath = "src/main/java/m/hossam/movielibraryviewer/script.py";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            Process process = processBuilder.start();

            // Read the output of the Python script (optional)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Python script executed. Exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
