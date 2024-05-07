package Game.HighScore;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Utility class for saving high scores to a file.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class HighScoreSaver {

    /**File path for storing high scores*/
    private static final String FILE_PATH = "data/DataManagement/highScore.txt";


    /**
     * Saves the provided score along with the current date and time to the high scores file.
     * @param score The score to be saved.
     */
    public void saveScore(int score) {
        // Save the current score
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            //Get the current date and time and format it
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String formattedDateTime = now.format(formatter);

            //Write the score and timestamp to the file
            writer.write(score + " - " + formattedDateTime + "\n");
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        //Additional operations can be performed here after saving the score
        System.out.println("Score saved successfully: " + score);
    }
}
