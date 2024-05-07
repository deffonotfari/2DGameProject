package Game.HighScore;

//imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for reading high scores from a file.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class HighScoreReader {
    /**File path for storing high scores.*/
    private static final String HIGH_SCORES_FILE = "data/DataManagement/highScore.txt"; // File path for storing high scores

    /**
     * Reads the high scores from the file and returns a list of high scores.
     * @return  a sorted list of high scores.
     */
    public static List<String> getHighScores() {
        //List that is being used to store high scores read from the file
        List<String> highScores = new ArrayList<>();

        //Read high scores from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
            String line;

            //Read each line from the file and add it to the list of high scores
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading high scores: " + e.getMessage());
        }

        // Parse the score values as integers
        List<Integer> scores = new ArrayList<>();
        for (String score : highScores) {
            int separatorIndex = score.indexOf('-');
            String scoreValue = score.substring(0, separatorIndex).trim();
            scores.add(Integer.parseInt(scoreValue));
        }

        // Sort the parsed score values in descending order
        scores.sort(Collections.reverseOrder());

        // Convert the sorted score values back to strings and construct the sorted list of high scores
        List<String> sortedHighScores = new ArrayList<>();
        for (int score : scores) {
            for (String entry : highScores) {
                if (entry.startsWith(String.valueOf(score))) {
                    sortedHighScores.add(entry);
                    break;
                }
            }
        }

        //Return the sorted list of high scores
        return sortedHighScores;
    }
}