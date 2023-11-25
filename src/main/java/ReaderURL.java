import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReaderURL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the URL from the user using a scanner
        System.out.print("Please enter the URL: ");
        String urlString = scanner.nextLine();

        try {
            // Create a URL object
            URL url = new URL(urlString);

            // Open a stream from the URL
            try (InputStream inputStream = url.openStream();
                 InputStreamReader reader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                // Get the path for the output file from the user using a scanner
                System.out.print("Please enter the path for the output file: ");
                String outputPath = scanner.nextLine();

                // Write to the specified file
                writeToFile(bufferedReader, outputPath);
                System.out.println("The file has been created successfully.");

            } catch (IOException e) {
                System.out.println("Error reading from the URL: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error creating URL: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void writeToFile(BufferedReader bufferedReader, String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        try (FileWriter writer = new FileWriter(path.toFile())) {
            String line;
            int lineNumber = 1;

            // Read each line from the URL and write to the file
            while ((line = bufferedReader.readLine()) != null) {
                // Count the number of words in the line
                int wordCount = line.split("\\s+").length;

                // Write the line number and the line content to the file
                writer.write(wordCount + " " + line + "\n");

                // Increment the line number
                lineNumber++;
            }
        }
    }
}
